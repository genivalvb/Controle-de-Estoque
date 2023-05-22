package com.controle_estoqueapi.Controle.de.Estoque.Controller;

import com.controle_estoqueapi.Controle.de.Estoque.DTO.PrevisionDTO;
import com.controle_estoqueapi.Controle.de.Estoque.DTO.PrevisionNewDTO;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Prevision;
import com.controle_estoqueapi.Controle.de.Estoque.Exception.ItemNotFoundException;
import com.controle_estoqueapi.Controle.de.Estoque.Mapper.Mapper;
import com.controle_estoqueapi.Controle.de.Estoque.Service.PrevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600,allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/previsoes")
public class PrevisionController {

    @Autowired
    private PrevisionService service;
    @Autowired
    private Mapper mapper;

    //Listar todas as previsões cadastradas
    @GetMapping("")
    public Iterable<Prevision> listPrevisions(){
        return service.listPrevisions();
    }

    // Cadastrar nova previsão
    @PostMapping("")
    public ResponseEntity<PrevisionDTO> addPrevision(@RequestBody PrevisionNewDTO previsionNewDTO) throws ItemNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toPrevisionDTO(service.registerPrevisions(mapper.toPrevision(previsionNewDTO))));
    }

    // Filtra previsões por iditem
    @GetMapping("/iditem/{iditem}")
    public ResponseEntity<List<Prevision>> consultarPrevisaoByIdItem(@PathVariable("iditem") Long iditem) throws ItemNotFoundException {
        List<Prevision> list = service.consultarByIdItem(iditem);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    //Alterar previsões
    @PutMapping("/alterar/{idPrevisao}")
    public ResponseEntity<Object> alterPrevision(@PathVariable ("idPrevision") Long idPrevision,
                                                  @Valid @RequestBody PrevisionNewDTO previsionNewDTO) throws ItemNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.toPrevisionDTO(service.alterPrevision(idPrevision, mapper.toPrevision(previsionNewDTO))));
    }

    //Excluir previsão (só pode ser excluído quando finalizada = false)
    @DeleteMapping("/excluir/{idPrevisao}")
    public ResponseEntity<String> deletePrevision(@PathVariable ("idPrevision") long idPrevision){
        service.deletePrevision(idPrevision);
        return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso");
    }
}
