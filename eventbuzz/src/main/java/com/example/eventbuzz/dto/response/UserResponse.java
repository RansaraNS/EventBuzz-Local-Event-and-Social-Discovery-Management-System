package com.example.eventbuzz.dto.response;

import com.example.eventbuzz.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    
    @JsonProperty("firstName")
    private String firstName;
    
    @JsonProperty("lastName")
    private String lastName;
    
    @JsonProperty("fullName")
    private String fullName;
    
    @JsonProperty("dateOfBirth")
    private LocalDate dateOfBirth;
    
    @JsonProperty("gender")
    private User.Gender gender;
    
    @JsonProperty("profilePictureUrl")
    private String profilePictureUrl;
    
    @JsonProperty("bio")
    private String bio;
    
    @JsonProperty("isEmailVerified")
    private boolean isEmailVerified;
    
    @JsonProperty("isPhoneVerified")
    private boolean isPhoneVerified;
    
    @JsonProperty("isActive")
    private boolean isActive;
    
    @JsonProperty("role")
    private String role;
    
    @JsonProperty("lastLoginAt")
    private LocalDateTime lastLoginAt;
    
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;
    
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;
    
    // Factory method to create UserResponse from User entity
    public static UserResponse fromUser(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setFullName(user.getFirstName() + " " + user.getLastName());
        response.setDateOfBirth(user.getDateOfBirth());
        response.setGender(user.getGender());
        response.setProfilePictureUrl(user.getProfilePictureUrl());
        response.setBio(user.getBio());
        response.setEmailVerified(user.getIsEmailVerified());
        response.setPhoneVerified(user.getIsPhoneVerified());
        response.setActive(user.getIsActive());
        response.setRole(user.getRole().getName());
        response.setLastLoginAt(user.getLastLoginAt());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        return response;
    }
}