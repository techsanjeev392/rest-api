package com.springboot3.restapis;

import com.springboot3.restapis.config.Config;
import com.springboot3.restapis.config.message.MessageService;
import com.springboot3.restapis.model.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class    RestApisApplication {

    //remove comment to run the application without using Spring's ApplicationContext

    public static void main(String[] args) {
        SpringApplication.run(RestApisApplication.class, args);
    }


    //remove comment to run the application using Spring's ApplicationContext and demonstrate dependency injection

//    public static void main(String[] args) {
//        SpringApplication.run(RestApisApplication.class, args);
//        ApplicationContext context =
//                new AnnotationConfigApplicationContext(Config.class);
//
//        MessageService service = context.getBean(MessageService.class);
//        service.sendMessage();
//        MessageService messageService=  new MessageService();
//        messageService.sendMessage();
//
//    }


    //remove comment to run the normal application without using Spring's ApplicationContext and demonstrate manual instantiation of MessageService
//    public static void main(String[] args) {
//        Person person = new Person("John Doe", 30);
//
//        System.out.println("Name: " + person.getName());
//        System.out.println("Age: " + person.getAge());
//
//    }

}
