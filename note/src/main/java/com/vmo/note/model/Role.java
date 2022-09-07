package com.vmo.note.model;

import org.hibernate.annotations.Nationalized;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "role")
public class Role extends BaseEntity {

    @Column(length = 60, unique = true)
    @Nationalized
    private String name;

    @Column(length = 254)
    @Nationalized
    private String description;

    public Role() {
    }

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
