package com.example.restspringbootangular;

import com.example.restspringbootangular.model.Product;
import com.example.restspringbootangular.repository.ProductRepository;
import com.example.restspringbootangular.service.product.DefaultProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DefaultProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new DefaultProductService(productRepository);
    }

    @Test
    void testGetAllByPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> expectedPage = mock(Page.class);
        when(productRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<Product> result = productService.getAllByPage(pageable);

        assertEquals(expectedPage, result);
        verify(productRepository, times(1)).findAll(pageable);
    }

    @Test
    void testFindById() {
        long id = 123L;
        Optional<Product> expectedProduct = Optional.of(mock(Product.class));
        when(productRepository.findById(id)).thenReturn(expectedProduct);

        Optional<Product> result = productService.findById(id);

        assertEquals(expectedProduct, result);
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void testDeleteById() {
        long id = 123L;

        productService.deleteById(id);

        verify(productRepository, times(1)).deleteById(id);
    }

    @Test
    void testSaveProduct() {
        Product product = mock(Product.class);
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.saveProduct(product);

        assertEquals(product, result);
        verify(productRepository, times(1)).save(product);
    }
}
