package com.vmo.note.model.dto;

/**
 * ImageNote dto
 */
public class ImageNoteDto {

    private Long id;

    private String imageUrl;

    private NoteDto noteDto;

    public ImageNoteDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public NoteDto getNoteDto() {
        return noteDto;
    }

    public void setNoteDto(NoteDto noteDto) {
        this.noteDto = noteDto;
    }
}
