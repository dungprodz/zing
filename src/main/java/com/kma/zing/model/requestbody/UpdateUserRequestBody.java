package com.kma.zing.model.requestbody;

import lombok.Data;

@Data
public class UpdateUserRequestBody {
    private String userName;
    private String email;
    private String phoneNumber;
    private String fullName;
    private String userImg;
}
