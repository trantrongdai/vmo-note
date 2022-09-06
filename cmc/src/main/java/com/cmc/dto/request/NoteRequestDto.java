package com.cmc.dto.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Student request dto
 */
public class NoteRequestDto {

    @NotNull(message = "Note title is required")
    @Length(max = 100)
    private String title;

    @Length(max = 500)
    private String description;

    private String imageUrl;

    private String checkBoxStatus;

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
}
