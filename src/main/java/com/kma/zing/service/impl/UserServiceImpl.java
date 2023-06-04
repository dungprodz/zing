package com.kma.zing.service.impl;

import com.kma.zing.entity.TblUserInforEntity;
import com.kma.zing.model.GetListUser;
import com.kma.zing.model.requestbody.SearchByUserNameRequestBody;
import com.kma.zing.model.requestbody.UpdateUserRequestBody;
import com.kma.zing.model.responsebody.SearchByUserNameResponseBody;
import com.kma.zing.model.responsebody.UpdateUserResponseBody;
import com.kma.zing.repository.UserInfoRepository;
import com.kma.zing.service.UserService;
import com.kma.zing.ulti.Common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserInfoRepository userInfoRepository;
    @Autowired
    public UserServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
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
    public SearchByUserNameResponseBody searchUser() {
        SearchByUserNameResponseBody responseBody = new SearchByUserNameResponseBody();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        TblUserInforEntity userInfo = userInfoRepository.findByUsername(userName);
        responseBody.setEmail(userInfo.getEmail());
        responseBody.setPhoneNumber(userInfo.getPhonenumber());
        responseBody.setFullName(userInfo.getFullname());
        responseBody.setImage(userInfo.getUserimg());
        return responseBody;
    }
}
