package com.practise.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ResponseData {	
	private int statusCode;
	private String statusMsg;
}
