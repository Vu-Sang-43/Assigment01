package com.HSF301.service;

import com.HSF301.pojo.Student;
import com.HSF301.repository.StudentRepository;
import com.HSF301.repository.StudentRepositoryImpl;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentRepository isStudentRepo = null;

    public StudentServiceImpl(String fileName) {
        isStudentRepo = new StudentRepositoryImpl(fileName);
    }

    @Override
    public List<Student> findAll() {
        return isStudentRepo.findAll();
    }

    @Override
    public void save(Student student) {
        isStudentRepo.save(student);
    }

    @Override
    public void update(Student student) {
        isStudentRepo.update(student);
    }

    @Override
    public Student findById(int studentId) {
        return isStudentRepo.findById(studentId);
    }

    @Override
    public void delete(int studentId) {
        isStudentRepo.delete(studentId);
    }

    @Override
    public void addSubjectToStudent(int studentId, int subjectId) {
        isStudentRepo.addSubjectToStudent(studentId, subjectId);
    }

}
