package com.atcs.product.BuyZone.service;

import com.atcs.product.BuyZone.dto.Pincode;
import com.atcs.product.BuyZone.dto.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    AuthService authService;
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    private final RestTemplate localApiClient;

    @Autowired
    public ProductService(RestTemplate localApiClient) {
        this.localApiClient = localApiClient;
    }

    public List<Product> getProduct(HttpServletRequest req, HttpServletResponse res, long id) throws IOException {

        if(authService.checkAuthToken(req))
        {
            String token = authService.extractToken(req);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
            ResponseEntity<Product[]> pr = localApiClient.exchange("http://localhost:8081/product/all", HttpMethod.GET,entity, Product[].class);

            return Arrays.asList(pr.getBody());

        }
        else
        {
             res.sendRedirect("/error/access-denied");
        }

        return null;
    }

      public List<Product> getByParameter(HttpServletRequest req, HttpServletResponse res, String parameter) throws IOException {

        if(authService.checkAuthToken(req))
        {
            String token = authService.extractToken(req);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
          //  String str = "{/name}";
            ResponseEntity<Product[]> pr = localApiClient.exchange("http://localhost:8081/product/searchResults/" + parameter, HttpMethod.GET,entity, Product[].class);

            return Arrays.asList(pr.getBody());

        }
        else
        {
            res.sendRedirect("/error/access-denied");
        }

        return null;
    }


    public Integer getExpectedDay(HttpServletRequest req, HttpServletResponse res,Long pincode) throws IOException {

        if(authService.checkAuthToken(req))
        {
            String token = authService.extractToken(req);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
            //  String str = "{/name}";
            ResponseEntity<Pincode> pr = localApiClient.exchange("http://localhost:8081/product/service/" + pincode, HttpMethod.GET,entity, Pincode.class);

            return pr.getBody().getDays();

        }
        else
        {
            res.sendRedirect("/error/access-denied");
        }

        return null;
    }

    public List<Long> getPrices(HttpServletRequest req, HttpServletResponse res, List<Long> ids) throws IOException {

        if(authService.checkAuthToken(req))
        {
            String token = authService.extractToken(req);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
            //  String str = "{/name}";
            ResponseEntity<Long> pr = localApiClient.exchange("http://localhost:8081/product/prices", HttpMethod.GET,entity, Long.class);

            return Arrays.asList(pr.getBody());

        }
        else
        {
            res.sendRedirect("/error/access-denied");
        }

        return null;
    }

}
