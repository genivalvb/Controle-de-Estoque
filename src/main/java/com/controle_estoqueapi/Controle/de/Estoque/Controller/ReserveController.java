package com.controle_estoqueapi.Controle.de.Estoque.Controller;

import com.controle_estoqueapi.Controle.de.Estoque.DTO.ReserveDTO;
import com.controle_estoqueapi.Controle.de.Estoque.DTO.ReserveNewDTO;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Reserve;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ItemNotFoundException;
import com.controle_estoqueapi.Controle.de.Estoque.Mapper.Mapper;
import com.controle_estoqueapi.Controle.de.Estoque.Service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600,allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/reservas")
public class ReserveController {

    @Autowired
    ReserveService service;

    @Autowired
    private Mapper mapper;

    @GetMapping
    public ResponseEntity<List<Reserve>> queryReserves(){
        List<Reserve> list = service.query();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{iditem}")
    public ResponseEntity<List<Reserve>> queryReservesByIdItem(@PathVariable("iditem") Long iditem) throws ItemNotFoundException {
        List<Reserve> list = service.queryByIdItem(iditem);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    // Salvar reserva nova
    @PostMapping("")
    public ResponseEntity<ReserveDTO> salvar(@Valid @RequestBody ReserveNewDTO reserveNewDTO) throws ItemNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toReserveDTO(service.salve(mapper.toReserve(reserveNewDTO))));
    }

    @PutMapping("/{idreserve}")
    public ResponseEntity<Object> alter(
            @PathVariable("idreserve") Long idreserve,
            @Valid @RequestBody ReserveNewDTO reserveNewDTO) throws ItemNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.toReserveDTO(service.alter(idreserve, mapper.toReserve(reserveNewDTO))));
    }

    @DeleteMapping("/{idreserva}")
    public ResponseEntity<Void> delete(@PathVariable("idreserve") Long idreserve) {
        service.delete(idreserve);
        return ResponseEntity.noContent().build();
    }
}
