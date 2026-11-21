package com.sesame.demo.cycle;
import jakarta.annotation.*; import org.springframework.stereotype.Component;
@Component public class Compteur { private int valeur; public int incrementer(){return ++valeur;} @PostConstruct void initialiser(){System.out.println("Compteur : initialisation");} @PreDestroy void nettoyer(){System.out.println("Compteur : destruction");} }
