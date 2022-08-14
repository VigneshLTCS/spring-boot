package com.practise.task.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practise.task.entity.User;
import com.practise.task.model.GetUserResponse;
import com.practise.task.model.LoginData;
import com.practise.task.model.LoginResponseData;
import com.practise.task.model.RegisterData;
import com.practise.task.model.ResponseData;
import com.practise.task.model.UserInfo;
import com.practise.task.respository.UserRepo;
import com.practise.task.service.AuthServiceImpl;



@RestController
@RequestMapping("/user")
public class AuthServiceController {
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	AuthServiceImpl serviceImpl;
	
	//Login
	@PostMapping("/authenticate")
	public LoginResponseData login(@RequestBody LoginData loginData) {
		User user = repo.findByUserName(loginData.getUsername());
		return serviceImpl.validateUser(user, loginData);		
	}
	
	//New User Registration
	@PostMapping("/register")
	public ResponseData register(@RequestBody RegisterData registerData) {
		if(serviceImpl.checkUserIfAlreadyExists(registerData.getEmailId())) {
			System.out.println("pass"+registerData.getPassword());
			return new ResponseData(406, "User Already Exists");
		}else {
			System.out.println("elsepass"+registerData.getPassword());
			return serviceImpl.registerUser(registerData);
		}		
	}
	
	//Update User Information
	@PostMapping("/update")
	public ResponseData update(@RequestBody RegisterData profileData) {
		return serviceImpl.updateUserInfo(profileData);	
	}
	
	//Get User Information
	@GetMapping("/get/{userid}")
	public GetUserResponse getUserInfo(@PathVariable String userid) {
		Long id = Long.parseLong(userid);
		if(!repo.existsById(id)) {
			return new GetUserResponse(404, "Data not found");
		}
		return new GetUserResponse(200, "Data fetched Successfully", serviceImpl.fetchUserData(id));
		
	}
	
	@DeleteMapping("delete/{userid}")
	public ResponseData deleteUser(@PathVariable String userid){
		Long id = Long.parseLong(userid);
		return serviceImpl.deleteUser(id);
	}
	
}
