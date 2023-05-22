package com.controle_estoqueapi.Controle.de.Estoque.Mapper;

import com.controle_estoqueapi.Controle.de.Estoque.DTO.ItemDTO;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    Item toModel(ItemDTO itemDTO);

    ItemDTO toDTO(Item item);
}
