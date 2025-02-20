package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Product product = new Product();
        when(productRepository.create(product)).thenReturn(product);
        Product result = productService.create(product);
        assertEquals(product, result);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAll() {
        Product product1 = new Product();
        Product product2 = new Product();
        Iterator<Product> iterator = Arrays.asList(product1, product2).iterator();
        when(productRepository.findAll()).thenReturn(iterator);
        List<Product> result = productService.findAll();
        assertEquals(2, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testDelete() {
        String productId = "123";
        doNothing().when(productRepository).delete(productId);
        productService.delete(productId);
        verify(productRepository, times(1)).delete(productId);
    }

    @Test
    void testFindById() {
        String productId = "123";
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(product);
        Product result = productService.findById(productId);
        assertEquals(product, result);
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testUpdate() {
        String productId = "123";
        Product product = new Product();
        doNothing().when(productRepository).update(productId, product);
        productService.update(productId, product);
        verify(productRepository, times(1)).update(productId, product);
    }
}
