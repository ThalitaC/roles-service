package com.thalita.roles.model;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;



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