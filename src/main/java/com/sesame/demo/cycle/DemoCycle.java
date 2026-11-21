package com.sesame.demo.cycle;
import com.sesame.demo.commun.Outils; import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class DemoCycle { public static void main(String[] args){Outils.silence();System.out.println("Demarrage...");try(AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(Config.class)){
 System.out.println("Conteneur pret"); Compteur a=ctx.getBean(Compteur.class),b=ctx.getBean(Compteur.class);System.out.println("a == b : "+(a==b));a.incrementer();a.incrementer();System.out.println("Valeur lue via b : "+b.incrementer());
 Ticket t1=ctx.getBean(Ticket.class),t2=ctx.getBean(Ticket.class);System.out.println("t1 == t2 : "+(t1==t2));System.out.println("Fermeture du conteneur...");}System.out.println("Termine");}
}
