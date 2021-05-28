drop table if exists role cascade;
create table role(
                     id bigserial primary key,
                     name varchar(255) not null
);

INSERT INTO role values(1, 'ROLE_USER');
INSERT INTO role values (2, 'ROLE_MANAGER');
INSERT INTO role values (3, 'ROLE_ADMIN');
INSERT INTO role values (4, 'ROLE_SUPERADMIN');


drop table if exists users cascade;
create table users(
                      id bigserial primary key,
                      first_name varchar(255) not null,
                      last_name varchar(255) not null,
                      login varchar(255) not null,
                      password varchar(255) not null,
                      phone_number varchar(255),
                      password_2 varchar(255) not null
);

INSERT INTO users VALUES (1,
                          'Иванов',
                          'Сергей',
                          'ivanov_sergey@mail.com',
                          '$2a$10$8XBuvrPULsABNRyTkCZ8we5pm7pHU2DvPu5s6uU6KNQYvVKVEV5my',
                          '+79503743875',
                          'manager1');
INSERT INTO users VALUES (2,
                          'Петров',
                          'Иван',
                          'petrov_ivan@mail.com',
                          '$2a$10$fChzOczKQZ2BPCAfqfCwhOUv6HO1rDJTQjFNjoF8nCgrs03ujfGw2',
                          '+79303743875',
                          'admin1');
INSERT INTO users VALUES (3,
                          'Смирнов',
                          'Виктор',
                          'smirnov_viktor@mail.com',
                          '$2a$10$xLrvGmda4ahzFv5g15v2uukfBy6h5Csw3OwbVnfTUrKjY8XzexdNq',
                          '+79373743875',
                          'manager2');
INSERT INTO users VALUES (4,
                          'Бобров',
                          'Семен',
                          'bobrov_semen@mail.com',
                          '$2a$10$0qu4IkwBM8IR/pDsJcrxbeS.CBQdB3uhOH/w4zB/2qVG9iq.VA0Ru',
                          '+79673747875',
                          'admin2');
INSERT INTO users VALUES (5,
                          'Сидоров',
                          'Антон',
                          'sidorov_anton@mail.com',
                          '$2a$10$4LHMOl0KrOFIeYMuWixdz.lrr0lCzkLANeJWYrrtkroywKC4GzcvS',
                          '+79573547875',
                          'superadmin');
INSERT INTO users VALUES (6,
                          'Антонов',
                          'Дмитрий',
                          'antonov_dmitry@mail.com',
                          '$2a$10$ydk4w.RD1Mr1s/aZdCkEP.BGkU1gm7mczhL1R8eoVZZxZA5qiaPOu',
                          '+79575677875',
                          'user1');
INSERT INTO users VALUES (7,
                          'Михаил',
                          'Андреев',
                          'mihail_andreev@mail.com',
                          '$2a$10$Uz0pI6TlxQ1fsbORyuZSFuTk039UansbecUPL6IeXOwYgvphsrXZe',
                          '+79585677974',
                          'user2');
INSERT INTO users VALUES (8,
                          'Василий',
                          'Сергеев',
                          'vasiliy_sergeev@mail.com',
                          '$2a$10$4LC12TYaK4oPs2xBMAH/T.ynxShhg7dGQuF0NF//ftvZg9P4Paq0.',
                          '+79585677974',
                          'user3');
INSERT INTO users VALUES (9,
                          'Андрей',
                          'Максимов',
                          'andrei_maksimov@mail.com',
                          '$2a$10$OugPzm.PKy8wyDqQ98sf/uo23umT6uAT/C6xmFygIkCyaouQVrVsO',
                          '+79889607974',
                          'user4');
INSERT INTO users VALUES (10,
                          'Виталий',
                          'Анисимов',
                          'vitaliy_anisimov@mail.com',
                          '$2a$10$8KKxEAnzqsWJgFz7Xnk6PugPqxJv8esUrg63qR47AOYGJ8GAaGSku',
                          '+79389107954',
                          'user5');

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

INSERT INTO author VALUES (1, 'Юлиана', 'Козьмина');
INSERT INTO author VALUES (2, 'Роб', 'Харроп');
INSERT INTO author VALUES (3, 'Крис', 'Шефер');
INSERT INTO author VALUES (4, 'Кларенс', 'Хо');
INSERT INTO author VALUES (5, 'Кей', 'Хорстман');
INSERT INTO author VALUES (6, 'Кэтти', 'Сьерра');
INSERT INTO author VALUES (7, 'Берт', 'Бейтс');
INSERT INTO author VALUES (8, 'Кейн', 'Рейчел');
INSERT INTO author VALUES (9, 'Федор', 'Достоевский');
INSERT INTO author VALUES (10, 'Уильям', 'Моэм');
INSERT INTO author VALUES (11, 'Марио', 'Пьюзо');
INSERT INTO author VALUES (12, 'Данте', 'Алигьери');
INSERT INTO author VALUES (13, 'Йозеф', 'Оллерберг');

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

drop table if exists book cascade;
create table book(
                     id bigserial primary key,
                     title varchar(255) not null,
                     price int,
                     created_at timestamp default  current_timestamp,
                     updated_at timestamp default  current_timestamp
);

INSERT INTO book VALUES (1, 'Мрачный залив', 500);
INSERT INTO book VALUES (2, 'Java SE', 1000);
INSERT INTO book VALUES (3, 'Spring 5 для профессионалов', 3000);
INSERT INTO book VALUES (4, 'Изучаем Java', 3000);
INSERT INTO book VALUES (5, 'Преступление и наказание', 1500);
INSERT INTO book VALUES (6, 'Бремя страстей человеческих', 2000);
INSERT INTO book VALUES (7, 'Крестный отец', 2500);
INSERT INTO book VALUES (8, 'Сицилиец', 2100);
INSERT INTO book VALUES (9, 'Божественная комедия', 1700);
INSERT INTO book VALUES (10, 'Немецкий снайпер на восточном фронте', 700);

drop table if exists author_book cascade;
create table author_book(
                            book_id bigint not null references book(id),
                            author_id bigint not null references author(id),
                            primary key(book_id, author_id)
);

INSERT INTO author_book VALUES (1, 8);
INSERT INTO author_book VALUES (2, 5);
INSERT INTO author_book VALUES (3, 1);
INSERT INTO author_book VALUES (3, 2);
INSERT INTO author_book VALUES (3, 3);
INSERT INTO author_book VALUES (3, 4);
INSERT INTO author_book VALUES (4, 6);
INSERT INTO author_book VALUES (4, 7);
INSERT INTO author_book VALUES (5, 9);
INSERT INTO author_book VALUES (6, 10);
INSERT INTO author_book VALUES (7, 11);
INSERT INTO author_book VALUES (8, 11);
INSERT INTO author_book VALUES (9, 12);
INSERT INTO author_book VALUES (10, 13);

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
