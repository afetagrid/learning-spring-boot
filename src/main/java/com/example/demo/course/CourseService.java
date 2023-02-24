package com.example.demo.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public void addNewCourse(Course course) {
        if (courseRepository.findCourseByName(course.getName()).isPresent()) {
            throw new IllegalStateException("Course already exists");
        } else {
            courseRepository.save(course);
        }
    }
}
