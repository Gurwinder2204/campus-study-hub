package com.campusstudyhub.dto;

import java.time.LocalDateTime;

/**
 * DTO for question paper responses.
 */
public class QuestionPaperDto {

    private Long id;
    private String title;
    private Integer year;
    private String originalFileName;
    private Long fileSize;
    private LocalDateTime uploadedAt;
    private String uploadedByName;
    private Long subjectId;
    private String subjectName;

    // Constructors
    public QuestionPaperDto() {
    }

    public QuestionPaperDto(Long id, String title, Integer year, String originalFileName, Long fileSize,
            LocalDateTime uploadedAt, String uploadedByName, Long subjectId, String subjectName) {
        this.id = id;
        this.title = title;
        this.year = year;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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
