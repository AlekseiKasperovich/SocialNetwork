CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO roles (id, name)
VALUES (uuid_generate_v4(), 'ROLE_ADMIN');
INSERT INTO roles (id, name)
VALUES (uuid_generate_v4(), 'ROLE_USER');

INSERT INTO users (id, email, password, first_name, last_name, birthday, phone, status, role_id, sex)
VALUES (uuid_generate_v4(), 'admin@gmail.com',
        '$2a$12$8TwI0onDZ.LoALxno1TCZeT2P3ayUdA9I5UsmDaCchCAIraGRm9JK', 'admin', 'adminov',
        '2000-01-01', '+375299999999', 'ACTIVE', (select id from roles where name = 'ROLE_ADMIN'),
        'MALE');
INSERT INTO users (id, email, password, first_name, last_name, birthday, phone, status, role_id, sex)
VALUES (uuid_generate_v4(), 'user@gmail.com',
        '$2a$12$I3yuTCvlzJmVOez9q2njtODOXuxtSu3jxb2GRAoK5739WrmN9K0FO', 'user', 'userov',
        '1999-01-01', '+375291234567', 'ACTIVE', (select id from roles where name = 'ROLE_USER'),
        'MALE');
