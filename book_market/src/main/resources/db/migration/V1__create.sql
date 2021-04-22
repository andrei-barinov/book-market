drop table if exists role cascade;
create table role(
                     id bigserial primary key,
                     name varchar(255) not null
);

drop table if exists address cascade;
create table address(
                        id bigserial primary key,
                        country varchar(255) not null,
                        region varchar(255) not null,
                        locality varchar(255) not null,
                        street varchar(255)not null,
                        house int not null,
                        flat int,
                        index int not null
);

drop table if exists users cascade;
create table users(
                         id bigserial primary key,
                         first_name varchar(255) not null,
                         last_name varchar(255) not null,
                         login varchar(255) not null,
                         password varchar(255) not null,
                         address bigint references address(id) not null
);

drop table if exists users_role cascade;
create table users_role(
    user_id bigint not null references users(id),
    role_id bigint not null references role(id),
    primary key (user_id, role_id)
);


drop table if exists author cascade;
create table author(
                       id bigserial primary key,
                       first_name varchar(255) not null,
                       last_name varchar(255) not null
);

drop table if exists genre cascade;
create table genre(
                      id bigserial primary key,
                      name varchar(255)
);

drop table if exists book cascade;
create table book(
                     id bigserial primary key,
                     title varchar(255) not null,
                     price int,
                     author bigint references author(id) not null,
                     genre bigint references genre(id) not null,
                     created_at timestamp default  current_timestamp,
                     updated_at timestamp default  current_timestamp
);

drop table if exists author_book cascade;
create table author_book(
    book_id bigint not null references book(id),
    author_id bigint not null references author(id),
    primary key(book_id, author_id)
);

drop table if exists storage cascade;
create table storage(
                        id bigserial primary key,
                        book bigint references book(id) not null
);

drop table if exists order_items cascade;
create table order_items(
                            id bigserial primary key,
                            book bigint references book(id) not null,
                            quantity int not null,
                            price_per_book int not null,
                            total_price int
);

drop table if exists status cascade;
create table status(
                       id serial primary key,
                       name varchar(255) not null
);

drop table if exists orders cascade;
create table orders(
                       id bigserial primary key,
                       order_items bigint references order_items(id) not null,
                       users bigint references users(id) not null,
                       status int references status(id) not null
);