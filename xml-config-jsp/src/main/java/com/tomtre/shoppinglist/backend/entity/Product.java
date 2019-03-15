package com.tomtre.shoppinglist.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product extends TimestampUuidEntity {

    @Size(max = 255, message= "max 255 characters")
    @Column(nullable = false)
    private String title;

    @Size(max = 1000, message= "max 1000 characters")
    @Column(length = 1000)
    private String description;

    @Size(max = 255, message= "max 255 characters")
    @Column
    private String quantity;

    @Size(max = 255, message= "max 255 characters")
    @Column
    private String unit;

    @Column(nullable = false)
    private boolean checked;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Product() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                '}';
    }
}
