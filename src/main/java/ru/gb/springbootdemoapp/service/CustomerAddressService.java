package ru.gb.springbootdemoapp.service;

import org.springframework.stereotype.Service;
import ru.gb.springbootdemoapp.model.CustomerAddress;
import ru.gb.springbootdemoapp.repository.CustomerAddressRepository;

import java.util.Optional;

@Service
public class CustomerAddressService {

    private final CustomerAddressRepository customerAddressRepository;

    public CustomerAddressService(CustomerAddressRepository customerAddressRepository) {
        this.customerAddressRepository = customerAddressRepository;
    }

    public Optional<CustomerAddress> findById(Long id) {
        return customerAddressRepository.findById(id);
    }
}
