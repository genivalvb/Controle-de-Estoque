package com.controle_estoqueapi.Controle.de.Estoque.Controller;

import com.controle_estoqueapi.Controle.de.Estoque.Entity.LogFuture;
import com.controle_estoqueapi.Controle.de.Estoque.Service.LogFutureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600,allowedHeaders = "*")
@RestController
@RequestMapping("log")
public class LogFutureController {
    @Autowired
    LogFutureService logFutureService;

    @GetMapping("/{idItem}")
    public ResponseEntity<List<LogFuture>> consultLogIdItem(@PathVariable("idItem") long idItem){
        return ResponseEntity.status(HttpStatus.OK).body(logFutureService.queryLogIdItem(idItem));
    }
}
