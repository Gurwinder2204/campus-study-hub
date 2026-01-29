package com.campusstudyhub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

/**
 * DTO for video link creation and responses.
 */
public class VideoLinkDto {

    private Long id;

    @NotBlank(message = "Video title is required")
    private String title;

    @NotBlank(message = "YouTube URL is required")
    @URL(message = "Please provide a valid URL")
    private String youtubeUrl;

    private String thumbnailUrl;
    private String embedUrl;
    private String description;

    @NotNull(message = "Subject is required")
    private Long subjectId;

    private String subjectName;
    private String addedByName;
    private LocalDateTime addedAt;

    // Constructors
    public VideoLinkDto() {
    }

    public VideoLinkDto(String title, String youtubeUrl, Long subjectId) {
        this.title = title;
        this.youtubeUrl = youtubeUrl;
        this.subjectId = subjectId;
    }

    public VideoLinkDto(Long id, String title, String youtubeUrl, String thumbnailUrl, String embedUrl,
            String description, Long subjectId, String subjectName, String addedByName, LocalDateTime addedAt) {
        this.id = id;
        this.title = title;
        this.youtubeUrl = youtubeUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.embedUrl = embedUrl;
        this.description = description;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.addedByName = addedByName;
        this.addedAt = addedAt;
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

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getEmbedUrl() {
        return embedUrl;
    }

    public void setEmbedUrl(String embedUrl) {
        this.embedUrl = embedUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getAddedByName() {
        return addedByName;
    }

    public void setAddedByName(String addedByName) {
        this.addedByName = addedByName;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }
}
