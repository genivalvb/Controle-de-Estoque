package com.controle_estoqueapi.Controle.de.Estoque.Mapper;

import com.controle_estoqueapi.Controle.de.Estoque.DTO.ItemDTO;
import com.controle_estoqueapi.Controle.de.Estoque.DTO.MovementDTO;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Item;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.LocalDateTime;

@Mapper
public interface MovementMapper {
    MovementMapper INSTANCE = Mappers.getMapper(MovementMapper.class);

    Movement toModel(MovementDTO movementDTO);

    MovementDTO toDTO(Movement movement);
}
