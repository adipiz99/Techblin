DROP DATABASE IF EXISTS webstore;

CREATE DATABASE webstore;
USE webstore;

DROP user IF EXISTS 'webstore'@'localhost';
CREATE USER 'webstore'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL ON webstore.* TO 'webstore'@'localhost';

DROP TABLE IF EXISTS product;

CREATE TABLE product (
  code int primary key AUTO_INCREMENT,
  name char(200) not null,
  description char(200),
  price int default 0,
  quantity int default 0,
  visible int default 0
);

DROP TABLE IF EXISTS amministratore;

CREATE TABLE amministratore (
  email char(60) primary key,
  nome char(40) not null,
  cognome char(40) not null,
  password char(50) not null,
  registrazione date not null
);

DROP TABLE IF EXISTS venditore;

CREATE TABLE venditore (
  ID char(20) not null,
  email char(60) primary key not null,
  nome char(40) not null,
  cognome char(40) not null,
  password char(50) not null,
  registrazione date not null
);

DROP TABLE IF EXISTS acquirente;

CREATE TABLE acquirente (
  email char(60) primary key  not null,
  nome char(40) not null,
  cognome char(40) not null,
  password char(50) not null,
  registrazione date not null
);

DROP TABLE IF EXISTS ordine;

CREATE TABLE ordine (
  id int primary key AUTO_INCREMENT,
  totale int not null,
  dataOrdine date not null,
  stato char(30) not null,
  nome char(40) not null,
  cognome char(40) not null,
  acquirente char(100) not null,
  indirizzo char(100) not null,
  cap char(10) not null,
  città char(40) not null,
  provincia char(40) not null,
  tipologiaPagamento char(40) not null,
  datiPagamento char(100) not null,

  FOREIGN KEY (acquirente) REFERENCES acquirente(email)
									ON DELETE CASCADE
);

DROP TABLE IF EXISTS prodottiOrdine;

CREATE TABLE prodottiOrdine(
  id int PRIMARY KEY AUTO_INCREMENT,
  prodotto int,
  ordine int,
  
  FOREIGN KEY (prodotto) REFERENCES product(code)
									ON DELETE CASCADE,
  FOREIGN KEY (ordine) REFERENCES ordine(id)
									ON DELETE CASCADE
);

DROP TABLE IF EXISTS venditoreprodotto;

CREATE TABLE venditoreprodotto (
  venditore char(60) not null,
  prodotto int not null,
  
  PRIMARY KEY(venditore, prodotto), 
  FOREIGN KEY (venditore) REFERENCES venditore(email)
									ON DELETE CASCADE,
  FOREIGN KEY (prodotto) REFERENCES product(code)
									ON DELETE CASCADE
);

DROP TABLE IF EXISTS regione;

CREATE TABLE regione (
  reg_ID int not null,
  reg_nome char(60) not null,
  
  PRIMARY KEY(reg_ID)
);

DROP TABLE IF EXISTS provincia;

CREATE TABLE provincia (
  prov_ID int not null,
  prov_nome char(60) not null,
  reg_ID int not null,
  
  PRIMARY KEY(prov_ID),
  FOREIGN KEY (reg_ID) REFERENCES regione(reg_ID)
									ON DELETE NO ACTION
);

INSERT INTO product values (1,"MSI X299 Gaming Pro Carbon AC","Scheda madre da Gaming ATX, socket LGA 2066",550,5, 1);
INSERT INTO product values (2,"Asus ROG Strix Z390 Gaming","Scheda madre da Gaming ATX, socket LGA 1151",390,13, 1);
INSERT INTO product values (3,"Asus ROG Maximus XII EXTREME","Scheda madre da Gaming ATX, socket LGA 2066",960,4, 1);
INSERT INTO product values (4,"MSI GeForce RTX 2080 Super Gaming X Trio","Scheda video da Gaming, 8 Gb Vram GDDR6",830,11, 1);
INSERT INTO product values (5,"Gigabyte GeForce RTX 2060 Gaming OC PRO","Scheda video da Gaming, 6 Gb Vram GDDR6",329,3, 1);
INSERT INTO product values (6,"ASUS TUF Gaming GeForce GTX 1660 Super OC Edition","Scheda video da Gaming, 6 Gb Vram GDDR6",279,22, 1);
INSERT INTO product values (7,"Corsair Carbide Series SPEC-DELTA","Case da Gaming Mid-Tower con ventole RGB incluse",90,12, 1);
INSERT INTO product values (8,"Corsair Crystal Series 680X RGB","Case da Gaming Mid-Tower con ventole RGB incluse",249,16, 1);
INSERT INTO product values (9,"MSI MAG VAMPIRIC 010","Case da Gaming Mid-Tower con ventole RGB incluse",79,39, 1);
INSERT INTO product values (10,"Seasonic FOCUS+ 650W","Alimentatore da 650W certificato 80+ Platinum",149,8, 1);
INSERT INTO product values (11,"Asrock H110 Pro BTC+","Scheda madre da Mining ATX, socket LGA 1151",79,10, 1);
INSERT INTO product values (12,"Nvidia Quadro GV100","Scheda video da Server, 32 Gb Vram GDDR6",11500,35, 1);
INSERT INTO product values (13,"Intel Core i7-9700K","Processore LGA 1151, 3,6 - 5,1 Ghz",350,29, 1);
INSERT INTO product values (14,"Intel Core i9-10900K","Processore LGA 1200, 3,7 - 5,3 Ghz",950,31, 1);
INSERT INTO product values (15,"Razer Ornata V2","Tastiera semi-meccanica retroilluminata da Gaming",119,12, 1);



INSERT INTO amministratore values("admin@example.it", "NomeAdmin", "CognomeAdmin", "Admin", "2020-05-10");
INSERT INTO amministratore values("amministratore@example.it", "Andrea", "Di Pierno", "Password", "2020-05-10");

INSERT INTO acquirente values("acquirente@example.it", "NomeAcquirente", "CognomeAcquirente", "Acquirente", "2020-05-10");
INSERT INTO acquirente values("cliente@example.it", "NomeCliente", "CognomeCliente", "Cliente", "2020-05-10");

INSERT INTO venditore values("Seller1", "venditore@example.it", "NomeVenditore", "CognomeVenditore", "Venditore", "2020-05-10");
INSERT INTO venditore values("Seller2", "seller@example.it", "Nome", "Cognome", "Venditore", "2020-05-10");

INSERT INTO venditoreprodotto values ("venditore@example.it", 1);
INSERT INTO venditoreprodotto values ("seller@example.it", 2);
INSERT INTO venditoreprodotto values ("venditore@example.it", 3);
INSERT INTO venditoreprodotto values ("seller@example.it", 4);
INSERT INTO venditoreprodotto values ("venditore@example.it", 5);
INSERT INTO venditoreprodotto values ("seller@example.it", 6);
INSERT INTO venditoreprodotto values ("venditore@example.it", 7);
INSERT INTO venditoreprodotto values ("seller@example.it", 8);
INSERT INTO venditoreprodotto values ("venditore@example.it", 9);
INSERT INTO venditoreprodotto values ("seller@example.it", 10);
INSERT INTO venditoreprodotto values ("venditore@example.it", 11);
INSERT INTO venditoreprodotto values ("seller@example.it", 12);
INSERT INTO venditoreprodotto values ("venditore@example.it", 13);
INSERT INTO venditoreprodotto values ("seller@example.it", 14);
INSERT INTO venditoreprodotto values ("venditore@example.it", 15);

INSERT INTO regione values (1, "Abruzzo"), (2, "Basilicata"), (3, "Calabria"), (4, "Campania"), (5, "Emilia-Romagna"), (6, "Friuli Venezia Giulia"), (7, "Lazio"), (8, "Liguria"), (9, "Lombardia"), 
(10, "Marche"), (11, "Molise"), (12, "Piemonte"), (13, "Puglia"), (14, "Sardegna"), (15, "Sicilia"), (16, "Toscana"), (17, "Trentino-Alto Adige"), (18, "Umbria"), (19, "Valle d'Aosta"), 
(20, "Veneto");

INSERT INTO provincia values 	
	(1, 'Agrigento', 15),
	(2, 'Alessandria', 12),
	(3, 'Ancona', 10),
	(4, 'Arezzo', 16),
	(5, 'Ascoli Piceno', 10),
	(6, 'Asti', 12),
	(7, 'Avellino', 4),
	(8, 'Bari', 13),
	(9, 'Barletta-Andria-Trani', 13),
	(10, 'Belluno', 20),
	(11, 'Benevento', 4),
	(12, 'Bergamo', 9),
	(13, 'Biella', 12),
	(14, 'Bologna', 5),
	(15, 'Bolzano', 17),
	(16, 'Brescia', 9),
	(17, 'Brindisi', 13),
	(18, 'Cagliari', 14),
	(19, 'Caltanissetta', 15),
	(20, 'Campobasso', 11),
	(21, 'Carbonia-Iglesias', 14),
	(22, 'Caserta', 4),
	(23, 'Catania', 15),
	(24, 'Catanzaro', 3),
	(25, 'Chieti', 1),
	(26, 'Como', 9),
	(27, 'Cosenza', 3),
	(28, 'Cremona', 9),
	(29, 'Crotone', 3),
	(30, 'Cuneo', 12),
	(31, 'Enna', 15),
	(32, 'Fermo', 10),
	(33, 'Ferrara', 5),
	(34, 'Firenze', 16),
	(35, 'Foggia', 13),
	(36, 'Forlì-Cesena', 5),
	(37, 'Frosinone', 7),
	(38, 'Genova', 8),
	(39, 'Gorizia', 6),
	(40, 'Grosseto', 16),
	(41, 'Imperia', 8),
	(42, 'Isernia', 11),
	(43, 'L\'Aquila', 1),
	(44, 'La Spezia', 8),
	(45, 'Latina', 7),
	(46, 'Lecce', 13),
	(47, 'Lecco', 9),
	(48, 'Livorno', 16),
	(49, 'Lodi', 9),
	(50, 'Lucca', 16),
	(51, 'Macerata', 10),
	(52, 'Mantova', 9),
	(53, 'Massa e Carrara', 16),
	(54, 'Matera', 2),
	(55, 'Medio Campidano', 14),
	(56, 'Messina', 15),
	(57, 'Milano', 9),
	(58, 'Modena', 5),
	(59, 'Monza e Brianza', 9),
	(60, 'Napoli', 4),
	(61, 'Novara', 12),
	(62, 'Nuoro', 14),
	(63, 'Ogliastra', 14),
	(64, 'Olbia-Tempio', 14),
	(65, 'Oristano', 14),
	(66, 'Padova', 20),
	(67, 'Palermo', 15),
	(68, 'Parma', 5),
	(69, 'Pavia', 9),
	(70, 'Perugia', 18),
	(71, 'Pesaro e Urbino', 10),
	(72, 'Pescara', 1),
	(73, 'Piacenza', 5),
	(74, 'Pisa', 16),
	(75, 'Pistoia', 16),
	(76, 'Pordenone', 6),
	(77, 'Potenza', 2),
	(78, 'Prato', 16),
	(79, 'Ragusa', 15),
	(80, 'Ravenna', 5),
	(81, 'Reggio Calabria', 3),
	(82, 'Reggio Emilia', 5),
	(83, 'Rieti', 7),
	(84, 'Rimini', 5),
	(85, 'Roma', 7),
	(86, 'Rovigo', 20),
	(87, 'Salerno', 4),
	(88, 'Sassari', 14),
	(89, 'Savona', 8),
	(90, 'Siena', 16),
	(91, 'Siracusa', 15),
	(92, 'Sondrio', 9),
	(93, 'Taranto', 13),
	(94, 'Teramo', 1),
	(95, 'Terni', 18),
	(96, 'Torino', 12),
	(97, 'Trapani', 15),
	(98, 'Trento', 17),
	(99, 'Treviso', 20),
	(100, 'Trieste', 6),
	(101, 'Udine', 6),
	(102, 'Aosta', 19),
	(103, 'Varese', 9),
	(104, 'Venezia', 20),
	(105, 'Verbano-Cusio-Ossola', 12),
	(106, 'Vercelli', 12),
	(107, 'Verona', 20),
	(108, 'Vibo Valentia', 3),
	(109, 'Vicenza', 20),
	(110, 'Viterbo', 7);