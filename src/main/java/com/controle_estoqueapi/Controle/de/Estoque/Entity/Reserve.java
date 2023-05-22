package com.controle_estoqueapi.Controle.de.Estoque.Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Reserve {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long idReserve;

    @Column(name = " finished")
    private boolean finished = false;

    @Column(name = "quantity_reserve")
    private float quantityReserve;

    @Column(name = "date_expected", columnDefinition = "date")
    private LocalDate dateExpected;

    @Column(name = "ordem")
    private String ordem;

    @ManyToOne
    private Person person;

    @ManyToOne
    private Item item;

    public Reserve(){}

    public Reserve(long idReserve, boolean finished, float quantityReserve, LocalDate dateExpected, String ordem, Person person, Item item) {
        this.idReserve = idReserve;
        this.finished = finished;
        this.quantityReserve = quantityReserve;
        this.dateExpected = dateExpected;
        this.ordem = ordem;
        this.person = person;
        this.item = item;
    }

    public long getIdReserve() {
        return idReserve;
    }

    public void setIdReserve(long idReserve) {
        this.idReserve = idReserve;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reserve)) return false;
        Reserve reserve = (Reserve) o;
        return idReserve == reserve.idReserve && finished == reserve.finished && Float.compare(reserve.quantityReserve, quantityReserve) == 0 && Objects.equals(dateExpected, reserve.dateExpected) && Objects.equals(ordem, reserve.ordem) && Objects.equals(person, reserve.person) && Objects.equals(item, reserve.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReserve, finished, quantityReserve, dateExpected, ordem, person, item);
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "idReserve=" + idReserve +
                ", finished=" + finished +
                ", quantityReserve=" + quantityReserve +
                ", dateExpected=" + dateExpected +
                ", ordem='" + ordem + '\'' +
                ", person=" + person +
                ", item=" + item +
                '}';
    }
}
