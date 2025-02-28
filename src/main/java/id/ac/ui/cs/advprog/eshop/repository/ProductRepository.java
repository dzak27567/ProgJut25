package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final List<Product> productData = new ArrayList<>();

    @Autowired
    private IdGenerator idGenerator;

    public Product create(Product product) {
        if (product.getProductId() == null || product.getProductId().trim().isEmpty()) {
            product.setProductId(idGenerator.generateId());
        }
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String id) {
        return productData.stream()
                .filter(product -> product.getProductId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Product update(String id, Product updatedProduct) {
        Optional<Product> productToUpdate = productData.stream()
                .filter(product -> product.getProductId().equals(id))
                .findFirst();

        if (productToUpdate.isPresent()) {
            Product product = productToUpdate.get();
            updateProductFields(product, updatedProduct);
            return product;
        }
        return null;
    }

    // Pemisahan metode untuk update fields (SRP)
    private void updateProductFields(Product target, Product source) {
        target.setProductName(source.getProductName());
        target.setProductQuantity(source.getProductQuantity());
    }

    public void delete(String id) {
        productData.removeIf(product -> product.getProductId().equals(id));
    }
}