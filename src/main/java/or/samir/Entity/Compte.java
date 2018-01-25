package or.samir.Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_COMPTE",discriminatorType=DiscriminatorType.STRING,length=2)
public abstract class Compte  implements Serializable{
@Id
private String codeCompte;
private Date dateCreation;
private double solde;
@ManyToOne
@JoinColumn(name="CODE_CLIENT")
private Customer customer;
@OneToMany(mappedBy="compte")

private Collection<Operation> operations;
public Compte() {
	super();
}
public Compte(String codeCompte, Date dateCreation, double solde, Customer customer) {
	super();
	this.codeCompte = codeCompte;
	this.dateCreation = dateCreation;
	this.solde = solde;
	this.customer = customer;
}
public String getCodeCompte() {
	return codeCompte;
}
public void setCodeCompte(String codeCompte) {
	this.codeCompte = codeCompte;
}
public Date getDateCreation() {
	return dateCreation;
}
public void setDateCreation(Date dateCreation) {
	this.dateCreation = dateCreation;
}
public double getSolde() {
	return solde;
}
public void setSolde(double solde) {
	this.solde = solde;
}
public Customer getCustomer() {
	return customer;
}
public void setCustomer(Customer customer) {
	this.customer = customer;
}
public Collection<Operation> getOperations() {
	return operations;
}
public void setOperations(Collection<Operation> operations) {
	this.operations = operations;
}


}
