package com.cmc.model;

import org.hibernate.annotations.Nationalized;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "note")
public class Note extends BaseEntity {

    @Size(max = 100)
    @Nationalized
    private String title;

    @Size(max = 500)
    @Nationalized
    private String description;

    private String imageUrl;

    private String checkBoxStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCheckBoxStatus() {
        return checkBoxStatus;
    }

    public void setCheckBoxStatus(String checkBoxStatus) {
        this.checkBoxStatus = checkBoxStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
