package com.sesame.demo.declaration;
import com.sesame.demo.commun.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class DemoDeclaration {
 public static void main(String[] args){ Outils.silence(); try(AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(Config.class)){
  System.out.println("Beans : "+Outils.beans(ctx));
  ctx.getBean("notificateur",Notificateur.class).envoyer("amine@sesame.tn","Bienvenue");
  ctx.getBean("alerteSms",Notificateur.class).envoyer("amine@sesame.tn","Bienvenue");
 }}
}
