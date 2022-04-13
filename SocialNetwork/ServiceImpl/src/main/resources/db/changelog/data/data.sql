--liquibase formatted sql
--changeset aleksei:1
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_USER');

INSERT INTO users (id, email, password, first_name, last_name, birthday, phone, status, role_id, sex) VALUES (3, 'admin@gmail.com', '$2a$12$8TwI0onDZ.LoALxno1TCZeT2P3ayUdA9I5UsmDaCchCAIraGRm9JK', 'admin', 'adminov', '2000-01-01', '+375299999999', 'ACTIVE', 1, 'MALE');
INSERT INTO users (id, email, password, first_name, last_name, birthday, phone, status, role_id, sex) VALUES (4, 'user@gmail.com', '$2a$12$I3yuTCvlzJmVOez9q2njtODOXuxtSu3jxb2GRAoK5739WrmN9K0FO', 'user', 'userov', '1999-01-01', '+375291234567', 'ACTIVE', 2, 'MALE');

SELECT setval('public.hibernate_sequence', 4, true);
