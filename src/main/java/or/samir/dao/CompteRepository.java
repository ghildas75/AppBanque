package or.samir.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import or.samir.Entity.Compte;

public interface CompteRepository extends JpaRepository<Compte, String>{

}
