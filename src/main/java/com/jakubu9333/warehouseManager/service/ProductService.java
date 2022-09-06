package com.jakubu9333.warehouseManager.service;


import com.jakubu9333.warehouseManager.dao.ProductRepository;
import com.jakubu9333.warehouseManager.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Optional;

/**
 * @author Jakub Uhlarik
 */

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        Optional<Product> productOpt = productRepository.findByName(product.getName());
        if (productOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name found");
        }
        productRepository.save(product);
        return product;
    }

    public void deleteProduct(Long id) {
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product id " + id + " not found");
        }
        productRepository.deleteById(id);
        throw new ResponseStatusException(HttpStatus.valueOf(201));
    }

    @Transactional
    public Product updateProducts(Long id, String name, String imageUrl, Integer priceFull, Integer priceCents, Integer amount, Integer row, Integer column, Integer floor) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product id " + id + " not found"));

        if (name != null && name.length() > 0 && !name.equals(product.getName())) {
            Optional<Product> productOpt = productRepository.findByName(name);
            if (productOpt.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name found");
            }
            product.setName(name);
        }

        if (imageUrl != null && imageUrl.length() > 0 && !imageUrl.equals(product.getName())) {
            product.setImageUrl(imageUrl);
        }
        if (priceFull != null && priceFull > 0 && priceFull != product.getPriceFull()) {
            product.setPriceFull(priceFull);
        }
        if (priceCents != null && priceCents > 0 && priceCents != product.getPriceCents()) {
            product.setPriceCents(priceCents);
        }
        if (amount != null && amount > 0 && amount != product.getAmount()) {
            product.setAmount(amount);
        }
        if (row != null && row > 0 && row != product.getRow()) {
            product.setRow(row);
        }
        if (floor != null && floor > 0 && floor != product.getFloor()) {
            product.setFloor(floor);
        }
        if (column != null && column > 0 && column != product.getColumn()) {
            product.setPriceFull(column);
        }
        return product;
    }

    @Transactional
    public Product changeAmount(Long id, int amount) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product id " + id + " not found"));
        if (product.getAmount() + amount < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "less than you want to sell");
        }
        product.setAmount(product.getAmount() + amount);
        return product;
    }

}
