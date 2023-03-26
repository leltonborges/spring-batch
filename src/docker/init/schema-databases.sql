create schema batch collate utf8_general_ci;
create schema project collate utf8_general_ci;

create user if not exists project
    identified by 'secret123';
grant all privileges on batch.* to project;
grant all privileges on project.* to project;

CREATE SEQUENCE `project`.SQ_CLIENT INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE `project`.SQ_TRANSACTION INCREMENT BY 1 START WITH 1;

CREATE TABLE `project`.TB_CLIENT
(
    SQ_CLIENT    BIGINT DEFAULT NEXTVAL(`project`.`SQ_CLIENT`) NOT NULL,
    ST_NAME      VARCHAR(255)                                  NULL,
    ST_LAST_NAME VARCHAR(255)                                  NULL,
    NU_AGE       INT                                           NULL,
    ST_EMAIL     VARCHAR(255)                                  NULL,
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
    SQ_CLIENT      BIGINT   NOT NULL,
    SQ_TRANSACTION BIGINT   NOT NULL,
    DF_CREATED     DATETIME NULL,
    CONSTRAINT PK_TB_CLIENT_TRANSACTION PRIMARY KEY (SQ_CLIENT, SQ_TRANSACTION),
    CONSTRAINT FK_TB_CLIENT_TRANSACTION_ON_SQ_CLIENT FOREIGN KEY (SQ_CLIENT)
        REFERENCES TB_CLIENT(SQ_CLIENT),
    CONSTRAINT FK_TB_CLIENT_TRANSACTION_ON_SQ_TRANSACTION FOREIGN KEY (SQ_TRANSACTION)
        REFERENCES TB_TRANSACTION (SQ_TRANSACTION)
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