package com.vmo.note.model;

import javax.persistence.*;

@Entity
@Table(name = "image_note")
public class ImageNote extends BaseEntity {

    private String imageUrl;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "note_id", referencedColumnName = "id")
    private BasicNote basicNote;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BasicNote getBasicNote() {
        return basicNote;
    }

    public void setBasicNote(BasicNote basicNote) {
        this.basicNote = basicNote;
    }
}
