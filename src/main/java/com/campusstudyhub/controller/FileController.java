package com.campusstudyhub.controller;

import com.campusstudyhub.dto.NoteDto;
import com.campusstudyhub.dto.QuestionPaperDto;
import com.campusstudyhub.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for file download operations.
 */
@Controller
@RequestMapping("/files")
public class FileController {

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    private final ResourceService resourceService;

    public FileController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * Download a note file.
     */
    @GetMapping("/notes/{id}/download")
    public ResponseEntity<Resource> downloadNote(@PathVariable Long id) {
        log.info("Downloading note: {}", id);

        NoteDto note = resourceService.getNote(id);
        Resource resource = resourceService.downloadNoteFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + note.getOriginalFileName() + "\"")
                .body(resource);
    }

    /**
     * Download a question paper file.
     */
    @GetMapping("/papers/{id}/download")
    public ResponseEntity<Resource> downloadPaper(@PathVariable Long id) {
        log.info("Downloading paper: {}", id);

        QuestionPaperDto paper = resourceService.getPaper(id);
        Resource resource = resourceService.downloadPaperFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + paper.getOriginalFileName() + "\"")
                .body(resource);
    }
}
