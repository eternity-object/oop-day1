package org.eternity.script.movie.persistence;

import org.eternity.script.movie.domain.Customer;

public interface CustomerDAO {
    Customer selectCustomer(Long customerId);
}
