package com.sarthak.springcoredemo.rest;

import com.sarthak.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    //define private field for the dependency
    private Coach myCoach;
    private Coach anotherCoach;
    @Autowired
    public DemoController(@Qualifier("cricketCoach") Coach theCoach,
                          @Qualifier("cricketCoach") Coach theAnotherCoach){
        System.out.println("In constructor : " + getClass().getSimpleName());
        myCoach = theCoach;
        anotherCoach = theAnotherCoach;
    }

    @GetMapping("/check")
    public String check(){
        return "Comparing beans : myCoach == anotherCoach, " + (myCoach == anotherCoach);
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }
}
