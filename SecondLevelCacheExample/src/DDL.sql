create table EMPLOYEE20 (
   id INT NOT NULL,
   name VARCHAR(20) default NULL,   
   salary  INT  default NULL,
   PRIMARY KEY (id)
);

insert into EMPLOYEE20 (id,name,salary) values(100,'Raj',5000); 

insert into EMPLOYEE20 (id,name,salary) values(101,'Ravi',6000); 