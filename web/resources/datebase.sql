create schema ginkgo collate utf8mb4_0900_ai_ci;

create table user_login
(
    id int auto_increment
        primary key,
    handle varchar(15) not null,
    password char(32) not null,
    constraint handle
        unique (handle)
);

create table Task
(
    id int auto_increment
        primary key,
    userId int not null,
    `desc` varchar(50) not null,
    time date default '1999-10-01' null,
    frequency int default 0 null,
    `repeat` int default 0 null,
    rValue int null,
    last_completed date null,
    constraint Task_ibfk_1
        foreign key (userId) references user_login (id)
);

create index userId
    on Task (userId);

create index login
    on user_login (handle, password);

