drop table if exists book cascade;
create table book(
                     id bigserial primary key,
                     title varchar(255) not null,
                     price int,
                     storage bigint,
                     created_at timestamp default  current_timestamp,
                     updated_at timestamp default  current_timestamp
);

INSERT INTO book VALUES (1, 'Мрачный залив', 500, 1);
INSERT INTO book VALUES (2, 'Java SE', 1000, 2);
INSERT INTO book VALUES (3, 'Spring 5 для профессионалов', 3000, 3);
INSERT INTO book VALUES (4, 'Изучаем Java', 3000, 4);
INSERT INTO book VALUES (5, 'Преступление и наказание', 1500, 5);
INSERT INTO book VALUES (6, 'Бремя страстей человеческих', 2000, 6);
INSERT INTO book VALUES (7, 'Крестный отец', 2500, 7);
INSERT INTO book VALUES (8, 'Сицилиец', 2100, 8);
INSERT INTO book VALUES (9, 'Божественная комедия', 1700, 9);
INSERT INTO book VALUES (10, 'Немецкий снайпер на восточном фронте', 700, 10);