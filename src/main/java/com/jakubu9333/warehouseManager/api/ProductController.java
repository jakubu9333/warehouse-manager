package com.jakubu9333.warehouseManager.api;

import com.jakubu9333.warehouseManager.model.Product;
import com.jakubu9333.warehouseManager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping(path = "{productId}")
    public void deleteProduct(@PathVariable(name = "productId") Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping(path = "{productId}")
    public Product updateProduct(@PathVariable(name = "productId") Long id,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) String imageUrl,
                                 @RequestParam(required = false) Integer priceFull,
                                 @RequestParam(required = false) Integer priceCents,
                                 @RequestParam(required = false) Integer amount,
                                 @RequestParam(required = false) Integer row,
                                 @RequestParam(required = false) Integer column,
                                 @RequestParam(required = false) Integer floor) {
        return productService.updateProducts(id, name, imageUrl, priceFull, priceCents, amount, row, column, floor);
    }

    @PutMapping(path = "{productId}/buy")
    public Product buyProduct(@PathVariable(name = "productId") Long id,
                              @RequestParam(required = false) Integer amount
    ) {
        if (amount == null) {
            amount = 1;
        }
        return productService.changeAmount(id, amount);
    }

    @PutMapping(path = "{productId}/sell")
    public Product sellProduct(@PathVariable(name = "productId") Long id,
                               @RequestParam(required = false) Integer amount
    ) {
        if (amount == null) {
            amount = -1;
        }
        return productService.changeAmount(id, amount);
    }

}
