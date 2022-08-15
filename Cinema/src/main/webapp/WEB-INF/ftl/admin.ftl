<div id="header">
    <h2>FreeMarker Spring MVC Hello World</h2>
</div>
<div id="content">
    <fieldset>
        <legend>Add movie hall</legend>
        <form name="car" action="/admin/panel/halls" method="post">
            Serial number : <input type="number" name="serialNumber" /><br/>
            Number of seats : <input type="number" name="numberOfSeats" /><br/>
            <input type="submit" value="Add hall" />
        </form>
    </fieldset>
    <br/>
    <table class="datatable">
        <tr>
            <th>Serial Number</th>
            <th>Number of seats</th>
        </tr>
        <#if model["HallsList"]?has_content>
            <#list model["HallsList"] as hall>
                <tr>
                    <td>${hall.serialNumber}</td>
                    <td>${hall.numberOfSeats}</td>
                </tr>
            </#list>
        </#if>
    </table>
</div>
