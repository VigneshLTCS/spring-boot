package com.practise.task.service;
import javax.persistence.EntityNotFoundException;

import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.practise.task.entity.User;
import com.practise.task.model.LoginData;
import com.practise.task.model.LoginResponseData;
import com.practise.task.model.RegisterData;
import com.practise.task.model.ResponseData;
import com.practise.task.model.UserInfo;
import com.practise.task.respository.UserRepo;
import com.practise.task.util.Util;


@Service
public class AuthServiceImpl  {
	@Autowired
	UserRepo repo;
	@Autowired
	Util util;
	
	public LoginResponseData validateUser(User user, LoginData loginData) {
		if (user == null) {
			return new LoginResponseData(404, "User not found! Try Register");
		}else if(user.getPassword().equalsIgnoreCase(loginData.getPassword())) {
			return new LoginResponseData(200, "Login Successful", user.getId());
		}
		return new LoginResponseData(500, "Internal server error");
	}
	
	public ResponseData registerUser(RegisterData registerData) {
		User user = new User();
		BeanUtils.copyProperties(registerData, user);
		repo.save(user);
		return new ResponseData(200, "Registered Successfully");
	}
	
	public ResponseData updateUserInfo(RegisterData userData) {
		User user = repo.findByEmailId(userData.getEmailId());
		BeanUtils.copyProperties(userData, user, this.util.getNullPropertyNames(userData));
		repo.save(user);
		return new ResponseData(200, "User profile is updated");
	}
	
	public ResponseData deleteUser(Long id) {
		try {
				repo.deleteById(id);
				return new ResponseData(200, "User is deleted.");
			
		}
		catch(GenericJDBCException e) {
			return new ResponseData(401, "Bad Request");
		}
		catch(EmptyResultDataAccessException e) {
			return new ResponseData(404, "User not found");
		}
		
	}
	
	public boolean checkUserIfAlreadyExists(String email) {
		return repo.findByEmailId(email) !=null ? true : false;
	}
	
	public UserInfo fetchUserData(Long id) {
		User user = repo.getById(id);
		UserInfo info = new UserInfo();
		bindUserData(user, info);
		return info;
	}

	private void bindUserData(User user, UserInfo info) {
		info.setAddress1(user.getAddress1());
		info.setAddress2(user.getAddress2());
		info.setEmailId(user.getEmailId());
		info.setFirstName(user.getFirstName());
		info.setLastName(user.getLastName());
	}
	
}
