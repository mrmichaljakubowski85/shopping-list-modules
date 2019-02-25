package com.tomtre.shoppinglist.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String title;

    private String description;

    private String quantity;

    private String unit;

    @Column(nullable = false)
    private boolean checked;

    public Product() {

    }

    public Product(String title, String description, String quantity, String unit, boolean checked) {
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.unit = unit;
        this.checked = checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", quantity='" + quantity + '\'' +
                ", unit='" + unit + '\'' +
                ", checked=" + checked +
                "} " + super.toString();
    }
}
