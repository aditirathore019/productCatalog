package com.atcs.product.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atcs.product.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetalsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {


        final String reqTokenHeader = request.getHeader("Authorization");
        String username=null;
        String jwtToken = null;

        // first we will get the token
        if(reqTokenHeader!=null &&  reqTokenHeader.startsWith("Bearer "))
        {
            jwtToken = reqTokenHeader.substring(7);
            try
            {
                username = this.jwtUtil.extractUsername(jwtToken);
            } catch (ExpiredJwtException e)
            {
                // TODO: handle exception
                e.printStackTrace();
                System.out.println("token has expired");
            }
        }
        else
        {
            System.out.println("Invalid Token!");
        }



        // validation for the token
        // authenticated user will go into the context
        if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null) {

            final UserDetails ud = this.userDetalsService.loadUserByUsername(username);

            if(this.jwtUtil.validateToken(jwtToken, ud))
            {
                UsernamePasswordAuthenticationToken up = new UsernamePasswordAuthenticationToken(ud,null,ud.getAuthorities());

                up.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(up);
            }
        }
        else
        {
            System.out.println("Invalid Token");
        }


        filterChain.doFilter(request, response);


    }


}
