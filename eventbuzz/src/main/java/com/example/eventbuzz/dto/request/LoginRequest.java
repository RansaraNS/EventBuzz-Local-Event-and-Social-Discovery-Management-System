package com.example.eventbuzz.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    
    @NotBlank(message = "Email is required")
    @JsonProperty("emailOrPhone")
    private String emailOrPhone;

    @NotBlank(message = "Password is required")
    @JsonProperty("password")
    private String password;

    @JsonProperty("rememberMe")
    private boolean rememberMe = false;
}
