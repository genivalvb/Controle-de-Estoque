package com.controle_estoqueapi.Controle.de.Estoque.DTO;

import com.controle_estoqueapi.Controle.de.Estoque.Entity.Item;
import com.controle_estoqueapi.Controle.de.Estoque.Entity.Person;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReserveDTO {


    private Long idReserve;

    @NotNull
    @JsonInclude
    @Column(name = "finished")
    private boolean finished = false;

    @NotNull
    @JsonInclude
    @Column(name = "quantity_reserve")
    private float quantityReserve;

    @NotNull
    @JsonInclude
    @Column(name = "date_expected", columnDefinition = "date")
    private LocalDate dateExpected;

    @NotNull
    @JsonInclude
    @Column(name = "ordem")
    private String ordem;

    @NotNull
    @JsonInclude
    @ManyToOne
    private PersonPublicDTO person;

    @NotNull
    @JsonInclude
    @ManyToOne
    private Item item;
}
