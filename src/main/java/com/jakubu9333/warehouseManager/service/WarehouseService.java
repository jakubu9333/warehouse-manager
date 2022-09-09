package com.jakubu9333.warehouseManager.service;

import com.jakubu9333.warehouseManager.dao.WarehouseRepository;
import com.jakubu9333.warehouseManager.model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Jakub Uhlarik
 */
@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Warehouse getWareHouseById(Long id) {
        return warehouseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Warehouse createWarehouse() {
        Warehouse warehouse = new Warehouse(1, 1, 1);
        warehouseRepository.save(warehouse);
        return warehouse;
    }


    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }

    public List<Warehouse> getAllWarehouse() {
        return warehouseRepository.findAll();
    }

    @Transactional
    public Warehouse changeWarehouseSize(Long id, Boolean row, Boolean col, Boolean floor, int amount) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (row != null && row) {
            warehouse.setMaxRow(warehouse.getMaxRow() + amount);
        }
        if (col != null && col) {
            warehouse.setMaxColumn(warehouse.getMaxColumn() + amount);
        }
        if (floor != null && floor) {
            warehouse.setMaxFloor(warehouse.getMaxFloor() + amount);
        }
        return warehouse;
    }

    @Transactional
    public Warehouse expandWareHouse(Long id, Boolean row, Boolean col, Boolean floor) {
        return changeWarehouseSize(id, row, col, floor, 1);
    }

    @Transactional
    public Warehouse shrinkWareHouse(Long id, Boolean row, Boolean col, Boolean floor) {
        return changeWarehouseSize(id, row, col, floor, -1);
    }
}
