--liquibase formatted sql

--changeset Grigoryev_Pavel:001
INSERT INTO "user" (name, date_of_birth, password)
VALUES ('Иван Иванов', '1990-01-15', 'ivan12345678'),
       ('Анна Петрова', '1985-03-22', 'anna87654321'),
       ('Петр Сидоров', '1993-07-10', 'petr11223344'),
       ('Мария Кузнецова', '1988-11-30', 'maria55667788'),
       ('Алексей Смирнов', '1995-05-05', 'alex99887766'),
       ('Елена Васильева', '1992-09-12', 'elena33445566'),
       ('Дмитрий Попов', '1987-02-28', 'dmitry66778899'),
       ('Ольга Морозова', '1991-06-18', 'olga22334455'),
       ('Сергей Козлов', '1989-04-25', 'sergey44556677'),
       ('Наталья Лебедева', '1994-08-03', 'natalia77889900'),
       ('Иван Старовойтов', '1986-05-30', 'ivan87654321');

INSERT INTO account (user_id, balance)
VALUES (1, 5000.00),
       (2, 3000.00),
       (3, 7500.00),
       (4, 2000.00),
       (5, 10000.00),
       (6, 4500.00),
       (7, 6000.00),
       (8, 3500.00),
       (9, 8000.00),
       (10, 2500.00),
       (11, 1000.00);

INSERT INTO email_data (user_id, email)
VALUES (1, 'ivan.ivanov@example.com'),
       (1, 'ivan.work@example.com'),
       (2, 'anna.petrova@example.com'),
       (3, 'petr.sidorov@example.com'),
       (4, 'maria.kuznetsova@example.com'),
       (4, 'maria.personal@example.com'),
       (5, 'alexey.smirnov@example.com'),
       (6, 'elena.vasilieva@example.com'),
       (7, 'dmitry.popov@example.com'),
       (8, 'olga.morozova@example.com'),
       (9, 'sergey.kozlov@example.com'),
       (10, 'natalia.lebedeva@example.com'),
       (11, 'ivan.staravoit@example.com');

INSERT INTO phone_data (user_id, phone)
VALUES (1, '79207865432'),
       (1, '79109876543'),
       (2, '79217865433'),
       (3, '79227865434'),
       (3, '79127865435'),
       (4, '79237865436'),
       (5, '79247865437'),
       (6, '79257865438'),
       (7, '79267865439'),
       (8, '79277865440'),
       (9, '79287865441'),
       (10, '79297865442'),
       (11, '79297865443');
