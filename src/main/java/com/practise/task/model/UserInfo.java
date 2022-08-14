package com.practise.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String emailId;
	
	private String phoneNumber;
	
	private String address1;
	
	private String address2;
	
	

}
