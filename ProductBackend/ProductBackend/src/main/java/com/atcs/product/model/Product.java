package com.atcs.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String productCode;

    private String proName;

    private double proPrice;

    private String proDescription;

    private String proBrand;

    private String imgUrl;
}
