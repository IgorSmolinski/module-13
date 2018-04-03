package com.example.hibernate.Invoice;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="PRODUCT")
public class Product {
    private int id;
    private String name;


    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }


    @Id
    @GeneratedValue
    @NotNull
    @Column(name="PRODUCT_ID", unique = true)
    public int getId() {
        return id;
    }

    @NotNull
    @Column(name="NAME_OF_PRODUCT")
    public String getName() {
        return name;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }
}
