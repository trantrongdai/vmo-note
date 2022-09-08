package com.vmo.note.model.dto;

import com.vmo.note.dto.response.RoleResponseDto;

import java.util.HashSet;
import java.util.Set;

/**
 * User dto
 */
public class UserDto {
    private Long id;

    private String userName;

    private String email;

    private String name;

    private Set<RoleResponseDto> roles = new HashSet<>();

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RoleResponseDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleResponseDto> roles) {
        this.roles = roles;
    }
}
