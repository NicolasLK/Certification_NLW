package com.rocketseat.certification_NLW.modules.students.controllers;

/* 
    O Controller é responsável por receber todas as requisições do usuário 
*/

import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.certification_NLW.modules.students.dto.StudentCertificationAnswerDTO;
import com.rocketseat.certification_NLW.modules.students.dto.VerifyHasCertificationDTO;
import com.rocketseat.certification_NLW.modules.students.useCases.StudentCertificationAnswersUseCase;
import com.rocketseat.certification_NLW.modules.students.useCases.VerifyIfHasCertificationUseCase;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/students")
public class StudentController {

    // Utilizando o UseCase
    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    @Autowired
    private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

    @PostMapping("/verifyIfHasCertification")    
    public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO VerifyHasCertificationDTO) {
        // Email
        // Technology
        var result = this.verifyIfHasCertificationUseCase.execute(VerifyHasCertificationDTO);
        if (result) {
            return "Usuario ja realizou a prova";
        }
        return "Usuario pode fazer a prova";
    }

    @PostMapping("/certification/answer")
    public ResponseEntity<Object> certificationAnswer(
        @RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO) {
            try {
                var result = studentCertificationAnswersUseCase.execute(studentCertificationAnswerDTO);
                return ResponseEntity.ok().body(result);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        
    }
    
}
