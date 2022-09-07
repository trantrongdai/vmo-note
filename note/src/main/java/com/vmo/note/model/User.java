package com.vmo.note.model;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User extends BaseEntity {

    @Size(max = 100)
    @Nationalized
    private String username;

    private String password;

    @OneToMany(mappedBy = "user")
    private Set<BasicNote> basicNotes;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<BasicNote> getBasicNotes() {
        return basicNotes;
    }

    public void setBasicNotes(Set<BasicNote> basicNotes) {
        this.basicNotes = basicNotes;
    }
}
