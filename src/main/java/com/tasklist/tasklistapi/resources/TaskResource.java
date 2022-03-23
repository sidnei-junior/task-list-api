package com.tasklist.tasklistapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasklist.tasklistapi.models.Task;
import com.tasklist.tasklistapi.repository.TaskRepository;

@RestController
@RequestMapping(value="/api")
public class TaskResource {
	@Autowired
	TaskRepository taskRepository;
	
	@GetMapping("/task")
	public List<Task> listTasks() {
		return taskRepository.findAll();
	}
	
	@GetMapping("/task/{id}")
	public Task getTask(@PathVariable(value="id") long id) {
		return taskRepository.findById(id);
	}
	
	@PostMapping("/task")
	public Task createTask(@RequestBody Task task) {
		return taskRepository.save(task);
	}
}
