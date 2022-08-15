package com.practise.task.respository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;

import com.practise.task.entity.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends MongoRepository<User, ObjectId> {
	
	User findByEmailId(String emailId);
	
	User findByUserName(String userName);
	
	Optional<User> findById(ObjectId id);
	
	void deleteById(ObjectId id);

}
