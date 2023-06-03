package com.kma.zing.service;

import com.kma.zing.model.requestbody.RegisterRequestBody;
import com.kma.zing.model.responsebody.RegisterResponseBody;

public interface RegisterService {
    RegisterResponseBody register(RegisterRequestBody requestBody) throws Exception;
}
