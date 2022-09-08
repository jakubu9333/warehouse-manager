package com.jakubu9333.warehouseManager.service;


import com.jakubu9333.warehouseManager.dao.ProductRepository;
import com.jakubu9333.warehouseManager.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
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
    public Product updateProducts(Product newProduct) {
        Long id = newProduct.getId();
        Product oldProduct = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product id " + id + " not found"));
        String name = newProduct.getName();
        String imageUrl = newProduct.getImageUrl();
        Integer priceFull = newProduct.getPriceFull();
        Integer priceCents = newProduct.getPriceCents();
        Integer amount = newProduct.getAmount();
        Integer row = newProduct.getRow();
        Integer floor = newProduct.getFloor();
        Integer column = newProduct.getColumn();
        if (name != null && name.length() > 0 && !name.equals(oldProduct.getName())) {
            Optional<Product> productOpt = productRepository.findByName(name);
            if (productOpt.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name found");
            }
            oldProduct.setName(name);
        }


        if (imageUrl != null && imageUrl.length() > 0 && !imageUrl.equals(oldProduct.getName())) {
            oldProduct.setImageUrl(imageUrl);
        }
        if (priceFull != null && priceFull > 0 && !priceFull.equals(oldProduct.getPriceFull())) {
            oldProduct.setPriceFull(priceFull);
        }
        if (priceCents != null && priceCents > 0 && !priceCents.equals(oldProduct.getPriceCents())) {
            oldProduct.setPriceCents(priceCents);
        }
        if (amount != null && amount > 0 && !amount.equals(oldProduct.getAmount())) {
            oldProduct.setAmount(amount);
        }
        if (row != null && row > 0 && !row.equals(oldProduct.getRow())) {
            oldProduct.setRow(row);
        }
        if (floor != null && floor > 0 && !floor.equals(oldProduct.getFloor())) {
            oldProduct.setFloor(floor);
        }
        if (column != null && column > 0 && !column.equals(oldProduct.getColumn())) {
            oldProduct.setPriceFull(column);
        }
        return oldProduct;
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
