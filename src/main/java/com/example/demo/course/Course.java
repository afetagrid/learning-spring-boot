package com.example.demo.course;

import com.example.demo.student.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Course")
@Table(name = "courses")
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "courses")
    @JsonBackReference
    private List<Student> students;

    public Course() {
    }

    public Course(Long id, String name, List<Student> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }

    public Course(String name, List<Student> students) {
        this.name = name;
        this.students = students;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
