package com.cmc.repository;

import com.cmc.model.BasicNote;
import com.cmc.model.ImageNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageNoteRepository extends JpaRepository<ImageNote, Long> {

    ImageNote findByBasicNote(BasicNote basicNote);
}
