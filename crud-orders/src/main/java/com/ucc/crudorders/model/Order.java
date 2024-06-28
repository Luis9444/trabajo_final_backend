package com.ucc.crudorders.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity 
@Table(name = "Orders")
@Getter
@Setter
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 

public class Order {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;

    @NotBlank(message = "ord: El campo no puede estar vacio")
    private String ord;
    @NotBlank(message = "sku: El campo no puede estar vacio")
    private String sku;
    @NotBlank(message = "cant: El campo no puede estar vacio")
    private String cant;

}
