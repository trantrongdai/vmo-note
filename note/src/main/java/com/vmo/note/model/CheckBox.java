package com.vmo.note.model;

import javax.persistence.*;


@Entity
@Table(name = "check_box")
public class CheckBox extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @Column(name = "selected")
    private boolean selected = false;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "check_box_note_id", nullable = true)
    private CheckBoxNote checkBoxNote;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public CheckBoxNote getCheckBoxNote() {
        return checkBoxNote;
    }

    public void setCheckBoxNote(CheckBoxNote checkBoxNote) {
        this.checkBoxNote = checkBoxNote;
    }
}
