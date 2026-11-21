package com.sesame.demo.injection;
import com.sesame.demo.commun.Notificateur; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.stereotype.Service;
@Service public class ServiceSetter { private Notificateur notificateur; @Autowired public void setNotificateur(Notificateur n){this.notificateur=n;} public String decrire(){return getClass().getSimpleName()+" utilise "+notificateur.getClass().getSimpleName();} }
