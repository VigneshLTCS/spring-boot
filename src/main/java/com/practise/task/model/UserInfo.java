package com.practise.task.model;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
	private ObjectId id;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String emailId;
	
	private String phoneNumber;
		

}
