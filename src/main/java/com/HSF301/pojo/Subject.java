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
}