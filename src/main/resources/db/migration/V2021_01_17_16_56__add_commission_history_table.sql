CREATE TABLE commission_history
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    order_id varchar(64) NOT NULL,
    price decimal,
    commission_status varchar(20) NOT NULL,
    created_at datetime NOT NULL,
    updated_at datetime
);
CREATE UNIQUE INDEX commission_history_id_uindex ON commission_history (id);