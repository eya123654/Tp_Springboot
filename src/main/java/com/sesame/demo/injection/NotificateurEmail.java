package com.sesame.demo.injection;
import com.sesame.demo.commun.Notificateur; import org.springframework.context.annotation.Primary; import org.springframework.stereotype.Component;
@Component @Primary public class NotificateurEmail implements Notificateur { @Override public void envoyer(String d,String m){System.out.println("[EMAIL] vers "+d+" : "+m);} }
