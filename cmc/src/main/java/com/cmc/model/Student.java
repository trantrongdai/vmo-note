package com.cmc.model;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student", uniqueConstraints = {@UniqueConstraint(columnNames = {"rollNumber"})})
public class Student extends BaseEntity {

    @Size(max = 100)
    @Nationalized
    private String name;

    private String rollNumber;

    @ManyToOne
    @JoinColumn(name = "clazz_id", nullable = false)
    private Clazz clazz;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
}
