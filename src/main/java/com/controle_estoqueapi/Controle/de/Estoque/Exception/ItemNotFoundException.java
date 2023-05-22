package com.controle_estoqueapi.Controle.de.Estoque.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends Exception{
    public ItemNotFoundException(String itemName){
        super(String.format("Item with hashName %s not found in the system", itemName));
    }

    public ItemNotFoundException(Long id){
        super(String.format("Item with ID %s not found in the system", id));
    }
}
