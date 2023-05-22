package com.controle_estoqueapi.Controle.de.Estoque.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "movement")
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovement;
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
    @ManyToOne
    private Item item;
    @ManyToOne
    private Person person;

    public Movement(){};

    public Movement(long idMovement, LocalDateTime dateMovement, String type, String originDestination, float quantity, Stock stock, Item item, Person person) {
        this.idMovement = idMovement;
        this.dateMovement = dateMovement;
        this.type = type;
        this.originDestination = originDestination;
        this.quantity = quantity;
        this.stock = stock;
        this.item = item;
        this.person = person;
    }
    public Movement(LocalDateTime dateMovement, String type, String originDestination, float quantity, Stock stock, Item item, Person person) {
        this.dateMovement = dateMovement;
        this.type = type;
        this.originDestination = originDestination;
        this.quantity = quantity;
        this.stock = stock;
        this.item = item;
        this.person = person;
    }

    public long getIdMovement() {
        return idMovement;
    }

    public void setIdMovement(long idMovement) {
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movement)) return false;
        Movement movement = (Movement) o;
        return idMovement == movement.idMovement && Float.compare(movement.quantity, quantity) == 0 && Objects.equals(dateMovement, movement.dateMovement) && Objects.equals(type, movement.type) && Objects.equals(originDestination, movement.originDestination) && Objects.equals(stock, movement.stock) && Objects.equals(item, movement.item) && Objects.equals(person, movement.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMovement, dateMovement, type, originDestination, quantity, stock, item, person);
    }

    @Override
    public String toString() {
        return "Movement{" +
                "idMovement=" + idMovement +
                ", dateMovement=" + dateMovement +
                ", type='" + type + '\'' +
                ", originDestination='" + originDestination + '\'' +
                ", quantity=" + quantity +
                ", stock=" + stock +
                ", item=" + item +
                ", person=" + person +
                '}';
    }
}
