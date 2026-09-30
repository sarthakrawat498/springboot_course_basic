package com.sarthak.springcoredemo.config;

import com.sarthak.springcoredemo.common.Coach;
import com.sarthak.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    @Bean("aquatic")
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
