package or.samir.metier;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import or.samir.Entity.Compte;
import or.samir.Entity.CompteCourant;
import or.samir.Entity.Operation;
import or.samir.Entity.Retrait;
import or.samir.Entity.Versement;
import or.samir.dao.CompteRepository;

import or.samir.dao.OperationRepository;
@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {

	    @Autowired
	   	private CompteRepository compteRepository;
	    @Autowired
	   	private OperationRepository operationRepository;
	@Override
	public Compte ConsulterCompte(String codeCompte) {
		// TODO Auto-generated method stub
		Compte cpt1=compteRepository.findOne(codeCompte);
		if(cpt1==null) throw new RuntimeException("compte introuvable");
		return cpt1;
	}

	@Override
	public void verser(String codeCompte, double montant) {
		// TODO Auto-generated method stub
		Compte c1=ConsulterCompte(codeCompte);
		operationRepository.save(new Versement(new Date(), montant, c1));
		c1.setSolde(c1.getSolde()+montant);
		compteRepository.save(c1);
	}

	@Override
	public void retirer(String codeCompte, double montant) {
		// TODO Auto-generated method stub
		Compte c1=ConsulterCompte(codeCompte);
		double faciliteCaisse=0;
		if( c1 instanceof CompteCourant) 
			 faciliteCaisse=((CompteCourant) c1).getDecouvert();
		if(c1.getSolde()+ faciliteCaisse<montant)  throw new RuntimeException("solde inssuffisant decouvert depasse");
		
		operationRepository.save(new Retrait(new Date(), montant, c1));
	}

	@Override
	public void virment(String codeCompte1, String codeCompte2, double montant) {
		// TODO Auto-generated method stub
	
	if(codeCompte1.equals(codeCompte2))	 throw new RuntimeException("Virement impossible deux compte identique");
	retirer(codeCompte1,montant);
	verser(codeCompte2,montant);
	}

	
	@Override
	public Page<Operation> listOperation(String codeCompte, int page, int size) {
		// TODO Auto-generated method stub
	
		return operationRepository.listOperation(codeCompte,new PageRequest(page,size));
	}

}
