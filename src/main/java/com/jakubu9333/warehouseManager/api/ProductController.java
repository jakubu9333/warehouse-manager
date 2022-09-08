package com.jakubu9333.warehouseManager.api;

import com.jakubu9333.warehouseManager.model.Product;
import com.jakubu9333.warehouseManager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author Jakub Uhlarik
 */

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
    private final ProductService productService;


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping(path = "{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable(name = "productId")Long id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "productId") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path="/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProducts(product), HttpStatus.CREATED);
    }

    @PutMapping(path = "/buy/{productId}")
    public ResponseEntity<Product> buyProduct(@PathVariable(name = "productId") Long id,
                                              @RequestParam(required = false) Integer amount
    ) {
        if (amount == null) {
            amount = 1;
        }
        return new ResponseEntity<>(productService.changeAmount(id, amount), HttpStatus.CREATED);
    }

    @PutMapping(path = "/sell/{productId}")
    public ResponseEntity<Product> sellProduct(@PathVariable(name = "productId") Long id,
                                               @RequestParam(required = false) Integer amount
    ) {
        if (amount == null) {
            amount = -1;
        }
        return new ResponseEntity<>(productService.changeAmount(id, amount), HttpStatus.CREATED);
    }

}
