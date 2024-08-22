package com.ecommerce.demo.controller;

import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.repository.ProductRepo;
import com.ecommerce.demo.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService prodService;
    @Autowired
    private ProductRepo productRepo;

    @RequestMapping("/")
    public String greet(HttpServletRequest request) {
        return "Hello user with this id " + request.getSession().getId();
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return prodService.getProducts();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return prodService.getProductById(id);
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {
        return prodService.getImageByProductId(productId);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
        return prodService.addProduct(product, imageFile);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product,
                                                @RequestPart MultipartFile imageFile) {
        return prodService.updateProduct(id, product, imageFile);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        return prodService.deleteProduct(id);
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        return prodService.searchProducts(keyword);
    }
}
