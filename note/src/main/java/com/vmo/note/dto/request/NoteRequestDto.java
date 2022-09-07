package com.vmo.note.dto.request;

import com.vmo.note.model.dto.CheckBoxDto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

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

    private List<CheckBoxDto> checkBoxes;

    private String noteType;

    private boolean completed = false;

    public NoteRequestDto() {
    }

    public NoteRequestDto(@NotNull(message = "Note title is required") @Length(max = 100) String title, @Length(max = 500) String description, String noteType) {
        this.title = title;
        this.description = description;
        this.noteType = noteType;
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
