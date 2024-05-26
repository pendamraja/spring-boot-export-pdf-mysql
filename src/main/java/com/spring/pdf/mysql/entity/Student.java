package com.spring.pdf.mysql.entity;


import jakarta.persistence.*;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int student_id;

    @Column(name= "student_name")
    private String studentName;

    @Column(name = "age")
    private int age;

    @Column(name = "college")
    private String college;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "email")
    private String email;
}
