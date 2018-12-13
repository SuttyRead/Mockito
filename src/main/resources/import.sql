create table IF NOT EXISTS persistent_logins(username tinytext  null, series tinytext null, token tinytext null, last_used timestamp null);

INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO role (name) VALUES ('USER');

INSERT INTO user (birthday, email, first_name, last_name, login, password, role_id) VALUES ('2009-12-11', 'admin@gmail.com', 'Admin', 'Admin', 'admin', '$2a$10$sOmo6cJZzDOyhVBR8nRfWufRVWLehBzL3O.SfHkpxUTeTrZSnCHNe', 1);
INSERT INTO user (birthday, email, first_name, last_name, login, password, role_id) VALUES ('2009-12-11', 'SuttyRead1@gmail.com', 'Sutty', 'Read', 'SuttyRead1', '$2a$10$RR3WyZpfT5NFEeZ4R/aO4uFc9d.YWw9zkv9sV2qTqar8vpLSGTl0m', 2);
INSERT INTO user (birthday, email, first_name, last_name, login, password, role_id) VALUES ('2009-12-11', 'SuttyRead2@gmail.com', 'Sutty', 'Read', 'SuttyRead2', '$2a$10$aJIuTQL.8p85Pw8TuOldru3Y4fwXnUik4xDiqCpSgJfa8LSbWRBIm', 2);
INSERT INTO user (birthday, email, first_name, last_name, login, password, role_id) VALUES ('2009-12-11', 'SuttyRead3@gmail.com', 'Sutty', 'Read', 'SuttyRead3', '$2a$10$zzN4pZ02tDNGQppYOyb.9.tkw6EJNKYDx9GJvuG7vEKeGIqoY6l6O', 2);
