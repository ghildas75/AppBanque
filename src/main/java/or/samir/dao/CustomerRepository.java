package or.samir.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import or.samir.Entity.Customer;

public interface CustomerRepository  extends JpaRepository<Customer,Long>{

}
