package com.example.restspringbootangular;

import com.example.restspringbootangular.model.Customer;
import com.example.restspringbootangular.repository.CustomerRepository;
import com.example.restspringbootangular.service.customer.DefaultCustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    private DefaultCustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new DefaultCustomerService(customerRepository);
    }

    @Test
    void testGetAllByPage() {
        Pageable pageable = PageRequest.of(0, 10); // Create a Pageable with page index 0 and size 10
        Page<Customer> expectedPage = mock(Page.class);
        when(customerRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<Customer> result = customerService.getAllByPage(pageable);

        assertEquals(expectedPage, result);
        verify(customerRepository, times(1)).findAll(pageable);
    }

    @Test
    void testFindById() {
        String id = "123";
        Optional<Customer> expectedCustomer = Optional.of(mock(Customer.class));
        when(customerRepository.findById(id)).thenReturn(expectedCustomer);

        Optional<Customer> result = customerService.findById(id);

        assertEquals(expectedCustomer, result);
        verify(customerRepository, times(1)).findById(id);
    }

    @Test
    void testDeleteById() {
        String id = "123";

        customerService.deleteById(id);

        verify(customerRepository, times(1)).deleteById(id);
    }

    @Test
    void testSaveCustomer() {
        Customer customer = mock(Customer.class);
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer result = customerService.saveCustomer(customer);

        assertEquals(customer, result);
        verify(customerRepository, times(1)).save(customer);
    }
}
