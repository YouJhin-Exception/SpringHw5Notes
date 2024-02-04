package org.youjhin.springhw5notes.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.youjhin.springhw5notes.model.WorkerEntity;
import org.youjhin.springhw5notes.services.interfaces.NoteService;
import org.youjhin.springhw5notes.services.interfaces.WorkerService;

/**
 * Класс-контроллер для обработки HTTP-запросов, связанных с работниками.
 * Этот класс управляет операциями с работниками, такими как добавление, вывод списка, назначение задач и удаление работников.
 */
@Controller
@RequestMapping("/workers")
@Tag(name = "Worker API", description = "API для управления работниками")
public class WorkerController {

    private final NoteService noteService;
    private final WorkerService workerService;

    /**
     * Конструктор для инициализации WorkerController с необходимыми сервисами.
     *
     * @param noteService   Сервис, отвечающий за управление заметками.
     * @param workerService Сервис, отвечающий за управление работниками.
     */
    public WorkerController(NoteService noteService, WorkerService workerService) {
        this.noteService = noteService;
        this.workerService = workerService;
    }

    /**
     * Обрабатывает HTTP-запрос GET для получения всех работников и отображения их.
     *
     * @param model Модель для добавления данных о работниках для отображения.
     * @return Наименование представления для отображения списка работников.
     */
    @Operation(summary = "Получить всех работников", responses = {
            @ApiResponse(description = "Успешно", responseCode = "200")
    })
    @GetMapping()
    public String getAllWorkers(Model model) {
        model.addAttribute("workers", workerService.findAll());
        return "allWorkers";
    }

    /**
     * Обрабатывает HTTP-запрос GET для отображения формы добавления нового работника.
     *
     * @return Наименование представления для формы добавления работника.
     */
    @Operation(summary = "Показать форму для добавления нового работника")
    @GetMapping("/addWorker")
    public String showAddWorkerForm() {
        return "addWorker";
    }

    /**
     * Обрабатывает HTTP-запрос POST для сохранения нового работника.
     *
     * @param worker Сущность работника, которую необходимо сохранить.
     * @return URL для перенаправления после сохранения работника.
     */
    @Operation(summary = "Сохранить нового работника", responses = {
            @ApiResponse(description = "Перенаправление на список всех работников", responseCode = "302")
    })
    @PostMapping("/saveWorker")
    public String saveWorker(WorkerEntity worker) {
        workerService.save(worker);
        return "redirect:/workers";
    }

    /**
     * Обрабатывает HTTP-запрос GET для отображения формы назначения работника на заметку.
     *
     * @param model Модель для добавления данных о заметке и работнике для отображения.
     * @return Наименование представления для формы назначения работника на заметку.
     */
    @Operation(summary = "Показать форму для назначения работника на заметку")
    @GetMapping("/assignedWorker")
    public String showForm(Model model) {
        model.addAttribute("notes", noteService.getAllNotes());
        model.addAttribute("workers", workerService.findAll());
        return "assignedWorker";
    }

    /**
     * Обрабатывает HTTP-запрос POST для назначения работника на заметку.
     *
     * @param noteId   Идентификатор заметки, на которую назначается работник.
     * @param workerId Идентификатор работника, который назначается.
     * @return URL для перенаправления после назначения работника на заметку.
     */
    @Operation(summary = "Назначить работника на заметку", responses = {
            @ApiResponse(description = "Перенаправление на форму назначения работника на заметку", responseCode = "302")
    })
    @PostMapping("/assignedWorker")
    public String assignedWorkerToNote(
            @Parameter(description = "ID заметки, на которую назначается работник") @RequestParam("noteId") Long noteId,
            @Parameter(description = "ID работника, который назначается") @RequestParam("workerId") Long workerId) {
        workerService.assignTo(noteId, workerId);
        return "redirect:/workers/assignedWorker";
    }

    /**
     * Обрабатывает HTTP-запрос POST для снятия работника с задачи.
     *
     * @param workerId Идентификатор работника, из которого удаляется задача.
     * @return URL для перенаправления после удаления работника из задачи.
     */
    @Operation(summary = "Снятие работника с задачи", responses = {
            @ApiResponse(description = "Перенаправление на список всех работников", responseCode = "302")
    })
    @PostMapping("/takeOffWorker")
    public String takeOffTask(@Parameter(description = "ID работника, с которого снимается задача") @RequestParam("workerId") Long workerId) {
        workerService.takeOffTask(workerId);
        return "redirect:/workers";
    }
}




