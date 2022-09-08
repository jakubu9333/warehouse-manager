package com.jakubu9333.warehouseManager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Jakub Uhlarik
 */
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    public  Long id;
    public Integer maxColumn;
    public Integer maxFloor;
    public Integer maxRow;


    public Warehouse(Integer maxColumn, Integer maxFloor, Integer maxRow) {
        this.maxColumn = maxColumn;
        this.maxFloor = maxFloor;
        this.maxRow = maxRow;
    }
}
