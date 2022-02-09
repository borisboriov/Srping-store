create table products
(
    id    bigserial primary key,
    title varchar(255),
    price int,
    rate  int
);

insert into products (title, price, rate)
values ('Milk', 100, 5),
       ('Bread', 90, 10),
       ('Sugar', 80, 15),
       ('Meat', 70, 20),
       ('Fresh', 60, 30),
       ('Salt', 50, 40),
       ('Butter', 40, 1),
       ('Salmon', 30, 2),
       ('Tuna', 20, 3),
       ('Pepper', 10, 4),
       ('Salad', 200, 44),
       ('Bree', 300, 34),
       ('Cheese', 400, 90),
       ('Vine', 500, 77),
       ('Beer', 177, 24);


create table users
(
    id         bigserial primary key,
    username   varchar(36) not null,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    order_id   bigserial
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
       ('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);


create table orders
(
    id          bigserial primary key,
    user_id     bigint not null references users (id),
--         product_id      bigint not null references products (id),
    total_price int    not null,
    address     varchar(255),
    phone       varchar(255)
--         order_id        bigserial
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint not null references products (id),
    order_id          bigint not null references orders (id),
    quantity          int    not null,
    price_per_product int    not null,
    price             int    not null
);