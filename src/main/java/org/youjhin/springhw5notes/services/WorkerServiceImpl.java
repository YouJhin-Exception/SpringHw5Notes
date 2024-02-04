package org.youjhin.springhw5notes.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.youjhin.springhw5notes.model.NoteEntity;
import org.youjhin.springhw5notes.model.WorkerEntity;
import org.youjhin.springhw5notes.repository.WorkersRepository;
import org.youjhin.springhw5notes.services.interfaces.WorkerService;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Реализация интерфейса WorkerService, предоставляющая функциональность управления работниками.
 * Этот класс обрабатывает операции, связанные с работниками, такие как поиск, сохранение, назначение задач и удаление.
 */
@Service
public class WorkerServiceImpl implements WorkerService {
    private final WorkersRepository workersRepository;
    private final NoteServiceImpl noteService;

    /**
     * Конструктор для инициализации WorkerServiceImpl с репозиторием работников и сервисом заметок.
     *
     * @param workersRepository Репозиторий работников для доступа к данным о работниках.
     * @param noteService       Сервис заметок для управления операциями над заметками.
     */
    @Autowired
    public WorkerServiceImpl(WorkersRepository workersRepository, NoteServiceImpl noteService) {
        this.workersRepository = workersRepository;
        this.noteService = noteService;
    }

    /**
     * Получает список всех работников.
     *
     * @return Список всех работников.
     */
    @Override
    public List<WorkerEntity> findAll() {
        return workersRepository.findAll();
    }

    /**
     * Находит работника по указанному идентификатору.
     *
     * @param id Идентификатор работника.
     * @return Найденный работник.
     * @throws NoSuchElementException если работник с указанным идентификатором не найден.
     */
    @Override
    public WorkerEntity findById(Long id) {
        return workersRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Worker with id: " + id + " was not found."));
    }

    /**
     * Сохраняет работника.
     *
     * @param worker Работник для сохранения.
     */
    @Override
    public void save(WorkerEntity worker) {
        workersRepository.save(worker);
    }

    /**
     * Назначает работника на указанную заметку.
     *
     * @param noteId   Идентификатор заметки.
     * @param workerId Идентификатор работника.
     */
    @Override
    public void assignTo(Long noteId, Long workerId) {
        WorkerEntity worker = findById(workerId);
        NoteEntity note = noteService.findById(noteId);
        worker.setWorkerTask(note);
        workersRepository.save(worker);
    }

    /**
     * Удаляет задачу, назначенную на работника.
     *
     * @param workerId Идентификатор работника.
     */
    @Override
    public void takeOffTask(Long workerId) {
        WorkerEntity worker = findById(workerId);
        worker.setWorkerTask(null);
        workersRepository.save(worker);
    }
}