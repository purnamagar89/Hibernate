/**Create the database*/
create schema hibernate_annotations_hql_1;

/**Create the employee table*/
create table hibernate_annotations_hql_1.tbl_employee
(
emp_id int,
emp_name varchar(30),
emp_addr varchar(30),
emp_salary double
);

/**Create the anotheremployee table*/
create table hibernate_annotations_hql_1.tbl_anotheremployee
(
emp_id int,
emp_name varchar(30),
emp_addr varchar(30),
emp_salary double
);

/**Insert some records*/
insert into hibernate_annotations_hql_1.tbl_employee values(1,'Ankur','Plano',10000.33);
insert into hibernate_annotations_hql_1.tbl_employee values(2,'Praveen','Irving',8888.67);
insert into hibernate_annotations_hql_1.tbl_employee values(3,'Gopal','Allen',2345.44);

/**Select all records*/
select * from hibernate_annotations_hql_1.tbl_employee;

