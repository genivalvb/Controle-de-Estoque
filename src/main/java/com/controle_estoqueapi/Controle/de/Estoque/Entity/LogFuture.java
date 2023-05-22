package com.controle_estoqueapi.Controle.de.Estoque.Entity;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Immutable
@Subselect("SELECT UUID() AS ID, LOG_FUTURE.* FROM LOG_FUTURE")
@Table(name = "log_future")
public class LogFuture {
    @Id
    private String id;
    private long idItem;
    private String moveType;
    private String originDestination;
    private LocalDate data;
    private Float quantity;
    private Float stockMoment;


    public LogFuture(String moveType, String originDestination, LocalDate data, Float quantity) {
        this.moveType = moveType;
        this.originDestination = originDestination;
        this.data = data;
        this.quantity = quantity;
        this.stockMoment = stockMoment;
    }

    public LogFuture(LocalDate data, String originDestination, Float quantity) {
        this.originDestination = originDestination;
        this.data = data;
        this.quantity = quantity;
    }

    public LogFuture() {
    }

    public String getMoveType() {
        return moveType;
    }

    public void setMoveType(String tipoMovimentacao) {
        this.moveType = moveType;
    }

    public String getOriginDestination() {
        return originDestination;
    }

    public void setOriginDestination(String originDestination) {
        this.originDestination = originDestination;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantidade) {
        this.quantity = quantity;
    }

    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }
    public Float getStockMoment() {
        return stockMoment;
    }

    public void setStockMoment(Float stockMoment) {
        this.stockMoment = stockMoment;
    }
}
