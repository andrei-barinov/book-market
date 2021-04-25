drop table if exists storage cascade;
create table storage(
                        id bigserial,
                        book bigint primary key,
                        quantity bigint
);

INSERT INTO storage VALUES (1, 1, 100);
INSERT INTO storage VALUES (2, 2, 100);
INSERT INTO storage VALUES (3, 3, 100);
INSERT INTO storage VALUES (4, 4, 100);
INSERT INTO storage VALUES (5, 5, 100);
INSERT INTO storage VALUES (6, 6, 100);
INSERT INTO storage VALUES (7, 7, 100);
INSERT INTO storage VALUES (8, 8, 100);
INSERT INTO storage VALUES (9, 9, 100);
INSERT INTO storage VALUES (10, 10, 100);

drop table if exists book cascade;
create table book(
                     id bigserial primary key,
                     title varchar(255) not null,
                     price int,
                     storage bigint references storage(book),
                     created_at timestamp default  current_timestamp,
                     updated_at timestamp default  current_timestamp
);