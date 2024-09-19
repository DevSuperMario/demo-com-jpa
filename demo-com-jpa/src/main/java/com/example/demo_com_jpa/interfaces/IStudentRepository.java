package com.example.demo_com_jpa.interfaces;

import com.example.demo_com_jpa.entities.Student;

import java.util.List;

public interface IStudentRepository {
    void save(Student student);

    Student findById(int id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    void update(int id, Student student);

    void deleteById(int id);
    void deleteAll();
}
