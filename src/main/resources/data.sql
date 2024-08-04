/* -------------------- consumer 데이터 insert -------------------- */
INSERT INTO `consumer` (id, name, age, money)
SELECT 'sh.park', '상훈', 33, 10000000
FROM dual
WHERE NOT EXISTS(SELECT * FROM `consumer` WHERE id = 'sh.park');

INSERT INTO `consumer` (id, name, age, money)
SELECT 'hj.oh', '현종', 29, 50000000
FROM dual
WHERE NOT EXISTS(SELECT * FROM `consumer` WHERE id = 'hj.oh');

INSERT INTO `consumer` (id, name, age, money)
SELECT 'yh.kim', '윤하', 28, 25000000
FROM dual
WHERE NOT EXISTS(SELECT * FROM `consumer` WHERE id = 'yh.kim');

INSERT INTO `consumer` (id, name, age, money)
SELECT 'yg.lee', '유경', 27, 100000000
FROM dual
WHERE NOT EXISTS(SELECT * FROM `consumer` WHERE id = 'yg.lee');
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
