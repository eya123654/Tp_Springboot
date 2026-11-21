package com.sesame.demo.declaration;
import com.sesame.demo.commun.*;
import org.springframework.context.annotation.*;
@Configuration
public class Config {
 @Bean public Notificateur notificateur(){ return new EmailNotificateur(); }
 @Bean(name="alerteSms") public Notificateur sms(){ return new SmsNotificateur(); }
}
