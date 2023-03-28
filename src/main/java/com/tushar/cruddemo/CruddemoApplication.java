package com.tushar.cruddemo;

import com.tushar.cruddemo.dao.StudentRepository;
import com.tushar.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentRepository studentRepository){
		return runner -> {
//			createStudent(studentRepository);
			
//			readStudent(studentRepository);

//			queryForStudents(studentRepository);

//			queryForStudentByLastName(studentRepository);

//			updateStudent(studentRepository);

//			deleteStudent(studentRepository);

//			deleteAllStudents(studentRepository);
		};
	}

	private void deleteAllStudents(StudentRepository studentRepository) {
		System.out.println("All rows deleted");
		studentRepository.deleteAll();
	}

	private void deleteStudent(StudentRepository studentRepository) {
		int studentId = 2;
//		Student myStudent = studentRepository.findById(studentId);

		// change last name to "Scooby"
		System.out.println("Deleting student");
		Optional<Student> student = studentRepository.findById(studentId);
		if(!student.isEmpty()){
			studentRepository.delete(student.get());
		}
		else {
			System.out.println("No Such student");
		}
		// delete the student

	}

	private void updateStudent(StudentRepository studentRepository) {
		// retrieve student based on the id: primary key
		int studentId = 1;
		Optional<Student> myStudentOptional = studentRepository.findById(studentId);
		if(myStudentOptional.isEmpty())
			return;
		Student myStudent = myStudentOptional.get();
		// change last name to "Scooby"
		System.out.println("Updatting student");
		myStudent.setLastName("Scooby");

		// update the student
		studentRepository.save(myStudent);

		// display the student
		System.out.println(myStudent);
	}

//	private void queryForStudentByLastName(StudentRepository studentRepository) {
//		List<Student> theStudents = studentRepository.f("last1");
//
//		// display the list
//		for (Student tempStudent: theStudents){
//			System.out.println(tempStudent);
//		}
//	}

	private void queryForStudents(StudentRepository studentRepository) {
		// get a list of students
		List<Student> theStudents = studentRepository.findAll();

		// display the list
		for (Student tempStudent: theStudents){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentRepository studentRepository) {

		// create the student object
		System.out.println("Creating new student object");
		Student tempStudent = new Student("second", "last", "secondAndLast@email.com");
		// save the student object

		System.out.println("Saving the student");
		studentRepository.save(tempStudent);

		// display id of the saved student
		System.out.println("Saved student. Generated Id: " + tempStudent.getId());
		int id = tempStudent.getId();
		// display student
		Optional<Student> savedStudent = studentRepository.findById(id);
		if(savedStudent.isEmpty())
			return;
		System.out.println("Found the student: " + savedStudent.get());
	}

	private void createStudent(StudentRepository studentRepository) {
		// create the student object
		System.out.println("Creating new student object");
		Student tempStudent = new Student("Tushar", "Gupta", "TusharGupta@email.com");
		// save the student object

		System.out.println("Saving the student");
		studentRepository.save(tempStudent);

		// display id of the saved student
		System.out.println("Saved student. Generated Id: " + tempStudent.getId());
		
	}
}
