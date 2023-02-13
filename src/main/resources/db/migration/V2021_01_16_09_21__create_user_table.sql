create table user
(
    id         int auto_increment primary key,
    username   varchar(50)                          null,
    password   varchar(100)                         null,
    name       varchar(50)                          null,
    money      decimal                              not null,
    created_at datetime   default CURRENT_TIMESTAMP not null,
    updated_at datetime                             null,
    is_active  tinyint(1) default '1'               not null
)
    charset = utf8;