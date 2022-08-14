package com.practise.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterData {
	private String firstName;
	private String lastName;
	private String userName;
	private String emailId;
	private String password;
	private String address1;
	private String address2;
	private String phoneNumber;
}
