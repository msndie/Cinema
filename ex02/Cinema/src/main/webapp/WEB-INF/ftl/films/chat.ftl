<!DOCTYPE html>
<html>
<head>
    <title>Chat room</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

    <style>
        * {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }
        html,body {
            height: 100%;
            overflow: hidden;
        }
        body {
            margin: 0;
            padding: 0;
            font-weight: 400;
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            font-size: 1rem;
            line-height: 1.58;
            color: #333;
            background-color: #f4f4f4;
            height: 100%;
        }
        body:before {
            height: 50%;
            width: 100%;
            position: absolute;
            top: 0;
            left: 0;
            background: #128ff2;
            content: "";
            z-index: 0;
        }
        .clearfix:after {
            display: block;
            content: "";
            clear: both;
        }
        .hidden {
            display: none;
        }
        .form-control {
            width: 100%;
            min-height: 38px;
            font-size: 15px;
            border: 1px solid #c8c8c8;
        }
        .form-group {
            margin-bottom: 15px;
        }
        input {
            padding-left: 10px;
            outline: none;
        }
        h1, h2, h3, h4, h5, h6 {
            margin-top: 20px;
            margin-bottom: 20px;
        }
        h1 {
            font-size: 1.7em;
        }
        a {
            color: #128ff2;
        }
        button {
            box-shadow: none;
            border: 1px solid transparent;
            font-size: 14px;
            outline: none;
            line-height: 100%;
            white-space: nowrap;
            vertical-align: middle;
            padding: 0.6rem 1rem;
            border-radius: 2px;
            transition: all 0.2s ease-in-out;
            cursor: pointer;
            min-height: 38px;
        }
        button.default {
            background-color: #e8e8e8;
            color: #333;
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.12);
        }
        button.primary {
            background-color: #128ff2;
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.12);
            color: #fff;
        }
        button.accent {
            background-color: #ff4743;
            box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.12);
            color: #fff;
        }
        .connect-page-container .connect-submit {
            margin-top: 10px;
        }
        #chat-page {
            position: relative;
            height: 100%;
        }
        .chat-container {
            max-width: 700px;
            margin-left: auto;
            margin-right: auto;
            background-color: #fff;
            box-shadow: 0 1px 11px rgba(0, 0, 0, 0.27);
            margin-top: 30px;
            height: calc(100% - 60px);
            max-height: 600px;
            position: relative;
        }
        #chat-page ul {
            list-style-type: none;
            background-color: #FFF;
            margin: 0;
            overflow: auto;
            overflow-y: scroll;
            padding: 0 20px 0px 20px;
            height: calc(100% - 150px);
        }
        #chat-page #messageForm {
            padding: 20px;
        }
        #chat-page ul li {
            line-height: 1.5rem;
            padding: 10px 20px;
            margin: 0;
            border-bottom: 1px solid #f4f4f4;
        }
        #chat-page ul li p {
            margin: 0;
        }
        #chat-page .chat-message {
            padding-left: 68px;
            position: relative;
        }
        #chat-page .chat-message i {
            position: absolute;
            width: 42px;
            height: 42px;
            overflow: hidden;
            left: 10px;
            display: inline-block;
            vertical-align: middle;
            font-size: 18px;
            line-height: 42px;
            color: #fff;
            text-align: center;
            border-radius: 50%;
            font-style: normal;
            text-transform: uppercase;
        }
        #chat-page .chat-message span {
            color: #333;
            font-weight: 600;
        }
        #chat-page .chat-message p {
            color: #43464b;
        }
        #messageForm .input-group input {
            float: left;
            width: calc(100% - 85px);
        }
        #messageForm .input-group button {
            float: left;
            width: 80px;
            height: 38px;
            margin-left: 5px;
        }
        .connecting {
            padding-top: 5px;
            text-align: center;
            color: #777;
            position: absolute;
            top: 65px;
            width: 100%;
        }
        @media screen and (max-width: 730px) {
            .chat-container {
                margin-left: 10px;
                margin-right: 10px;
                margin-top: 10px;
            }
        }
        @media screen and (max-width: 480px) {
            .chat-container {
                height: calc(100% - 30px);
            }
            .connect-page-container {
                width: auto;
                margin-left: 15px;
                margin-right: 15px;
                padding: 25px;
            }
            #chat-page ul {
                height: calc(100% - 120px);
            }
            #messageForm .input-group button {
                width: 65px;
            }
            #messageForm .input-group input {
                width: calc(100% - 70px);
            }
            .connecting {
                top: 60px;
            }
            .chat-header h2 {
                font-size: 1.1em;
            }
        }
    </style>

</head>
<body>
    <h1>${model["Film"].title}</h1>

    <div id="connect-page">
        <div class="connect-page-container">
            <h1 class="title">Push the button to connect</h1>
            <form id="connectForm" name="connectForm">
                <div class="form-group">
                    <button type="submit" class="accent connect-submit">Start Chatting</button>
                </div>
            </form>
        </div>
    </div>
    <div id="chat-page" class="hidden">
        <div class="chat-container">
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
                        <li class="chat-message">
<#--                            <i>-->
                            <span>
                                ${author}
                                <p>
                                    ${msg.text}
                                </p>
                            </span>
<#--                            </i>-->
                        </li>
                    </#list>
                </#if>
            </ul>
            <form id="messageForm" name="messageForm">
                <div class="form-group">
                    <div class="input-group clearfix">
                        <input type="text" id="message" placeholder="Type a message..." autocomplete="off" class="form-control"/>
                        <button type="submit" class="primary">Send</button>
                    </div>
                </div>
            </form>
        </div>
    </div>


    <script>
        'use strict';

        var connectPage = document.querySelector('#connect-page');
        var chatPage = document.querySelector('#chat-page');
        var connectForm = document.querySelector('#connectForm');
        var messageForm = document.querySelector('#messageForm');
        var messageInput = document.querySelector('#message');
        var messageArea = document.querySelector('#messageArea');
        var connectingElement = document.querySelector('.connecting');

        var stompClient = null;

        function connect(event) {
            usernamePage.classList.add('hidden');
            chatPage.classList.remove('hidden');
            var socket = new SockJS('/ws');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, onConnected, onError);
            event.preventDefault();
        }

        function onConnected() {
            stompClient.subscribe('/films/${model["Id"]}/chat/messages', onMessageReceived);
            connectingElement.classList.add('hidden');
        }

        function onError(error) {
            connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
        }

        function sendMessage(event) {
            var messageContent = messageInput.value.trim();
            if(messageContent && stompClient) {
                var chatMessage = {
                    username: '${model["Name"]}',
                    message: messageInput.value,
                };
                stompClient.send("/films/${model["Id"]}/chat", {}, JSON.stringify(chatMessage));
                messageInput.value = '';
            }
            event.preventDefault();
        }

        function onMessageReceived(payload) {
            var message = JSON.parse(payload.body);
            var messageElement = document.createElement('li');
            messageElement.classList.add('chat-message');
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

        connectForm.addEventListener('submit', connect, true)
        messageForm.addEventListener('submit', sendMessage, true)
    </script>
</body>
</html>