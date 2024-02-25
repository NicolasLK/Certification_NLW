package com.rocketseat.certification_NLW.modules.questions.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.certification_NLW.modules.questions.dto.AlternativesResultDTO;
import com.rocketseat.certification_NLW.modules.questions.dto.QuestionResultDTO;
import com.rocketseat.certification_NLW.modules.questions.entities.AlternativesEntity;
import com.rocketseat.certification_NLW.modules.questions.entities.QuestionEntity;
import com.rocketseat.certification_NLW.modules.questions.repositories.QuestionRepository;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {
        var result = this.questionRepository.findByTechnology(technology);
        
        var toMap = result.stream().map(question -> mapQuestiontoDTO(question)).collect(Collectors.toList());
        return toMap;
    }

    static QuestionResultDTO mapQuestiontoDTO(QuestionEntity question) {
        var questionResultDTO = QuestionResultDTO.builder()
        .id(question.getId())
        .technology(question.getTechnology())
        .description(question.getDescription()).build();

        List<AlternativesResultDTO> alternativesResultDTOs = question.getAlternatives()
        .stream().map(alternative -> mapAlternativeDTO(alternative))
        .collect(Collectors.toList());

        questionResultDTO.setAlternatives(alternativesResultDTOs);
        return questionResultDTO;
    }

    static AlternativesResultDTO mapAlternativeDTO(AlternativesEntity alternativesResultDTO) {
        return AlternativesResultDTO.builder()
        .id(alternativesResultDTO.getId())
        .description(alternativesResultDTO.getDescription()).build();
    }
}
