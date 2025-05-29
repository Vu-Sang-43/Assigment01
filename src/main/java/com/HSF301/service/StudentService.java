package com.HSF301.service;

import com.HSF301.pojo.Student;

import java.util.List;

public interface StudentService {
    public List<Student> findAll();

    public void save(Student student);

    public void update(Student student);

    public Student findById(int studentId);

    public void delete(int studentId);

    public void addSubjectToStudent(int studentId, int subjectId);
}
