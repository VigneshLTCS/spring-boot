package com.practise.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUserResponse {
	private int statusCode;
	private String statusMsg;
	private UserInfo userdetails;
	
	public GetUserResponse(int statusCode, String statusMsg) {
		this.statusCode = statusCode;
		this.statusMsg = statusMsg;
	}
	
	
}
