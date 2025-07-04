package com.example.eventbuzz.dto;

import com.example.eventbuzz.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    @JsonProperty("id")
    private Long id;
    
    @Email(message = "Please provide a valid email address")
    @JsonProperty("email")
    private String email;
    
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Please provide a valid phone number")
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 100, message = "First name must be between 2 and 100 characters")
    @JsonProperty("firstName")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 100, message = "Last name must be between 2 and 100 characters")
    @JsonProperty("lastName")
    private String lastName;
    
    @JsonProperty("dateOfBirth")
    private LocalDate dateOfBirth;
    
    @JsonProperty("gender")
    private User.Gender gender;
    
    @JsonProperty("profilePictureUrl")
    private String profilePictureUrl;
    
    @Size(max = 500, message = "Bio cannot exceed 500 characters")
    @JsonProperty("bio")
    private String bio;
    
    @JsonProperty("roleId")
    private Long roleId;
    
    // Factory method to create UserDto from User entity
    public static UserDto fromUser(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setDateOfBirth(user.getDateOfBirth());
        dto.setGender(user.getGender());
        dto.setProfilePictureUrl(user.getProfilePictureUrl());
        dto.setBio(user.getBio());
        dto.setRoleId(user.getRole().getId());
        return dto;
    }
}