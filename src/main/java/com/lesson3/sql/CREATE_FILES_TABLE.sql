CREATE TABLE FILES (
    ID NUMBER NOT NULL ENABLE,
    CONSTRAINT FILE_PK PRIMARY KEY (ID),
    NAME NVARCHAR2(20) NOT NULL,
    FORMAT NVARCHAR2(10) NOT NULL,
    FILE_SIZE NUMBER,
    STORAGE_ID NUMBER,
    CONSTRAINT FILE_STORAGE_FK FOREIGN KEY(STORAGE_ID) REFERENCES STORAGE(ID)
);