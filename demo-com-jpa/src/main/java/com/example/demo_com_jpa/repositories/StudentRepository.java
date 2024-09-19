package com.example.demo_com_jpa.repositories;

import com.example.demo_com_jpa.entities.Student;
import com.example.demo_com_jpa.interfaces.IStudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentRepository implements IStudentRepository {
    private EntityManager entityManager;

    @Autowired
    public StudentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(Student student) {
        this.entityManager.persist(student);
    }

    @Override
    public Student findById(int id) {
        return this.entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        return entityManager
                .createQuery("select s from Student s ORDER BY s.firstName", Student.class)
                .getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = entityManager
                .createQuery("select s from Student s WHERE s.lastName LIKE :name", Student.class);

        query.setParameter("name", "%" + lastName + "%");

        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(int id, Student student) {
        Student studentInDb =  this.entityManager.find(Student.class, id);

        studentInDb.setFirstName(student.getFirstName());
        studentInDb.setLastName(student.getLastName());
        studentInDb.setEmail(student.getEmail());

        this.entityManager.merge(studentInDb);
    }

    @Override
    public void deleteById(int id) {
        Query query = entityManager
                .createQuery("delete from Student s WHERE s.id = :id", Student.class);

        query.setParameter("id", id);

        query.executeUpdate();
    }

    @Override
    public void deleteAll() {
        Query query = entityManager
                .createQuery("delete from Student s", Student.class);

        query.executeUpdate();
    }
}
