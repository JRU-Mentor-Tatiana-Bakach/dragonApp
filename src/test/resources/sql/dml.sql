DELETE FROM payment;
DELETE FROM dragon;
DELETE FROM flight;
DELETE FROM user_info;
DELETE FROM users;

ALTER SEQUENCE dragon_id_seq RESTART with 1;
ALTER SEQUENCE flight_id_seq RESTART with 1;
ALTER SEQUENCE payment_id_seq RESTART with 1;
ALTER SEQUENCE user_info_id_seq RESTART with 1;
ALTER SEQUENCE users_id_seq RESTART with 1;

INSERT INTO user_info (date_of_birth, email, name, phone_number, surname)
VALUES ('2000-02-15', 'peter@gmail.com', 'Peter', '+123456789101', 'Dow'),
       ('1989-04-08', 'hanna@gmail.com', 'Hanna', '+123456789102', 'Kerys'),
       ('2003-11-11', 'misha@gmail.com', 'Misha', '+123456789103', 'Wood'),
       ('1999-10-30', 'felicia@gmail.com', 'Felicia', '+123456789104', 'Air'),
       ('2005-07-14', 'richard@gmail.com', 'Richard', '+123456789105', 'Green'),
       ('1995-12-18', 'olga@gmail.com', 'Olga', '+123456789106', 'Trust'),
       ('2007-03-28', 'david@gmail.com', 'David', '+123456789107', 'Drake');

INSERT INTO users (user_name, password, role, user_info_id)
VALUES ('peter', '123456', 'ADMIN', 1),
       ('hanna', 'qwerty', 'USER', 2),
       ('misha', 'asdfgh', 'USER', 3),
       ('felicia', 'qwe123', 'USER', 4),
       ('richard', '67890', 'USER', 5),
       ('olga', 'olgaolga12', 'USER', 6),
       ('david', 'zxcvbnm', 'USER', 7);

INSERT INTO dragon (name, age, color, element)
VALUES ('Syrax', 120, 'RED', 'FIRE'),
       ('Caraxes', 75, 'GREEN', 'EARTH'),
       ('Mushu', 12, 'YELLOW', 'FIRE'),
       ('Norbert', 100, 'BLACK', 'EARTH'),
       ('Ghidorah', 39, 'BLUE', 'WATER'),
       ('Falkor', 200, 'GREEN', 'AIR'),
       ('Vhagar', 50, 'WHITE', 'AIR'),
       ('Vermithor', 84, 'RED', 'FIRE'),
       ('Meraxes', 320, 'GREEN', 'WATER'),
       ('Drogon', 27, 'YELLOW', 'AIR'),
       ('Rhaegal', 55, 'GREEN', 'WATER'),
       ('Viserion ', 105, 'YELLOW', 'EARTH');

INSERT INTO flight (date_of_flight, user_id, dragon_id, price, speed)
VALUES ('2023-04-25', 1, 10, 200.0, 105),
       ('2023-06-01', 4, 7, 185.0, 90),
       ('2023-06-12', 3, 3, 150.0, 118),
       ('2023-07-03', 2, 8, 205.0, 126),
       ('2023-07-05', 7, 5, 180.0, 56),
       ('2023-07-14', 5, 1, 250.0, 200),
       ('2023-07-17', 6, 4, 167.5, 134),
       ('2023-07-25', 1, 2, 183.0, 95),
       ('2023-08-01', 3, 9, 150.0, 75),
       ('2023-08-16', 2, 6, 220.0, 178);


INSERT INTO payment (date_of_payment, bank_data)
VALUES ('2023-04-25', 'HSBC Bank'),
       ('2023-05-30', 'Royal Bank of Scotland'),
       ('2023-07-01', 'Barclays Bank'),
       ('2023-07-02', 'Barclays Bank'),
       ('2023-07-05', 'Lloyds Banking Group'),
       ('2023-07-20', 'HSBC Bank'),
       ('2023-08-01', 'Barclays Bank'),
       ('2023-08-14', 'HSBC Bank');

INSERT INTO payment_flight (flight_id, payment_id)
VALUES (1, 1),
       (2, 2),
       (3, 2),
       (4, 3),
       (4, 4),
       (5, 5),
       (6, 5),
       (7, 5),
       (8, 6),
       (9, 7),
       (10, 8);