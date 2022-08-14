package com.practise.task.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practise.task.entity.User;


public interface UserRepo extends JpaRepository<User, Long> {
	
	User findByEmailId(String emailId);
	
	User findByUserName(String userName);
	
	User findById(Integer id);
	
	void deleteById(Integer id);

}
