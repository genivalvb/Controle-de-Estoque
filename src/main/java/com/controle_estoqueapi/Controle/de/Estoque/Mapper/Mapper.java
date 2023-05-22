package com.controle_estoqueapi.Controle.de.Estoque.Mapper;

import com.controle_estoqueapi.Controle.de.Estoque.DTO.*;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.*;
import com.controle_estoqueapi.Controle.de.Estoque.Service.RoleService;
import com.controle_estoqueapi.Controle.de.Estoque.Utils.ConversorDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

@Component
public class Mapper {

    @Autowired
    private RoleService roleService;


    public MovementDTO toMovementDTO (Movement movement) {

        long idMovement = movement.getIdMovement();
        Stock stock = movement.getStock();
        Person person = movement.getPerson();
        String type = movement.getType();
        String originDestination = movement.getOriginDestination();
        float quantity = movement.getQuantity();
        //LocalDateTime dateMovement = ConversorDate.toLocalDateTime(movement.getDateMovement());
        LocalDateTime dateMovement = movement.getDateMovement();

        return new MovementDTO(idMovement,dateMovement,type,originDestination,quantity,this.toPersonPublicDTO(person),stock);
    }


    public Movement toMovement(MovementDTO movementDTO) {
        return new Movement(
                //ConversorDate.toInstant(movementDTO.getDateMovement()),
                movementDTO.getDateMovement(),
                movementDTO.getType(),
                movementDTO.getOriginDestination(),
                movementDTO.getQuantity(),
                movementDTO.getStock(),
                movementDTO.getStock().getItem(),
                this.toPerson(movementDTO.getPerson()));
    }

    public PersonPublicDTO toPersonPublicDTO(Person person){
        long idPerson = person.getIdPerson();
        String name = person.getName();
        String mail = person.getMail();
        String role = person.getRoles().stream().findFirst().orElseThrow(() -> new EntityNotFoundException("Role não encontrada")).getAuthority();


        return new PersonPublicDTO( idPerson, name, mail, role);
    }

    public PersonNewDTO toPersonNewDTO(Person person){
        long idPerson = person.getIdPerson();

        String name = person.getName();
        String mail = person.getMail();
        String role = person.getRoles().stream().findFirst().orElseThrow(() -> new EntityNotFoundException("Role não encontrada")).getAuthority();
        String password = person.getPassword();

        return new PersonNewDTO( idPerson, name, password, mail, role);
    }

    public Person toPerson(PersonPublicDTO personPublicDTO){
        return new Person(personPublicDTO.getName(),"","");
    }

    public Person toPerson(PersonNewDTO personNewDTO){
        Person person = new Person();
        Role role = roleService.queryByAuthority(personNewDTO.getRole());
        HashSet<Role> roles = new HashSet<Role>();
        roles.add(role);
        person.setIdPerson(0l);
        person.setName(personNewDTO.getName());
        person.setMail(personNewDTO.getMail());
        person.setPassword(personNewDTO.getPassword());
        person.setRoles(roles);
        return person;
    }

    public Movement toMovement(MovementNewDTO movementNewDTO){
        Person u = new Person();
        u.setIdPerson(movementNewDTO.getIdPerson());

        Item i = new Item();
        i.setIdItem(movementNewDTO.getIdItem());

        Movement m = new Movement();
        m.setQuantity(movementNewDTO.getQuantity());
        m.setOriginDestination(movementNewDTO.getOriginDestination());
        m.setPerson(u);
        m.setItem(i);

        return m;
    }


    public MovementNewDTO toMovementNewDTO(Movement movement){
        return new MovementNewDTO(movement.getOriginDestination()
                ,movement.getQuantity()
                ,movement.getItem().getIdItem()
                , movement.getPerson().getIdPerson());
    }

    public PrevisionDTO toPrevisionDTO (Prevision prevision) {

        long idPrevision = prevision.getIdPrevision();
        String ordem = prevision.getOrdem();
        Person person = prevision.getPerson();
        Item item = prevision.getItem();
        float quantityExpected = prevision.getQuantityExpected();
        LocalDate dateExpected = prevision.getDateExpected();
        boolean finished = prevision.getFinished();

        return new PrevisionDTO(idPrevision,item,this.toPersonPublicDTO(person),quantityExpected,dateExpected,ordem,finished);
    }


    public Prevision toPrevision(PrevisionDTO previsionDTO) {
        return new Prevision(
                previsionDTO.getIdPrevision(),
                previsionDTO.getItem(),
                this.toPerson(previsionDTO.getPerson()),
                previsionDTO.getQuantityExpected(),
                previsionDTO.getDateExpected(),
                previsionDTO.getOrdem(),
                previsionDTO.isFinished());
    }


    public Prevision toPrevision(PrevisionNewDTO previsionNewDTO){
        Person u = new Person();
        u.setIdPerson(previsionNewDTO.getIdPerson());

        Item i = new Item();
        i.setIdItem(previsionNewDTO.getIdItem());

        Prevision p = new Prevision();
        p.setQuantityExpected(previsionNewDTO.getQuantityExpected());
        p.setDateExpected(previsionNewDTO.getDateExpected());
        p.setOrdem(previsionNewDTO.getOrdem());
        p.setFinished(previsionNewDTO.isFinished());
        p.setPerson(u);
        p.setItem(i);

        return p;
    }


    public PrevisionNewDTO previsionNewDTO(Prevision prevision){
        return new PrevisionNewDTO(prevision.getOrdem(),
                prevision.getQuantityExpected(),
                prevision.getItem().getIdItem(),
                prevision.getPerson().getIdPerson(),
                prevision.getDateExpected(),
                prevision.getFinished());
    }



    public ReserveDTO toReserveDTO(Reserve reserve) {

        long idReserve = reserve.getIdReserve();
        boolean finished = reserve.isFinished();
        float quantityReserve = reserve.getQuantityReserve();
        LocalDate dateExpected = reserve.getDateExpected();
        String ordem = reserve.getOrdem();
        Person person = reserve.getPerson();
        Item item = reserve.getItem();

        return new ReserveDTO(idReserve,finished,quantityReserve,dateExpected, ordem,this.toPersonPublicDTO(person),item);
    }


    public Reserve toReserve(ReserveDTO reserveDTO) {
        return new Reserve(
                reserveDTO.getIdReserve(),
                reserveDTO.isFinished(),
                reserveDTO.getQuantityReserve(),
                reserveDTO.getDateExpected(),
                reserveDTO.getOrdem(),
                this.toPerson(reserveDTO.getPerson()),
                reserveDTO.getItem());
    }

    public Reserve toReserve(ReserveNewDTO reserveNewDTO){
        Person u = new Person();
        u.setIdPerson(reserveNewDTO.getIdPerson());

        Item i = new Item();
        i.setIdItem(reserveNewDTO.getIdItem());

        Reserve r = new Reserve();
        r.setQuantityReserve(reserveNewDTO.getQuantityReserve());
        r.setDateExpected(reserveNewDTO.getDateExpected());
        r.setOrdem(reserveNewDTO.getOrdem());
        r.setFinished(reserveNewDTO.isFinished());
        r.setPerson(u);
        r.setItem(i);

        return r;
    }

    public ReserveNewDTO reserveNewDTO(Reserve reserve){
        return new ReserveNewDTO(reserve.isFinished(),
                reserve.getQuantityReserve(),
                reserve.getDateExpected(),
                reserve.getOrdem(),
                reserve.getPerson().getIdPerson(),
                reserve.getItem().getIdItem());
    }




}
