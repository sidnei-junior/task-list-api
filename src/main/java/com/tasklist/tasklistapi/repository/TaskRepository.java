package com.tasklist.tasklistapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasklist.tasklistapi.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	Task findById(long id);
}
