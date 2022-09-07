package com.vmo.note.repository;

import com.vmo.note.model.BasicNote;
import com.vmo.note.model.ImageNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageNoteRepository extends JpaRepository<ImageNote, Long> {

    ImageNote findByBasicNote(BasicNote basicNote);
}
