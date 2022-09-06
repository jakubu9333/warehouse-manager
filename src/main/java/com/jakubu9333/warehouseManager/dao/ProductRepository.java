package com.jakubu9333.warehouseManager.dao;

import com.jakubu9333.warehouseManager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Jakub Uhlarik
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findByName(String name);
}
