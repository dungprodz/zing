package com.kma.zing.controller;

import com.kma.zing.model.requestbody.SearchByUserNameRequestBody;
import com.kma.zing.model.requestbody.UpdateUserRequestBody;
import com.kma.zing.model.responsebody.SearchByUserNameResponseBody;
import com.kma.zing.model.responsebody.UpdateUserResponseBody;
import com.kma.zing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zing/v1/user")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/update")
    public ResponseEntity<UpdateUserResponseBody> updateUser(@RequestBody UpdateUserRequestBody requestBody) throws Exception {
        UpdateUserResponseBody responseBody = userService.updateUser(requestBody);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<SearchByUserNameResponseBody> searchUser() throws Exception {
        SearchByUserNameResponseBody responseBody = userService.searchUser();
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
