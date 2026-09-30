package com.sarthak.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {
    public CricketCoach(){
        System.out.println("In constructor : " + getClass().getSimpleName());
    }

    //define our init method
    @PostConstruct
    public void doStartupStuff(){
        System.out.println("in doStartupStuff() : " + getClass().getSimpleName());
    }
    //define our destroy method
    @PreDestroy
    public void doCleanupStuff(){
        System.out.println("in doCleanupStuff() : " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes : ) ";
    }
}
