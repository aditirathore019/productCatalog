package com.atcs.product.service;

import com.atcs.product.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public List<Product> getAll();

    public List<Product> getByParameter(String parameter);

    public void addProduct(Product product);

    public Product findDescription(String code);

    public List<String> getPrices(List<String> codes);

    public Integer servicable(Long pincode);

    public List<Product> filterProducts(Double price1, Double price2);


//    public Integer servicable(String code,Long pincode);

}
