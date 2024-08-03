package com.example.InventoryService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "count")
    int count;
    @Column(name = "cost")
    double cost;
    @Column(name = "SKU")
    String SKU;
    @Column(name = "category")
    String category;
    @Column(name = "product_name")
    String product_name;
    @Column(name = "description")
    String description;

}
