package com.rocketseat.certification_NLW.modules.questions.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.rocketseat.certification_NLW.modules.questions.entities.QuestionEntity;
import com.rocketseat.certification_NLW.modules.questions.repositories.QuestionRepository;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/* 
    O Controller é responsável por receber todas as requisições do usuário 
*/

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;
 
    @GetMapping("/technology/{technology}")    
    public List<QuestionEntity> findByTechnology(@PathVariable String technology) {
        return this.questionRepository.findByTechnology(technology);
    }
}
