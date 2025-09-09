package com.acai.bions_api.dtos;

import jakarta.validation.constraints.NotBlank;

public record EmailDto(
        @NotBlank
        String to,
        @NotBlank
        String subject,
        @NotBlank
        String body) {
}
