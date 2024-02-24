package com.rocketseat.certification_NLW.modules.students.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.certification_NLW.modules.students.dto.VerifyHasCertificationDTO;
import com.rocketseat.certification_NLW.modules.students.repositories.CertificationStudentRepository;

/*
    Os Services são responsáveis pela lógica de negócio da sua aplicação, 
    além de ser responsável por se comunicar com as camadas mais internas do Software, 
    como por exemplo, uma camada de Dados.
*/

@Service
public class VerifyIfHasCertificationUseCase {
    
    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public boolean execute(VerifyHasCertificationDTO dto) {
        var result = this.certificationStudentRepository.findByStudentEmailAndTechnology(dto.getEmail(), dto.getTechnology());
        if (!result.isEmpty()) {
            return true;
        }
        return false;
    }
}
