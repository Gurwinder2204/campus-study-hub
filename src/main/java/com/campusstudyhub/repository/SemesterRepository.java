package com.campusstudyhub.repository;

import com.campusstudyhub.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Semester entity operations.
 */
@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {

    /**
     * Find a semester by its number.
     * 
     * @param number the semester number (1-8)
     * @return Optional containing the semester if found
     */
    Optional<Semester> findByNumber(Integer number);

    /**
     * Check if a semester exists with the given number.
     * 
     * @param number the semester number
     * @return true if semester exists
     */
    boolean existsByNumber(Integer number);
}
