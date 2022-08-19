package com.atcs.product.BuyZone.controller;

import com.atcs.product.BuyZone.dto.Product;
import com.atcs.product.BuyZone.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller

public class AuthController {

    @Autowired
    AuthService authService;

    @GetMapping("/error/access-denied")
    public String getError()
    {
        return "error/access-denied";
    }

    @GetMapping("/")
    public String getIndex()
    {
        return "index";
    }


    @GetMapping("/register")
    public String register(HttpServletRequest req, HttpServletResponse res,@RequestParam(value = "params",required = false) String params,Model model)
    {
        if(authService.checkAuthToken(req))
        {

            return "redirect:/";
        }
        if(!(Objects.isNull(params)))
        {
            model.addAttribute("params",params);
        }
        return "register";
    }


    @GetMapping("/login")
    public String login(HttpServletRequest req, HttpServletResponse res)
    {
        if(authService.checkAuthToken(req))
        {

            return "redirect:/";
        }

        return "login";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse res)
    {

        return "index";
    }

    @PostMapping("/api/register")
    public void apiRegister(HttpServletRequest req, HttpServletResponse res,@RequestParam("email") String email,@RequestParam("password") String password,@RequestParam("cpassword") String cpassword,@RequestParam("firstname") String firstName,@RequestParam("lastname") String lastName) throws IOException {
        authService.register(req,res,email,password,cpassword,firstName,lastName);
    }


    @PostMapping("/api/login")
    public void apiLogin(HttpServletRequest req, HttpServletResponse res, @RequestParam("username") String username, @RequestParam("password") String password) throws IOException {
       authService.login(req,res,username,password);

    }



    @PostMapping("/api/logout")
    public void apiLogout(HttpServletRequest req, HttpServletResponse res) throws IOException {
        authService.logout(req,res);
    }
}
