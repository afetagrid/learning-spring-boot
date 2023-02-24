package com.example.demo.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public void addNewSchool(School school) {
        if (schoolRepository.findSchoolByName(school.getName()).isPresent()) {
            throw new IllegalStateException("School already exists");
        } else {
            schoolRepository.save(school);
        }
    }

    public List<School> getSchools() {
        return schoolRepository.findAll();
    }
}
