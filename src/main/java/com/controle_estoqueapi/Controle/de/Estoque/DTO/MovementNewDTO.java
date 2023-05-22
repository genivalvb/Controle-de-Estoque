package com.controle_estoqueapi.Controle.de.Estoque.DTO;

public class MovementNewDTO {
    private String originDestination;
    private float quantity;
    private long idItem;
    private long idPerson;

    public MovementNewDTO(String originDestination, float quantity, long idItem, long idPerson) {
        this.originDestination = originDestination;
        this.quantity = quantity;
        this.idItem = idItem;
        this.idPerson = idPerson;
    }

    public MovementNewDTO(){}

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
}
