package com.sesame.demo.stereotypes;
import com.sesame.demo.commun.Outils; import java.lang.annotation.Annotation; import java.util.Arrays;
import org.springframework.beans.BeansException; import org.springframework.context.annotation.AnnotationConfigApplicationContext; import org.springframework.stereotype.*;
public class DemoStereotypes {
 public static void main(String[] args){Outils.silence();try(AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(Config.class)){
  System.out.println("Beans : "+Outils.beans(ctx)); afficher(ctx,"@Controller",Controller.class);afficher(ctx,"@Service   ",Service.class);afficher(ctx,"@Repository",Repository.class);
  System.out.println("@Service est une @Component : "+Service.class.isAnnotationPresent(Component.class)); ctx.getBean(EmpruntController.class).demander("amine@sesame.tn","Clean Code");
 }catch(BeansException e){System.out.println("Erreur : "+e.getClass().getSimpleName());System.out.println(e.getMostSpecificCause().getMessage());}}
 static void afficher(AnnotationConfigApplicationContext ctx,String e,Class<? extends Annotation> a){System.out.println(e+" : "+Arrays.toString(ctx.getBeanNamesForAnnotation(a)));}
}
