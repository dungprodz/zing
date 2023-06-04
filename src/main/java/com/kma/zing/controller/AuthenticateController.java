package com.kma.zing.controller;

import com.kma.zing.model.requestbody.JwtLoginRequestBody;
import com.kma.zing.model.responsebody.JwtLoginResponseBody;
import com.kma.zing.service.impl.JwtUserDetailsService;
import com.kma.zing.ulti.Common;
import com.kma.zing.ulti.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
@CrossOrigin("*")
@RestController
@RequestMapping("zing/v1/auth")
public class AuthenticateController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userService;

    @Autowired
    public AuthenticateController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid JwtLoginRequestBody authenticationRequest, HttpServletRequest request) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtLoginResponseBody(token, Common.SUCCESS));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> getLogout(HttpServletRequest request) throws Exception {
        String token = request.getHeader(Common.HEADER_AUTHORIZATION);
        return ResponseEntity.ok(userService.logout(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
