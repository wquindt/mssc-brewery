package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Created by wq on 2021-12-14.
 */
@RequestMapping(RequestUrls.CUSTOMER)
@RestController
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<CustomerDto> getBeer(@PathVariable("id") UUID id){

        return new ResponseEntity<>(service.getCustomerById(id), HttpStatus.OK);
    }


    @PostMapping //POST - Create new Customer
    public ResponseEntity<HttpHeaders> handlePost(@Valid @RequestBody CustomerDto customerDto) {
        CustomerDto savedDto = service.saveNewCustomer(customerDto);

        HttpHeaders headers = new HttpHeaders();
        //TODO: add hostname to url
        headers.add("Location", RequestUrls.CUSTOMER + "/" + savedDto.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping({"/{customerId}"})
    public ResponseEntity<Object> handleUpdate(@PathVariable("customerId") UUID customerId, @Valid @RequestBody CustomerDto customerDto) {
        service.updateCustomer(customerId, customerDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{customerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@PathVariable("customerId") UUID customerId) {
        service.deleteById(customerId);
    }
}
