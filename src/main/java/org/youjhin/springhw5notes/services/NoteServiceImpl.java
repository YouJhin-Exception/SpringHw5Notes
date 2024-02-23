package org.youjhin.springhw5notes.services;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.youjhin.springhw5notes.enums.Status;
import org.youjhin.springhw5notes.model.NoteEntity;
import org.youjhin.springhw5notes.repository.NoteRepository;
import org.youjhin.springhw5notes.services.interfaces.NoteService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Реализация интерфейса NoteService.
 * Предоставляет методы для взаимодействия с объектами NoteEntity.
 */
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    /**
     * Конструирует NoteServiceImpl с указанным NoteRepository.
     *
     * @param noteRepository Репозиторий для управления объектами NoteEntity.
     */
    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * Получает все заметки из репозитория.
     *
     * @return Список всех заметок.
     */
    @Override
    public List<NoteEntity> getAllNotes() {
        return noteRepository.findAll();
    }

    /**
     * Получает заметки из репозитория на основе их статуса.
     *
     * @param status Статус заметок для получения.
     * @return Список заметок с указанным статусом.
     */
    @Override
    public List<NoteEntity> getNotesByStatus(Status status) {
        return noteRepository.findByStatus(status);
    }

    /**
     * Создает новую заметку с указанными деталями.
     * Устанавливает статус заметки по умолчанию в NOT_STARTED.
     *
     * @param note Создаваемая заметка.
     */
    @Override
    public void createNote(NoteEntity note) {
        note.setStatus(Status.NOT_STARTED);
        noteRepository.save(note);
    }

    /**
     * Обновляет статус заметки с указанным ID.
     *
     * @param id        ID заметки для обновления.
     * @param newStatus Новый статус для установки заметке.
     * @throws EntityNotFoundException Если заметка с указанным ID не найдена.
     */
    @Override
    public void updateStatusNote(Long id, Status newStatus) {
        Optional<NoteEntity> findNote = noteRepository.findById(id);

        if (findNote.isPresent()) {
            NoteEntity note = findNote.get();
            note.setStatus(newStatus);
            noteRepository.save(note);
        } else {
            throw new EntityNotFoundException("Заметка с ID " + id + " не найдена");
        }
    }

    /**
     * Удаляет заметку с указанным ID.
     *
     * @param id ID заметки для удаления.
     */
    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    /**
     * Находим заметку с указанным ID.
     *
     * @param noteId ID заметки для поиска.
     * @throws NoSuchElementException Если заметка не найдена.
     */
    @Override
    public NoteEntity findById(Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new NoSuchElementException("Note with id: " + noteId + " was not found."));
    }

}