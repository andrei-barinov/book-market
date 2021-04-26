drop table if exists orders cascade;
create table orders (
                        id                      bigserial primary key,
                        owner_id                bigint references users(id),
                        price                   int,
                        address                 varchar(255),
                        created_at              timestamp default current_timestamp,
                        updated_at              timestamp default current_timestamp
);