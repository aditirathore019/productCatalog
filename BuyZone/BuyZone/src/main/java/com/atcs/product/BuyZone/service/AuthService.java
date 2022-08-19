package com.atcs.product.BuyZone.service;

import com.atcs.product.BuyZone.dto.JwtResponce;
import com.atcs.product.BuyZone.dto.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class AuthService {

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    private final RestTemplate localApiClient;


    public AuthService(RestTemplate localApiClient) {
        this.localApiClient = localApiClient;
    }

    HttpServletResponse httpServletResponseGlobal = null;

    public void login(HttpServletRequest req, HttpServletResponse res, String username, String password) throws IOException {

        if(checkAuthToken(req))
        {
            res.sendRedirect("http://localhost:5000/");
           return;
        }
        // create request body
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode user = mapper.createObjectNode();
        user.put("username", username);
        user.put("password", password);

        // set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(user), headers);

        ResponseEntity<JwtResponce> pr = localApiClient.exchange("http://localhost:8081/generate-token", HttpMethod.POST, entity, JwtResponce.class);
        Cookie auth = new Cookie("Authorization", pr.getBody().getToken());
        auth.setPath("/");
        res.addCookie(auth);
        httpServletResponseGlobal=res;
        log.info("res{}",auth);
        res.sendRedirect("http://localhost:5000/");




    }

    public void register(HttpServletRequest req, HttpServletResponse res, String email, String password,String cpassword,String firstName,String lastName) throws IOException {

        if(checkAuthToken(req))
        {

            res.sendRedirect("http://localhost:5000/");
            return;
        }

        if(!(password.equals(cpassword)))
        {
            res.sendRedirect("http://localhost:5000/register/?params=password and confirm password should be same");
            return;
        }
        // create request body
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode user = mapper.createObjectNode();
        user.put("username", email);
        user.put("password", password);
        user.put("firstName", firstName);
        user.put("lastName",lastName);

        // set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(user), headers);

        ResponseEntity<Void> pr = localApiClient.exchange("http://localhost:8081/addUser", HttpMethod.POST, entity, Void.class);
       if(pr.getStatusCodeValue()==200)
       {
           res.sendRedirect("http://localhost:5000/login");
       }
       else {
           res.sendRedirect("http://localhost:5000/register");
       }



    }

    public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException {



        Cookie[] cookie = req.getCookies();
        log.info("Cookie{}",cookie);
        System.out.println(Arrays.toString(cookie));
        for(Cookie c:cookie)
        {
            if(c.getName().equals("Authorization"))
            {
                c.setMaxAge(0);
                res.addCookie(c);
                break;
            }
        }


    }



    public Boolean checkAuthToken(HttpServletRequest req)
    {

        List<Cookie> cookies ;
        Boolean flag = false;

        if(Objects.isNull(req.getCookies())) {
            cookies = new ArrayList<>();
        }
        else {
           cookies = Arrays.asList(req.getCookies());
        }

        for(Cookie c:cookies)
        {
            if(c.getName().equals("Authorization"))
            {

                flag = true;
                break;
            }
        }
        return flag;
    }

    public String extractToken(HttpServletRequest req)
    {
        Cookie[] cookies = req.getCookies();
        String token = null;
        for(Cookie c:cookies)
        {
            if(c.getName().equals("Authorization"))
            {
                token = c.getValue();
                break;
            }
        }
        return token;
    }


}
