package com.tushar.cruddemo;

import com.tushar.cruddemo.dao.StudentDAO;
import com.tushar.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
//			createStudent(studentDAO);
			
//			readStudent(studentDAO);

//			queryForStudents(studentDAO);

			queryForStudentByLastName(studentDAO);
		};
	}

	private void queryForStudentByLastName(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findByLastName("last1");

		// display the list
		for (Student tempStudent: theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findAll();

		// display the list
		for (Student tempStudent: theStudents){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {

		// create the student object
		System.out.println("Creating new student object");
		Student tempStudent = new Student("second", "last", "secondAndLast@email.com");
		// save the student object

		System.out.println("Saving the student");
		studentDAO.save(tempStudent);

		// display id of the saved student
		System.out.println("Saved student. Generated Id: " + tempStudent.getId());
		int id = tempStudent.getId();
		// display student
		Student savedStudent = studentDAO.findById(id);
		System.out.println("Found the student: " + savedStudent);
	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating new student object");
		Student tempStudent = new Student("second", "last", "secondAndLast@email.com");
		// save the student object

		System.out.println("Saving the student");
		studentDAO.save(tempStudent);

		// display id of the saved student
		System.out.println("Saved student. Generated Id: " + tempStudent.getId());
		
	}
}
