package com.cmc.model.dto;

/**
 * CheckBoxNote dto
 */
public class CheckBoxNoteDto {

    private Long id;

    private String checkBoxStatus;

    private NoteDto noteDto;

    public CheckBoxNoteDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCheckBoxStatus() {
        return checkBoxStatus;
    }

    public void setCheckBoxStatus(String checkBoxStatus) {
        this.checkBoxStatus = checkBoxStatus;
    }

    public NoteDto getNoteDto() {
        return noteDto;
    }

    public void setNoteDto(NoteDto noteDto) {
        this.noteDto = noteDto;
    }
}
