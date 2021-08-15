package dev.patika.controller;

import dev.patika.models.Student;
import dev.patika.service.StudentService;

import java.util.List;

public class StudentController {

    private StudentService studentService = new StudentService();
    public Student findStudent(int studentId){
        return studentService.findById(studentId);
    }
    public List<Student> findAllStudents(){
        return studentService.findAll();
    }

    public void saveStudent(Student Student){
        studentService.saveToDatabase(Student);
    }


    public void deleteStudent(int id){
        studentService.deleteFromDatabase(id);
    }

    public void updateStudent(Student Student, int id){
        studentService.updateOnDatabase(Student, id);
    }

}
