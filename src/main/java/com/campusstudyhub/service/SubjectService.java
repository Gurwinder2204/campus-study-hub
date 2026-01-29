package com.campusstudyhub.service;

import com.campusstudyhub.dto.SubjectDto;
import com.campusstudyhub.entity.Semester;
import com.campusstudyhub.entity.Subject;
import com.campusstudyhub.exception.ResourceNotFoundException;
import com.campusstudyhub.repository.NoteRepository;
import com.campusstudyhub.repository.QuestionPaperRepository;
import com.campusstudyhub.repository.SemesterRepository;
import com.campusstudyhub.repository.SubjectRepository;
import com.campusstudyhub.repository.VideoLinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for subject management operations.
 */
@Service
@Transactional
public class SubjectService {

    private static final Logger log = LoggerFactory.getLogger(SubjectService.class);

    private final SubjectRepository subjectRepository;
    private final SemesterRepository semesterRepository;
    private final NoteRepository noteRepository;
    private final QuestionPaperRepository questionPaperRepository;
    private final VideoLinkRepository videoLinkRepository;

    public SubjectService(SubjectRepository subjectRepository,
            SemesterRepository semesterRepository,
            NoteRepository noteRepository,
            QuestionPaperRepository questionPaperRepository,
            VideoLinkRepository videoLinkRepository) {
        this.subjectRepository = subjectRepository;
        this.semesterRepository = semesterRepository;
        this.noteRepository = noteRepository;
        this.questionPaperRepository = questionPaperRepository;
        this.videoLinkRepository = videoLinkRepository;
    }

    /**
     * Create a new subject.
     * 
     * @param dto the subject data
     * @return the created subject DTO
     */
    public SubjectDto createSubject(SubjectDto dto) {
        log.info("Creating subject: {} for semester {}", dto.getName(), dto.getSemesterId());

        Semester semester = semesterRepository.findById(dto.getSemesterId())
                .orElseThrow(() -> new ResourceNotFoundException("Semester", "id", dto.getSemesterId()));

        Subject subject = new Subject();
        subject.setName(dto.getName());
        subject.setCode(dto.getCode());
        subject.setDescription(dto.getDescription());
        subject.setSemester(semester);

        subject = subjectRepository.save(subject);
        log.info("Subject created: {} with ID {}", subject.getName(), subject.getId());

        return toDto(subject);
    }

    /**
     * Update an existing subject.
     * 
     * @param id  the subject ID
     * @param dto the updated data
     * @return the updated subject DTO
     */
    public SubjectDto updateSubject(Long id, SubjectDto dto) {
        log.info("Updating subject: {}", id);

        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));

        subject.setName(dto.getName());
        subject.setCode(dto.getCode());
        subject.setDescription(dto.getDescription());

        if (dto.getSemesterId() != null && !dto.getSemesterId().equals(subject.getSemester().getId())) {
            Semester semester = semesterRepository.findById(dto.getSemesterId())
                    .orElseThrow(() -> new ResourceNotFoundException("Semester", "id", dto.getSemesterId()));
            subject.setSemester(semester);
        }

        subject = subjectRepository.save(subject);
        return toDto(subject);
    }

    /**
     * Delete a subject.
     * 
     * @param id the subject ID
     */
    public void deleteSubject(Long id) {
        log.info("Deleting subject: {}", id);
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));
        subjectRepository.delete(subject);
        log.info("Subject deleted: {}", id);
    }

    /**
     * Get a subject by ID.
     * 
     * @param id the subject ID
     * @return the subject DTO
     */
    @Transactional(readOnly = true)
    public SubjectDto getSubject(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));
        return toDtoWithCounts(subject);
    }

    /**
     * Get a subject entity by ID.
     * 
     * @param id the subject ID
     * @return the subject entity
     */
    @Transactional(readOnly = true)
    public Subject getSubjectEntity(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));
    }

    /**
     * List all subjects.
     * 
     * @return list of subject DTOs
     */
    @Transactional(readOnly = true)
    public List<SubjectDto> listAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(this::toDtoWithCounts)
                .collect(Collectors.toList());
    }

    /**
     * List subjects by semester ID.
     * 
     * @param semesterId the semester ID
     * @return list of subject DTOs
     */
    @Transactional(readOnly = true)
    public List<SubjectDto> listBySemester(Long semesterId) {
        return subjectRepository.findBySemesterId(semesterId).stream()
                .map(this::toDtoWithCounts)
                .collect(Collectors.toList());
    }

    /**
     * List subjects by semester number.
     * 
     * @param semesterNumber the semester number (1-8)
     * @return list of subject DTOs
     */
    @Transactional(readOnly = true)
    public List<SubjectDto> listBySemesterNumber(Integer semesterNumber) {
        return subjectRepository.findBySemesterNumber(semesterNumber).stream()
                .map(this::toDtoWithCounts)
                .collect(Collectors.toList());
    }

    /**
     * Search subjects by name.
     * 
     * @param query the search query
     * @return list of matching subject DTOs
     */
    @Transactional(readOnly = true)
    public List<SubjectDto> searchSubjects(String query) {
        return subjectRepository.findByNameContainingIgnoreCase(query).stream()
                .map(this::toDtoWithCounts)
                .collect(Collectors.toList());
    }

    /**
     * Get all semesters.
     * 
     * @return list of semesters
     */
    @Transactional(readOnly = true)
    public List<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    /**
     * Get semester by ID.
     * 
     * @param id the semester ID
     * @return the semester
     */
    @Transactional(readOnly = true)
    public Semester getSemester(Long id) {
        return semesterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Semester", "id", id));
    }

    /**
     * Convert Subject entity to DTO.
     */
    private SubjectDto toDto(Subject subject) {
        SubjectDto dto = new SubjectDto();
        dto.setId(subject.getId());
        dto.setName(subject.getName());
        dto.setCode(subject.getCode());
        dto.setDescription(subject.getDescription());
        dto.setSemesterId(subject.getSemester().getId());
        dto.setSemesterNumber(subject.getSemester().getNumber());
        return dto;
    }

    /**
     * Convert Subject entity to DTO with resource counts.
     */
    private SubjectDto toDtoWithCounts(Subject subject) {
        SubjectDto dto = toDto(subject);
        dto.setNotesCount(noteRepository.countBySubjectId(subject.getId()));
        dto.setPapersCount(questionPaperRepository.countBySubjectId(subject.getId()));
        dto.setVideosCount(videoLinkRepository.countBySubjectId(subject.getId()));
        return dto;
    }
}
