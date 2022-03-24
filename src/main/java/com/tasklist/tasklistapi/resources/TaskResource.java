package com.tasklist.tasklistapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasklist.tasklistapi.models.Task;
import com.tasklist.tasklistapi.repository.TaskRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(value="/api")
@Api(value="Tasks API REST")
public class TaskResource {
	@Autowired
	TaskRepository taskRepository;
	
	@PostMapping("/task")
	@ApiOperation(value="Create a task")
	public Task createTask(@RequestBody Task task) {
		return taskRepository.save(task);
	}
	
	@GetMapping("/task")
	@ApiOperation(value="Return tasks list")
	public List<Task> listTasks() {
		return taskRepository.findAll();
	}
	
	@PutMapping("/task")
	@ApiOperation(value="Update a task")
	public Task updateTask(@RequestBody Task task) {
		return taskRepository.save(task);
	}
	
	@DeleteMapping("/task/{id}")
	@ApiOperation(value="Delete a task")
	public void deleteTask(@PathVariable(value="id") long id) {
		taskRepository.deleteById(id);
	}
	
	@GetMapping("/task/{id}")
	@ApiOperation(value="Return a unique task")
	public Task getTask(@PathVariable(value="id") long id) {
		return taskRepository.findById(id);
	}
}
