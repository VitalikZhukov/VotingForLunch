DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM menu;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 10000;

INSERT INTO users (login, password, email, restaurant_id)
VALUES ('User', 'Password1', '1@tut.by', null),
       ('Admin', 'Password', 'admin@tut.by', null);

INSERT INTO user_roles (user_id, role)
VALUES (10000, 'USER'),
       (10001, 'USER'),
       (10001, 'ADMIN');

INSERT INTO restaurants (name, vote_counter)
VALUES ('Brovar', 8),
       ('Lidbeer', 15);

INSERT INTO menu (restaurant_id, dish, price)
VALUES (10002, 'First', 12.5),
       (10002, 'Second', 14.8),
       (10002, 'Third', 2.98),
       (10003, 'First', 4.5),
       (10003, 'Second', 8.8),
       (10003, 'Third', 2.85);