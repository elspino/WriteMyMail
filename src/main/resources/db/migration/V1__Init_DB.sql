create table email (
                       id uuid not null,
                       user_id uuid,
                       name varchar(255) not null unique,
                       password varchar(255) not null,
                       type varchar(255),
                       primary key (id)
);

create table prompt (
                        name varchar(255) not null,
                        info TEXT,
                        primary key (name)
);

create table "user" (
                        id uuid not null,
                        info varchar(255),
                        name varchar(255),
                        password varchar(255) not null,
                        role varchar(255) not null check (role in ('USER','ADMIN')),
                        username varchar(255) not null unique,
                        primary key (id)
);

alter table if exists email
    add constraint FKncyt7y2wans100oxk8vgnkl9m
        foreign key (user_id)
            references "user"