package com.rungroop.web.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;

    @NotEmpty(message = "First name is required")
    private String FirstName;
    @NotEmpty(message = "Last name is required")
    private String LastName;
    @NotEmpty(message = "Username is required")
    private String username;
}
