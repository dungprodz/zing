package com.kma.zing.service.impl;

import com.kma.zing.entity.TblUsersEntity;
import com.kma.zing.model.responsebody.JwtLogoutResponseBody;
import com.kma.zing.repository.UserRepository;
import com.kma.zing.ulti.Common;
import com.kma.zing.ulti.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Slf4j
@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.secret}")
    private String secretKey;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TblUsersEntity user = userRepository.findAllByUsernameAndStatus(username, Common.ACTIVE);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public JwtLogoutResponseBody logout(String token) {
        try {
            log.info("{} logout currentToken {}", getClass().getSimpleName(), token);
            String currentToken = token.substring(7);

            // kiểm tra token đã hết hạn chưa
            if (!jwtTokenUtil.isTokenExpired(currentToken)) {
                // nếu token chưa hết hạn, cần set lại thời gian hết hạn của token
                Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(currentToken).getBody();
                long expiredTime = System.currentTimeMillis() - 1000;
                claims.setExpiration(new Date(expiredTime));
                String updatedToken = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secretKey).compact();
//                log.info("{} logout updatedToken {}", getClass().getSimpleName(), updatedToken);
            }

            return new JwtLogoutResponseBody(Common.SUCCESS);
        } catch (Exception e) {
            log.error("{} Exception {}", getClass().getSimpleName(), e);
            return new JwtLogoutResponseBody(Common.FAIL);
        }
    }

}
