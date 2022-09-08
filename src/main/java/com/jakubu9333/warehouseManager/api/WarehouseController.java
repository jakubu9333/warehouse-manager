package com.jakubu9333.warehouseManager.api;

import com.jakubu9333.warehouseManager.model.Warehouse;
import com.jakubu9333.warehouseManager.service.WarehouseService;
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

/**
 * @author Jakub Uhlarik
 */
@RestController
@RequestMapping(path = "api/v1/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }


    @GetMapping(path = "{warehouseId}")
    public ResponseEntity<Warehouse> getWarehouse(@PathVariable("warehouseId") Long id) {
        return new ResponseEntity<>(warehouseService.getWareHouseById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Warehouse> createWarehouse() {
        return new ResponseEntity<>(warehouseService.createWarehouse(), HttpStatus.CREATED);
    }

    @PutMapping("/expand/{warehouseId}")
    public ResponseEntity<Warehouse> expandWarehouse(@PathVariable("warehouseId") Long id,
                                                     @RequestParam(required = false) Boolean col,
                                                     @RequestParam(required = false) Boolean floor,
                                                     @RequestParam(required = false) Boolean row) {
        return new ResponseEntity<>(warehouseService.expandWareHouse(id, row, col, floor),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{warehouseId}")
    public ResponseEntity<?> deleteWarehouse(@PathVariable("warehouseId") Long id ){
        warehouseService.deleteWarehouse(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
