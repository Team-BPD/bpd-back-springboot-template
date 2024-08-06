/* -------------------- consumer 데이터 insert -------------------- */
INSERT INTO `consumer` (id, name, age, money)
SELECT 'psh', '상훈', 33, 10000000
FROM dual
WHERE NOT EXISTS(SELECT * FROM `consumer` WHERE id = 'psh');

INSERT INTO `consumer` (id, name, age, money)
SELECT 'ohj', '현종', 29, 50000000
FROM dual
WHERE NOT EXISTS(SELECT * FROM `consumer` WHERE id = 'ohj');

INSERT INTO `consumer` (id, name, age, money)
SELECT 'kyh', '윤하', 28, 25000000
FROM dual
WHERE NOT EXISTS(SELECT * FROM `consumer` WHERE id = 'kyh');

INSERT INTO `consumer` (id, name, age, money)
SELECT 'lyg', '유경', 27, 100000000
FROM dual
WHERE NOT EXISTS(SELECT * FROM `consumer` WHERE id = 'lyg');
/* -------------------------------------------------------------- */

/* ------------------ client system 데이터 insert ----------------- */
INSERT INTO `client_system` (id, password, comment)
SELECT 'GREENNET', '1234', '그린넷 시스템'
FROM dual
WHERE NOT EXISTS(SELECT * FROM `client_system` WHERE id = 'GREENNET');

INSERT INTO `client_system` (id, password, comment)
SELECT 'D-SALES', '1234', 'D-SALES 시스템'
FROM dual
WHERE NOT EXISTS(SELECT * FROM `client_system` WHERE id = 'D-SALES');
/* -------------------------------------------------------------- */

/* ---------------------- fruit 데이터 insert --------------------- */
INSERT INTO `fruit` (id, name, price)
SELECT 'apple', '사과', 1000
FROM dual
WHERE NOT EXISTS(SELECT * FROM `fruit` WHERE id = 'apple');

INSERT INTO `fruit` (id, name, price)
SELECT 'orange', '오렌지', 2000
FROM dual
WHERE NOT EXISTS(SELECT * FROM `fruit` WHERE id = 'orange');

INSERT INTO `fruit` (id, name, price)
SELECT 'melon', '멜론', 5000
FROM dual
WHERE NOT EXISTS(SELECT * FROM `fruit` WHERE id = 'melon');

INSERT INTO `fruit` (id, name, price)
SELECT 'banana', '바나나', 2000
FROM dual
WHERE NOT EXISTS(SELECT * FROM `fruit` WHERE id = 'banana');
/* -------------------------------------------------------------- */
