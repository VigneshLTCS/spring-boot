package com.practise.task.entity;



import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	
	@Id	
	private ObjectId id;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String emailId;
	
	private String phoneNumber;
		
}
