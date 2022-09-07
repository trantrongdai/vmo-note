package com.vmo.note.repository;

import com.vmo.note.model.BasicNote;
import com.vmo.note.model.CheckBoxNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckBoxNoteRepository extends JpaRepository<CheckBoxNote, Long> {

    CheckBoxNote findByBasicNote(BasicNote basicNote);
}
