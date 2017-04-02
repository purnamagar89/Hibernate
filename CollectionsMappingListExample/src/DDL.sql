create table EMPLOYEE3 (
   id INT NOT NULL,
   firstName VARCHAR(20) default NULL,
   lastName  VARCHAR(20) default NULL,
   salary INT  default NULL,
   PRIMARY KEY (id)
);

create table CERTIFICATE (
   id INT NOT NULL,
   certificate_name VARCHAR(30) default NULL,
   idx INT default NULL, 
   employee_id INT default NULL,
   PRIMARY KEY (id)
);