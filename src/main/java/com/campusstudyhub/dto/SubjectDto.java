package com.campusstudyhub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO for subject creation and updates.
 */
public class SubjectDto {

    private Long id;

    @NotBlank(message = "Subject name is required")
    @Size(min = 2, max = 200, message = "Subject name must be between 2 and 200 characters")
    private String name;

    @Size(max = 50, message = "Subject code must be at most 50 characters")
    private String code;

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    @NotNull(message = "Semester is required")
    private Long semesterId;

    private Integer semesterNumber;

    private long notesCount;
    private long papersCount;
    private long videosCount;

    // Constructors
    public SubjectDto() {
    }

    public SubjectDto(String name, Long semesterId) {
        this.name = name;
        this.semesterId = semesterId;
    }

    public SubjectDto(Long id, String name, String code, String description, Long semesterId, Integer semesterNumber) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.semesterId = semesterId;
        this.semesterNumber = semesterNumber;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public Integer getSemesterNumber() {
        return semesterNumber;
    }

    public void setSemesterNumber(Integer semesterNumber) {
        this.semesterNumber = semesterNumber;
    }

    public long getNotesCount() {
        return notesCount;
    }

    public void setNotesCount(long notesCount) {
        this.notesCount = notesCount;
    }

    public long getPapersCount() {
        return papersCount;
    }

    public void setPapersCount(long papersCount) {
        this.papersCount = papersCount;
    }

    public long getVideosCount() {
        return videosCount;
    }

    public void setVideosCount(long videosCount) {
        this.videosCount = videosCount;
    }
}
