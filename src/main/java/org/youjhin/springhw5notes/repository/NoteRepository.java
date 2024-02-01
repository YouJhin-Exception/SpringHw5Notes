package org.youjhin.springhw5notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youjhin.springhw5notes.enums.Status;
import org.youjhin.springhw5notes.model.NoteEntity;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<NoteEntity,Long> {

    List<NoteEntity> findByStatus(Status status);
    Optional<NoteEntity> findById(Long noteId);

}
