package com.practise.task.service;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.bson.types.ObjectId;
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
import com.practise.task.model.UpdateUserRequest;
import com.practise.task.model.UserInfo;
import com.practise.task.respository.UserRepo;
import com.practise.task.util.Util;


@Service
public class AuthServiceImpl  {
	@Autowired
	UserRepo repo;
	@Autowired
	Util util;
	
	public ResponseData registerUser(RegisterData registerData) {
		User user = new User();
		BeanUtils.copyProperties(registerData, user);
		repo.save(user);
		return new ResponseData(200, "Registered Successfully");
	}
	
	public ResponseData updateUserInfo(UpdateUserRequest dataToUpdate) {
		try {
			User user = this.findUserById(dataToUpdate.getId());
			user.setFirstName(dataToUpdate.getFirstName());
			user.setLastName(dataToUpdate.getLastName());
			user.setEmailId(dataToUpdate.getEmailId());
			user.setPhoneNumber(dataToUpdate.getPhoneNumber());
			user.setUserName(dataToUpdate.getUserName());
			repo.save(user);
			return new ResponseData(200, "User profile is updated");
		}
		catch(NullPointerException e) {
			return new ResponseData(404, "User not found");
		}
	}
	
	 private User findUserById(String id) {
			 Optional<User> result = repo.findById(new ObjectId(id));
		     return result.orElseThrow(() -> null);
	 }
	
	public ResponseData deleteUser(String id) {
		try {
			User deleted =this.findUserById(id);
			repo.delete(deleted);
			return new ResponseData(200, "User is deleted.");
		}
		catch(NullPointerException e) {
			return new ResponseData(404, "User not found");
		}
			
	}
	
	public boolean checkUserIfAlreadyExists(String email) {
		return repo.findByEmailId(email) !=null ? true : false;
	}
	
	
	public UserInfo fetchUserData(String id) {
		try {
			Optional<User> user = repo.findById(new ObjectId(id));
			//if(user != null) {
				UserInfo info = new UserInfo();
				info.setFirstName(user.get().getFirstName());
				info.setLastName(user.get().getLastName());
				info.setEmailId(user.get().getEmailId());
				info.setPhoneNumber(user.get().getPhoneNumber());
				info.setUserName(user.get().getUserName());
				info.setId(user.get().getId());
			//}
			return info;
		}
		catch(NoSuchElementException e) {
			return null;
		}
			
	}
	
}
