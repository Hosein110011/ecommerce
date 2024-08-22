package com.ecommerce.demo.service;

import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo repo;
    @Autowired
    private ProductRepo productRepo;

    //List<Product> products = new ArrayList<>(Arrays.asList(new Product(101, "Iphone", 500000), new Product(102, "Canon Camera", 10000000)));

    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Product> getProductById(int id) {
        return new ResponseEntity<>(productRepo.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<String> addProduct(Product prod, MultipartFile imageFile) {
        try {
            prod.setImageName(imageFile.getOriginalFilename());
            prod.setImageType(imageFile.getContentType());
            prod.setImageData(imageFile.getBytes());
            productRepo.save(prod);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateProduct(int id, Product prod, MultipartFile imageFile) {
        if (!productRepo.existsById(id)) {
            return new ResponseEntity<>("not found.", HttpStatus.NOT_FOUND);
        }
        try {
            prod.setImageData(imageFile.getBytes());
            prod.setImageName(imageFile.getOriginalFilename());
            prod.setImageType(imageFile.getContentType());
            productRepo.save(prod);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteProduct(int id) {
        if (!productRepo.existsById(id)) {
            return new ResponseEntity<>("Failed to delete", HttpStatus.NOT_FOUND);
        }
        productRepo.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    public ResponseEntity<byte[]> getImageByProductId(int productId) {
        Product product = productRepo.findById(productId).get();
        byte[] imageFile = product.getImageData();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }

    public ResponseEntity<List<Product>> searchProducts(String keyword) {
        List<Product> products = productRepo.searchProducts(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
