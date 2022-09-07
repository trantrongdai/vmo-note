package com.vmo.note.repository;

import com.vmo.note.model.BasicNote;
import com.vmo.note.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasicNoteRepository extends JpaRepository<BasicNote, Long> {

    boolean existsByUserAndId(User user, Long id);

    Page<BasicNote> findAllByUser(User user, Pageable pageable);

    List<BasicNote> findAllByUserAndCompleted(User user, boolean completed);
}
