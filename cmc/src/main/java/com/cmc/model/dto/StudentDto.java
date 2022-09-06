package com.cmc.model.dto;

/**
 * Student dto
 */
public class StudentDto {

    private Long id;

    private String name;

    private String rollNumber;

    private ClazzDto clazz;

    public StudentDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public ClazzDto getClazz() {
        return clazz;
    }

    public void setClazz(ClazzDto clazz) {
        this.clazz = clazz;
    }
}
