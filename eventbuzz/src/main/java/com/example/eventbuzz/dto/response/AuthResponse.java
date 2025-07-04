package com.example.eventbuzz.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    
    @JsonProperty("accessToken")
    private String accessToken;
    
    @JsonProperty("tokenType")
    private String tokenType = "Bearer";
    
    @JsonProperty("expiresIn")
    private long expiresIn;
    
    @JsonProperty("refreshToken")
    private String refreshToken;
    
    @JsonProperty("user")
    private UserResponse user;
    
    public AuthResponse(String accessToken, long expiresIn, String refreshToken, UserResponse user) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.user = user;
    }
}