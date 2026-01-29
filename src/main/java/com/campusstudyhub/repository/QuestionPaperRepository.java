package com.campusstudyhub.repository;

import com.campusstudyhub.entity.QuestionPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for QuestionPaper entity operations.
 */
@Repository
public interface QuestionPaperRepository extends JpaRepository<QuestionPaper, Long> {

    /**
     * Find all question papers for a given subject.
     * 
     * @param subjectId the subject ID
     * @return list of question papers
     */
    List<QuestionPaper> findBySubjectId(Long subjectId);

    /**
     * Find question papers by subject ordered by year descending.
     * 
     * @param subjectId the subject ID
     * @return list of question papers
     */
    List<QuestionPaper> findBySubjectIdOrderByYearDesc(Long subjectId);

    /**
     * Find question papers by exam year.
     * 
     * @param year the exam year
     * @return list of question papers
     */
    List<QuestionPaper> findByYear(Integer year);

    /**
     * Find all question papers uploaded by a specific user.
     * 
     * @param userId the user ID
     * @return list of question papers
     */
    List<QuestionPaper> findByUploadedById(Long userId);

    /**
     * Find recent question papers ordered by upload date.
     * 
     * @return list of recent question papers
     */
    @Query("SELECT qp FROM QuestionPaper qp ORDER BY qp.uploadedAt DESC")
    List<QuestionPaper> findRecentPapers();

    /**
     * Count question papers for a subject.
     * 
     * @param subjectId the subject ID
     * @return count of question papers
     */
    long countBySubjectId(Long subjectId);
}
