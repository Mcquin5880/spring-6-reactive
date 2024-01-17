package org.mcq.spring6reactive.mapper;

import org.mapstruct.Mapper;
import org.mcq.spring6reactive.domain.Customer;
import org.mcq.spring6reactive.model.CustomerDTO;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO customerDTO);

    CustomerDTO customerToCustomerDto(Customer customer);
}
