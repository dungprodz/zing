package com.kma.zing.model;

import lombok.Data;

@Data
public class GetListUser {
    private int userId;
    private String fullName;
    private String userName;
    private String email;
    private String phoneNumber;
}
