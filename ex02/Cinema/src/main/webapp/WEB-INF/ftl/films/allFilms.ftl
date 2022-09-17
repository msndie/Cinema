<!DOCTYPE html>
<html lang="en">

<head>
    <title>Films</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <style>
        html,
        body {
            height: 100%;
        }

        body {
            display: flex;
            align-items: flex-start;
            justify-content: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }
    </style>
</head>

<body>
    <div class="container col">
        <div class="container table-responsive">
            <#if model["Films"]?has_content>
                <table class="filmTable table table-hover">
                    <thead>
                        <th>Title</th>
                        <th>Year of release</th>
                        <th>Age restrictions</th>
                        <th>Description</th>
                        <th>Poster</th>
                    </thead>
                    <#list model["Films"] as film>
                        <tr>
                            <td><a href="/films/${film.id}/chat" class="link-primary">${film.title}</a></td>
                            <td>${film.year?string.computer}</td>
                            <td>${film.ageRestrictions}</td>
                            <td>${film.description}</td>
                            <td>
                                <#if film.poster??>
                                    <a href="/images/${film.poster.uuid}" target="_blank"
                                        class="link-primary">${film.poster.name}</a>
                                    <#else>
                                        No poster
                                </#if>
                            </td>
                        </tr>
                    </#list>
                </table>
            </#if>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
</body>

</html>