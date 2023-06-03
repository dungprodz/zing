package com.kma.zing.service.impl;

import com.kma.zing.entity.TblUserInforEntity;
import com.kma.zing.model.requestbody.UpdateUserRequestBody;
import com.kma.zing.model.responsebody.SearchByUserNameResponseBody;
import com.kma.zing.model.responsebody.UpdateUserResponseBody;
import com.kma.zing.repository.UserInfoRepository;
import com.kma.zing.service.UserService;
import com.kma.zing.ulti.Common;
import com.kma.zing.ulti.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserInfoRepository userInfoRepository;
    private final JwtTokenUtil jwtTokenUtil;
    @Autowired
    public UserServiceImpl(UserInfoRepository userInfoRepository, JwtTokenUtil jwtTokenUtil) {
        this.userInfoRepository = userInfoRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public UpdateUserResponseBody updateUser(UpdateUserRequestBody requestBody) throws Exception {
        UpdateUserResponseBody responseBody = new UpdateUserResponseBody();
        TblUserInforEntity userInfo = userInfoRepository.findByUserid(requestBody.getUserid());
        if(Objects.isNull(userInfo)){
            throw new Exception("DATA NOT FOUND");
        }
        userInfo.setEmail(requestBody.getEmail());
        userInfo.setFullname(requestBody.getFullName());
        userInfo.setPhonenumber(requestBody.getPhoneNumber());
        userInfoRepository.save(userInfo);
        responseBody.setStatus(Common.SUCCESS);
        return responseBody;
    }

    @Override
    public SearchByUserNameResponseBody searchUser(HttpServletRequest httpServletRequest) {
        SearchByUserNameResponseBody responseBody = new SearchByUserNameResponseBody();
        String requestHeader = httpServletRequest.getHeader(Common.HEADER_AUTHORIZATION);
        String username = jwtTokenUtil.getUsernameFromToken(requestHeader.substring(7));
        TblUserInforEntity userInfo = userInfoRepository.findByUsername(username);
        responseBody.setEmail(userInfo.getEmail());
        responseBody.setPhoneNumber(userInfo.getPhonenumber());
        responseBody.setFullName(userInfo.getFullname());
        responseBody.setImage(userInfo.getUserimg());
        return responseBody;
    }
}
