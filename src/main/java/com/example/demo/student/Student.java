package com.example.demo.student;

import com.example.demo.student.address.Address;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity(name = "Student")
@Table(
        name = "students",
        uniqueConstraints = {@UniqueConstraint(name = "student_email_unique_constraint", columnNames = "email")}
)
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "email",
            nullable = false
//            unique = true
    )
    private String email;

    @Column(
            name = "date_of_birth",
            nullable = false
    )
    private LocalDate dob;

    @Transient
    private Integer age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_address_id")
    private Address address;


    public Student() {
    }

    public Student(Long id, String name, String email, LocalDate dob, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.address = address;
    }

    public Student(String name, String email, LocalDate dob, Address address) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.address = address;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
