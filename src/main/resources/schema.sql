CREATE TABLE IF NOT EXISTS `consumer`
(
    id    VARCHAR(255) NOT NULL,
    name  VARCHAR(255) NOT NULL,
    age   BIGINT       NOT NULL,
    money BIGINT DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `client_system`
(
    id       VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    comment  VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `FRUIT`
(
    id    VARCHAR(255) NOT NULL,
    name  VARCHAR(255) NOT NULL,
    price BIGINT       NOT NULL,
    PRIMARY KEY (id)
);
