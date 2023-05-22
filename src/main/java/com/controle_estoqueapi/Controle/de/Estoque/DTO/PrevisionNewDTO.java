package com.controle_estoqueapi.Controle.de.Estoque.DTO;

import java.time.LocalDate;

public class PrevisionNewDTO {

    private String ordem;
    private float quantityExpected;
    private long idItem;
    private long idPerson;
    private LocalDate dateExpected;
    private boolean finished = false;


    public PrevisionNewDTO(String ordem, float quantityExpected, long idItem, long idPerson, LocalDate dateExpected, boolean finished) {
        this.ordem = ordem;
        this.quantityExpected = quantityExpected;
        this.idItem = idItem;
        this.idPerson = idPerson;
        this.dateExpected = dateExpected;
        this.finished = finished;
    }
    public PrevisionNewDTO(){}

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public float getQuantityExpected() {
        return quantityExpected;
    }

    public void setQuantityExpected(float quantityExpected) {
        this.quantityExpected = quantityExpected;
    }

    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }

    public long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(long idPerson) {
        this.idPerson = idPerson;
    }

    public LocalDate getDateExpected() {
        return dateExpected;
    }

    public void setDateExpected(LocalDate dateExpected) {
        this.dateExpected = dateExpected;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
