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

CREATE TABLE `project`.TB_CLIENTE_TRANSACTION
(
    SQ_CLIENT      BIGINT   NOT NULL,
    SQ_TRANSACTION BIGINT   NOT NULL,
    DF_CREATED     DATETIME NULL,
    CONSTRAINT PK_TB_CLIENTE_TRANSACTION PRIMARY KEY (SQ_CLIENT, SQ_TRANSACTION),
    CONSTRAINT FK_TB_CLIENTE_TRANSACTION_ON_SQ_CLIENT FOREIGN KEY (SQ_CLIENT)
        REFERENCES TB_CLIENT(SQ_CLIENT),
    CONSTRAINT FK_TB_CLIENTE_TRANSACTION_ON_SQ_TRANSACTION FOREIGN KEY (SQ_TRANSACTION)
        REFERENCES TB_TRANSACTION (SQ_TRANSACTION)
);