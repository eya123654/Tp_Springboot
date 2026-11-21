package com.sesame.demo.injection;
public class DemoHorsSpring { public static void main(String[] args){
 ServiceConstructeur a=new ServiceConstructeur(new NotificateurEmail()); System.out.println("Constructeur : "+a.decrire());
 ServiceChamp b=new ServiceChamp(); try{System.out.println("Champ : "+b.decrire());}catch(NullPointerException e){System.out.println("Champ : NullPointerException");}
}}
