package com.example.eventbuzz.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyEmailRequest {
    
    @NotBlank(message = "Verification token is required")
    @JsonProperty("token")
    private String token;
}