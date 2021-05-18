package com.portal.springrest.controller;

import com.portal.springrest.Main;
import com.portal.springrest.model.Account;
import com.portal.springrest.model.AuthRequestDTO;
import com.portal.springrest.repository.interfaces.AccountRepository;
import com.portal.springrest.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/auth")
public class AuthRestControllerV1 {

    private final AuthenticationManager authenticationManager;
    private AccountRepository accountRepository;
    private JwtTokenProvider jwtTokenProvider;

    public AuthRestControllerV1(AuthenticationManager authenticationManager, AccountRepository accountRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> auth(@RequestBody AuthRequestDTO requestDTO){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getName(), requestDTO.getPass()));
        Account account = accountRepository.findByName(requestDTO.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String token = jwtTokenProvider.createToken(requestDTO.getName(), account.getRole().name());
        Map<Object, Object> resp = new HashMap<>();
        resp.put("name", requestDTO.getName());
        resp.put("token", token);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);
    }

}
