package com.cmc.repository;

import com.cmc.model.BasicNote;
import com.cmc.model.CheckBoxNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckBoxNoteRepository extends JpaRepository<CheckBoxNote, Long> {

    CheckBoxNote findByBasicNote(BasicNote basicNote);
}
