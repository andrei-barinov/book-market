drop table if exists book cascade;
create table book(
                     id bigserial primary key,
                     title varchar(255) not null,
                     price int,
                     created_at timestamp default  current_timestamp,
                     updated_at timestamp default  current_timestamp
);

drop table if exists users cascade;
create table users(
                      id bigserial primary key,
                      first_name varchar(255) not null,
                      last_name varchar(255) not null,
                      login varchar(255) not null,
                      password varchar(255) not null,
                      address bigint references address(id) not null,
                      phone_number varchar(255)
);

drop table if exists storage cascade;
create table storage(
                        id bigserial primary key,
                        book bigint references book(id) not null,
                        quantity bigint
);

INSERT INTO role values(1, 'ROLE_USER');
INSERT INTO role values (2, 'ROLE_MANAGER');
INSERT INTO role values (3, 'ROLE_ADMIN');
INSERT INTO role values (4, 'ROLE_SUPERADMIN');

INSERT INTO address VALUES (1, 'Россия', 'Нижегородская область', 'Дзержинск', 'Ленина', 2, 5, 606000);
INSERT INTO address VALUES (2, 'Россия', 'Нижегородская область', 'Сергач', 'Свердлова', 160, null, 607510);
INSERT INTO address VALUES (3, 'Россия', 'Нижегородская область', 'Нижний Новгород', 'Гагарина', 57, 23, 603005);
INSERT INTO address VALUES (4, 'Россия', 'Нижегородская область', 'Саров', 'Менделеева', 157, 29, 607188);
INSERT INTO address VALUES (5, 'Россия', 'Нижегородская область', 'Арзамас', 'Гагарина', 123, 78, 607220);
INSERT INTO address VALUES (6, 'Россия', 'Нижегородская область', 'Нижний Новгород', 'Родионова', 27, 83, 603010);
INSERT INTO address VALUES (7, 'Россия', 'Нижегородская область', 'Дзержинск', 'Циолковского', 4, 89, 606010);
INSERT INTO address VALUES (8, 'Россия', 'Нижегородская область', 'Нижний Новгород', 'Ошарская', 78, 89, 603012);
INSERT INTO address VALUES (9, 'Россия', 'Нижегородская область', 'Нижний Новгород', 'Большая покровская', 3, 45, 603045);
INSERT INTO address VALUES (10, 'Россия', 'Нижегородская область', 'Нижний Новгород', 'Ильинская', 7, 37, 603047);

INSERT INTO users VALUES (1,
                             'Иванов',
                             'Сергей',
                             'ivanov_sergey@mail.com',
                             'manager1',
                             1,
                             '+79503743875');
INSERT INTO users VALUES (2,
                             'Петров',
                             'Иван',
                             'petrov_ivan@mail.com',
                             'admin1',
                             2,
                             '+79303743875');
INSERT INTO users VALUES (3,
                             'Смирнов',
                             'Виктор',
                             'smirnov_viktor@mail.com',
                             'manager2',
                             3,
                             '+79373743875');
INSERT INTO users VALUES (4,
                             'Бобров',
                             'Семен',
                             'bobrov_semen@mail.com',
                             'admin2',
                             4,
                             '+79673747875');
INSERT INTO users VALUES (5,
                             'Сидоров',
                             'Антон',
                             'sidorov_anton@mail.com',
                             'superadmin',
                             5,
                             '+79573547875');
INSERT INTO users VALUES (6,
                             'Антонов',
                             'Дмитрий',
                             'antonov_dmitry@mail.com',
                             'user1',
                             6,
                             '+79575677875');
INSERT INTO users VALUES (7,
                             'Михаил',
                             'Андреев',
                             'mihail_andreev@mail.com',
                             'user2',
                             7,
                             '+79585677974');
INSERT INTO users VALUES (8,
                             'Василий',
                             'Сергеев',
                             'vasiliy_sergeev@mail.com',
                             'user3',
                             8,
                             '+79585677974');
INSERT INTO users VALUES (9,
                             'Андрей',
                             'Максимов',
                             'andrei_maksimov@mail.com',
                             'user4',
                             9,
                             '+79889607974');
INSERT INTO users VALUES (10,
                             'Виталий',
                             'Анисимов',
                             'vitaliy_anisimov@mail.com',
                             'user5',
                             10,
                             '+79389107954');

INSERT INTO users_role VALUES (1, 2);
INSERT INTO users_role VALUES (1, 1);
INSERT INTO users_role VALUES (2, 3);
INSERT INTO users_role VALUES (2, 1);
INSERT INTO users_role VALUES (3, 2);
INSERT INTO users_role VALUES (3, 1);
INSERT INTO users_role VALUES (4, 3);
INSERT INTO users_role VALUES (4, 1);
INSERT INTO users_role VALUES (5, 4);
INSERT INTO users_role VALUES (6, 1);
INSERT INTO users_role VALUES (7, 1);
INSERT INTO users_role VALUES (8, 1);
INSERT INTO users_role VALUES (9, 1);
INSERT INTO users_role VALUES (10, 1);

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

INSERT INTO status VALUES (1, 'В обработке');
INSERT INTO status VALUES (2, 'Отправлен');
INSERT INTO status VALUES (3, 'Доставлен');