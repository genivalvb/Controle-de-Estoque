package com.controle_estoqueapi.Controle.de.Estoque.DTO;

import com.controle_estoqueapi.Controle.de.Estoque.Entity.Item;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Person;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Stock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Builder
//@NoArgsConstructor
@AllArgsConstructor
public class MovementDTO {

    private Long idMovement = 0L;


    @Column(name ="date_movement")
    private LocalDateTime dateMovement;

    @Column(name = "type")
    private String type; //IN OUT

    @Column(name = "origin_destination")
    private String originDestination;

    @Column(name = "quantity")
    private float quantity;

    @ManyToOne
    private Stock stock;

    //@ManyToOne
    //private Item item;

    @ManyToOne
    private PersonPublicDTO person;

    public MovementDTO(){

    }

    public MovementDTO(Long idMovement, LocalDateTime dateMovement, String type, String originDestination, float quantity, PersonPublicDTO person, Stock stock) {
        this.idMovement = idMovement;
        this.dateMovement = dateMovement;
        this.type = type;
        this.originDestination = originDestination;
        this.quantity = quantity;
        this.stock = stock;
        this.person = person;
    }

    public Long getIdMovement() {
        return idMovement;
    }

    public void setIdMovement(Long idMovement) {
        this.idMovement = idMovement;
    }

    public LocalDateTime getDateMovement() {
        return dateMovement;
    }

    public void setDateMovement(LocalDateTime dateMovement) {
        this.dateMovement = dateMovement;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOriginDestination() {
        return originDestination;
    }

    public void setOriginDestination(String originDestination) {
        this.originDestination = originDestination;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public PersonPublicDTO getPerson() {
        return person;
    }

    public void setPerson(PersonPublicDTO person) {
        this.person = person;
    }
}
