package com.vmo.note.model.dto;

import java.util.List;

/**
 * Clazz dto
 */
public class NoteDto {

    private Long id;

    private String title;

    private String description;

    private String imageUrl;

    private List<CheckBoxDto> checkBoxes;

    private String noteType;

    private boolean completed = false;

    public NoteDto() {
    }

    public NoteDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<CheckBoxDto> getCheckBoxes() {
        return checkBoxes;
    }

    public void setCheckBoxes(List<CheckBoxDto> checkBoxes) {
        this.checkBoxes = checkBoxes;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
