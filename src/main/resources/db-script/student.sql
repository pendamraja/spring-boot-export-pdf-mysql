CREATE DATABASE exportpdf;
use exportpdf;
CREATE TABLE Student (
    student_id int NOT NULL AUTO_INCREMENT,
    student_name varchar(255) NOT NULL,
    age int,
    college varchar(350),
    address varchar(350),
    phone_no varchar(12),
    email varchar(50),
    PRIMARY KEY (student_id)
);

ALTER TABLE Student AUTO_INCREMENT=100;

INSERT INTO Student (student_name,age,college,address, phone_no,email) VALUES ('Sathvik',13,'VNR','Hyderabad','9966645870', 'sathvik@gmail.com');

INSERT INTO Student (student_name,age,college,address, phone_no,email) VALUES ('Nandhini',10,'STMARTINS','Hyderabad','9634145879', 'nandhini@gmail.com');



