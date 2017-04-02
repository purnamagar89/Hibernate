create table EMPLOYEE7 (
   id INT NOT NULL,
   first_name VARCHAR(20) default NULL,
   last_name  VARCHAR(20) default NULL,
   salary     INT  default NULL,
   PRIMARY KEY (id)
);

create table CERTIFICATE4 (
   id INT NOT NULL,
   certificate_type VARCHAR(40) default NULL,
   certificate_name VARCHAR(30) default NULL,
   employee_id INT default NULL,
   PRIMARY KEY (id)
);