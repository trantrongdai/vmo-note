package com.cmc.repository;

import com.cmc.model.CheckBoxNote;
import com.cmc.model.ImageNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckBoxNoteRepository extends JpaRepository<CheckBoxNote, Long> {
}
