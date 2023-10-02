package com.webmvc.todo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

@Entity
@Table(name="Tags")
public class Tag {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	protected int id;
	
	@Column(name="tagname")
	protected String tagname;
	
	@Column(name="color")
	protected String color;
	
	@OneToMany(mappedBy="tag")
	protected List<Todo> todos;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	protected User user;
	
	@PreRemove
	private void preRemove() {
	    for (Todo t : todos) {
	        t.setTag(null);
	    }
	}
	
	public Tag() {
	}
	
	public Tag(String tagname, String color) {
		super();
		this.tagname = tagname;
		this.color = color;
	}
	
	public Tag(String tagname, String color, User user) {
		super();
		this.tagname = tagname;
		this.color = color;
		this.user = user;
	}
	
	public Tag(int id, String tagname, String color) {
		super();
		this.id = id;
		this.tagname = tagname;
		this.color = color;
	}
	
	public Tag(int id, String tagname, String color, User user) {
		super();
		this.id = id;
		this.tagname = tagname;
		this.color = color;
		this.user = user;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTagname() {
		return tagname;
	}
	
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public List<Todo> getTodos() {
		return todos;
	}
	
	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}
