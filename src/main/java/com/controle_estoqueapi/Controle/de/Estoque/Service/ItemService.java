package com.controle_estoqueapi.Controle.de.Estoque.Service;

import com.controle_estoqueapi.Controle.de.Estoque.DTO.ItemDTO;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Item;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ItemAlreadyRegisteredException;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ItemNotFoundException;
import com.controle_estoqueapi.Controle.de.Estoque.Mapper.ItemMapper;
import com.controle_estoqueapi.Controle.de.Estoque.Repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper = ItemMapper.INSTANCE;


    public ItemDTO createItem(ItemDTO itemDTO) throws ItemAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(itemDTO.getName());
        Item item = itemMapper.toModel(itemDTO);
        Item savedItem = itemRepository.save(item);
        return itemMapper.toDTO(savedItem);
    }

    public ItemDTO findByName(String name) throws ItemNotFoundException {
        Item foundItem = itemRepository.findByName(name)
                .orElseThrow(() -> new ItemNotFoundException(name));
        return itemMapper.toDTO(foundItem);
    }

    //Retorna a lista completa dos items
    public List<ItemDTO> listAll(){
        return itemRepository.findAll()
                .stream()
                .map(itemMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ItemDTO findById(Long id) throws ItemNotFoundException{
        Item foundItem = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
        return itemMapper.toDTO(foundItem);
    }

    public ItemDTO findByCode(Long code) throws ItemNotFoundException{
        Item foundItem = itemRepository.findByCode(code)
                .orElseThrow(() -> new ItemNotFoundException(code));
        return itemMapper.toDTO(foundItem);
    }

    public void deleteById(Long id) throws ItemNotFoundException{
        verifyIfExists(id);
        itemRepository.deleteById(id);
    }


    //Verificadores se existe e se não foi encontrado
    private void verifyIfIsAlreadyRegistered(String name) throws ItemAlreadyRegisteredException {
        Optional<Item> optSaveditem = itemRepository.findByName(name);
        if (optSaveditem.isPresent()) {
            throw new ItemAlreadyRegisteredException(name);
        }
    }

    private Item verifyIfExists(Long id) throws ItemNotFoundException {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }
}
