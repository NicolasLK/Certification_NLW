package com.rocketseat.certification_NLW.modules.students.entities;

/*
Uma entidade tem uma ou mais propriedades nomeadas, que podem ter um ou mais valores. 
Entidades do mesmo tipo não precisam ter as mesmas propriedades. 
*/

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Students")
public class StudentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    /* 
     * OneToOne
     * OneToMany
     * ManyToOne
     * ManyToMany
    */

    @OneToMany(mappedBy = "studentEntity")
    private List<CertificationStudentEntity> certificationStudentEntity;
}
