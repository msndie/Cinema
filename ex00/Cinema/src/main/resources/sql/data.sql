INSERT INTO ex00.films(id, title, year, age_restrictions, description, poster_id)
VALUES
       (1, 'Bad film', 2017, 0, 'Such a shame', null),
       (2, 'Good film', 2017, 18, 'Good, just good', null),
       (3, 'Best film ever :D', 2014, 16, 'Best of the best', null)
ON CONFLICT DO NOTHING;

INSERT INTO ex00.halls(id, serial_number, number_of_seats)
VALUES
       (1, 1, 30),
       (2, 2, 50),
       (3, 3, 10)
ON CONFLICT DO NOTHING;

INSERT INTO ex00.sessions(id, film_id, hall_id, price, date_time)
VALUES
       (1, 1, 3, 10, '2022-09-20 15:00:00'),
       (2, 2, 1, 220, '2022-09-20 17:00:00'),
       (3, 3, 2, 250, '2022-09-20 19:00:00')
ON CONFLICT DO NOTHING;