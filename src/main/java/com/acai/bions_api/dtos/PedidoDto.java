package com.acai.bions_api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PedidoDto(

        @NotBlank(message = "O campo 'clientname' é obrigatório.") String clientname,
        @NotBlank(message = "O campo 'tamanho' é obrigatório.")   String tamanho,
        @NotBlank(message = "O campo 'fruta' é obrigatório.") String fruta,
        @NotNull @Size(min = 1, message = "A lista de acompanhamentos não pode estar vazia.")  List<String> acompanhamentos,
        @NotBlank(message = "O campo 'calda' é obrigatório.") String calda) {
}
