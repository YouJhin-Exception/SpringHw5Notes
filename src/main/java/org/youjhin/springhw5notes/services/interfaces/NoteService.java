package org.youjhin.springhw5notes.services.interfaces;

import org.youjhin.springhw5notes.enums.Status;
import org.youjhin.springhw5notes.model.NoteEntity;

import java.util.List;

public interface NoteService {
    List<NoteEntity> getAllNotes();

    List<NoteEntity> getNotesByStatus(Status status);

    void createNote(NoteEntity note);

    void updateStatusNote(Long id, Status newStatus);

    void deleteNote(Long id);

    NoteEntity findById(Long noteId);
}
