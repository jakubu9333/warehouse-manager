package com.jakubu9333.warehouseManager.dao;

import com.jakubu9333.warehouseManager.model.Product;
import com.jakubu9333.warehouseManager.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jakub Uhlarik
 */
@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
