package com.sesame.demo.stereotypes;
import java.util.*; import org.springframework.stereotype.Repository;
@Repository public class EmpruntRepository { private final List<String> emprunts=new ArrayList<>(); public void ajouter(String livre){emprunts.add(livre);} public int nombre(){return emprunts.size();} }
