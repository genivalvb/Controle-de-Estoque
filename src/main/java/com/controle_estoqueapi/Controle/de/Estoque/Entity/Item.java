package com.controle_estoqueapi.Controle.de.Estoque.Entity;


import javax.persistence.Entity;
import javax.persistence.Table;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idItem;
    @Column(name = "description", nullable = false, length = 150)
    private String description = "";

    @Column(name = "code",nullable = false, length = 20)
    private long code;

    @Column(name = "name",nullable = false, length = 20)
    private String name = "";
    @Column(nullable = false, length = 20)
    private String ggroup = "";
    @Column(name = "family",nullable = false, length = 20)
    private String family = "";
    @Column(name = "unit",nullable = false, length = 20)
    private String unit = "";
    @Column(name = "stock_safe",columnDefinition = "float default 0")
    private float stockSafe;

    public Item() {
    }

    public Item(long idItem, String description, long code, String name, String ggroup, String family, String unit, float stockSafe) {
        this.idItem = idItem;
        this.description = description;
        this.code = code;
        this.name = name;
        this.ggroup = ggroup;
        this.family = family;
        this.unit = unit;
        this.stockSafe = stockSafe;
    }

    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getGgroup() {
        return ggroup;
    }

    public void setGgroup(String ggroup) {
        this.ggroup = ggroup;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getStockSafe() {
        return stockSafe;
    }

    public void setStockSafe(float stockSafe) {
        this.stockSafe = stockSafe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return idItem == item.idItem && Float.compare(item.stockSafe, stockSafe) == 0 && Objects.equals(description, item.description) && Objects.equals(code, item.code) && Objects.equals(name, item.name) && Objects.equals(ggroup, item.ggroup) && Objects.equals(family, item.family) && Objects.equals(unit, item.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idItem, description, code, name, ggroup, family, unit, stockSafe);
    }

    @Override
    public String toString() {
        return "Item{" +
                "idItem=" + idItem +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", ggroup='" + ggroup + '\'' +
                ", family='" + family + '\'' +
                ", unit='" + unit + '\'' +
                ", stockSafe=" + stockSafe +
                '}';
    }
}
