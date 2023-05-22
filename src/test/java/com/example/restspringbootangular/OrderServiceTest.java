package com.example.restspringbootangular;

import com.example.restspringbootangular.model.Order;
import com.example.restspringbootangular.repository.OrderRepository;
import com.example.restspringbootangular.service.order.DefaultOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    private DefaultOrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        orderService = new DefaultOrderService(orderRepository);
    }

    @Test
    void testGetAll() {
        List<Order> expectedOrders = List.of(mock(Order.class));
        when(orderRepository.findAll()).thenReturn(expectedOrders);

        List<Order> result = orderService.getAll();

        assertEquals(expectedOrders, result);
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Long id = 123L;
        Optional<Order> expectedOrder = Optional.of(mock(Order.class));
        when(orderRepository.findById(id)).thenReturn(expectedOrder);

        Optional<Order> result = orderService.findById(id);

        assertEquals(expectedOrder, result);
        verify(orderRepository, times(1)).findById(id);
    }

    @Test
    void testGetAllByPage() {
        int pageSize = 10;
        int pageNumber = 0;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Order> expectedPage = mock(Page.class);
        when(orderRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<Order> result = orderService.getAllByPage(pageable);

        assertEquals(expectedPage, result);
        verify(orderRepository, times(1)).findAll(pageable);
    }
}
