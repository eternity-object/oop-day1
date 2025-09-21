package org.eternity.script.movie.persistence.memory;

import org.eternity.script.movie.domain.Customer;
import org.eternity.script.movie.persistence.CustomerDAO;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerMemoryDAO extends InMemoryDAO<Customer> implements CustomerDAO {
    public CustomerMemoryDAO() {
        add(new Customer("사용자"));
    }

    @Override
    public Customer selectCustomer(Long customerId) {
        return getOne(customer -> customer.getId().equals(customerId));
    }
}
