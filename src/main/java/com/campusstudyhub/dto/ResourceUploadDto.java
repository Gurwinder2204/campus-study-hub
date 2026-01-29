package com.campusstudyhub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

/**
 * DTO for resource (note/paper) upload requests.
 */
public class ResourceUploadDto {

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Subject is required")
    private Long subjectId;

    private Integer year; // For question papers

    private MultipartFile file;

    // Constructors
    public ResourceUploadDto() {
    }

    public ResourceUploadDto(String title, Long subjectId) {
        this.title = title;
        this.subjectId = subjectId;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
