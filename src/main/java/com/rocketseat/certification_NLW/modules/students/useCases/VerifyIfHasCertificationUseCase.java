package com.rocketseat.certification_NLW.modules.students.useCases;

import org.springframework.stereotype.Service;

import com.rocketseat.certification_NLW.modules.students.dto.VerifyHasCertificationDTO;

/*
    Os Services são responsáveis pela lógica de negócio da sua aplicação, 
    além de ser responsável por se comunicar com as camadas mais internas do Software, 
    como por exemplo, uma camada de Dados.
*/

@Service
public class VerifyIfHasCertificationUseCase {
    
    public boolean execute(VerifyHasCertificationDTO dto) {
        if (dto.getEmail().equals("nicolas.loffi@gmail.com") && dto.getTechnology().equals("Java")) {
            return true;
        }
        return false;
    }
}
