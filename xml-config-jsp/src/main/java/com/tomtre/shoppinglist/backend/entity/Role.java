package com.tomtre.shoppinglist.backend.entity;

import javax.persistence.*;


@Entity
@SequenceGenerator(name = BaseIdEntity.SEQUENCE_GENERATOR_NAME, sequenceName = "role_seq")
@Table(name = "roles")
public class Role extends BaseIdEntity {

    @Column(nullable = false)
    private String name;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
