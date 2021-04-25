drop table if exists order_items cascade;
create table order_items(
                            id bigserial primary key,
                            book_id bigint references book(id) not null,
                            quantity int not null,
                            price_per_book int not null,
                            total_price int,
                            created_at timestamp default  current_timestamp,
                            updated_at timestamp default  current_timestamp
);