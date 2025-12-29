package com.rungroop.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {
    private Long id;

    @NotBlank(message = "Event name is required")
    private String name;

    @NotBlank(message = "Location is required")
    private String location;

    private String description;
    private String imageUrl;
    private LocalDateTime startDtateTime;
    private LocalDateTime endDtateTime;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Long clubId;
}

