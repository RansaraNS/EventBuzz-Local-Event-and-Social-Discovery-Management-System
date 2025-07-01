package com.example.eventbuzz.repository;

import com.example.eventbuzz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User entity operations.
 * Provides custom queries for user-related database operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Find user by email address
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Find user by phone number
     */
    Optional<User> findByPhoneNumber(String phoneNumber);
    
    /**
     * Check if email already exists
     */
    boolean existsByEmail(String email);
    
    /**
     * Check if phone number already exists
     */
    boolean existsByPhoneNumber(String phoneNumber);
    
    /**
     * Find user by email or phone number (for login)
     */
    @Query("SELECT u FROM User u WHERE u.email = :identifier OR u.phoneNumber = :identifier")
    Optional<User> findByEmailOrPhoneNumber(@Param("identifier") String identifier);
    
    /**
     * Find all active users
     */
    List<User> findByIsActiveTrue();
    
    /**
     * Find all inactive users
     */
    List<User> findByIsActiveFalse();
    
    /**
     * Find users by email verification status
     */
    List<User> findByIsEmailVerified(Boolean isEmailVerified);
    
    /**
     * Find users by phone verification status
     */
    List<User> findByIsPhoneVerified(Boolean isPhoneVerified);
    
    /**
     * Find users by role name
     */
    @Query("SELECT u FROM User u WHERE u.role.name = :roleName")
    List<User> findByRoleName(@Param("roleName") String roleName);
    
    /**
     * Find users who haven't logged in since a specific date
     */
    @Query("SELECT u FROM User u WHERE u.lastLoginAt < :date OR u.lastLoginAt IS NULL")
    List<User> findUsersNotLoggedInSince(@Param("date") LocalDateTime date);
    
    /**
     * Find users created between dates
     */
    @Query("SELECT u FROM User u WHERE u.createdAt BETWEEN :startDate AND :endDate")
    List<User> findUsersCreatedBetween(@Param("startDate") LocalDateTime startDate, 
                                     @Param("endDate") LocalDateTime endDate);
    
    /**
     * Count total active users
     */
    long countByIsActiveTrue();
    
    /**
     * Count verified users (either email or phone verified)
     */
    @Query("SELECT COUNT(u) FROM User u WHERE u.isEmailVerified = true OR u.isPhoneVerified = true")
    long countVerifiedUsers();
    
    /**
     * Search users by name (first name or last name containing the search term)
     */
    @Query("SELECT u FROM User u WHERE " +
           "LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(CONCAT(u.firstName, ' ', u.lastName)) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<User> searchByName(@Param("searchTerm") String searchTerm);
    
    /**
     * Find users with incomplete profiles (missing phone or profile picture)
     */
    @Query("SELECT u FROM User u WHERE u.phoneNumber IS NULL OR u.profilePictureUrl IS NULL")
    List<User> findUsersWithIncompleteProfiles();
}