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

    public NoteDto(String title, String description, String noteType) {
        this.title = title;
        this.description = description;
        this.noteType = noteType;
    }

    private NoteDto(NoteDto.NoteDtoBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.imageUrl = builder.imageUrl;
        this.checkBoxes = builder.checkBoxes;
        this.noteType = builder.noteType;
        this.completed = builder.completed;
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

    public static class NoteDtoBuilder {
        private Long id;

        private String title;

        private String description;

        private String imageUrl;

        private List<CheckBoxDto> checkBoxes;

        private String noteType;

        private boolean completed = false;


        public NoteDtoBuilder() {
        }

        public NoteDtoBuilder(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public NoteDtoBuilder(String title, String description, String noteType) {
            this.title = title;
            this.description = description;
            this.noteType = noteType;
        }


        public NoteDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public NoteDtoBuilder title(String title) {
            this.title = title;
            return this;
        }

        public NoteDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public NoteDtoBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }


        public NoteDtoBuilder checkBoxes(List<CheckBoxDto> checkBoxes) {
            this.checkBoxes = checkBoxes;
            return this;
        }

        public NoteDtoBuilder noteType(boolean completed) {
            this.completed = completed;
            return this;
        }

        public NoteDto build() {
            NoteDto noteDto = new NoteDto(this);
            return noteDto;
        }
    }
}
