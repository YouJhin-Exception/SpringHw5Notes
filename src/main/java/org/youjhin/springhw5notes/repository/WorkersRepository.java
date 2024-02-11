package org.youjhin.springhw5notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youjhin.springhw5notes.model.WorkerEntity;

public interface WorkersRepository extends JpaRepository<WorkerEntity, Long> {

}
