--cover_type
insert into covery_type("name") values ('twarda');
insert into covery_type("name") values ('miękka');
--categories
insert into categories ("category_name") values ('kryminał i sensacja');
insert into categories ("category_name") values ('thriller');
insert into categories ("category_name") values ('polscy autorzy');
insert into categories ("category_name") values ('proza');
insert into categories ("category_name") values ('proza polska');
insert into categories ("category_name") values ('powieść');
insert into categories ("category_name") values ('fantastyka');
insert into categories ("category_name") values ('fantazy');
insert into categories ("category_name") values ('podróże');
insert into categories ("category_name") values ('science fiction');
--books
insert into books("title", "author", "cover_type_id", "description", "ean", "pages", "price", "publisher", "release_date", "file_name")
values ('Głosy z zaświatów', 'Remigiusz Mróz', 2, 'Nowa książka znanego autora', 9788380759893, 442, 23.14, 'Filia', '2020-01-29', 'Glosy_z_zaswiatow.png');
insert into books("title", "author", "cover_type_id", "description", "ean", "pages", "price", "publisher", "release_date", "file_name")
values ('O krok za daleko', 'Harlan Coben', 2, 'Triller autorstwa Cobena.', 9788381258258, 448, 24.98, 'Albatros', '2020-01-15', 'O_krok_za_daleko.png');
insert into books("title", "author", "cover_type_id", "description", "ean", "pages", "price", "publisher", "release_date", "file_name")
values ('Księgi Jakubowe', 'Olga Tokarczuk', 1, 'Nagroda Literacka Nike 2015!', 9788308049396, 912, 45.80, 'Wydawnictwo Literackie', '2014-10-23', 'Ksiegi_Jakubowe.png');
insert into books("title", "author", "cover_type_id", "description", "ean", "pages", "price", "publisher", "release_date", "file_name")
values ('Smok odrodzony', 'Robert Jordan', 1, 'PORÓWNYWANY DO WŁADCY PIERŚCIENI MONUMENTALNY CYKL FANTASY, KTÓRY POKOCHAŁO PONAD 40 MILIONÓW CZYTELNIKÓW NA CAŁYM ŚWIECIE', 9788381168458, 442, 39.93, 'Zysk i S-ka', '2020-04-07', 'Smok_odrodzony.png');
insert into books("title", "author", "cover_type_id", "description", "ean", "pages", "price", "publisher", "release_date", "file_name")
values ('Polska przydrożna', 'Piotr Marecki', 2, 'Początek piekielnie gorącego lata 2019. 15 dni spędzonych w drodze, 4872 kilometry na liczniku. Setki miasteczek, wsi, wiosek i przysiółków. Drogi, ale częściej bezdroża.', 9788380499980, 200, 65.98, 'Czarne', '2020-04-08', 'polska_przydrozna.png');
insert into books("title", "author", "cover_type_id", "description", "ean", "pages", "price", "publisher", "release_date", "file_name")
values ('Luna: Wschód', 'Ian McDonald', 1, 'Trylogia Luna McDonalda, do której prawa ekranizacji za ogromną sumę zakupiła telewizja CBS, dawno została uznana za jeden z najbardziej ekscytujących i ważnych cykli SF ostatniej dekady.', 9788366409101, 294, 45.80, 'MAG', '2020-04-03', 'Luna_wschod.png');
insert into books("title", "author", "cover_type_id", "description", "ean", "pages", "price", "publisher", "release_date", "file_name")
values ('Wyrwa', 'Wojciech Chmielarz', 2, 'Kiedy Maciej Tomski dowiaduje się, że jego żona Janina zginęła w wypadku samochodowym, myśli, że wali mu się cały świat. Jak powiedzieć dwóm córeczkom, że właśnie straciły matkę? Jak powiedzieć teściom?', 9788366500167, 384, 27.39, 'Wydawnictwo Marginesy', '2020-05-06', 'Wyrwa.png');
insert into books("title", "author", "cover_type_id", "description", "ean", "pages", "price", "publisher", "release_date", "file_name")
values ('Stulecie kryminału', 'praca zbiorowa', 2, 'Osiemnaście mistrzowskich kryminalnych opowiadań. Opowieści o zbrodni i sprawiedliwości.', 9788366517608, 148, 27.93, 'Czwarta Strona', '2020-04-15', 'Stulecie_kryminalu.png');
insert into books("title", "author", "cover_type_id", "description", "ean", "pages", "price", "publisher", "release_date", "file_name")
values ('Cyberpunk. Odrodzenie', 'Andrzej Zmieniański', 1, 'Nagroda Literacka Nike 2015!', 9788328713628, 360, 24.73, 'Akurat', '2020-08-12', 'Cyberpunk_odrodzenie.png');
--comments
insert into comments("author", "date", "description", "rating", "book_id")
values ('Kasiula', '2020-01-01', 'Książka jest bardzo wciągająca. Polecam!', 5, 1);
insert into comments("author", "date", "description", "rating", "book_id")
values ('Zbyszek_568', '2019-11-12', 'Miejscami nudnawa ale wartoczytać do końca.', 4.5, 1);
insert into comments("author", "date", "description", "rating", "book_id")
values ('Robert-XL', '2019-10-22', 'Co za dno. Stracony czas!', 0.5, 1);
insert into comments("author", "date", "description", "rating", "book_id")
values ('Klaudia Zybert', '2020-01-18', 'Po prostu świetna', 5, 2);
insert into comments("author", "date", "description", "rating", "book_id")
values ('Tymoteusz Nowak', '2019-05-06', 'Zdecydowanie nie polecam.', 2, 2);
insert into comments("author", "date", "description", "rating", "book_id")
values ('Czytelnik', '2018-12-22', 'Nie jest najgorsza, ale czytałem lepsze.', 3.5, 3);
--books_categories
insert into books_categories("books_id", "categories_id") values (1, 1);
insert into books_categories("books_id", "categories_id") values (1, 2);
insert into books_categories("books_id", "categories_id") values (1, 3);
insert into books_categories("books_id", "categories_id") values (2, 1);
insert into books_categories("books_id", "categories_id") values (2, 2);
insert into books_categories("books_id", "categories_id") values (3, 4);
insert into books_categories("books_id", "categories_id") values (3, 5);
insert into books_categories("books_id", "categories_id") values (3, 6);
insert into books_categories("books_id", "categories_id") values (4, 7);
insert into books_categories("books_id", "categories_id") values (4, 8);
insert into books_categories("books_id", "categories_id") values (5, 9);
insert into books_categories("books_id", "categories_id") values (6, 7);
insert into books_categories("books_id", "categories_id") values (6, 8);
insert into books_categories("books_id", "categories_id") values (7, 1);
insert into books_categories("books_id", "categories_id") values (7, 2);
insert into books_categories("books_id", "categories_id") values (7, 3);
insert into books_categories("books_id", "categories_id") values (8, 1);
insert into books_categories("books_id", "categories_id") values (8, 2);
insert into books_categories("books_id", "categories_id") values (8, 3);
insert into books_categories("books_id", "categories_id") values (9, 3);
insert into books_categories("books_id", "categories_id") values (9, 6);
insert into books_categories("books_id", "categories_id") values (9, 9);
---roles-----
insert into roles("role_name") values ('USER');
insert into roles("role_name") values ('ADMIN');
--users---
insert into users ("address", "city", "create_at", "enabled", "first_name", "last_name", "password", "username", "zip_code")
values ('Rudnickiego 10/65a', 'Warszawa', '2020-04-29', 1, 'Lukasz', 'Kwiatkowski', '$2a$10$heqBtvXcbQxmtz04szwD4ew2v3rfe1GfuU8x/.Ix3Akpiv66hg0ti', 'admin@gmail.com', '01-458');
insert into users ("address", "city", "create_at", "enabled", "first_name", "last_name", "password", "username", "zip_code")
values ('Rudnickiego 5', 'Warszawa', '2020-04-19', 1, 'Jacek', 'Nowak', '$2a$10$.lclwVMSMzBaKTXhTezNDeORK/B2igQmrp9O5dh5tATVHvQPV6g.W', 'user@gmail.com', '01-828');
--add roles into users---
insert into users_roles("user_id", "role_id") values (1, 1);
insert into users_roles("user_id", "role_id") values (1, 2);
insert into users_roles("user_id", "role_id") values (2, 1);