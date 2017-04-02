create table EMPLOYEE10 (
   id INT NOT NULL,
   first_name VARCHAR(20) default NULL,
   last_name  VARCHAR(20) default NULL,
   salary     INT  default NULL,
   PRIMARY KEY (id)
);

create table CERTIFICATE6 (
   id INT NOT NULL,
   certificate_name VARCHAR(30) default NULL,
   PRIMARY KEY (id)
);

create table EMP_CERT (
   employee_id INT NOT NULL,
   certificate_id INT NOT NULL,
   PRIMARY KEY (employee_id,certificate_id)
);