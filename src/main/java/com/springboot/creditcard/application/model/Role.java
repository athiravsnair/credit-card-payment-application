package com.springboot.creditcard.application.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "rolename")
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role() {
        super();
    }
}
