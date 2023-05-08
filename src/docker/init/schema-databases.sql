create schema batch collate utf8_general_ci;
create schema project collate utf8_general_ci;

create
user if not exists project
    identified by 'secret123';
grant all privileges on batch.* to project;
grant all privileges on project.* to project;

CREATE SEQUENCE `project`.SQ_CLIENT INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE `project`.SQ_TRANSACTION INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE `project`.`SQ_PHONE` INCREMENT BY 1 START WITH 1;

CREATE TABLE `project`.TB_CLIENT
(
    SQ_CLIENT    BIGINT DEFAULT NEXTVAL(`project`.`SQ_CLIENT`) NOT NULL,
    ST_NAME      VARCHAR(255) NULL,
    ST_LAST_NAME VARCHAR(255) NULL,
    NU_AGE       INT NULL,
    ST_EMAIL     VARCHAR(255) NULL,
    CONSTRAINT PK_TB_CLIENT PRIMARY KEY (SQ_CLIENT)
);

CREATE TABLE `project`.TB_TRANSACTION
(
    SQ_TRANSACTION    BIGINT DEFAULT NEXTVAL(`project`.`SQ_TRANSACTION`) NOT NULL,
    NU_ID_TRANSACTION VARCHAR(255)                                       NULL,
    ST_DESCRIPTION    VARCHAR(255)                                       NULL,
    VL_AMOUNT         DECIMAL                                            NULL,
    CONSTRAINT PK_TB_TRANSACTION PRIMARY KEY (SQ_TRANSACTION),
    CONSTRAINT UC_TB_TRANSACTION_NU_ID_TRANSACTION UNIQUE (NU_ID_TRANSACTION)
);

CREATE TABLE `project`.TB_CLIENT_TRANSACTION
(
    SQ_CLIENT      BIGINT NOT NULL,
    SQ_TRANSACTION BIGINT NOT NULL,
    DF_CREATED     DATETIME NULL,
    CONSTRAINT PK_TB_CLIENT_TRANSACTION PRIMARY KEY (SQ_CLIENT, SQ_TRANSACTION),
    CONSTRAINT FK_TB_CLIENT_TRANSACTION_ON_SQ_CLIENT FOREIGN KEY (SQ_CLIENT)
        REFERENCES TB_CLIENT (SQ_CLIENT),
    CONSTRAINT FK_TB_CLIENT_TRANSACTION_ON_SQ_TRANSACTION FOREIGN KEY (SQ_TRANSACTION)
        REFERENCES TB_TRANSACTION (SQ_TRANSACTION)
);

CREATE TABLE `project`.TB_PHONE
(
    SQ_PHONE       BIGINT DEFAULT NEXTVAL(`project`.`SQ_PHONE`) NOT NULL PRIMARY KEY,
    NUM_DD         INT(2) NOT NULL,
    NUM_PHONE      INT(9) NOT NULL,
    CHIP           ENUM('1', '2') NOT NULL,
    DS_DESCRIPTION VARCHAR(255),
    ST_CHIP        ENUM('0', '1', '2') NOT NULL,
    CONSTRAINT AK_TELEFONE_UNIQUE UNIQUE (NUM_DD, NUM_PHONE)
);

INSERT INTO project.TB_CLIENT (SQ_CLIENT, ST_NAME, ST_LAST_NAME, NU_AGE, ST_EMAIL)
VALUES (DEFAULT, 'Fulano0', 'De Tal0', 34, 'fulano0@example.com'),
       (DEFAULT, 'Fulano1', 'De Tal1', 34, 'fulano1@example.com'),
       (DEFAULT, 'Fulano2', 'De Tal2', 34, 'fulano2@example.com'),
       (DEFAULT, 'Fulano3', 'De Tal3', 34, 'fulano3@example.com'),
       (DEFAULT, 'Fulano4', 'De Tal4', 34, 'fulano4@example.com'),
       (DEFAULT, 'Fulano5', 'De Tal5', 34, 'fulano5@example.com'),
       (DEFAULT, 'Fulano6', 'De Tal6', 34, 'fulano6@example.com'),
       (DEFAULT, 'Fulano6', 'De Tal6', 34, 'fulano6@example.com'),
       (DEFAULT, 'Fulano7', 'De Tal7', 34, 'fulano7@example.com'),
       (DEFAULT, 'Fulano8', 'De Tal8', 34, 'fulano8@example.com'),
       (DEFAULT, 'Fulano9', 'De Tal9', 34, 'fulano9@example.com');

INSERT INTO project.TB_PHONE (SQ_PHONE, NUM_DD, NUM_PHONE, CHIP, DS_DESCRIPTION, ST_CHIP)
VALUES (DEFAULT, 61, 982010454, '1', 'TIM', '1'),
       (DEFAULT, 89, 984616605, '2', 'OI', '2'),
       (DEFAULT, 61, 949840504, '2', 'TIM', '0'),
       (DEFAULT, 89, 987410103, '1', 'CLARO', '2'),
       (DEFAULT, 61, 998794010, '2', 'VIVO', '1'),
       (DEFAULT, 62, 986061041, '1', 'CLARO', '0'),
       (DEFAULT, 61, 986540132, '1', 'OI', '1'),
       (DEFAULT, 62, 988560132, '1', 'TIM', '2'),
       (DEFAULT, 89, 998401041, '2', 'VIVO', '1'),
       (DEFAULT, 61, 996010610, '1', 'TIM', '0'),
       (DEFAULT, 62, 989085016, '1', 'OI', '0'),
       (DEFAULT, 61, 987930104, '2', 'VIVO', '1'),
       (DEFAULT, 11, 980846061, '1', 'CLARO', '1'),
       (DEFAULT, 11, 970700606, '1', 'CLARO', '2'),
       (DEFAULT, 61, 994014061, '2', 'TIM', '1'),
       (DEFAULT, 86, 909480949, '2', 'OI', '2'),
       (DEFAULT, 86, 904094046, '2', 'OI', '1');
