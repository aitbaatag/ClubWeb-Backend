package com.rungroop.web.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationDto {
    @NotEmpty
    private Long id;
    @NotEmpty
    private String password;
    @NotEmpty
    private String username;
    @NotEmpty
    @Email
    private String email;
}
