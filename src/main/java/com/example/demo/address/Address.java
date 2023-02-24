package com.example.demo.address;


import com.example.demo.student.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity(name = "Address")
@Table(name = "address")
public class Address {

    @Id
    @SequenceGenerator(
            name = "address_sequence",
            sequenceName = "address_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String street;

    private String number;

    @Column(nullable = false)
    private String city;
    private String country;

    @OneToOne(mappedBy = "address")
    @JsonBackReference
    private Student student;

    public Address() {
    }

    public Address(Long id, String street, String number, String city, String country, Student student) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.student = student;
    }

    public Address(String street, String number, String city, String country, Student student) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", student=" + student +
                '}';
    }
}
