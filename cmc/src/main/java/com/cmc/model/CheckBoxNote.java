package com.cmc.model;

import javax.persistence.*;

@Entity
@Table(name = "check_box_note")
public class CheckBoxNote extends BaseEntity {

    private String checkBoxStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "note_id", referencedColumnName = "id")
    private BasicNote basicNote;

    public String getCheckBoxStatus() {
        return checkBoxStatus;
    }

    public void setCheckBoxStatus(String checkBoxStatus) {
        this.checkBoxStatus = checkBoxStatus;
    }

    public BasicNote getBasicNote() {
        return basicNote;
    }

    public void setBasicNote(BasicNote basicNote) {
        this.basicNote = basicNote;
    }
}
