INSERT INTO MANUFACTURER VALUES (0, 'Audi');
INSERT INTO MANUFACTURER VALUES (1, 'BMW');
INSERT INTO MANUFACTURER VALUES (2, 'Buick');
INSERT INTO MANUFACTURER VALUES (3, 'Cadillack');
INSERT INTO MANUFACTURER VALUES (4, 'Chevrolet');
INSERT INTO MANUFACTURER VALUES (5, 'Ford');
INSERT INTO MANUFACTURER VALUES (6, 'Honda');
INSERT INTO MANUFACTURER VALUES (7, 'Hyundai');
INSERT INTO MANUFACTURER VALUES (8, 'MAN');
INSERT INTO MANUFACTURER VALUES (9, 'Mercedes');
INSERT INTO MANUFACTURER VALUES (10, 'Mercedes-Benz');
INSERT INTO MANUFACTURER VALUES (11, 'Mitsubishi');
INSERT INTO MANUFACTURER VALUES (12, 'Nissan');
INSERT INTO MANUFACTURER VALUES (13, 'Peugeot');
INSERT INTO MANUFACTURER VALUES (14, 'Renault');
INSERT INTO MANUFACTURER VALUES (15, 'Saab');
INSERT INTO MANUFACTURER VALUES (16, 'Suzuki');
INSERT INTO MANUFACTURER VALUES (17, 'Toyota');
INSERT INTO MANUFACTURER VALUES (18, 'Volkswagen');
INSERT INTO MODEL VALUES (0, 'A4');
INSERT INTO MODEL VALUES (1, 'A6');
INSERT INTO MODEL VALUES (2, 'A8');
INSERT INTO MODEL VALUES (3, 'B4');
INSERT INTO MODEL VALUES (4, 'B5');
INSERT INTO MODEL VALUES (5, 'Beetle');
INSERT INTO MODEL VALUES (6, 'C3');
INSERT INTO MODEL VALUES (7, 'C6');
INSERT INTO MODEL VALUES (8, 'Civic');
INSERT INTO MODEL VALUES (9, 'Corolla');
INSERT INTO MODEL VALUES (10, 'Cruze');
INSERT INTO MODEL VALUES (11, 'Cruise');
INSERT INTO MODEL VALUES (12, 'CRX');
INSERT INTO MODEL VALUES (13, 'Golf');
INSERT INTO MODEL VALUES (14, 'Impala');
INSERT INTO MODEL VALUES (15, 'M2');
INSERT INTO MODEL VALUES (16, 'M8');
INSERT INTO MODEL VALUES (17, 'Passat');
INSERT INTO MODEL VALUES (18, 'Sunny');
INSERT INTO MODEL VALUES (19, 'Pulsar');
INSERT INTO MODEL VALUES (20, 'S3');
INSERT INTO MODEL VALUES (21, 'S4');
INSERT INTO MODEL VALUES (22, 'TT');
INSERT INTO COLOR VALUES (0, 'Black', 'Черный');
INSERT INTO COLOR VALUES (1, 'Blue', 'Голубой');
INSERT INTO COLOR VALUES (2, 'Brown', 'Коричневый');
INSERT INTO COLOR VALUES (3, 'Cyan', 'Бирюзовый');
INSERT INTO COLOR VALUES (4, 'Green', 'Зеленый');
INSERT INTO COLOR VALUES (5, 'Grey', 'Серый');
INSERT INTO COLOR VALUES (6, 'Orange', 'Оранжевый');
INSERT INTO COLOR VALUES (7, 'Pink', 'Розовый');
INSERT INTO COLOR VALUES (8, 'Purple', 'Пурпурный');
INSERT INTO COLOR VALUES (9, 'Red', 'Красный');
INSERT INTO COLOR VALUES (10, 'Violet', 'Фиолетовый');
INSERT INTO COLOR VALUES (11, 'White', 'Белый');
INSERT INTO COLOR VALUES (12, 'Yellow', 'Желтый');
INSERT INTO USER VALUES (0, 'Иван', 'Петров', '1985-10-21', 'user', '111', 'Vanya@gmail.com', 1, '750000');
INSERT INTO USER VALUES (1, 'Павел', 'Бобылев', '1986-09-15', 'admin', '111', 'Logr@gmail.com', 0, '0');
INSERT INTO USER VALUES (2, 'Влад', 'Цай', '1987-08-08', 'driver', '111', 'Vlad@gmail.com', 2, '0');
INSERT INTO USER VALUES (3, 'Василий', 'Кузякин', '1987-09-19', 'DRIVER2', '456', 'Vasiliy@gmail.com', 2, '0');
INSERT INTO USER VALUES (4, 'Петр', 'Сидорченко', '1976-05-07', 'DRIVER3', '456', 'Petr@gmail.com', 2, '0');
INSERT INTO USER VALUES (5, 'Леонид', 'Ершов', '1970-04-04', 'DRIVER4', '456', 'Leonid@gmail.com', 2, '0');
INSERT INTO USER VALUES (6, 'Дмитрий', 'Перов', '1978-12-06', 'DRIVER5', '456', 'Dmitriy@gmail.com', 2, '0');
INSERT INTO USER VALUES (7, 'Денис', 'Егоров', '1972-02-28', 'DRIVER6', '456', 'Denis@gmail.com', 2, '0');
INSERT INTO USER VALUES (8, 'Евгений', 'Солошенко', '1987-03-10', 'DRIVER7', '456', 'Evgeniy@gmail.com', 2, '0');
INSERT INTO USER VALUES (9, 'Максим', 'Корейко', '1964-03-27', 'DRIVER8', '456', 'Maxim@gmail.com', 2, '0');
INSERT INTO USER VALUES (10, 'Михаил', 'Афанасьев', '1968-10-17', 'DRIVER9', '456', 'Mikhail@gmail.com', 2, '0');
INSERT INTO USER VALUES (11, 'Валентин', 'Граидзе', '1982-06-24', 'DRIVER10', '456', 'Valentin@gmail.com', 2, '0');
INSERT INTO VEHICLE (ID, VEHICLE_TYPE, MODEL_ID, MANUFACTURER_ID, PRODUCTION_YEAR, COLOR_ID, MILEAGE, FUEL_TYPE, OPERABLE, RENT_PRICE, DRIVER_ID, STANDING_PLACES_NUMBER, PASSENGER_SEATS_NUMBER, DOORS_NUMBER, CONDITIONER, MAX_PAYLOAD, ENCLOSED, TIPPER)
VALUES
  (19, 0, 0, 0, 1999, 1, 213, 3, TRUE, 4000.00, 9, 38, 18, 2, NULL, NULL, NULL, NULL),
  (21, 0, 14, 4, 2005, 4, 20, 0, TRUE, 5000.00, NULL, 55, 50, 2, NULL, NULL, NULL, NULL),
  (5, 0, 4, 5, 2000, 5, 93, 4, TRUE, 5000.00, 7, 12, 10, 2, NULL, NULL, NULL, NULL),
  (4, 0, 1, 4, 1998, 4, 180, 1, TRUE, 8000.00, 6, 42, 38, 4, NULL, NULL, NULL, NULL),
  (18, 0, 10, 5, 1990, 7, 123, 1, TRUE, 2000.00, 4, 42, 21, 2, NULL, NULL, NULL, NULL),
  (20, 0, 19, 15, 2001, 4, 122, 0, TRUE, 122.00, 2, 21, 12, 2, NULL, NULL, NULL, NULL),
  (10, 0, 21, 15, 1999, 4, 200, 0, TRUE, 10000.00, 10, 42, 32, 2, NULL, NULL, NULL, NULL),
  (12, 1, 15, 13, 2000, 1, 50, 0, TRUE, 5000.00, 7, NULL, 5, 4, TRUE, NULL, NULL, NULL),
  (13, 1, 20, 14, 2010, 4, 200, 0, TRUE, 50000.00, 9, NULL, 4, 4, TRUE, NULL, NULL, NULL),
  (17, 1, 13, 12, 2008, 4, 250, 0, TRUE, 2000.00, 2, NULL, 1, 2, TRUE, NULL, NULL, NULL),
  (9, 1, 0, 0, 2013, 9, 85, 0, TRUE, 4500.00, NULL, NULL, 4, 4, TRUE, NULL, NULL, NULL),
  (16, 1, 14, 4, 2014, 11, 10, 0, TRUE, 8000.00, 8, NULL, 4, 4, TRUE, NULL, NULL, NULL),
  (45, 1, 6, 9, 2007, 9, 170, 0, TRUE, 7500.00, 4, NULL, 6, 4, TRUE, NULL, NULL, NULL),
  (11, 1, 5, 18, 2012, 10, 10, 0, TRUE, 15000.00, 2, NULL, 4, 4, TRUE, NULL, NULL, NULL),
  (8, 2, 1, 8, 1994, 8, 18, 0, TRUE, 10000.00, 10, NULL, NULL, NULL, NULL, 15, TRUE, FALSE),
  (15, 2, 16, 6, 2005, 2, 40, 0, TRUE, 20000.00, 2, NULL, NULL, NULL, NULL, 50, FALSE, FALSE),
  (14, 2, 15, 12, 1998, 0, 140, 1, TRUE, 12000.00, 6, NULL, NULL, NULL, NULL, 10, TRUE, TRUE),
  (2, 1, 15, 2, 2010, 2, 132, 3, TRUE, 4000.00, 4, NULL, 4, 4, TRUE, NULL, NULL, NULL),
  (0, 0, 9, 17, 1995, 12, 125, 0, TRUE, 2000.00, 2, 27, 19, 5, NULL, NULL, NULL, NULL),
  (6, 1, 8, 6, 1990, 6, 140, 0, FALSE, 9000.00, 8, NULL, 2, 5, FALSE, NULL, NULL, NULL),
  (3, 0, 5, 3, 1991, 3, 240, 0, TRUE, 7000.00, 5, 32, 24, 3, NULL, NULL, NULL, NULL),
  (7, 2, 11, 7, 1992, 7, 160, 1, TRUE, 12000.00, 9, NULL, NULL, NULL, NULL, 20, FALSE, TRUE);
INSERT INTO PUBLIC.VH_ORDER (ID, CLIENT_ID, VEHICLE_ID, DATE_START, DAYS_COUNT, DATE_ORDERED, SUM, STATUS) VALUES
  (15, 0, 11, DATE '2014-10-02', 2, TIMESTAMP '2014-10-02 09:20:50.0', 30000.00, 2),
  (11, 0, 9, DATE '2014-10-05', 10, TIMESTAMP '2014-10-02 08:09:04.0', 45000.00, 2),
  (10, 0, 17, DATE '2014-10-05', 10, TIMESTAMP '2014-10-02 08:06:15.0', 20000.00, 2),
  (14, 0, 13, DATE '2014-10-07', 4, TIMESTAMP '2014-10-02 08:58:01.0', 200000.00, 2),
  (13, 0, 16, DATE '2014-10-07', 10, TIMESTAMP '2014-10-02 08:31:42.0', 80000.00, 2),
  (12, 0, 12, DATE '2014-10-05', 4, TIMESTAMP '2014-10-02 08:15:46.0', 20000.00, 2),
  (48, 0, 0, DATE '2014-10-08', 8, TIMESTAMP '2014-10-06 12:42:06.0', 16000.00, 0),
  (50, 0, 12, DATE '2015-01-20', 5, TIMESTAMP '2015-01-12 11:06:35.0', 25000.00, 0),
  (49, 0, 17, DATE '2014-12-18', 200, TIMESTAMP '2014-12-10 10:36:40.0', 400000.00, 3),
  (16, 0, 16, DATE '2014-10-05', 6, TIMESTAMP '2014-10-05 03:39:39.0', 48000.00, 1),
  (17, 0, 6, DATE '2014-10-17', 7, TIMESTAMP '2014-10-05 06:29:38.0', 56000.00, 1),
  (9, 0, 0, DATE '2014-10-06', 7, TIMESTAMP '2014-10-02 08:02:23.0', 14000.00, 1);