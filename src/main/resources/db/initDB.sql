DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM menu;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 10000;

INSERT INTO users (login, password, email, restaurant_id)
VALUES ('User1', 'Password1', '1@tut.by', 1),
       ('User2', 'Password2', '2@tut.by', 2),
       ('Admin', 'Password', 'admin@tut.by', null);

INSERT INTO user_roles (user_id, role)
VALUES (10000, 'USER'),
       (10001, 'USER'),
       (10002, 'USER');

INSERT INTO restaurants (name, menu_id, vote_counter)
VALUES ('Brovar', 1, 8),
       ('Lidbeer', 2, 15);

INSERT INTO menu (menu_id,restaurant_id, dish, price)
VALUES (1, 'First', 12.5),
       (1, 'Second', 14.8),
       (2, 'First', 4.5),
       (2, 'Second', 8.8);