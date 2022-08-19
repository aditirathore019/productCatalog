package com.atcs.product.controller;


import com.atcs.product.model.Product;
import com.atcs.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public List<Product> getAll()
    {
        return  productService.getAll();
    }

    @PostMapping("/post")
    public void addProduct(@RequestBody Product product)
    {
        productService.addProduct(product);
    }



    @GetMapping("/prices")
    public List<String> getPrices(List<String> codes)
    {
        return productService.getPrices(codes);
    }

    @GetMapping("/service/{pincode}")
    public Integer serviceAbility(@PathVariable("pincode") Long pincode)
    {
       return productService.servicable(pincode);
    }

    @GetMapping("/searchResults/{parameter}")
    public List<Product> searchProduct(@PathVariable("parameter") String parameter){
        return productService.getByParameter(parameter);
    }

    @GetMapping("/filter/{price1}/{price2}")
    public List<Product> filterProducts(@PathVariable("price1") Double price1, @PathVariable("price2") Double price2){
        return productService.filterProducts(price1,price2);
    }

}
