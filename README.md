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
