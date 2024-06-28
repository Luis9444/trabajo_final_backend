package com.ucc.crudorders.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotBlank(message = "sku: El campo no puede estar vacio")
    private String sku;

    @NotBlank(message = "name: El campo no puede estar vacio")
    private String name;
    private String description;

    @NotNull(message = "price: El campo no puede estar vacio")
    @DecimalMin(value = "0.0", message = "Debe ser mayor o igual a 0.0")
    private Double price;
    private Boolean status;
}