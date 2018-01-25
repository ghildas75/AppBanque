package or.samir.metier;

import org.springframework.data.domain.Page;

import or.samir.Entity.Compte;
import or.samir.Entity.Operation;

public interface IBanqueMetier {
public Compte ConsulterCompte(String codeCompte);
public void verser(String codeCompte, double montant);
public void retirer(String codeCompte, double montant);
public void virment(String codeCompte1,String codeCompte2, double montant);
public Page<Operation> listOperation(String codeCompte,int page, int size);

}
