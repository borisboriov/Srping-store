create table categories
(
    id    serial,
    title varchar(255),
    PRIMARY KEY (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       int,
    category_id integer REFERENCES categories (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

insert into categories (title)
values ('Bakery'),
       ('Alcohol'),
       ('Dairy'),
       ('Meat');


insert into products (title, price, category_id)
values ('Bread', 100, 1),
       ('Baguette', 80, 1),
       ('Bun', 100, 1),
       ('Wine', 80, 2),
       ('Whiskey', 100, 2),
       ('Beer', 80, 2),
       ('Milk', 100, 3),
       ('Yogurt', 80, 3),
       ('Cheese', 100, 3),
       ('Salami', 80, 4),
       ('Ham', 80, 4),
       ('Turkey', 90, 4);

create table users
(
    id         bigserial primary key,
    username   varchar(36) not null,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table users_roles
(
    user_id    bigint not null references users (id),
    role_id    bigint not null references roles (id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
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
    total_price int    not null,
    address     varchar(255),
    phone       varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint not null references products (id),
    order_id          bigint not null references orders (id),
    quantity          int    not null,
    price_per_product int    not null,
    price             int    not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);

insert into orders (user_id, total_price, address, phone)
values (1, 200, 'address', '12345');

insert into order_items (product_id, order_id, quantity, price_per_product, price)
values (1, 1, 2, 100, 200);









