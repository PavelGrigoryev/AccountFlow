--liquibase formatted sql

--changeset Grigoryev_Pavel:000
CREATE TABLE IF NOT EXISTS "user"
(
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(500),
    date_of_birth DATE,
    password      VARCHAR(500) NOT NULL
);

CREATE TABLE IF NOT EXISTS account
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT         NOT NULL UNIQUE REFERENCES "user" (id) ON DELETE CASCADE,
    balance DECIMAL(19, 2) NOT NULL CHECK (balance >= 0)
);

CREATE TABLE IF NOT EXISTS email_data
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT       NOT NULL REFERENCES "user" (id) ON DELETE CASCADE,
    email   VARCHAR(200) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS phone_data
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT      NOT NULL REFERENCES "user" (id) ON DELETE CASCADE,
    phone   VARCHAR(13) NOT NULL UNIQUE
);
