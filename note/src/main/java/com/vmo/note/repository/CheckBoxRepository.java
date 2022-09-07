package com.vmo.note.repository;

import com.vmo.note.model.CheckBox;
import com.vmo.note.model.CheckBoxNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckBoxRepository extends JpaRepository<CheckBox, Long> {

    List<CheckBox> findByCheckBoxNote(CheckBoxNote checkBoxNote);
}
