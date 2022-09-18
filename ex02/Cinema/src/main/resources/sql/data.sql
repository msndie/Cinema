INSERT INTO ex02.films(id, title, year, age_restrictions, description, poster_id)
VALUES
    (1, 'Bad film', 2017, 0, 'Such a shame', null),
    (2, 'Good film', 2017, 18, 'Good, just good', null),
    (3, 'Best film ever :D', 2014, 16, 'Best of the best', null)
ON CONFLICT DO NOTHING;

INSERT INTO ex02.halls(id, serial_number, number_of_seats)
VALUES
    (1, 1, 30),
    (2, 2, 50),
    (3, 3, 10)
ON CONFLICT DO NOTHING;

INSERT INTO ex02.sessions(id, film_id, hall_id, price, date_time)
VALUES
    (1, 1, 3, 10, '2022-09-20 15:00:00'),
    (2, 2, 1, 220, '2022-09-20 17:00:00'),
    (3, 3, 2, 250, '2022-09-20 19:00:00')
ON CONFLICT DO NOTHING;

INSERT INTO ex02.messages(id, user_name, message, film_id)
VALUES
       (1, 'Bran_an_Tuirseach', 'Bad movie', 1),
       (2, 'Brouver_Hoog', 'Agree', 1),
       (3, 'Brouver_Hoog', 'Good movie', 2),
       (4, 'Bran_an_Tuirseach', 'Agree', 2),
       (5, 'Priscilla', 'Great movie', 3),
       (6, 'Imlerith', 'For sure', 3)
ON CONFLICT DO NOTHING;