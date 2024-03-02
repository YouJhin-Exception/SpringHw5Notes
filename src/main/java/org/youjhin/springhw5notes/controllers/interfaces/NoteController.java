package org.youjhin.springhw5notes.controllers.interfaces;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.youjhin.springhw5notes.enums.Status;
import org.youjhin.springhw5notes.model.NoteEntity;

public interface NoteController {
    @GetMapping()
    String getAllNotes(Model model);

    @GetMapping("/addNote")
    String showAddNoteForm();

    @PostMapping("/saveNote")
    String saveNote(@Parameter(description = "Новая заметка для сохранения") NoteEntity note);

    @PostMapping("/delNote")
    String deleteNote(@Parameter(description = "ID заметки для удаления") @RequestParam("noteId") Long noteId);

    @PostMapping("/statNote")
    String updateStatusNote(
            @Parameter(description = "ID заметки, статус которой нужно обновить") @RequestParam("noteId") Long noteId,
            @Parameter(description = "Новый статус заметки") @RequestParam("status") Status status);

    @PostMapping("/viewByStatusNote")
    String viewByStatus(
            @Parameter(description = "Статус заметок для отображения", required = false) @RequestParam(value = "status", defaultValue = "ALL") Status status, Model model);

    @ModelAttribute("statuses")
    Status[] noteStatus();
}
