package com.atcs.product.BuyZone.controller;

import com.atcs.product.BuyZone.dto.Product;
import com.atcs.product.BuyZone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/app/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public String getAll(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {
        List<Product> product = productService.getProduct(req, res, 1);
        model.addAttribute("products",product);
        return "home";
    }

    @GetMapping("/search/{parameter}")
    public String getByParameter(HttpServletRequest req, HttpServletResponse res, Model model, @PathVariable("parameter") String parameter) throws IOException{
        List<Product> product = productService.getByParameter(req,res,parameter);
        model.addAttribute("products",product);
        return "product";
    }

    @GetMapping("/prices")
    public String getPrices(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {
        List<Long> prices = productService.getPrices(req,res, new ArrayList<>());
        model.addAttribute("products",prices);
        return "product";
    }

    @GetMapping("/service/{pincode}")
    public String serviceAbility(HttpServletRequest req,HttpServletResponse res, Model model,@PathVariable("pincode") Long pincode) throws IOException {
        Integer i = productService.getExpectedDay(req,res,pincode);
        model.addAttribute("service",i);
        return "service";

    }
//    @GetMapping("/name/{name}")
//    public String getByName(HttpServletRequest req, HttpServletResponse res, Model model, @PathVariable("name") String name) throws IOException{
//        Product product = productService.getByName(req,res,name);
//        model.addAttribute("product",product);
//        return "product";
//    }
//
//    @GetMapping("/brand/{brand}")
//    public String getByBrand(HttpServletRequest req, HttpServletResponse res, Model model, @PathVariable("brand") String brand) throws IOException{
//        Product product = productService.getByBrand(req,res,brand);
//        model.addAttribute("product",product);
//        return "product";
//    }
//
//    @GetMapping("/code/{code}")
//    public String getByCode(HttpServletRequest req, HttpServletResponse res, Model model, @PathVariable("code") Long code) throws IOException{
//        Product product = productService.getByCode(req,res,code);
//        model.addAttribute("product",product);
//        return "product";
//    }

}
