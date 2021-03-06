package com.songwie.hadoop;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import groovy.lang.Grab;
 
@EnableScheduling
@EnableWebMvc
@SpringBootApplication(scanBasePackages="com.songwie")
public class RootApplication {
    public static void main(String[] args) {
    	ConfigurableApplicationContext cac = SpringApplication.run(RootApplication.class, args);
        System.err.println("## songwie-hadoop startup success...");
        
        cac.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
            @Override
            public void onApplicationEvent(ContextClosedEvent event) {
                System.err.println("## songwie-hadoop ContextClosedEvent close hadoop");
            }
        });
    }
}
