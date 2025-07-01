package com.example.eventbuzz.repository;

import com.example.eventbuzz.entity.VerificationToken;
import com.example.eventbuzz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for VerificationToken entity operations.
 * Provides methods for token-related database operations.
 */
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    
    /**
     * Find verification token by token string
     */
    Optional<VerificationToken> findByToken(String token);
    
    /**
     * Find tokens by user and token type
     */
    List<VerificationToken> findByUserAndTokenType(User user, VerificationToken.TokenType tokenType);
    
    /**
     * Find valid (unused and not expired) token by token string
     */
    @Query("SELECT vt FROM VerificationToken vt WHERE vt.token = :token " +
           "AND vt.usedAt IS NULL AND vt.expiresAt > :currentTime")
    Optional<VerificationToken> findValidToken(@Param("token") String token, 
                                             @Param("currentTime") LocalDateTime currentTime);
    
    /**
     * Find valid tokens by user and token type
     */
    @Query("SELECT vt FROM VerificationToken vt WHERE vt.user = :user " +
           "AND vt.tokenType = :tokenType AND vt.usedAt IS NULL " +
           "AND vt.expiresAt > :currentTime")
    List<VerificationToken> findValidTokensByUserAndType(@Param("user") User user,
                                                        @Param("tokenType") VerificationToken.TokenType tokenType,
                                                        @Param("currentTime") LocalDateTime currentTime);
    
    /**
     * Find expired tokens
     */
    @Query("SELECT vt FROM VerificationToken vt WHERE vt.expiresAt < :currentTime")
    List<VerificationToken> findExpiredTokens(@Param("currentTime") LocalDateTime currentTime);
    
    /**
     * Delete expired tokens (cleanup method)
     */
    @Modifying
    @Query("DELETE FROM VerificationToken vt WHERE vt.expiresAt < :currentTime")
    int deleteExpiredTokens(@Param("currentTime") LocalDateTime currentTime);
    
    /**
     * Delete used tokens older than specified date
     */
    @Modifying
    @Query("DELETE FROM VerificationToken vt WHERE vt.usedAt IS NOT NULL " +
           "AND vt.usedAt < :cutoffDate")
    int deleteUsedTokensOlderThan(@Param("cutoffDate") LocalDateTime cutoffDate);
    
    /**
     * Invalidate (mark as used) all tokens of a specific type for a user
     */
    @Modifying
    @Query("UPDATE VerificationToken vt SET vt.usedAt = :currentTime " +
           "WHERE vt.user = :user AND vt.tokenType = :tokenType " +
           "AND vt.usedAt IS NULL")
    int invalidateUserTokensByType(@Param("user") User user,
                                 @Param("tokenType") VerificationToken.TokenType tokenType,
                                 @Param("currentTime") LocalDateTime currentTime);
    
    /**
     * Count active tokens by user
     */
    @Query("SELECT COUNT(vt) FROM VerificationToken vt WHERE vt.user = :user " +
           "AND vt.usedAt IS NULL AND vt.expiresAt > :currentTime")
    long countActiveTokensByUser(@Param("user") User user, 
                               @Param("currentTime") LocalDateTime currentTime);
    
    /**
     * Find tokens by user (all tokens regardless of status)
     */
    List<VerificationToken> findByUserOrderByCreatedAtDesc(User user);
}