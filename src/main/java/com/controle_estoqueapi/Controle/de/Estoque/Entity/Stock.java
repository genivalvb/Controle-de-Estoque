package com.controle_estoqueapi.Controle.de.Estoque.Entity;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idStock;
    private String localization;
    private Float realStock;
    private Float futureStock;
    private LocalDate futureDate;

    private String description;

    private Float unitPrice;

    private Float stockValue;

    private String controlAbc;

    private Integer percentageAccumulated;

    private String economicLot;


    @ManyToOne
    private Item item;

    public Stock() {
    }

    public Stock(String localization, Float realStock, Item item) {
        this.localization = localization;
        this.realStock = realStock;
        this.item = item;
    }

    public Stock(long idStock, String localization, Float realStock, Float futureStock, LocalDate futureDate, String description, Float unitPrice, Float stockValue, String controlAbc, Integer percentageAccumulated, String economicLot, Item item) {
        this.idStock = idStock;
        this.localization = localization;
        this.realStock = realStock;
        this.futureStock = futureStock;
        this.futureDate = futureDate;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stockValue = stockValue;
        this.controlAbc = controlAbc;
        this.percentageAccumulated = percentageAccumulated;
        this.economicLot = economicLot;
        this.item = item;
    }

    public long getIdStock() {
        return idStock;
    }

    public void setIdStock(long idStock) {
        this.idStock = idStock;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public Float getRealStock() {
        return realStock;
    }

    public void setRealStock(Float realStock) {
        this.realStock = realStock;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Float getFutureStock() {
        return futureStock;
    }

    public void setFutureStock(Float futureStock) {
        this.futureStock = futureStock;
    }

    public LocalDate getFutureDate() {
        return futureDate;
    }

    public void setFutureDate(LocalDate futureDate) {
        this.futureDate = futureDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Float getStockValue() {
        return stockValue;
    }

    public void setStockValue(Float stockValue) {
        this.stockValue = stockValue;
    }

    public String getControlAbc() {
        return controlAbc;
    }

    public void setControlAbc(String controlAbc) {
        this.controlAbc = controlAbc;
    }

    public Integer getPercentageAccumulated() {
        return percentageAccumulated;
    }

    public void setPercentageAccumulated(Integer percentageAccumulated) {
        this.percentageAccumulated = percentageAccumulated;
    }

    public String getEconomicLot() {
        return economicLot;
    }

    public void setEconomicLot(String economicLot) {
        this.economicLot = economicLot;
    }
}
