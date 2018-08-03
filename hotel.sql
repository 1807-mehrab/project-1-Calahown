Create Table Accounts (
    a_id Integer Constraint a_id_pk Primary Key,
    pass VarChar2(32),
    username VarChar2(32) Unique,
    email VarChar2(100) Unique,
    is_host Integer check (is_host = '1' or is_host = '0')
);

Create Table Rooms (
    r_id Integer Constraint r_id_pk Primary Key,
    roomtype VarChar2(20),
    a_id Integer,
    roomstate Integer,
    image VarChar2(1000),
    Constraint a_id_FK Foreign Key (a_id) references accounts(a_id)
);

Create or Replace function isHost (x in integer) return number as z number;
Begin
    Select is_host into z from Accounts where a_id = x;
    return z;
End;
/

Create Table Issues (
    i_id Integer Constraint i_id_pk Primary Key,
    reportmessage VARCHAR2(200),
    r_id Integer,
    reporter_id Integer,
    responder_id Integer,
    status Integer Check (status = 0 or status = 1),
    responsemessage VarChar2(1000)
);

Alter Table Issues add Constraint reporter_aid_fk Foreign Key (reporter_id) references accounts(a_id);
Alter Table Issues add Constraint responder_aid_fk Foreign Key (responder_id) references accounts(a_id);

Create Table Reservations (
    resv_id Integer Constraint resv_id_pk Primary Key,
    a_id Integer,
    r_id Integer,
    checkin date,
    checkout date,
    approve Integer,
    Constraint resv_a_id_fk Foreign Key (a_id) references accounts(a_id),
    Constraint resv_r_id_fk Foreign Key (r_id) references rooms(r_id)
);

Create Sequence SQ_accounts_PK Increment by 1;
Create Sequence SQ_issues_PK Increment by 1;
Create Sequence SQ_reservations_PK Increment by 1;


create or replace trigger TR_Insert_accounts
Before insert on accounts
for each row
Begin
    Select SQ_accounts_PK.NEXTVAL INTO :NEW.a_id FROM dual;
END;
/

create or replace trigger TR_Insert_issues
Before insert on issues
for each row
Begin
    Select SQ_issues_PK.NEXTVAL Into :new.i_id FROM dual;
End;
/

create or replace trigger TR_Insert_reservations
Before insert on reservations
for each row
Begin
    Select SQ_reservations_PK.NEXTVAL Into :new.resv_id FROM dual;
End;
/

Insert Into Accounts (username, pass, is_host, email) Values ('Admin', 'p4ssw0rd', 1, 'test@test.com');
Insert Into Rooms (r_id, roomtype, a_id, roomstate, image) values (101, 'Single', null, 1 , null); 
Insert Into Rooms (r_id, roomtype, a_id, roomstate, image) values (102, 'Single', null, 2 , null); 
Insert Into Rooms (r_id, roomtype, a_id, roomstate, image) values (201, 'Double', null, 1 , null); 
Insert Into Rooms (r_id, roomtype, a_id, roomstate, image) values (202, 'Double', null, 2 , null); 
Insert Into Rooms (r_id, roomtype, a_id, roomstate, image) values (301, 'Suite', null, 1 , null); 
Insert Into Rooms (r_id, roomtype, a_id, roomstate, image) values (303, 'Suite', null, 2 , null); 
Insert Into Issues (reportmessage, r_id, reporter_id, responder_id, status, responsemessage) values ('test', 101, 1, 1, 0, 'test');

commit;