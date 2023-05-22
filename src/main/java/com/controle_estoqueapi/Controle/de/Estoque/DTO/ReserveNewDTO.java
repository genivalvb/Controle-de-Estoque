package com.controle_estoqueapi.Controle.de.Estoque.DTO;

import com.controle_estoqueapi.Controle.de.Estoque.Entity.Item;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ReserveNewDTO {


    private boolean finished = false;


    private float quantityReserve;


    private LocalDate dateExpected;


    private String ordem;


    private long idPerson;


    private long idItem;

    public ReserveNewDTO(){

    }

    public ReserveNewDTO(boolean finished, float quantityReserve, LocalDate dateExpected, String ordem, long idPerson, long idItem) {
        this.finished = finished;
        this.quantityReserve = quantityReserve;
        this.dateExpected = dateExpected;
        this.ordem = ordem;
        this.idPerson = idPerson;
        this.idItem = idItem;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public float getQuantityReserve() {
        return quantityReserve;
    }

    public void setQuantityReserve(float quantityReserve) {
        this.quantityReserve = quantityReserve;
    }

    public LocalDate getDateExpected() {
        return dateExpected;
    }

    public void setDateExpected(LocalDate dateExpected) {
        this.dateExpected = dateExpected;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(long idPerson) {
        this.idPerson = idPerson;
    }

    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }
}
