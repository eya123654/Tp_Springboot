package com.sesame.demo.injection;
import com.sesame.demo.commun.Notificateur; import org.springframework.stereotype.Component;
@Component public class NotificateurSms implements Notificateur { @Override public void envoyer(String d,String m){System.out.println("[SMS] vers "+d+" : "+m);} }
