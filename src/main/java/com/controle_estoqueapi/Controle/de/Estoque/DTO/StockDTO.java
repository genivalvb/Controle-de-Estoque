package com.controle_estoqueapi.Controle.de.Estoque.DTO;

import com.controle_estoqueapi.Controle.de.Estoque.Entity.Item;
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
public class StockDTO {

    private long idStock;

    @NotNull
    @JsonInclude
    @Column(name = "localization")
    private String localization;

    @NotNull@JsonInclude
    @Column(name = "real_stock")
    private Float realStock;

    @NotNull@JsonInclude
    @Column(name = "future_stock")
    private Float futureStock;

    @NotNull@JsonInclude
    @Column(name = "future_date")
    private LocalDate futureDate;


    @ManyToOne
    private Item item;

    @NotNull@JsonInclude
    @Column(name = "unitprice")
    private Float unitPrice;

    @NotNull@JsonInclude
    @Column(name = "description")
    private String description;

    @NotNull@JsonInclude
    @Column(name = "stock_value")
    private Float stockValue;

    @NotNull@JsonInclude
    @Column(name = "control_abc")
    private String controlAbc;

    @NotNull@JsonInclude
    @Column(name = "percentageAccumulated")
    private Integer percentageAccumulated;

    @NotNull@JsonInclude
    @Column(name = "economic_lot")
    private String economicLot;


}


