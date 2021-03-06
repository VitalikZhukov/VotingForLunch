DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS restaurants;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 10000;

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    login            VARCHAR                           NOT NULL,
    password         VARCHAR                           NOT NULL,
    email            VARCHAR                           NOT NULL,
    registered       TIMESTAMP(0)        DEFAULT now() NOT NULL,
    enabled          BOOL                DEFAULT TRUE  NOT NULL,
    restaurant_id    INTEGER             DEFAULT NULL
);
CREATE UNIQUE INDEX email_index ON users (email);

CREATE TABLE user_roles
(
    user_id         INTEGER                             NOT NULL,
    role            VARCHAR,
    CONSTRAINT user_roles_index UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name            VARCHAR                             NOT NULL,
    vote_counter    INTEGER                             DEFAULT 0
);

CREATE TABLE menu
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    restaurant_id   INTEGER,
    dish            VARCHAR                             NOT NULL,
    price           NUMERIC(7, 2)                       NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);