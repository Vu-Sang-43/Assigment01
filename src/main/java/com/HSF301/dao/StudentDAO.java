package com.HSF301.dao;

import com.HSF301.pojo.Student;
import com.HSF301.pojo.Subject;
import jakarta.persistence.*;

import java.util.List;

public class StudentDAO {
    private final EntityManagerFactory emf;

    public StudentDAO(String fileConfig) {
        // Change from "default" to "JPAs" to match persistence.xml
        this.emf = Persistence.createEntityManagerFactory("JPAs");
    }

    public void save(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void update(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(int studentId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, studentId);
            if (student != null) {
                em.remove(student);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public Student findById(int studentId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT s FROM Student s LEFT JOIN FETCH s.subjects WHERE s.id = :id",
                    Student.class
            );
            query.setParameter("id", studentId);
            try {
                return query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        } finally {
            em.close();
        }
    }

    public List<Student> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Student> query = em.createQuery(
                    "SELECT DISTINCT s FROM Student s LEFT JOIN FETCH s.subjects",
                    Student.class
            );
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
    public void addSubjectToStudent(int studentId, int subjectId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Student student = em.find(Student.class, studentId);
            Subject subject = em.find(Subject.class, subjectId);

            if (student != null && subject != null) {
                subject.setStudent(student);
                subject.setStudentName(student.getFirstname() + " " + student.getLastname());
                student.getSubjects().add(subject);

                em.merge(student);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}