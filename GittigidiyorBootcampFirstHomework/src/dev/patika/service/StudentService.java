package dev.patika.service;

import dev.patika.models.Student;
import dev.patika.repository.CrudRepository;
import dev.patika.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentService implements CrudRepository<Student>{

    EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");
    @Override
    public List<Student> findAll() {
        return em.createQuery("FROM Student", Student.class).getResultList();
    }

    @Override
    public Student findById(int id) {
        return em.find(Student.class, id);
    }

    @Override
    public void saveToDatabase(Student student) {
        em.persist(student);
    }

    @Override
    public void deleteFromDatabase(int id) {
        em.getTransaction();
        Student student = em.createQuery("FROM Student s WHERE s.id=:id", Student.class).setParameter("id",id).getSingleResult();
        em.remove(student);
        em.getTransaction().commit();
    }

    @Override
    public void updateOnDatabase(Student object, int id) {

    }
}
