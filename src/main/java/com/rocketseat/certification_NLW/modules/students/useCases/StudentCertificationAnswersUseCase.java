package com.rocketseat.certification_NLW.modules.students.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.certification_NLW.modules.questions.entities.QuestionEntity;
import com.rocketseat.certification_NLW.modules.questions.repositories.QuestionRepository;
import com.rocketseat.certification_NLW.modules.students.dto.StudentCertificationAnswerDTO;
import com.rocketseat.certification_NLW.modules.students.dto.VerifyHasCertificationDTO;
import com.rocketseat.certification_NLW.modules.students.entities.AnswersCertificationsEntity;
import com.rocketseat.certification_NLW.modules.students.entities.CertificationStudentEntity;
import com.rocketseat.certification_NLW.modules.students.entities.StudentEntity;
import com.rocketseat.certification_NLW.modules.students.repositories.CertificationStudentRepository;
import com.rocketseat.certification_NLW.modules.students.repositories.StudentRepository;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto) throws Exception {

        var hasCertification = this.verifyIfHasCertificationUseCase.execute(new VerifyHasCertificationDTO(dto.getEmail(), dto.getTechnology()));

        if(hasCertification) {
            throw new Exception("Voce ja possui certificacao!");
        }

        // Busca as alternativas das perguntas
        // - Corretas e incorretas
        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());
        List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();

        AtomicInteger correctAnswers = new AtomicInteger(0);

        dto.getQuestionsAnswers().stream().forEach(questionAnswer -> {
           var question = questionsEntity.stream().filter(
            q -> q.getId().equals(questionAnswer.getQuestionID())).findFirst().get();

            var findCorrectAlternative = question.getAlternatives().stream().filter(
                alternative -> alternative.isCorrect()).findFirst().get();

            if(findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())) {
                questionAnswer.setCorrect(true);
                correctAnswers.incrementAndGet();
            } else {
                questionAnswer.setCorrect(false);
            }

            var answersCertificationEntity = AnswersCertificationsEntity.builder()
            .answerID(questionAnswer.getAlternativeID())
            .questionID(questionAnswer.getQuestionID())
            .isCorrect(questionAnswer.isCorrect())
            .build();

            answersCertifications.add(answersCertificationEntity);
        });

        // Verificar se o Student existe pelo e-mail
        var student = studentRepository.findByEmail(dto.getEmail());
        UUID studentID;
        if(student.isEmpty()) {
            var studentCreated = StudentEntity.builder().email(dto.getEmail()).build(); 
            studentCreated = studentRepository.save(studentCreated);
            studentID = studentCreated.getId();
        } else {
            studentID = student.get().getId();
        }

        

        CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
        .technology(dto.getTechnology())
        .studentID(studentID)
        .grade(correctAnswers.get())
        .build();

       var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

       answersCertifications.stream().forEach(answersCertification -> {
        answersCertification.setCertificationID(certificationStudentEntity.getId());
        answersCertification.setCertificationStudentEntity(certificationStudentEntity);
       });

       certificationStudentEntity.setAnswersCertificationsEntities(answersCertifications);

       certificationStudentRepository.save(certificationStudentEntity);

        return certificationStudentCreated;

        // Salva as informacoes da certificacao
    }
    
}
