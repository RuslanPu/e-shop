package com.example.demo.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "power_c")
    private String powerC;

    @Column(name = "power_h")
    private String powerH;

    @Column(name = "size")
    private String size;

    @Column(name = "weight")
    private String weight;

    @Column(name = "cost_sale_usd")
    private String costSaleUsd;

    @Column(name = "cost_sale_rub")
    private String costSaleRub;

    @Column(name = "cost_usd")
    private String costUsd;

    @Column(name = "cost_rub")
    private String costRub;

    @Column(name = "description")
    private String desc;

    @Column(name = "description_s")
    private String desc_s;


    @Column(name = "alt_image")
    private String altImage;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(brand, product.brand) && Objects.equals(powerC, product.powerC) && Objects.equals(powerH, product.powerH) && Objects.equals(size, product.size) && Objects.equals(weight, product.weight) && Objects.equals(costSaleUsd, product.costSaleUsd) && Objects.equals(costSaleRub, product.costSaleRub) && Objects.equals(costUsd, product.costUsd) && Objects.equals(costRub, product.costRub) && Objects.equals(desc, product.desc) && Objects.equals(desc_s, product.desc_s) && Objects.equals(altImage, product.altImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, powerC, powerH, size, weight, costSaleUsd, costSaleRub, costUsd, costRub, desc, desc_s, altImage);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPowerC() {
        return powerC;
    }

    public void setPowerC(String powerC) {
        this.powerC = powerC;
    }

    public String getPowerH() {
        return powerH;
    }

    public void setPowerH(String powerH) {
        this.powerH = powerH;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCostSaleUsd() {
        return costSaleUsd;
    }

    public void setCostSaleUsd(String costSaleUsd) {
        this.costSaleUsd = costSaleUsd;
    }

    public String getCostSaleRub() {
        return costSaleRub;
    }

    public void setCostSaleRub(String costSaleRub) {
        this.costSaleRub = costSaleRub;
    }

    public String getCostUsd() {
        return costUsd;
    }

    public void setCostUsd(String costUsd) {
        this.costUsd = costUsd;
    }

    public String getCostRub() {
        return costRub;
    }

    public void setCostRub(String costRub) {
        this.costRub = costRub;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc_s() {
        return desc_s;
    }

    public void setDesc_s(String desc_s) {
        this.desc_s = desc_s;
    }

    public String getAltImage() {
        return altImage;
    }

    public void setAltImage(String altImage) {
        this.altImage = altImage;
    }




}
