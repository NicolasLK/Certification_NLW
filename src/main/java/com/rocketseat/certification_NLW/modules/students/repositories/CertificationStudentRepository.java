package com.rocketseat.certification_NLW.modules.students.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rocketseat.certification_NLW.modules.students.entities.CertificationStudentEntity;

/* Repository
 * 
 * Camada responsavel por interagir com o banco.
 * 
*/

@Repository
public interface CertificationStudentRepository extends JpaRepository<CertificationStudentEntity, UUID>{

    @Query("SELECT c FROM Certifications c INNER JOIN c.studentEntity std WHERE std.email = :email AND c.technology = :technology")
    List<CertificationStudentEntity> findByStudentEmailAndTechnology(String email, String technology);

    @Query("SELECT c FROM Certifications c ORDER BY c.grade DESC LIMIT 10")
    List<CertificationStudentEntity> findTop10ByOrderByGradeDesc();
    
}
