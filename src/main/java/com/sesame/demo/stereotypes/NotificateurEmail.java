package com.sesame.demo.stereotypes;
import com.sesame.demo.commun.Notificateur; import org.springframework.stereotype.Component;
@Component public class NotificateurEmail implements Notificateur { @Override public void envoyer(String d,String m){System.out.println("[EMAIL] vers "+d+" : "+m);} }
