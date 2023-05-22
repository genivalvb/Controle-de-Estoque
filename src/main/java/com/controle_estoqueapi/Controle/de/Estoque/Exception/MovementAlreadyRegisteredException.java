package com.controle_estoqueapi.Controle.de.Estoque.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MovementAlreadyRegisteredException extends Exception{
    public MovementAlreadyRegisteredException(String movementName){
        super(String.format("Item with Name %s already registered in the system", movementName));
    }
}
