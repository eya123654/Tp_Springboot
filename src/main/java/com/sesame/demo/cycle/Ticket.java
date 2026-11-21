package com.sesame.demo.cycle;
import jakarta.annotation.*; import org.springframework.context.annotation.Scope; import org.springframework.stereotype.Component;
@Component @Scope("prototype") public class Ticket { @PostConstruct void initialiser(){System.out.println("Ticket : cree");} @PreDestroy void nettoyer(){System.out.println("Ticket : detruit");} }
