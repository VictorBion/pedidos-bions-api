package com.acai.bions_api.dtos;

import java.util.List;

public record PedidoDto(String clientname,
                        String tamanho,
                        String fruta,
                        List<String> acompanhamentos,
                        String calda) {
}
