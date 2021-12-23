package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.BeerDto;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto getCustomerById(UUID id) {
        log.info("Get Customer by ID " + id.toString());
        return CustomerDto.builder().id(UUID.randomUUID())
                .name("Jon Jonson")
                .build();
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDto) {
        log.info("Save new customer " + customerDto.toString());
        return CustomerDto.builder().id(UUID.randomUUID()).build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        log.info("Update customer " + customerDto.toString());
    }

    @Override
    public void deleteById(UUID customerId) {
        log.info("Delete customer by ID " + customerId.toString());
    }
}
