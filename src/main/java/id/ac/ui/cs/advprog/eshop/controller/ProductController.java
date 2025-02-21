package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model){
        // Inisiasi variabel untuk menyimpan pesan error
        String errorMessage = null;

        // Validasi manual
        if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
            errorMessage = "Product name cannot be empty";
        }
        if (product.getProductQuantity() <= 0) {
            errorMessage = "Quantity must be greater than 0";
        }

        // Jika ada error, kembali ke form dengan pesan error
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("product", product);
            return "CreateProduct";
        }

        // Jika tidak ada error, lanjutkan ke create product
        service.create(product);
        return "redirect:/product/list";
    }

    @GetMapping("/list")
    public String productListPage(Model model){
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable String id, Model model) {
        Product product = service.findById(id);
        if (product == null) {
            return "redirect:/product/list"; // Redirect jika product tidak ditemukan
        }
        model.addAttribute("product", product);
        return "EditProduct"; // Pastikan sama dengan nama file editProduct.html
    }



    @PostMapping("/edit/{id}")
    public String editProductPost(@PathVariable String id, @ModelAttribute Product product, Model model) {
        String errorMessage = null;

        if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
            errorMessage = "Product name cannot be empty";
        }
        if (product.getProductQuantity() <= 0) {
            errorMessage = "Quantity must be greater than 0";
        }

        if (errorMessage != null) {
            // Pastikan productId di-set sebelum dikembalikan ke view
            product.setProductId(id);  // Tambahkan ini
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("product", product);
            return "EditProduct";
        }

        service.update(id, product);
        return "redirect:/product/list";
    }


    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        service.delete(id);
        return "redirect:/product/list";
    }
}
