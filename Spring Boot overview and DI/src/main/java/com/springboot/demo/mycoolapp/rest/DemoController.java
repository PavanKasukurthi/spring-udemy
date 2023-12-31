package com.springboot.demo.mycoolapp.rest;

import com.springboot.demo.mycoolapp.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Coach myCoach;

//    @Autowired
//    public void setMyCoach(Coach myCoach) {
//        this.myCoach = myCoach;
//    }

    @Autowired
    public DemoController(@Qualifier("aqua") Coach theCoach) {
        System.out.println("In constructor: " + getClass().getSimpleName());
        myCoach = theCoach;
    }

    @GetMapping("/getWorkout")
    private String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }


}
