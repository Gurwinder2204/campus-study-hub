package com.campusstudyhub.repository;

import com.campusstudyhub.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Note entity operations.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    /**
     * Find all notes for a given subject.
     * 
     * @param subjectId the subject ID
     * @return list of notes
     */
    List<Note> findBySubjectId(Long subjectId);

    /**
     * Find all notes uploaded by a specific user.
     * 
     * @param userId the user ID
     * @return list of notes
     */
    List<Note> findByUploadedById(Long userId);

    /**
     * Find recent notes ordered by upload date.
     * 
     * @return list of recent notes
     */
    @Query("SELECT n FROM Note n ORDER BY n.uploadedAt DESC")
    List<Note> findRecentNotes();

    /**
     * Find notes by subject with limit.
     * 
     * @param subjectId the subject ID
     * @return list of notes for the subject
     */
    List<Note> findBySubjectIdOrderByUploadedAtDesc(Long subjectId);

    /**
     * Count notes for a subject.
     * 
     * @param subjectId the subject ID
     * @return count of notes
     */
    long countBySubjectId(Long subjectId);
}
