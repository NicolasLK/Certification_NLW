package com.rocketseat.certification_NLW.modules.students.entities;

/*
Uma entidade tem uma ou mais propriedades nomeadas, que podem ter um ou mais valores. 
Entidades do mesmo tipo não precisam ter as mesmas propriedades. 
*/

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {
    
    private UUID id;
    private String email;
    private List<CertificationStudentEntity> certificationStudentEntity;
}
