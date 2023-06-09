package com.kma.zing.service;

import com.kma.zing.model.requestbody.SearchByUserNameRequestBody;
import com.kma.zing.model.requestbody.UpdateUserRequestBody;
import com.kma.zing.model.responsebody.SearchByUserNameResponseBody;
import com.kma.zing.model.responsebody.UpdateUserResponseBody;

public interface UserService {
    UpdateUserResponseBody updateUser(UpdateUserRequestBody requestBody) throws Exception;

    SearchByUserNameResponseBody searchUser();
}
