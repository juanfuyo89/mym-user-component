package com.mym.consulting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse extends Response {
    public UserResponse(String message, List<User> userList){
        super.setResponseMessage(message);
        this.userList = userList;
    }
    private List<User> userList;
}
