package com.thalita.roles.model;

import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public RoleModel() {
        super();
    }

    public RoleModel(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public RoleModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoleModel setName(String name) {
        this.name = name;
        return this;
    }
}