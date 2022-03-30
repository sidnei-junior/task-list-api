package com.tasklist.tasklistapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Task> createTask(@RequestBody Task task) {
		if (task.notFoundAllCreateResources()) {
			return new ResponseEntity<Task>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Task>(taskRepository.save(task), HttpStatus.OK);
	}
	
	
	@GetMapping("/task")
	@ApiOperation(value="Return tasks list")
	public List<Task> listTasks() {
		return taskRepository.findAll();
	}
	
	@PutMapping("/task")
	@ApiOperation(value="Update a task")
	public ResponseEntity<Task> updateTask(@RequestBody Task task) {
		Task taskToUpdate = taskRepository.findById(task.getId());
		if (taskToUpdate == null) {
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		
		if (task.notFoundAllUpdateResources()) {
			return new ResponseEntity<Task>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Task>(taskRepository.save(task), HttpStatus.OK);
	}
	
	@DeleteMapping("/task/{id}")
	@ApiOperation(value="Delete a task")
	public ResponseEntity<Void> deleteTask(@PathVariable(value="id") long id) {
		Task taskToDelete = taskRepository.findById(id);
		if (taskToDelete == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		taskRepository.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/task/{id}")
	@ApiOperation(value="Return a unique task")
	public ResponseEntity<Task> getTask(@PathVariable(value="id") long id) {
		Task task = taskRepository.findById(id);
		if (task == null) {
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}
}
