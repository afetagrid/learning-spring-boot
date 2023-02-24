package com.example.demo.student;

import com.example.demo.course.Course;
import com.example.demo.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("Student with id " + studentId + " doesn't exist");
        }
        studentRepository.deleteById(studentId);

    }

    @Transactional
    public void updateStudent(Long studentId, String studentName, String studentEmail) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " doesn't exist"));
        if (studentName != null &&
            studentName.length() > 0 &&
            !Objects.equals(studentName, student.getName())) {
            student.setName(studentName);
        }
        if (studentEmail != null &&
                studentEmail.length() > 0 &&
                !Objects.equals(studentEmail, student.getEmail())) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(studentEmail);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            student.setEmail(studentEmail);
        }
    }

    @Transactional
    public void assignCourseToStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " doesn't exist"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException("Course with id " + courseId + " doesn't exists"));

        List <Course> courses = student.getCourses();
        courses.add(course);
        student.setCourses(courses);
    }
}
