package com.sesame.demo.ioc;
import com.sesame.demo.commun.*;

public class AvecIoC {
    static class EmpruntService {
        private final Notificateur notificateur;
        EmpruntService(Notificateur notificateur) { this.notificateur = notificateur; }
        void emprunter(String etudiant, String livre) {
            System.out.println("Emprunt enregistre : " + livre);
            notificateur.envoyer(etudiant, "Vous avez emprunte " + livre);
        }
    }
    public static void main(String[] args) {
        EmpruntService parEmail = new EmpruntService(new EmailNotificateur());
        EmpruntService parSms = new EmpruntService(new SmsNotificateur());
        parEmail.emprunter("amine@sesame.tn", "Clean Code");
        parSms.emprunter("amine@sesame.tn", "Clean Code");
    }
}
