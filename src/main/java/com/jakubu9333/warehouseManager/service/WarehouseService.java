package com.jakubu9333.warehouseManager.service;

import com.jakubu9333.warehouseManager.dao.WarehouseRepository;
import com.jakubu9333.warehouseManager.model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

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
        return warehouseRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Warehouse createWarehouse() {
        Warehouse warehouse = new Warehouse(0,0,0);
        warehouseRepository.save(warehouse);
        return warehouse;
    }

    @Transactional
    public Warehouse expandWareHouse(Long id, Boolean row ,Boolean col, Boolean floor){
        Warehouse warehouse=warehouseRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (row!= null && row){
            warehouse.setMaxRow(warehouse.getMaxRow()+1);
        }
        if (col!= null && col){
            warehouse.setMaxColumn(warehouse.getMaxColumn()+1);
        }
        if (floor!= null && floor){
            warehouse.setMaxFloor(warehouse.getMaxFloor()+1);
        }

        return warehouse;
    }

    public void deleteWarehouse(Long id){
        warehouseRepository.deleteById(id);
    }
}
