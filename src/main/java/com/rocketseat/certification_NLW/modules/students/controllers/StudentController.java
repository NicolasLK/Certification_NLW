package com.rocketseat.certification_NLW.modules.students.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.certification_NLW.modules.students.dto.VerifyHasCertificationDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/students")
public class StudentController {

    @PostMapping("/verifyIfHasCertification")    
    public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO VerifyHasCertificationDTO) {
        
        System.out.println(VerifyHasCertificationDTO);
        return "Usuario";
    }
    
}
