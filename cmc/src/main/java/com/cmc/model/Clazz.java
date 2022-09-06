package com.cmc.model;

import org.hibernate.annotations.Nationalized;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

@Entity
@Table(name = "clazz", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Clazz extends BaseEntity {

    @Size(max = 100)
    @Nationalized
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
