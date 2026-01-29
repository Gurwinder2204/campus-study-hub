package com.campusstudyhub.repository;

import com.campusstudyhub.entity.VideoLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for VideoLink entity operations.
 */
@Repository
public interface VideoLinkRepository extends JpaRepository<VideoLink, Long> {

    /**
     * Find all video links for a given subject.
     * 
     * @param subjectId the subject ID
     * @return list of video links
     */
    List<VideoLink> findBySubjectId(Long subjectId);

    /**
     * Find video links by subject ordered by added date descending.
     * 
     * @param subjectId the subject ID
     * @return list of video links
     */
    List<VideoLink> findBySubjectIdOrderByAddedAtDesc(Long subjectId);

    /**
     * Find all video links added by a specific user.
     * 
     * @param userId the user ID
     * @return list of video links
     */
    List<VideoLink> findByAddedById(Long userId);

    /**
     * Find recent video links ordered by added date.
     * 
     * @return list of recent video links
     */
    @Query("SELECT v FROM VideoLink v ORDER BY v.addedAt DESC")
    List<VideoLink> findRecentVideos();

    /**
     * Count video links for a subject.
     * 
     * @param subjectId the subject ID
     * @return count of video links
     */
    long countBySubjectId(Long subjectId);
}
