package dev.patika.clients;

import dev.patika.controller.StudentController;
import dev.patika.models.*;
import dev.patika.models.enums.Gender;
import dev.patika.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //saveToDatabase();
        List<Student> returnedList = new StudentController().findAllStudents();
        for (Student student: returnedList) {
            System.out.println(student);
        }

    }
    private static void saveToDatabase(){
        //Data
        Student student1 = new Student("Ayse GELIR", LocalDate.of(1959,10,23),"AyseAddress", Gender.FEMALE);
        Student student2 = new Student("Ali GELIR", LocalDate.of(1969,8,15),"AliAddress", Gender.MALE);
        Student student3 = new Student("Veli GELMEZ", LocalDate.of(1979,2,5),"VeliAddress", Gender.MALE);
        Student student4 = new Student("Feyza GELMEZ ", LocalDate.of(1989,11,29),"FeyzaAddress", Gender.FEMALE);

        Course course1 = new Course("Math",15590,5);
        Course course2 = new Course("Piano",11250,4);
        Course course3 = new Course("Physics",16540,4);

        Instructor visitingResearcher1 = new VisitingResearcher("Gokce YAZAR","GokceAddress","05345001010",100);
        Instructor visitingResearcher2 = new VisitingResearcher("Bilge CIZER","BilgeAddress","05345001010",90);
        Instructor permanentInstructor1 = new PermanentInstructor("Tuncay SOYLER","TuncayAddress","05377003030",120);


        //Relationships
        course1.setInstructor(visitingResearcher1);
        course2.setInstructor(visitingResearcher2);
        course3.setInstructor(permanentInstructor1);

        student1.getCourses().add(course1);
        student1.getCourses().add(course2);
        student2.getCourses().add(course3);
        student3.getCourses().add(course3);
        student4.getCourses().add(course1);
        student4.getCourses().add(course3);


        //DataPersistence
        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");
        try {
            em.getTransaction().begin();
            em.persist(student1);
            em.persist(student2);
            em.persist(student3);
            em.persist(student4);

            em.persist(course1);
            em.persist(course2);
            em.persist(course3);

            em.persist(visitingResearcher1);
            em.persist(visitingResearcher2);
            em.persist(permanentInstructor1);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }

    }
}
