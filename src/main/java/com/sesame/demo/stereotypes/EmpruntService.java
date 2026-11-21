package com.sesame.demo.stereotypes;
import com.sesame.demo.commun.Notificateur; import org.springframework.stereotype.Service;
@Service public class EmpruntService { private final EmpruntRepository repository; private final Notificateur notificateur;
 public EmpruntService(EmpruntRepository repository,Notificateur notificateur){this.repository=repository;this.notificateur=notificateur;}
 public void emprunter(String etudiant,String livre){repository.ajouter(livre);System.out.println("Emprunt enregistre : "+livre+" ("+repository.nombre()+" emprunt(s) en memoire)");notificateur.envoyer(etudiant,"Vous avez emprunte "+livre);}
}
