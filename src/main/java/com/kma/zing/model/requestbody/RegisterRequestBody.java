package com.kma.zing.model.requestbody;

import lombok.Data;

@Data
public class RegisterRequestBody {
    private String fullName;
    private String userName;
    private String passWord;
    private String email;
    private String phoneNumber;
}
