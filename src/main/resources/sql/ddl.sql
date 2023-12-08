CREATE TABLE IF NOT EXISTS dragon
(
    age     INTEGER,
    id      SERIAL       NOT NULL,
    color   VARCHAR(255) CHECK (color in ('RED', 'YELLOW', 'GREEN', 'BLUE', 'BLACK', 'WHITE')),
    element VARCHAR(255) CHECK (element in ('AIR', 'FIRE', 'WATER', 'EARTH')),
    name    VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS flight
(
    date_of_flight DATE           NOT NULL,
    dragon_id      INTEGER,
    id             SERIAL         NOT NULL,
    price          NUMERIC(38, 2) NOT NULL,
    speed          INTEGER,
    user_id        INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (dragon_id) REFERENCES dragon,
    FOREIGN KEY (user_id) REFERENCES users
);

CREATE TABLE IF NOT EXISTS payment
(
    date_of_payment DATE   NOT NULL,
    id              SERIAL NOT NULL,
    bank_data       VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_info
(
    date_of_birth DATE,
    id            SERIAL       NOT NULL,
    user_id       INTEGER UNIQUE,
    email         VARCHAR(255) NOT NULL UNIQUE,
    name          VARCHAR(255) NOT NULL,
    phone_number  VARCHAR(255) NOT NULL UNIQUE,
    role          VARCHAR(255) CHECK (role in ('USER', 'ADMIN')),
    surname       VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS users
(
    id        SERIAL       NOT NULL,
    password  VARCHAR(255),
    user_name varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS payment_flight
(
    flight_id  INTEGER NOT NULL,
    payment_id INTEGER NOT NULL,
    PRIMARY KEY (flight_id, payment_id),
    FOREIGN KEY (flight_id) REFERENCES flight,
    FOREIGN KEY (payment_id) REFERENCES payment
);