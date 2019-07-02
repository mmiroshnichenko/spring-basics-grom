CREATE TABLE ADVERTISEMENT (
    ID NUMBER,
    CONSTRAINT ADVERTISEMENT_PK PRIMARY KEY(ID),
    AUTHOR_ID NUMBER NOT NULL,
    CONSTRAINT USER_FK FOREIGN KEY(AUTHOR_ID) REFERENCES USERS(ID),
    TITLE NVARCHAR2(100),
    DESCRIPTION NVARCHAR2(200),
    CURRENCY NVARCHAR2(10),
    CATEGORY NVARCHAR2(50),
    SUBCATEGORY NVARCHAR2(50),
    CITY NVARCHAR2(50),
    PHONE NVARCHAR2(50),
    CREATION_TIME TIMESTAMP,
    EXPIRATION_TIME TIMESTAMP
);

CREATE SEQUENCE ADV_SEQ MINVALUE 1 START WITH 1 INCREMENT BY 1;