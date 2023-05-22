package com.controle_estoqueapi.Controle.de.Estoque.Mapper;

import com.controle_estoqueapi.Controle.de.Estoque.Entity.Reserve;
import org.mapstruct.factory.Mappers;

public interface ReserveMapper {
    ReserveMapper INSTANCE = Mappers.getMapper(ReserveMapper.class);

    Reserve toModel(ReserveMapper reserveDTO);

    ReserveMapper toDTO(Reserve reserve);
}
