CREATE TABLE PLANE (
    ID NUMBER,
    CONSTRAINT PLANE_PK PRIMARY KEY(ID),
    MODEL NVARCHAR2(50),
    CODE NVARCHAR2(20),
    YEAR_PRODUCED TIMESTAMP,
    AVG_FUEL_CONSUMPTION NUMBER(*, 2)
);

CREATE SEQUENCE PLANE_SEQ MINVALUE 1 START WITH 1 INCREMENT BY 1;