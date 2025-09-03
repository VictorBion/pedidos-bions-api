package com.acai.bions_api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "TB_PEDIDO")
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String clientname;

    @Column(nullable = false, length = 5)
    private String tamanho;

    @Column(nullable = false, length = 10)
    private String fruta;

    private List<String> acompanhamentos;

    @Column(length = 20)
    private String calda;

}
