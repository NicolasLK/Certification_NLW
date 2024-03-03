package com.rocketseat.certification_NLW.modules.students.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.certification_NLW.modules.questions.entities.QuestionEntity;
import com.rocketseat.certification_NLW.modules.questions.repositories.QuestionRepository;
import com.rocketseat.certification_NLW.modules.students.dto.StudentCertificationAnswerDTO;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private QuestionRepository questionRepository;

    public StudentCertificationAnswerDTO execute(StudentCertificationAnswerDTO dto) {
        // Busca as alternativas das perguntas
        // - Corretas e incorretas
        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());

        dto.getQuestionsAnswers().stream().forEach(questionAnswer -> {
           var question = questionsEntity.stream().filter(
            q -> q.getId().equals(questionAnswer.getQuestionID())).findFirst().get();

            var findCorrectAlternative = question.getAlternatives().stream().filter(
                alternative -> alternative.isCorrect()).findFirst().get();

            if(findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())) {
                questionAnswer.setCorrect(true);
            } else {
                questionAnswer.setCorrect(false);
            }
        });
        return dto;

        // Salva as informacoes da certificacao
    }
    
}
