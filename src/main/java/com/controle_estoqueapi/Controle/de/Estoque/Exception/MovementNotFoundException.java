package com.controle_estoqueapi.Controle.de.Estoque.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovementNotFoundException extends Exception{

    public MovementNotFoundException(String movementName){
        super(String.format("Item with hashName %s not found in the system", movementName));
    }

    public MovementNotFoundException(Long idItem){
        super(String.format("Item with ID %s not found in the system", idItem));
    }
}
