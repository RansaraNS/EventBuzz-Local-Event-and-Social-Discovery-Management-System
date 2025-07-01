package com.example.eventbuzz.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * VerificationToken entity for handling email/phone verification and password reset tokens.
 * Tokens have expiration times and can only be used once.
 */
@Entity
@Table(name = "verification_tokens")
public class VerificationToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Token is required")
    @Column(name = "token", nullable = false, unique = true)
    private String token;
    
    @NotNull(message = "Token type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "token_type", nullable = false)
    private TokenType tokenType;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @NotNull(message = "Expiration date is required")
    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;
    
    @Column(name = "used_at")
    private LocalDateTime usedAt;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    // Enums
    public enum TokenType {
        EMAIL_VERIFICATION,
        PHONE_VERIFICATION,
        PASSWORD_RESET
    }
    
    // Constructors
    public VerificationToken() {
        this.createdAt = LocalDateTime.now();
    }
    
    public VerificationToken(String token, TokenType tokenType, User user, LocalDateTime expiresAt) {
        this();
        this.token = token;
        this.tokenType = tokenType;
        this.user = user;
        this.expiresAt = expiresAt;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public TokenType getTokenType() {
        return tokenType;
    }
    
    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
    
    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
    
    public LocalDateTime getUsedAt() {
        return usedAt;
    }
    
    public void setUsedAt(LocalDateTime usedAt) {
        this.usedAt = usedAt;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    // Utility methods
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }
    
    public boolean isUsed() {
        return usedAt != null;
    }
    
    public boolean isValid() {
        return !isExpired() && !isUsed();
    }
    
    public void markAsUsed() {
        this.usedAt = LocalDateTime.now();
    }
    
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VerificationToken)) return false;
        VerificationToken that = (VerificationToken) o;
        return token != null && token.equals(that.token);
    }
    
    @Override
    public int hashCode() {
        return token != null ? token.hashCode() : 0;
    }
    
    @Override
    public String toString() {
        return "VerificationToken{" +
                "id=" + id +
                ", tokenType=" + tokenType +
                ", isExpired=" + isExpired() +
                ", isUsed=" + isUsed() +
                ", createdAt=" + createdAt +
                '}';
    }
}