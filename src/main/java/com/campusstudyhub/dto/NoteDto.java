package com.campusstudyhub.dto;

import java.time.LocalDateTime;

/**
 * DTO for note responses.
 */
public class NoteDto {

    private Long id;
    private String title;
    private String originalFileName;
    private Long fileSize;
    private LocalDateTime uploadedAt;
    private String uploadedByName;
    private Long subjectId;
    private String subjectName;

    // Constructors
    public NoteDto() {
    }

    public NoteDto(Long id, String title, String originalFileName, Long fileSize,
            LocalDateTime uploadedAt, String uploadedByName, Long subjectId, String subjectName) {
        this.id = id;
        this.title = title;
        this.originalFileName = originalFileName;
        this.fileSize = fileSize;
        this.uploadedAt = uploadedAt;
        this.uploadedByName = uploadedByName;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public String getUploadedByName() {
        return uploadedByName;
    }

    public void setUploadedByName(String uploadedByName) {
        this.uploadedByName = uploadedByName;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * Get formatted file size (KB/MB).
     */
    public String getFormattedFileSize() {
        if (fileSize == null)
            return "Unknown";
        if (fileSize < 1024)
            return fileSize + " B";
        if (fileSize < 1024 * 1024)
            return String.format("%.1f KB", fileSize / 1024.0);
        return String.format("%.1f MB", fileSize / (1024.0 * 1024.0));
    }
}
