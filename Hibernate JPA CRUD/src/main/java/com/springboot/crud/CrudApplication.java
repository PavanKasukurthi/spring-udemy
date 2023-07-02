package com.springboot.crud;

import com.springboot.crud.dao.StudentDAO;
import com.springboot.crud.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
//			createStudent(studentDAO);
			createMultipleStudents(studentDAO);
		};
	}

	private void createMultipleStudents(StudentDAO studentDAO){
		//create multiple students
		System.out.println("Creating 3 student object...");
		Student tempStudent1 = new Student("Joey", "Tribbiani", "joeyt@gmail.com");
		Student tempStudent2 = new Student("Chandler", "Bing", "chandlerbing@gmail.com");
		Student tempStudent3 = new Student("Ross", "Geller", "rossgeller@gmail.com");

		// save the students
		System.out.println("Saving the students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	};

	private void createStudent(StudentDAO studentDAO) {

		//create student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Jake", "Peralta", "jakeperalta99@gmail.com");

		//save student object
		System.out.println("Saving the student object");
		studentDAO.save(tempStudent);

		//display id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}

}
