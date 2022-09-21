# Cinema

<details>
<summary>Screenshots</summary>
<details>
<summary>/admin/panel/films</summary>
<img src="/screenshots/admin_panel_films.png">
</details>
<details>
<summary>/admin/panel/halls</summary>
<img src="/screenshots/admin_panel_halls.png">
</details>
<details>
<summary>/admin/panel/sessions</summary>
<img src="/screenshots/admin_panel_sessions.png">
</details>
<details>
<summary>/sessions</summary>
<img src="/screenshots/sessions.png">
</details>
<details>
<summary>/films</summary>
<img src="/screenshots/films.png">
</details>
<details>
<summary>/films/{id}/chat</summary>
<img src="/screenshots/films_{id}_chat.png">
</details>
</details>

This is a site for movie theater, it will be developed more in next project called <a href="https://github.com/msndie/CinemaSpringBoot">CinemaSpringBoot</a>.

In this project we use:
- Spring Controllers
- Hibernate without Spring Data Jpa
- Ajax-requests for live search on /sessions page
- Spring Websockets for chats (every movie has its own chat, since this project has no signIn/signUp functionality each user has its own random name, which is saved in cookies)
- FreeMarker as template engine.

# Launch

<H3>Docker</H3>

Just go to docker folder and run it with the following command

```
docker compose up --build
```

<H3>Manual</H3>
You need to create an empty database in postgres and write your credentials in application.properties file in corresponding folder and change path to folder where images will be stored.

Then just go to the folder ( i recommend ex02 :D ) and run the following command
```
mvn clean package org.codehaus.cargo:cargo-maven3-plugin:run
```

<h3>Voila</h3>
Website can be accessed through <a href="http://localhost:8080">localhost:8080</a>, but there is no no index

# Mappings
- /admin/panel/films   (create film)
- /admin/panel/halls   (create hall)
- /admin/panel/sessions   (create session with available hall and film)
- /sessions   (search available sessions with live search by name of the film)
- /sessions/{id}   (info about particular session)
- /films   (list of all films)
- /films/{id}   (info about particular film)
- /films/{id}/chat   (multiuser chat about particular film)
