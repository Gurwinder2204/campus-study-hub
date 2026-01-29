package com.campusstudyhub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import java.time.LocalDateTime;

/**
 * VideoLink entity representing curated YouTube video links for subjects.
 */
@Entity
@Table(name = "video_links")
public class VideoLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Video title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "YouTube URL is required")
    @URL(message = "Please provide a valid URL")
    @Column(name = "youtube_url", nullable = false)
    private String youtubeUrl;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(length = 500)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "added_by", nullable = false)
    private User addedBy;

    @Column(name = "added_at", nullable = false, updatable = false)
    private LocalDateTime addedAt;

    @PrePersist
    protected void onCreate() {
        this.addedAt = LocalDateTime.now();
        if (this.thumbnailUrl == null && this.youtubeUrl != null) {
            this.thumbnailUrl = extractThumbnailUrl(this.youtubeUrl);
        }
    }

    /**
     * Extracts YouTube video thumbnail URL from video URL.
     */
    private String extractThumbnailUrl(String youtubeUrl) {
        String videoId = extractVideoId(youtubeUrl);
        if (videoId != null) {
            return "https://img.youtube.com/vi/" + videoId + "/hqdefault.jpg";
        }
        return null;
    }

    /**
     * Extracts YouTube video ID from various URL formats.
     */
    private String extractVideoId(String url) {
        if (url == null)
            return null;

        // Handle youtu.be format
        if (url.contains("youtu.be/")) {
            int start = url.indexOf("youtu.be/") + 9;
            int end = url.indexOf("?", start);
            return end == -1 ? url.substring(start) : url.substring(start, end);
        }

        // Handle youtube.com format
        if (url.contains("v=")) {
            int start = url.indexOf("v=") + 2;
            int end = url.indexOf("&", start);
            return end == -1 ? url.substring(start) : url.substring(start, end);
        }

        // Handle embed format
        if (url.contains("/embed/")) {
            int start = url.indexOf("/embed/") + 7;
            int end = url.indexOf("?", start);
            return end == -1 ? url.substring(start) : url.substring(start, end);
        }

        return null;
    }

    // Constructors
    public VideoLink() {
    }

    public VideoLink(String title, String youtubeUrl, Subject subject, User addedBy) {
        this.title = title;
        this.youtubeUrl = youtubeUrl;
        this.subject = subject;
        this.addedBy = addedBy;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    /**
     * Gets the embed URL for the video.
     */
    public String getEmbedUrl() {
        String videoId = extractVideoId(this.youtubeUrl);
        if (videoId != null) {
            return "https://www.youtube.com/embed/" + videoId;
        }
        return this.youtubeUrl;
    }
}
