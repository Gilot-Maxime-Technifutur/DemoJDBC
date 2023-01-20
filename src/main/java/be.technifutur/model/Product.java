package be.technifutur.model;

import lombok.Data;

@Data
public class Product {
    private long id;
    private boolean discontinued;
    private String name;
    private String qttPerUnit;
    private Supplier supplier;
    private double unitPrice;
    private int stock;
}
