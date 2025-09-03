package com.acai.bions_api.Repositories;

import com.acai.bions_api.models.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<PedidoModel, UUID> {
}
