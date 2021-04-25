drop table if exists category cascade;
create table category(
                     id bigserial primary key,
                     name varchar(255) not null
);

INSERT INTO category VALUES (1, 'Детектив');
INSERT INTO category VALUES (2, 'Информационные технологии');
INSERT INTO category VALUES (3, 'Русская литература');
INSERT INTO category VALUES (4, 'Зарубежная литература');
INSERT INTO category VALUES (5, 'Мемуары');
INSERT INTO category VALUES (6, 'Роман');
INSERT INTO category VALUES (7, 'Поэма');

drop table if exists category_book cascade;
create table category_book(
                            book_id bigint not null references book(id),
                            category_id bigint not null references category(id),
                            primary key (book_id, category_id)
);

INSERT INTO category_book VALUES (1, 1);
INSERT INTO category_book VALUES (1, 4);
INSERT INTO category_book VALUES (2, 2);
INSERT INTO category_book VALUES (3, 2);
INSERT INTO category_book VALUES (4, 2);
INSERT INTO category_book VALUES (5, 3);
INSERT INTO category_book VALUES (5, 6);
INSERT INTO category_book VALUES (6, 4);
INSERT INTO category_book VALUES (6, 6);
INSERT INTO category_book VALUES (7, 4);
INSERT INTO category_book VALUES (7, 6);
INSERT INTO category_book VALUES (8, 4);
INSERT INTO category_book VALUES (8, 6);
INSERT INTO category_book VALUES (9, 4);
INSERT INTO category_book VALUES (9, 7);
INSERT INTO category_book VALUES (10, 4);
INSERT INTO category_book VALUES (10, 5);

drop table if exists genre cascade;