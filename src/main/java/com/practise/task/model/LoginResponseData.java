package com.practise.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseData {
	private int statusCode;
	private String statusMsg;
	private Long userId;
	public LoginResponseData(int statusCode, String statusMsg) {
		this.statusCode = statusCode;
		this.statusMsg = statusMsg;
	}
	
	
}
