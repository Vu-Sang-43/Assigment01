package com.HSF301.pojo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Subject")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "Name_Subject", nullable = false,  length = 30)
    private String Name_Subject;

    @Column(name = "student_name", length = 50)
    private String studentName;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
    public Subject(String nameSubject, String studentName) {
        this.Name_Subject = nameSubject;
        this.studentName = studentName;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_Subject() {
        return Name_Subject;
    }

    public void setName_Subject(String name_Subject) {
        Name_Subject = name_Subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}