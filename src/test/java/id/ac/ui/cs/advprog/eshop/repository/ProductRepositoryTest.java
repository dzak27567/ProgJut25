package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a09de46-90b1-437d-a0bf-d821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProductSuccess() {
        // Setup: Create product and add to repository
        Product product = new Product();
        product.setProductId("test-id-1");
        product.setProductName("Original Name");
        product.setProductQuantity(10);
        productRepository.create(product);

        // Action: Update the product
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Updated Name");
        updatedProduct.setProductQuantity(20);
        productRepository.update("test-id-1", updatedProduct);

        // Assertion: Check if the product is updated
        Product result = productRepository.findById("test-id-1");
        assertNotNull(result);
        assertEquals("Updated Name", result.getProductName());
        assertEquals(20, result.getProductQuantity());
    }

    @Test
    void testEditProductNotFound() {
        // Setup: Create product but don't add to repository
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Updated Name");
        updatedProduct.setProductQuantity(20);

        // Action: Try to update a non-existent product
        productRepository.update("non-existent-id", updatedProduct);

        // Assertion: Check that nothing is updated
        Product result = productRepository.findById("non-existent-id");
        assertNull(result);
    }

    @Test
    void testDeleteProductSuccess() {
        // Setup: Create product and add to repository
        Product product = new Product();
        product.setProductId("test-id-2");
        product.setProductName("To be deleted");
        product.setProductQuantity(5);
        productRepository.create(product);

        // Action: Delete the product
        productRepository.delete("test-id-2");

        // Assertion: Verify product is deleted
        Product result = productRepository.findById("test-id-2");
        assertNull(result);
    }

    @Test
    void testDeleteProductNotFound() {
        // Action: Try to delete a non-existent product
        productRepository.delete("non-existent-id");

        // Assertion: Check that no exception occurs and nothing changes
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext()); // Ensure repository is still empty
    }

    @Test
    void testCreateWithGeneratedId() {
        Product product = new Product();
        product.setProductName("Test Product");
        product.setProductQuantity(10);
        // productId tidak di-set

        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct.getProductId());
        assertFalse(createdProduct.getProductId().isEmpty());
    }

    @Test
    void testCreateWithEmptyProductId() {
        Product product = new Product();
        product.setProductName("Test Product");
        product.setProductQuantity(10);
        product.setProductId(""); // ID kosong

        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct.getProductId());
        assertFalse(createdProduct.getProductId().isEmpty());
    }

    @Test
    void testCreateWithWhitespaceProductId() {
        Product product = new Product();
        product.setProductName("Test Product");
        product.setProductQuantity(10);
        product.setProductId("   "); // ID whitespace

        Product createdProduct = productRepository.create(product);

        assertNotNull(createdProduct.getProductId());
        assertFalse(createdProduct.getProductId().isEmpty());
        assertNotEquals("   ", createdProduct.getProductId());
    }

    @Test
    void testUpdateProductInMiddleOfList() {
        // Tambahkan beberapa produk
        Product product1 = new Product();
        product1.setProductId("id1");
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("id2");
        productRepository.create(product2);

        Product product3 = new Product();
        product3.setProductId("id3");
        productRepository.create(product3);

        // Update product2
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(99);
        productRepository.update("id2", updatedProduct);

        // Verifikasi update
        Product result = productRepository.findById("id2");
        assertEquals("Updated Product", result.getProductName());
        assertEquals(99, result.getProductQuantity());

        // Verifikasi produk lain tidak berubah
        assertEquals(product1.getProductName(), productRepository.findById("id1").getProductName());
        assertEquals(product3.getProductName(), productRepository.findById("id3").getProductName());
    }
}






