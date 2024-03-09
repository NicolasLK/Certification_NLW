package com.rocketseat.certification_NLW.modules.students.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "AnswersCertificationsStudents")
public class AnswersCertificationsEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "Certification_id")
    private UUID certificationID;

    @ManyToOne
    @JoinColumn(name = "Certification_id", insertable = false, updatable = false)
    @JsonBackReference
    private CertificationStudentEntity certificationStudentEntity;

    @Column(name = "Student_id")
    private UUID studentID;

    @ManyToOne
    @JoinColumn(name = "Student_id", insertable = false, updatable = false)
    private StudentEntity studentEntity;

    @Column(name = "Question_id")
    private UUID questionID;

    @Column(name = "Answer_id")
    private UUID answerID;

    @Column(name = "Is_correct")
    private Boolean isCorrect;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
