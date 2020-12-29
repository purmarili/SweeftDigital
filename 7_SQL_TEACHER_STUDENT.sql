drop schema if exists school;
create schema school;
use school;
drop table if exists teacher;
drop table if exists subject;
drop table if exists pupil;
drop table if exists class;
drop table if exists class_subject;
drop table if exists class_teacher;
drop table if exists teacher_subject;

create table teacher (
	id int primary key not null,
    name varchar(100) not null,
    surname varchar(100) not null,
    gender varchar(100) not null
);

create table subject (
	id int primary key not null,
    name varchar(100) not null
);

create table pupil (
	id int primary key not null,
    name varchar(100) not null,
    surname varchar(100) not null,
    gender varchar(100) not null,
    class int
);

create table class (
	id int primary key,
    name varchar(100)
);

create table class_subject (
	class_id int not null,
    subject_id int not null
);

create table class_teacher (
	class_id int not null,
    teacher_id int not null
);

create table teacher_subject (
	teacher_id int not null,
    subject_id int not null
);

insert into teacher (id, name, surname, gender)
values (1, "first_teacher", "first_teacher_surname", "male");
insert into teacher (id, name, surname, gender)
values (2, "second_teacher", "second_teacher_surname", "female");
insert into teacher (id, name, surname, gender)
values (3, "third_teacher", "third_teacher_surname", "male");
insert into teacher (id, name, surname, gender)
values (4, "fourth_teacher", "fourth_teacher_surname", "male");
insert into teacher (id, name, surname, gender)
values (5, "fifth_teacher", "fifth_teacher_surname", "female");
 
insert into pupil (id, name, surname, gender, class)
values (1, "giorgi", "nadzaladevi", "male", 2);
insert into pupil (id, name, surname, gender, class)
values (2, "second_pupil", "second_pupil_surname", "female", 4);
insert into pupil (id, name, surname, gender, class)
values (3, "third_pupil", "third_pupil_surname", "male", 1);
insert into pupil (id, name, surname, gender, class)
values (4, "giorgi", "guramishvili", "male", 4);
insert into pupil (id, name, surname, gender, class)
values (5, "fifth_pupil", "fifth_pupil_surname", "female", 2);
insert into pupil (id, name, surname, gender, class)
values (6, "sixth_pupil", "sixth_pupil_surname", "male", 1);
insert into pupil (id, name, surname, gender, class)
values (7, "seventh_pupil", "seventh_pupil_surname", "female", 3);
insert into pupil (id, name, surname, gender, class)
values (8, "giorgi", "sarajishvili", "male", 5);
insert into pupil (id, name, surname, gender, class)
values (9, "nineth_pupil", "nineth_pupil_surname", "male", 6);
insert into pupil (id, name, surname, gender, class)
values (10, "tenth_pupil", "tenth_pupil_surname", "female", 7);

insert into subject (id, name)
values (1, "first_subject");
insert into subject (id, name)
values (2, "second_subject");
insert into subject (id, name)
values (3, "third_subject");
insert into subject (id, name)
values (4, "fourth_subject");
insert into subject (id, name)
values (5, "fifth_subject");
insert into subject (id, name)
values (6, "sixth_subject");

insert into class (id, name)
values (1, "first_class");
insert into class (id, name)
values (2, "second_class");
insert into class (id, name)
values (3, "third_class");
insert into class (id, name)
values (4, "fourth_class");
insert into class (id, name)
values (5, "fifth_class");
insert into class (id, name)
values (6, "sixth_class");
insert into class (id, name)
values (7, "seventh_class");

insert into class_teacher (class_id, teacher_id)
values (1, 2);
insert into class_teacher (class_id, teacher_id)
values (1, 3);
insert into class_teacher (class_id, teacher_id)
values (1, 6);
insert into class_teacher (class_id, teacher_id)
values (2, 1);
insert into class_teacher (class_id, teacher_id)
values (3, 2);
insert into class_teacher (class_id, teacher_id)
values (4, 5);
insert into class_teacher (class_id, teacher_id)
values (5, 2);
insert into class_teacher (class_id, teacher_id)
values (5, 3);
insert into class_teacher (class_id, teacher_id)
values (6, 2);
insert into class_teacher (class_id, teacher_id)
values (6, 4);
insert into class_teacher (class_id, teacher_id)
values (7, 1);
insert into class_teacher (class_id, teacher_id)
values (7, 2);

insert into teacher_subject (teacher_id, subject_id)
values (1, 3);
insert into teacher_subject (teacher_id, subject_id)
values (1, 4);
insert into teacher_subject (teacher_id, subject_id)
values (1, 1);
insert into teacher_subject (teacher_id, subject_id)
values (2, 5);
insert into teacher_subject (teacher_id, subject_id)
values (3, 6);
insert into teacher_subject (teacher_id, subject_id)
values (4, 2);
insert into teacher_subject (teacher_id, subject_id)
values (5, 2);
insert into teacher_subject (teacher_id, subject_id)
values (5, 1);
insert into teacher_subject (teacher_id, subject_id)
values (4, 5);
insert into teacher_subject (teacher_id, subject_id)
values (1, 5);
insert into teacher_subject (teacher_id, subject_id)
values (4, 1);


select t.id, t.name, t.surname, t.gender
from teacher as t
right outer join class_teacher as ct
on ct.teacher_id = t.id
right outer join pupil as p
on p.class = ct.class_id
where p.name = "giorgi"