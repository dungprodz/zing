package com.kma.zing.model.responsebody;

import lombok.Data;

@Data
public class SearchByUserNameResponseBody {
    private String fullName;
    private String image;
    private String email;
    private String phoneNumber;
}
