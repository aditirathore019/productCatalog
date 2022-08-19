package com.atcs.product.service;

import com.atcs.product.model.Pincode;
import com.atcs.product.model.Product;
import com.atcs.product.repo.PincodeRepo;
import com.atcs.product.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
@Component
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepo productRepo;

    @Autowired
    PincodeRepo pincodeRepo;

    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> getByParameter(String parameter) {
        List<Product> allProducts = productRepo.findAll();
        List<Product> searchedProducts = new ArrayList<Product>();
        for(Product product: allProducts){
            if(parameter.equals(product.getProName())) {
                searchedProducts.add(product);
            }
            else
            if(parameter.equalsIgnoreCase(product.getProBrand())){
                searchedProducts.add(product);
            }
            else
            if(parameter.equalsIgnoreCase(product.getProductCode())){
                searchedProducts.add(product);
            }
        }
        return searchedProducts;
    }

    @Override
    public void addProduct(Product product) {
        productRepo.save(product);
    }


    @Override
    public Product findDescription(String code) {
        Product pro = productRepo.findByProductCode(code);
        return pro;
    }

    @Override
    public List<String> getPrices(List<String> codes) {

        return productRepo.getAllCodes(codes);
    }

    @Override
    public Integer servicable(Long pincode) {
        List<Pincode> pincodeList = pincodeRepo.findAll();
        System.out.println(pincodeList);
        for(Pincode pinObj: pincodeList){
            System.out.println(pincode);
            System.out.println(pinObj.getPin());
            if(pincode.equals(pinObj.getPin())){
                System.out.println("inside");
                return pinObj.getDays();
            }
        }
        return null;
    }

    @Override
    public List<Product> filterProducts(Double price1, Double price2) {
        List<Product> allProducts = productRepo.findAll();
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product: allProducts){
            if(product.getProPrice()>=price1 && product.getProPrice()<=price2){
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }


//    @Override
//    public Integer servicable(String code, Long pincode) {
//        Product pro = productRepo.findByProductCode(code);
//        List<Pincode> pincodes = pro.getProPincodes();
//        for(Pincode pin:pincodes)
//        {
//            if(pin.getPin()==pincode)
//            {
//                return pin.getDays();
//            }
//        }
//        return null;
//    }

}
