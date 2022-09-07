package com.cmc.repository;

import com.cmc.model.BasicNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicNoteRepository extends JpaRepository<BasicNote, Long> {
}
