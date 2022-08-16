<div id="header">
    <h2>FreeMarker Spring MVC Hello World</h2>
</div>
<div id="content">
    <fieldset>
        <legend>Add movie hall</legend>
        <form name="film" action="/admin/panel/films" method="post">
            Title : <input type="text" name="title" required/><br/>
            Year of release (1895 - 2022) : <input type="number" name="year" required/><br/>
            Age restrictions : <input type="number" name="age" required/><br/>
            Description : <input type="text" name="description" required/><br/>
            <input type="submit" value="Add film" />
        </form>
    </fieldset>
    <br/>
    <table class="datatable">
        <tr>
            <th>Title</th>
            <th>Year of release</th>
            <th>Age restrictions</th>
            <th>Description</th>
            <th>Poster</th>
        </tr>
        <#if model["FilmsList"]?has_content>
            <#list model["FilmsList"] as film>
                <tr>
                    <td>${film.title}</td>
                    <td>${film.year}</td>
                    <td>${film.ageRestrictions}</td>
                    <td>${film.description}</td>
                    <#if film.poster??>
                    <#else>
                        <td>
                            <form action="/images" method="post" enctype="multipart/form-data">
                                <input type="file" name="file" accept="image/*"/>
                                <input type="submit" value="Upload poster">
                            </form>
                        </td>
                    </#if>
                </tr>
            </#list>
        </#if>
    </table>
</div>
