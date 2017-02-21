insert into publisher
Set
name = 'Drofa',
registeredAddress = 'Russia, Moscow',
businessAddress = 'Russia, Moscow';

insert into publisher
Set
name = 'Prosvyashenie',
registeredAddress = 'Russia, Saint-Petersberg',
businessAddress = 'Russia, Moscow';

insert into publisher
Set
name = 'Rosman',
registeredAddress = 'Russia, Moscow',
businessAddress = 'Russia, Saint-Peterberg';

insert into user
Set
name = 'Nikita',
middleName = 'Mihailovich',
lastName = 'Dolgopolov',
type = 'admin';

insert into user
Set
name = 'Mariya',
middleName = 'null',
lastName = 'Sitnikova',
type = 'admin';

insert into user
Set
name = 'Simon',
middleName = 'Romanovich',
lastName = 'Bryatov',
type = 'user';

insert into book
Set
name = 'Harry Potter',
authors = 'J.Rouling',
year = '2009-06-30',
pages = 500,
idPublisher = 1;

insert into book
Set
name = 'World and War',
authors = 'L.Tolstoy',
year = '2013-01-31',
pages = 1500,
idPublisher = 2;

insert into book
Set
name = 'Dead souls',
authors = 'N.Vasilevich',
year = '2011-09-07',
pages = 450,
idPublisher = 2;

insert into book
Set
name = 'Centervil ghost',
authors = 'O.Wild',
year = '2016-02-14',
pages = 200,
idPublisher = 3;


insert into copyofthebook
Set
idBook = 1,
issue = true,
idUser = 3;

insert into copyofthebook
Set
idBook = 1,
issue = false;

insert into copyofthebook
Set
idBook = 2,
issue = false;

insert into copyofthebook
Set
idBook = 3,
issue = true,
idUser = 3;

insert into copyofthebook
Set
idBook = 3,
issue = false;

insert into copyofthebook
Set
idBook = 3,
issue = false;

insert into copyofthebook
Set
idBook = 4,
issue = false;

insert into copyofthebook
Set
idBook = 4,
issue = false;

insert into catalog
Set
name = 'Художественные произведения';

insert into catalog
Set
name = 'Научная фантастика';

insert into catalog
Set
name = 'Биография';

insert into catalog
Set
name = 'Русская классика',
idOfParent = 1;

insert into catalog
Set
name = 'Фантастика',
idOfParent = 1;

insert into book_catalog
Set
idBook = 1,
idCatalog = 5;

insert into book_catalog
Set
idBook = 2,
idCatalog = 4;

insert into book_catalog
Set
idBook = 3,
idCatalog = 4;

insert into book_catalog
Set
idBook = 4,
idCatalog = 5;
