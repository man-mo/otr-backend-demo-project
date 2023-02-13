CREATE TABLE visit_code
(
    id          int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    value       varchar(64)     NOT NULL,
    salesman_id int             NOT NULL
);
CREATE UNIQUE INDEX visit_code_id_uindex ON visit_code (id);