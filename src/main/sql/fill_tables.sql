﻿INSERT INTO MANUFACTURER VALUES (0, 'Audi');
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

INSERT INTO USER VALUES(0, 'Иван', 'Петров', '1985-10-21', 'user', '111', 'Vanya@gmail.com', 'CLIENT', '0');
INSERT INTO USER VALUES(1, 'Павел', 'Бобылев', '1986-09-15', 'admin', '111', 'Logr@gmail.com', 'ADMIN', '0');
INSERT INTO USER VALUES(2, 'Влад', 'Цай', '1987-08-08', 'driver', '111', 'Vlad@gmail.com', 'DRIVER', '0');
INSERT INTO USER VALUES(3, 'Василий', 'Кузякин', '1987-09-19', 'DRIVER2', '456', 'Vasiliy@gmail.com', 'DRIVER', '0');
INSERT INTO USER VALUES(4, 'Петр', 'Сидорченко', '1976-05-07', 'DRIVER3', '456', 'Petr@gmail.com', 'DRIVER', '0');
INSERT INTO USER VALUES(5, 'Леонид', 'Ершов', '1970-04-04', 'DRIVER4', '456', 'Leonid@gmail.com', 'DRIVER', '0');
INSERT INTO USER VALUES(6, 'Дмитрий', 'Перов', '1978-12-06', 'DRIVER5', '456', 'Dmitriy@gmail.com', 'DRIVER', '0');
INSERT INTO USER VALUES(7, 'Денис', 'Егоров', '1972-02-28', 'DRIVER6', '456', 'Denis@gmail.com', 'DRIVER', '0');
INSERT INTO USER VALUES(8, 'Евгений', 'Солошенко', '1987-03-10', 'DRIVER7', '456', 'Evgeniy@gmail.com', 'DRIVER', '0');
INSERT INTO USER VALUES(9, 'Максим', 'Корейко', '1964-03-27', 'DRIVER8', '456', 'Maxim@gmail.com', 'DRIVER', '0');
INSERT INTO USER VALUES(10, 'Михаил', 'Афанасьев', '1968-10-17', 'DRIVER9', '456', 'Mikhail@gmail.com', 'DRIVER', '0');
INSERT INTO USER VALUES(11, 'Валентин', 'Граидзе', '1982-06-24', 'DRIVER10', '456', 'Valentin@gmail.com', 'DRIVER', '0');

INSERT INTO VEHICLE VALUES(0, 'CAR', 9, 0, 1995, 1, '122500', 'PETROL', TRUE, '2000', 2, NULL, 4, 4, TRUE, NULL, NULL, NULL);
INSERT INTO VEHICLE VALUES(1, 'CAR', 8, 1, 2002, 1, '80000', 'GAS', TRUE, '3000', 3, NULL, 5, 5, FALSE, NULL, NULL, NULL);
INSERT INTO VEHICLE VALUES(2, 'CAR', 7, 2, 2010, 2, '12500', 'GAS_PETROL', FALSE, '4000', 4, NULL, 4, 4, TRUE, NULL, NULL, NULL);
INSERT INTO VEHICLE VALUES(3, 'BUS', 6, 3, 1991, 3, '240000', 'PETROL', TRUE, '6000', 5, 32, 24, 3, NULL, NULL, NULL, NULL);
INSERT INTO VEHICLE VALUES(4, 'BUS', 5, 4, 1998, 4, '180500', 'DIESEL', FALSE, '8000', 6, 42, 38, 4, NULL, NULL, NULL, NULL);
INSERT INTO VEHICLE VALUES(5, 'BUS', 4, 5, 2000, 5, '93500', 'ELECTRICITY', TRUE, '5000', 7, 12, 10, 2, NULL, NULL, NULL, NULL);
INSERT INTO VEHICLE VALUES(6, 'TRUCK', 3, 6, 1990, 6, '140000', 'PETROL', TRUE, '8000', 8, NULL, NULL, NULL, NULL, '10', TRUE, FALSE);
INSERT INTO VEHICLE VALUES(7, 'TRUCK', 2, 7, 1992, 7, '160500', 'DIESEL', TRUE, '12000', 9, NULL, NULL, NULL, NULL, '20', FALSE, TRUE);
INSERT INTO VEHICLE VALUES(8, 'TRUCK', 1, 8, 1994, 8, '180000', 'PETROL', TRUE, '10000', 10, NULL, NULL, NULL, NULL, '15', TRUE, TRUE);
INSERT INTO VEHICLE VALUES(9, 'CAR', 0, 9, 2013, 9, '8500', 'PETROL', TRUE, '4500', 11, NULL, 4, 4, TRUE, NULL, NULL, NULL);