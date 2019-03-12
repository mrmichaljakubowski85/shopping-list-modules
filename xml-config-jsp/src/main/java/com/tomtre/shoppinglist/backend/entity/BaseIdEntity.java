package com.tomtre.shoppinglist.backend.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseIdEntity {

    public static final String ID_COLUMN_NAME = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = ID_COLUMN_NAME)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseIdEntity{" +
                "id=" + id +
                '}';
    }
}
