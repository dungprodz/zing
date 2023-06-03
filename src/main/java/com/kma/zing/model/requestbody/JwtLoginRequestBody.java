package com.kma.zing.model.requestbody;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class JwtLoginRequestBody implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;
    @NotEmpty(message = "Empty username")
    private String username;
    @NotEmpty(message = "Empty password")
    private String password;

    //need default constructor for JSON Parsing
    public JwtLoginRequestBody() {

    }

    public JwtLoginRequestBody(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
