--cover_type
insert into covery_type("name")
values ('twarda');
insert into covery_type("name")
values ('miękka');
--categories
insert into categories ("category_name")
values ('kryminał i sensacja');
insert into categories ("category_name")
values ('thriller');
insert into categories ("category_name")
values ('polscy autorzy');
insert into categories ("category_name")
values ('proza');
insert into categories ("category_name")
values ('proza polska');
insert into categories ("category_name")
values ('powieść');
--books
insert into books("title", "author", "cover_type_id", "description", "ean", "pages", "price", "publisher", "release_date", "file_name")
values ('Głosy z zaświatów', 'Remigiusz Mróz', 2, 'Nowa książka znanego autora', 9788380759893, 442, 23.14, 'Filia', '2020-01-29', '783441-352x500.jpg');
insert into books("title", "author", "cover_type_id", "description", "ean", "pages", "price", "publisher", "release_date", "file_name")
values ('O krok za daleko', 'Harlan Coben', 2, 'Triller autorstwa Cobena.', 9788381258258, 448, 24.98, 'Albatros', '2020-01-15', '770180-352x500.jpg');
insert into books("title", "author", "cover_type_id", "description", "ean", "pages", "price", "publisher", "release_date", "file_name")
values ('Księgi Jakubowe', 'Olga Tokarczuk', 1, 'Nagroda Literacka Nike 2015!', 9788308049396, 912, 45.80, 'Wydawnictwo Literackie', '2014-10-23', '742364-352x500.jfif');
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
insert into book_categories("book_id", "category_id")
values (1, 1);
insert into book_categories("book_id", "category_id")
values (1, 2);
insert into book_categories("book_id", "category_id")
values (1, 3);
insert into book_categories("book_id", "category_id")
values (2, 1);
insert into book_categories("book_id", "category_id")
values (2, 2);
insert into book_categories("book_id", "category_id")
values (3, 4);
insert into book_categories("book_id", "category_id")
values (3, 5);
insert into book_categories("book_id", "category_id")
values (3, 6);
