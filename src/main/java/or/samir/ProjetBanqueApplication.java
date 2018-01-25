package or.samir;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import or.samir.Entity.Compte;
import or.samir.Entity.CompteCourant;
import or.samir.Entity.CompteEpargne;
import or.samir.Entity.Customer;
import or.samir.Entity.Retrait;
import or.samir.Entity.Versement;
import or.samir.dao.CompteRepository;
import or.samir.dao.CustomerRepository;
import or.samir.dao.OperationRepository;

@SpringBootApplication
public class ProjetBanqueApplication implements CommandLineRunner{
    @Autowired
	private CustomerRepository customerRepository;
    @Autowired
   	private CompteRepository compteRepository;
    @Autowired
   	private OperationRepository operationRepository;
	public static void main(String[] args) {
		
		SpringApplication.run(ProjetBanqueApplication.class, args);
		/*ApplicationContext ctx=SpringApplication.run(ProjetBanqueApplication.class, args);
		CustomerRepository cl=ctx.getBean(CustomerRepository.class);
		cl.save(new Customer ("samir","samir@yahoo.ca"));*/
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
	Customer c1=customerRepository.save(new Customer ("samir","samir@yahoo.ca"));
	Customer c2=customerRepository.save(new Customer ("johanne","johanne@yahoo.com"));
	Compte cpt1=compteRepository.save(new CompteEpargne("c1",new Date(),9000,c1,5.5));
	Compte cpt2=compteRepository.save(new CompteCourant("c2",new Date(),9000,c2,6000));
	operationRepository.save(new Versement(new Date(), 6000, cpt1));
	operationRepository.save(new Versement(new Date(), 9000, cpt1));
	operationRepository.save(new Versement(new Date(), 2300, cpt1));
	operationRepository.save(new Retrait(new Date(), 9000, cpt1));
	operationRepository.save(new Versement(new Date(), 6000, cpt2));
	operationRepository.save(new Versement(new Date(), 9000, cpt2));
	operationRepository.save(new Versement(new Date(), 2300, cpt2));
	
	operationRepository.save(new Retrait(new Date(), 9000, cpt2));
	
	}
}
