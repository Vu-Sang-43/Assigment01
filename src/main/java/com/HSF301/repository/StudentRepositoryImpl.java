package com.HSF301.repository;

import com.HSF301.dao.StudentDAO;
import com.HSF301.pojo.Student;

import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    private StudentDAO studentDAO = null;

    public StudentRepositoryImpl(String fileConfig) {
        studentDAO = new StudentDAO(fileConfig);
    }

    @Override
    public void save(Student student) {
        studentDAO.save(student);
    }

    @Override
    public void delete(int studentId) {
        studentDAO.delete(studentId);
    }

    @Override
    public Student findById(int studentId) {
        return studentDAO.findById(studentId);
    }

    @Override
    public void update(Student student) {
        studentDAO.update(student);
    }

    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public void addSubjectToStudent(int studentId, int subjectId) {
        studentDAO.addSubjectToStudent(studentId, subjectId);
    }

}
