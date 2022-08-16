package com.practise.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    private String emailId;
}

