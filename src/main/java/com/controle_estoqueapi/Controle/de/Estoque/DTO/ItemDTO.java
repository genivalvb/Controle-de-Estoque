package com.controle_estoqueapi.Controle.de.Estoque.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private long idItem;

    @NotNull@JsonInclude
    @Column(name = "description")
    private String description = "";

    @NotNull@JsonInclude
    @Column(name = "code")
    private long code;

    @NotNull@JsonInclude
    @Column(name = "name")
    private String name = "";

    @NotNull@JsonInclude
    @Column(name = "ggroup")
    private String ggroup = "";

    @NotNull@JsonInclude
    @Column(name = "family")
    private String family = "";

    @NotNull@JsonInclude
    @Column(name = "unit")
    private String unit = "";

    @NotNull@JsonInclude
    @Column(name = "stock_safe")
    private float stockSafe;

}
