package com.controle_estoqueapi.Controle.de.Estoque.Controller;


import com.controle_estoqueapi.Controle.de.Estoque.DTO.ItemDTO;
import com.controle_estoqueapi.Controle.de.Estoque.DTO.MovementDTO;
import com.controle_estoqueapi.Controle.de.Estoque.DTO.MovementNewDTO;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Movement;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ItemAlreadyRegisteredException;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ItemNotFoundException;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.MovementAlreadyRegisteredException;
import com.controle_estoqueapi.Controle.de.Estoque.Mapper.Mapper;
import com.controle_estoqueapi.Controle.de.Estoque.Service.MovementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600,allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/movements")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovementController{

    @Autowired
    private final MovementService movementService;

    @Autowired
    private Mapper mapper;

    @GetMapping
    public ResponseEntity<List<MovementDTO>> consult(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(movementService.consult()
                        .stream()
                        .map(mapper::toMovementDTO)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{idItem}")
    public ResponseEntity<List<MovementDTO>> consult(@PathVariable ("idItem") Long idItem) throws ItemNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(movementService.queryByIdItem(idItem)
                        .stream()
                        .map(mapper::toMovementDTO)
                        .collect(Collectors.toList()));
    }

    @PostMapping("/entrada")
    public ResponseEntity<MovementDTO> input(@RequestBody MovementNewDTO movementNewDTO) throws ItemNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toMovementDTO(movementService.inputItem(mapper.toMovement(movementNewDTO))));
    }

    @PostMapping("/saida")
    public ResponseEntity<MovementDTO> saida(@RequestBody MovementNewDTO movementNewDTO) throws ItemNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toMovementDTO(movementService.exitItem(mapper.toMovement(movementNewDTO))));
    }
}
