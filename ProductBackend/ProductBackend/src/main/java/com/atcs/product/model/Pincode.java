package com.atcs.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pincode")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pincode {

    @Id
    private Long pin;
    private Integer days;
}
