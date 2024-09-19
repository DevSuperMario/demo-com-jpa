package com.example.demo_com_jpa;

import com.example.demo_com_jpa.entities.Student;
import com.example.demo_com_jpa.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoComJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoComJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return runner -> {
			saveStudent(studentRepository);
			getStudent(1, studentRepository);
			getAllStudents(studentRepository);
			getByLastName("So", studentRepository);
			updateStudent(2, studentRepository);
		};
	}

	private void saveStudent(StudentRepository studentRepository) {
		Student student = new Student("Primero nome test", "Sobrenome teste", "email teste");

		studentRepository.save(student);
	}

	private void getStudent(int id, StudentRepository studentRepository) {
		Student student = studentRepository.findById(id);

		System.out.println(student);
	}

	private void getAllStudents(StudentRepository studentRepository) {
		List<Student> students = studentRepository.findAll();

		students.forEach(s -> {
			System.out.println(s);
		});
	}

	private void getByLastName(String lastName, StudentRepository studentRepository) {
		List<Student> students = studentRepository.findByLastName(lastName);

		students.forEach(s -> {
			System.out.println(s);
		});
	}

	private void updateStudent(int id, StudentRepository studentRepository) {
		Student student = new Student("Jefté", "Goes", "teste@gmail.com");

		studentRepository.update(id, student);
	}

}
