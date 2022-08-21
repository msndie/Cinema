<html>
<head>
    <title>Chat room</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
</head>
<body>
    <h1>${model["Film"].title}</h1>

    <div class="connecting">
        Connecting...
    </div>

    <ul id="messageArea">
        <#if model["Messages"]?has_content>
            <#list model["Messages"] as msg>
                <#if msg.userName == model["name"]>
                    <#assign author="you"/>
                <#else>
                    <#assign author="${msg.userName}"/>
                </#if>
                <hr>
                ${author}
                ${msg.text}
                <hr>
            </#list>
        </#if>
    </ul>

    <form id="messageForm" name="messageForm">
        <input type="text" id="message" placeholder="Type a message...">
        <button type="submit">Send</button>
    </form>
    <script>

        var messageForm = document.querySelector('#messageForm');
        var messageInput = document.querySelector('#message');
        var messageArea = document.querySelector('#messageArea');
        var connectingElement = document.querySelector('.connecting');

        var stompClient = null;

        function connect() {
<#--            username = document.querySelector('#name').value.trim();-->
<#--            if(username) {-->
<#--                usernamePage.classList.add('hidden');-->
<#--                chatPage.classList.remove('hidden');-->
            var socket = new SockJS('/films/${model["Id"]}/chat');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, onConnected, onError);
<#--            event.preventDefault();-->
        }

        function onConnected() {
            // Subscribe to the Public Topic
            stompClient.subscribe('/films/${model["Id"]}/chat/messages', onMessageReceived);
<#--            // Tell your username to the server-->
<#--            stompClient.send("/app/chat.addUser",-->
<#--                {},-->
<#--                JSON.stringify({sender: username, type: 'JOIN'})-->
<#--            )-->
            connectingElement.classList.add('hidden');
        }

        function onError(error) {
            connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
            connectingElement.style.color = 'red';
        }

        function sendMessage(event) {
            console.log('test 1');
            if(!stompClient) {
                console.log('test 2');
                connect();
                console.log('test 3');
            }
            var messageContent = messageInput.value.trim();
            if(messageContent && stompClient) {
                var chatMessage = {
                    userName: '${model["Name"]}',
                    content: messageInput.value,
                };
                stompClient.send("/${model["Id"]}/chat", {}, JSON.stringify(chatMessage));
                messageInput.value = '';
            }
            event.preventDefault();
        }

        function onMessageReceived(payload) {
            var message = JSON.parse(payload.body);
            var messageElement = document.createElement('li');
            messageElement.classList.add('chat-message');
            var avatarElement = document.createElement('i');
            var avatarText = document.createTextNode(message.sender[0]);
            avatarElement.appendChild(avatarText);
            messageElement.appendChild(avatarElement);
            var usernameElement = document.createElement('span');
            var usernameText = document.createTextNode(message.userName);
            usernameElement.appendChild(usernameText);
            messageElement.appendChild(usernameElement);
            var textElement = document.createElement('p');
            var messageText = document.createTextNode(message.message);
            textElement.appendChild(messageText);
            messageElement.appendChild(textElement);
            messageArea.appendChild(messageElement);
            messageArea.scrollTop = messageArea.scrollHeight;
        }

        messageForm.addEventListener('submit', sendMessage, true)
    </script>
</body>
</html>