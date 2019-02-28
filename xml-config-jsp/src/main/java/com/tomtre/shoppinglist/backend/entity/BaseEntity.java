package com.tomtre.shoppinglist.backend.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

    public static final String ID_NAME = "id";

    //we don't use @GeneratedValue because it is UUID and the Entity can come with already set value
    @Id
    @Column(name = ID_NAME)
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
