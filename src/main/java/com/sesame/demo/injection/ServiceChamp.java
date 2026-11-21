package com.sesame.demo.injection;
import com.sesame.demo.commun.Notificateur; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.stereotype.Service;
@Service public class ServiceChamp { @Autowired private Notificateur notificateur; public ServiceChamp(){} public String decrire(){return getClass().getSimpleName()+" utilise "+notificateur.getClass().getSimpleName();} }
