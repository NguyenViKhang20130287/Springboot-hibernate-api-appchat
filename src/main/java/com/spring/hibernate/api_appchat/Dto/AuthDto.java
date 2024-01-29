package com.spring.hibernate.api_appchat.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {
    private String email;
    private String password;
    private String otp;
    private String displayName;
    private String newPassword;
}
