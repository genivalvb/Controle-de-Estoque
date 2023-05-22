package com.controle_estoqueapi.Controle.de.Estoque.Controller;

import com.controle_estoqueapi.Controle.de.Estoque.DTO.PersonNewDTO;
import com.controle_estoqueapi.Controle.de.Estoque.DTO.PersonPublicDTO;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Person;
import com.controle_estoqueapi.Controle.de.Estoque.Mapper.Mapper;
import com.controle_estoqueapi.Controle.de.Estoque.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600,allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private Mapper mapper;

    @GetMapping
    public ResponseEntity<List<PersonPublicDTO>> queryPeople(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(personService.listPeople()
                        .stream()
                        .map(mapper::toPersonPublicDTO)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{email}")
    public ResponseEntity<PersonPublicDTO> queryPersonByMail(@PathVariable("mail")String mail){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPersonPublicDTO(personService.queryByMail(mail)));
    }

    @PostMapping("/novo")
    public ResponseEntity<PersonPublicDTO> salvar(@RequestBody PersonNewDTO personNewDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toPersonPublicDTO(personService.save(mapper.toPerson(personNewDTO))));
    }

    //Alterar para DTO
    @PutMapping("/alterar/{mail}")
    public ResponseEntity<PersonNewDTO> alter(@PathVariable("mail") String mail, @RequestBody PersonNewDTO personNewDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(mapper.toPersonNewDTO(personService.alter(mail, mapper.toPerson(personNewDTO))));
    }

    //Alterar de perfil para role
    @PutMapping("/desabilitar/{mail}")
    public ResponseEntity<Person> disable(@PathVariable("mail") String mail){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(personService.disablePerson(mail));
    }
}
