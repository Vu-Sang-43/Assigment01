package com.HSF301.repository;

import com.HSF301.pojo.Student;

import java.util.List;

public interface StudentRepository {
    public void save(Student student);

    public void delete(int studentId);

    public Student findById(int studentId);

    public void update(Student student);

    public List<Student> findAll();

    public void addSubjectToStudent(int studentId, int subjectId);
}
