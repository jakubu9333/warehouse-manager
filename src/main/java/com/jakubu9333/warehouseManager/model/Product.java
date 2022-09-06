package com.jakubu9333.warehouseManager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class Product {


    @Id
    @SequenceGenerator(name = "product_sequence", allocationSize = 1, sequenceName = "product_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    private Long id;
    private String name;
    private String imageUrl;
    private int priceFull;
    private int priceCents;
    private int amount;
    private int column;
    private int row;
    private int floor;


    public Product(String name, String imageUrl, int priceFull, int priceCents, int column, int row, int floor, int amount) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.priceFull = priceFull;
        this.priceCents = priceCents;
        this.amount = amount;
        this.column = column;
        this.row = row;
        this.floor = floor;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + priceFull + "." + priceCents +
                ", amount=" + amount +
                ", column=" + column +
                ", row=" + row +
                ", floor=" + floor +
                '}';
    }
}
