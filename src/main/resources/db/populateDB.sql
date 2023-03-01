DELETE FROM user_role;
DELETE FROM meal;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meal    (user_id,       date_time,                  description,    calories)
VALUES              (100000,        '2023-01-30 06:00:00',      'Завтрак',      450),
                    (100000,        '2023-01-30 14:00:00',      'Обед',         450),
                    (100000,        '2023-01-30 21:00:00',      'Ужин',         450),
                    (100000,        '2023-01-31 06:00:00',      'Завтрак',      450),
                    (100000,        '2023-01-31 15:00:00',      'Обед',         450),
                    (100000,        '2023-01-31 21:20:00',      'Ужин',         450),
                    (100000,        '2023-01-30 23:00:00',      'Поздний ужин', 450),

                    (100001,        '2023-01-30 14:00:00',      'Админ ланч',   510),
                    (100001,        '2023-01-30 21:00:00',      'Админ ужин',   1500);


