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

    @GetMapping("/service")
    public Integer serviceAbility(String code,Long pincode)
    {
       return productService.servicable(code,pincode);
    }

    @GetMapping("/searchResults/{parameter}")
    public List<Product> searchProduct(@PathVariable("parameter") String parameter){
        return productService.getByParameter(parameter);
    }


//    @GetMapping("/name/{name}")
//    public Product findByName(@PathVariable("name") String name)
//    {
//        return productService.findByName(name);
//    }
//
//    @GetMapping("/code/{code}")
//    public Product findByCode(@PathVariable("code") String code)
//    {
//        return productService.findByProduct(code);
//    }
//
//    @GetMapping("/brand/{brand}")
//    public Product findByBrand(@PathVariable("brand") String brand)
//    {
//        return productService.findByBrandName(brand);
//    }

}
