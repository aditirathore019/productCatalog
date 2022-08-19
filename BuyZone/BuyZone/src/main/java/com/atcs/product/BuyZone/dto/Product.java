package com.atcs.product.BuyZone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {


    private Long proId;

    private String proName;

    private double proPrice;

    private String proDescription;

    private String proBrand;

    private String imgUrl;

    private List<Pincode> proPincodes;

}
