package com.controle_estoqueapi.Controle.de.Estoque.Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "prevision")
public class Prevision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrevision;

    @ManyToOne
    private Item item;
    @ManyToOne
    private Person person;

    @Column(name = "quantity_expected")
    private float quantityExpected;
    @Column(name = "date_expected")
    private LocalDate dateExpected;

    @Column(name = "ordem")
    private String ordem;

    @Column(name = "finished")
    private boolean finished = false;

    public Prevision(){}

    public Prevision(long idPrevision, Item item, Person person, float quantityExpected, LocalDate dateExpected, String ordem, boolean finished) {
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
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

    public boolean getFinished() {
        return this.finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prevision)) return false;
        Prevision prevision = (Prevision) o;
        return idPrevision == prevision.idPrevision && Float.compare(prevision.quantityExpected, quantityExpected) == 0 && finished == prevision.finished && Objects.equals(item, prevision.item) && Objects.equals(person, prevision.person) && Objects.equals(dateExpected, prevision.dateExpected) && Objects.equals(ordem, prevision.ordem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPrevision, item, person, quantityExpected, dateExpected, ordem, finished);
    }

    @Override
    public String toString() {
        return "Prevision{" +
                "idPrevision=" + idPrevision +
                ", item=" + item +
                ", person=" + person +
                ", quantityExpected=" + quantityExpected +
                ", dateExpected=" + dateExpected +
                ", ordem='" + ordem + '\'' +
                ", finished=" + finished +
                '}';
    }
}
