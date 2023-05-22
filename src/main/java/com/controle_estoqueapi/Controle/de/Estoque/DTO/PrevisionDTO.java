package com.controle_estoqueapi.Controle.de.Estoque.DTO;

import com.controle_estoqueapi.Controle.de.Estoque.Entity.Item;

import java.time.LocalDate;

public class PrevisionDTO {

    private long idPrevision;
    private Item item;
    private PersonPublicDTO person;
    private float quantityExpected;
    private LocalDate dateExpected;
    private String ordem;
    private boolean finished = false;

    public PrevisionDTO() {
    }

    public PrevisionDTO(long idPrevision, Item item, PersonPublicDTO person, float quantityExpected, LocalDate dateExpected, String ordem, boolean finished) {
        this.idPrevision = idPrevision;
        this.item = item;
        this.person = person;
        this.quantityExpected = quantityExpected;
        this.dateExpected = dateExpected;
        this.ordem = ordem;
        this.finished = finished;
    }

    public long getIdPrevision() {
        return idPrevision;
    }

    public void setIdPrevision(long idPrevision) {
        this.idPrevision = idPrevision;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public PersonPublicDTO getPerson() {
        return person;
    }

    public void setPerson(PersonPublicDTO person) {
        this.person = person;
    }

    public float getQuantityExpected() {
        return quantityExpected;
    }

    public void setQuantityExpected(float quantityExpected) {
        this.quantityExpected = quantityExpected;
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

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
