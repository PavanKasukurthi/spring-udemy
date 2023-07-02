package com.springboot.crud;

import com.springboot.crud.dao.StudentDAO;
import com.springboot.crud.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
//			createStudent(studentDAO);
//			createMultipleStudents(studentDAO);

//			readStudent(studentDAO);

//			queryForStudents(studentDAO);

			queryForStudentsByLastName(studentDAO);
		};

	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findByLastName("Geller");

		for(Student student: theStudents){
			System.out.println(student);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {

		//get a list of students
		List<Student> theStudents = studentDAO.findAll();

		//display list of students
		for (Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}


	private void readStudent(StudentDAO studentDAO) {
		//create a student object
		Student tempStudent4 = new Student("Phoebe", "Buffay", "phoebe@gmail.com");

		//save the student
		studentDAO.save(tempStudent4);

		//display id of the saved student
		System.out.println("Saved student id: " + tempStudent4.getId());

		//retrieve student based on the id
		System.out.println("Retrieving student with the given id: " + tempStudent4.getId());

		//display student
		System.out.println("Found the student: " + studentDAO.findById(tempStudent4.getId()));
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
