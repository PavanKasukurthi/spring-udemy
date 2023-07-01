package com.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    @Value("${hero.name}")
    private String heroName;

    @GetMapping("/")
    public String sayHello(){
        return heroName + " is the first super saiyan!";
    }

    @GetMapping("/saiyans")
    public String saiyans(){
        return heroName + " and Vegeta!!!";
    }

}
