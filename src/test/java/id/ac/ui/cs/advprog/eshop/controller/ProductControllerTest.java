package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProductPost_ValidProduct() throws Exception {
        Product product = new Product();
        product.setProductName("Laptop");
        product.setProductQuantity(5);

        // Mock valid product (validateProduct returns null when validation passes)
        when(productService.validateProduct(any(Product.class))).thenReturn(null);

        mockMvc.perform(post("/product/create")
                        .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).create(any(Product.class));
    }

    @Test
    void testCreateProductPost_InvalidProduct() throws Exception {
        Product product = new Product();
        product.setProductName("");
        product.setProductQuantity(0);

        // Setup mock to return error message for invalid product
        when(productService.validateProduct(any(Product.class))).thenReturn("Product name cannot be empty");

        mockMvc.perform(post("/product/create")
                        .flashAttr("product", product))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"))
                .andExpect(model().attributeExists("errorMessage"));

        // Verify validate was called but create was not
        verify(productService, times(1)).validateProduct(any(Product.class));
        verify(productService, never()).create(any(Product.class));
    }

    @Test
    void testProductListPage() throws Exception {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productService.findAll()).thenReturn(products);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ProductList"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    void testEditProductPage_Found() throws Exception {
        Product product = new Product();
        product.setProductId("1");
        when(productService.findById("1")).thenReturn(product);

        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testEditProductPage_NotFound() throws Exception {
        when(productService.findById("1")).thenReturn(null);

        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    void testEditProductPost_ValidProduct() throws Exception {
        Product product = new Product();
        product.setProductName("Laptop");
        product.setProductQuantity(5);

        // Mock valid product (validateProduct returns null when validation passes)
        when(productService.validateProduct(any(Product.class))).thenReturn(null);

        mockMvc.perform(post("/product/edit/1")
                        .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).update(eq("1"), any(Product.class));
    }

    @Test
    void testEditProductPost_InvalidProduct() throws Exception {
        Product product = new Product();
        product.setProductName("");
        product.setProductQuantity(0);

        // Setup mock to return error message for invalid product
        when(productService.validateProduct(any(Product.class))).thenReturn("Product name cannot be empty");

        mockMvc.perform(post("/product/edit/1")
                        .flashAttr("product", product))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"))
                .andExpect(model().attributeExists("errorMessage"));

        // Verify validate was called but update was not
        verify(productService, times(1)).validateProduct(any(Product.class));
        verify(productService, never()).update(anyString(), any(Product.class));
    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(get("/product/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).delete("1");
    }
}