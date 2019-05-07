CREATE TABLE znacka(
id INTEGER primary key,
nazov VARCHAR(100) NOT NULL
);

CREATE TABLE vonavky(
id SERIAL primary key,
nazov VARCHAR(100) NOT NULL,
velkost INTEGER NOT NULL,
typ VARCHAR(100) NOT NULL,
znacka_id INTEGER NOT NULL REFERENCES znacka(id) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE obchody(
id INTEGER primary key,
nazov VARCHAR(100) NOT NULL
);

CREATE TABLE vonavka_obchodu(
id SERIAL primary key,
vonavka_id INTEGER NOT NULL REFERENCES vonavky(id) ON UPDATE CASCADE ON DELETE RESTRICT,
obchod_id INTEGER NOT NULL REFERENCES obchody(id) ON UPDATE CASCADE ON DELETE RESTRICT,
cena REAL NOT NULL
);

CREATE TABLE moje_vonavky(
id SERIAL primary key,
vonavka_id INTEGER NOT NULL REFERENCES vonavky(id) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE moj_wishlist(
id SERIAL primary key,
vonavka_id INTEGER NOT NULL REFERENCES vonavky(id) ON UPDATE CASCADE ON DELETE RESTRICT
);

INSERT INTO obchody(id, nazov) VALUES
(1, 'Notino'),
(2,'Fann'),
(3,'Marionnaud');

INSERT INTO znacka(id, nazov) VALUES
(1, 'Armani'),
(2, 'Calvin Klein'),
(3, 'DKNY'),
(4, 'Gucci'),
(5, 'Hugo Boss'),
(6, 'Chloé'),
(7, 'Lacoste'),
(8, 'Lanvin'),
(9, 'Montblanc'),
(10, 'Prada'),
(11, 'Trussardi'),
(12, 'Versace'),
(13, 'Yves Saint Laurent');