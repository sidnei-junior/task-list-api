package com.tasklist.tasklistapi.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TASK")
public class Task implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String title;
	
	private String description;

	private long assigned;
	
	@Temporal(TemporalType.TIMESTAMP)     
	private Date created_at = new Date(System.currentTimeMillis());
	
	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getAssigned() {
		return assigned;
	}

	public void setAssigned(long assigned) {
		this.assigned = assigned;
	}

	public Date getCreated_at() {
		return created_at;
	}
}