package com.acai.bions_api.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PedidoComEmailDto(

        @NotNull @Valid
    EmailDto emailDto,
        @NotNull @Valid
    PedidoDto pedidoDto){}

