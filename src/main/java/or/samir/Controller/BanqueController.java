package or.samir.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import or.samir.Entity.Compte;
import or.samir.Entity.Operation;
import or.samir.metier.IBanqueMetier;

@Controller
public class BanqueController {
@Autowired
private IBanqueMetier banqMetier;
@RequestMapping ("/operations")
public String index() {
	
	return "Compte";
}
	
@RequestMapping ("/consultercompte")
public String ConsulterCompte(Model model,String codeCompte,@RequestParam(name="page",defaultValue="0") int page, 
		@RequestParam(name="size",defaultValue="5") int  size) {
try {
	model.addAttribute("codeCourant",codeCompte);
	Page<Operation> pages=banqMetier.listOperation(codeCompte, page, size);
	model.addAttribute("operations",pages.getContent() );
	int[] tabPages= new int[pages.getTotalPages()];
	model.addAttribute("pages", tabPages);
	Compte c1=	banqMetier.ConsulterCompte(codeCompte)	;
	model.addAttribute("compte",c1);
} catch (Exception e) {
	model.addAttribute("exception",e);
}	

return "Compte";

}
@RequestMapping(value="/saveOperation",method=RequestMethod.POST) 
 String saveOperation(Model model,String codeCompte, String typeOperation, String codeCompte2, double montant) {
try {

	if(typeOperation.equals("VERS")) {
	
	banqMetier.verser(codeCompte, montant);
}
else if(typeOperation.equals("RETR")) {
	
	banqMetier.retirer(codeCompte, montant);
}

else if(typeOperation.equals("VIR")) {
	
	banqMetier.virment(codeCompte, codeCompte2, montant);
}

	
} catch (Exception e) {
	// TODO: handle exception
	model.addAttribute("error", e);
	return "redirect:/consultercompte?codeCompte="+codeCompte+"&error="+e.getMessage();	
}
	
	return "redirect:/consultercompte?codeCompte="+codeCompte;	
}
}
