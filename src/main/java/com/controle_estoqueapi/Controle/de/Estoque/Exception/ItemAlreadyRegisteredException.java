package com.controle_estoqueapi.Controle.de.Estoque.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ItemAlreadyRegisteredException extends Exception{
    public ItemAlreadyRegisteredException(String itemName){
        super(String.format("Item with Name %s already registered in the system", itemName));
    }
}
