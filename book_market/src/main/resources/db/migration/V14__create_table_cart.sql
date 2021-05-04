 create table carts
 (
     id       bigint primary key,
     owner_id bigint references users (id),
     price    numeric(10, 2)
 );

 create table cart_items
 (
     id                bigserial primary key,
     cart_id           bigint references carts(id),
     product_id        bigint references book(id),
     title             varchar(255),
     quantity          int,
     price_per_product int,
     price             int,
     created_at        timestamp default current_timestamp,
     updated_at        timestamp default current_timestamp
 );