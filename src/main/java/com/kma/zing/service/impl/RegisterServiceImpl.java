package com.kma.zing.service.impl;

import com.kma.zing.entity.TblUserInforEntity;
import com.kma.zing.entity.TblUsersEntity;
import com.kma.zing.model.requestbody.RegisterRequestBody;
import com.kma.zing.model.responsebody.RegisterResponseBody;
import com.kma.zing.repository.UserInfoRepository;
import com.kma.zing.repository.UserRepository;
import com.kma.zing.service.RegisterService;
import com.kma.zing.ulti.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class RegisterServiceImpl implements RegisterService {
    private final UserInfoRepository userInfoRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public RegisterServiceImpl(UserInfoRepository userInfoRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userInfoRepository = userInfoRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public RegisterResponseBody register(RegisterRequestBody requestBody) throws Exception {
        RegisterResponseBody responseBody = new RegisterResponseBody();
        if(userInfoRepository.existsByUsername(requestBody.getUserName())){
            throw new Exception("USER EXIST");
        }
        TblUserInforEntity user = new TblUserInforEntity();
        TblUsersEntity usersEntity = new TblUsersEntity();

        usersEntity.setUsername(requestBody.getUserName());
        usersEntity.setPassword(passwordEncoder.encode(requestBody.getPassWord()));
        usersEntity.setStatus(Common.ACTIVE);
        usersEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        usersEntity.setPhone(requestBody.getPhoneNumber());
        userRepository.save(usersEntity);

        user.setEmail(requestBody.getEmail());
        user.setPhonenumber(requestBody.getPhoneNumber());
        user.setFullname(requestBody.getFullName());
        user.setUsername(requestBody.getUserName());
        user.setUserid(userRepository.findByUsername(requestBody.getUserName()).getId());
        userInfoRepository.save(user);

        responseBody.setStatus(Common.SUCCESS);
        return responseBody;
    }
}
