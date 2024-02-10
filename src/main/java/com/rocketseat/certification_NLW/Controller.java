package com.rocketseat.certification_NLW;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/Controller")
public class Controller {

    @GetMapping("/retornoController")
    public Usuario retornoController() {
        var user = new Usuario("Nicolas", 22);
        return user;
    }

    @PostMapping("/myFirstPOST")
    public String firstPost() {
        return "first POST";
    }
    
    
    
   record Usuario(String nome, int idade) {}
    
}
