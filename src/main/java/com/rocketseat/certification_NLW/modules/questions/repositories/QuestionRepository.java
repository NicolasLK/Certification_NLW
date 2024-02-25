package com.rocketseat.certification_NLW.modules.questions.repositories;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocketseat.certification_NLW.modules.questions.entities.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID>{
    
    List<QuestionEntity> findByTechnology(String technology);
}
