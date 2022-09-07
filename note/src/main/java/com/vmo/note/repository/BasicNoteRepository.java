package com.vmo.note.repository;

import com.vmo.note.model.BasicNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicNoteRepository extends JpaRepository<BasicNote, Long> {
}
