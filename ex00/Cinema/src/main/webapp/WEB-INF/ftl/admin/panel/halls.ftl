<html>
<head>
    <title>Halls manage</title>
</head>
<body>
    <fieldset>
        <legend>Add movie hall</legend>
        <form name="car" action="/admin/panel/halls" method="post">
            Serial number : <input type="number" name="serialNumber" min="1" required/><br/>
            Number of seats : <input type="number" name="numberOfSeats" min="1" required/><br/>
            <input type="submit" value="Add hall" />
        </form>
    </fieldset>

    <br/>

    <#if model["HallsList"]?has_content>
        <table class="datatable">
            <tr>
                <th>Serial Number</th>
                <th>Number of seats</th>
            </tr>
            <#list model["HallsList"] as hall>
                <tr>
                    <td>${hall.serialNumber?string.computer}</td>
                    <td>${hall.numberOfSeats?string.computer}</td>
                </tr>
            </#list>
        </table>
    </#if>
</body>
</html>
