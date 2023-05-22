package com.controle_estoqueapi.Controle.de.Estoque.Exception;

public class ActionDeniedException extends RuntimeException{
    public ActionDeniedException(String message) {
        super(message);
    }
}
