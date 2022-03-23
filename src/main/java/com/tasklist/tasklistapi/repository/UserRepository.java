package com.tasklist.tasklistapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasklist.tasklistapi.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
}
