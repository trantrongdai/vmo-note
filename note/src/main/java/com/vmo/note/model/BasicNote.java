package com.vmo.note.model;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "note")
public class BasicNote extends BaseEntity {

    @Size(max = 100)
    @Nationalized
    private String title;

    @Size(max = 500)
    @Nationalized
    private String description;

    private String noteType;

    private boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "basicNote", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private CheckBoxNote checkBoxNote;

    @OneToOne(mappedBy = "basicNote", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private ImageNote imageNote;

    public BasicNote() {
    }

    public BasicNote(@Size(max = 100) String title, @Size(max = 500) String description) {
        this.title = title;
        this.description = description;
    }

    public BasicNote(@Size(max = 100) String title, @Size(max = 500) String description, String noteType) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CheckBoxNote getCheckBoxNote() {
        return checkBoxNote;
    }

    public void setCheckBoxNote(CheckBoxNote checkBoxNote) {
        this.checkBoxNote = checkBoxNote;
    }

    public ImageNote getImageNote() {
        return imageNote;
    }

    public void setImageNote(ImageNote imageNote) {
        this.imageNote = imageNote;
    }
}
