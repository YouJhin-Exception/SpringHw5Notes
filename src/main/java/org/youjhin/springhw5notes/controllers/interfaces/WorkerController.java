package org.youjhin.springhw5notes.controllers.interfaces;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.youjhin.springhw5notes.model.WorkerEntity;

public interface WorkerController {

    @GetMapping()
    String getAllWorkers(Model model);

    @GetMapping("/addWorker")
    String showAddWorkerForm();

    @PostMapping("/saveWorker")
    String saveWorker(WorkerEntity worker);

    @GetMapping("/assignedWorker")
    String showForm(Model model);

    @PostMapping("/assignedWorker")
    String assignedWorkerToNote(
            @Parameter(description = "ID заметки, на которую назначается работник") @RequestParam("noteId") Long noteId,
            @Parameter(description = "ID работника, который назначается") @RequestParam("workerId") Long workerId);

    @PostMapping("/takeOffWorker")
    String takeOffTask(@Parameter(description = "ID работника, с которого снимается задача") @RequestParam("workerId") Long workerId);


}
