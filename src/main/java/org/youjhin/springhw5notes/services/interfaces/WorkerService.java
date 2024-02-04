package org.youjhin.springhw5notes.services.interfaces;

import org.youjhin.springhw5notes.model.WorkerEntity;

import java.util.List;

public interface WorkerService {

    List<WorkerEntity> findAll();

    WorkerEntity findById(Long id);

    void save(WorkerEntity worker);

    void assignTo(Long noteId, Long workerId);

    void takeOffTask(Long workerId);

}
