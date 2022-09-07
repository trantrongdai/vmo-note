package com.vmo.note.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "check_box_note")
public class CheckBoxNote extends BaseEntity {

    private String checkBoxStatus;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "note_id", referencedColumnName = "id")
    private BasicNote basicNote;

    @OneToMany(mappedBy = "checkBoxNote", cascade = CascadeType.ALL)
    private List<CheckBox> checkBoxes;

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

    public List<CheckBox> getCheckBoxes() {
        return checkBoxes;
    }

    public void setCheckBoxes(List<CheckBox> checkBoxes) {
        this.checkBoxes = checkBoxes;
    }
}
