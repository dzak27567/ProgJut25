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
        Product updatedProduct = new Product();

        // Menggunakan when-thenReturn karena implementasi update mengembalikan Product
        when(productRepository.update(productId, product)).thenReturn(updatedProduct);

        productService.update(productId, product);
        verify(productRepository, times(1)).update(productId, product);
    }

    @Test
    void testValidateProductValid() {
        Product product = new Product();
        product.setProductName("Valid Product");
        product.setProductQuantity(10);

        String result = productService.validateProduct(product);
        assertNull(result, "Validation should pass and return null");
    }

    @Test
    void testValidateProductInvalidName() {
        Product product = new Product();
        product.setProductName(""); // Empty name
        product.setProductQuantity(10);

        String result = productService.validateProduct(product);
        assertEquals("Product name cannot be empty", result);
    }

    @Test
    void testValidateProductInvalidQuantity() {
        Product product = new Product();
        product.setProductName("Valid Product");
        product.setProductQuantity(0); // Invalid quantity

        String result = productService.validateProduct(product);
        assertEquals("Quantity must be greater than 0", result);
    }
}