package org.youjhin.springhw5notes.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.youjhin.springhw5notes.enums.Status;
import org.youjhin.springhw5notes.model.NoteEntity;
import org.youjhin.springhw5notes.services.interfaces.NoteService;

import java.util.List;

/**
 * Контроллер для управления заметками.
 * Обрабатывает запросы, связанные с заметками.
 */
@Controller
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    /**
     * Конструирует NoteController с указанным NoteService.
     *
     * @param noteService Сервис для управления заметками.
     */
    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    /**
     * Получает все заметки и отображает их.
     *
     * @param model Модель для передачи данных в представление.
     * @return Представление со списком всех заметок.
     */
    @GetMapping()
    public String getAllNotes(Model model) {
        model.addAttribute("notes", noteService.getAllNotes());
        return "allNotes";
    }

    /**
     * Отображает форму для добавления новой заметки.
     *
     * @return Представление с формой для добавления заметки.
     */
    @GetMapping("/addNote")
    public String showAddNoteForm() {
        return "addNote";
    }

    /**
     * Сохраняет новую заметку.
     *
     * @param note Новая заметка для сохранения.
     * @return Перенаправление на страницу со всеми заметками.
     */
    @PostMapping("/saveNote")
    public String saveNote(NoteEntity note) {
        noteService.createNote(note);
        return "redirect:/notes";
    }

    /**
     * Удаляет заметку по её идентификатору.
     *
     * @param noteId Идентификатор заметки для удаления.
     * @return Перенаправление на страницу со всеми заметками.
     */
    @PostMapping("/delNote")
    public String deleteNote(@RequestParam("noteId") Long noteId){
        noteService.deleteNote(noteId);
        return "redirect:/notes";
    }

    /**
     * Обновляет статус заметки.
     *
     * @param noteId Идентификатор заметки, статус которой нужно обновить.
     * @param status Новый статус для заметки.
     * @return Перенаправление на страницу со всеми заметками.
     */
    @PostMapping("/statNote")
    public String updateStatusNote(@RequestParam("noteId") Long noteId, @RequestParam("status") Status status){
        noteService.updateStatusNote(noteId,status);
        return "redirect:/notes";
    }

    /**
     * Отображает заметки в соответствии с выбранным статусом.
     *
     * @param status Статус заметок для отображения.
     * @param model Модель для передачи данных в представление.
     * @return Представление со списком заметок с выбранным статусом или всеми заметками.
     */
    @PostMapping("/viewByStatusNote")
    public String viewByStatus(@RequestParam(value = "status", defaultValue = "ALL") Status status, Model model){

        List<NoteEntity> notesByStatus;
        if (status.equals(Status.ALL)){
            notesByStatus = noteService.getAllNotes();

        }else {
            notesByStatus = noteService.getNotesByStatus(status);
            model.addAttribute("notes", notesByStatus);
            return "allNotes";
        }

        model.addAttribute("notes", notesByStatus);
        return "allNotes";
    }

    /**
     * Предоставляет доступ к статусам заметок в представлении.
     *
     * @return Массив всех статусов заметок.
     */
    @ModelAttribute("statuses")
    public Status[] noteStatus() {
        return Status.values();
    }

}