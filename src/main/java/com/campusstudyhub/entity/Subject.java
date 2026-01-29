package com.campusstudyhub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Subject entity representing a course within a semester.
 */
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Subject name is required")
    @Size(min = 2, max = 200, message = "Subject name must be between 2 and 200 characters")
    @Column(nullable = false)
    private String name;

    @Column(length = 50)
    private String code;

    @Column(length = 500)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes = new ArrayList<>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionPaper> papers = new ArrayList<>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VideoLink> videos = new ArrayList<>();

    // Constructors
    public Subject() {
    }

    public Subject(String name, Semester semester) {
        this.name = name;
        this.semester = semester;
    }

    public Subject(String name, String code, String description, Semester semester) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.semester = semester;
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

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<QuestionPaper> getPapers() {
        return papers;
    }

    public void setPapers(List<QuestionPaper> papers) {
        this.papers = papers;
    }

    public List<VideoLink> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoLink> videos) {
        this.videos = videos;
    }

    public void addNote(Note note) {
        notes.add(note);
        note.setSubject(this);
    }

    public void addPaper(QuestionPaper paper) {
        papers.add(paper);
        paper.setSubject(this);
    }

    public void addVideo(VideoLink video) {
        videos.add(video);
        video.setSubject(this);
    }
}
