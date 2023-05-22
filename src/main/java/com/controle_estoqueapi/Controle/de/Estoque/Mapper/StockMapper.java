package com.controle_estoqueapi.Controle.de.Estoque.Mapper;


import com.controle_estoqueapi.Controle.de.Estoque.DTO.StockDTO;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StockMapper {

    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    Stock toModel(StockDTO stockDTO);

    StockDTO toDTO(Stock stock);
}
