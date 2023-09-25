package com.webmvc.todo.model;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	protected int id;
	
	@Column(name="email", unique = true)
	protected String email;
	
	@Column(name="pass")
	protected String password;
	
	@Column(name="salt")
	protected String salt;
	
	@Column(name="fullname")
	protected String fullname;
	
	@Column(name="gender", columnDefinition = "bit default 0")
	protected boolean gender;
	
	@Temporal(TemporalType.DATE)
	@Column(name="birthdate")
	protected Date birthdate;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	protected List<Todo> todos;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	protected List<Tag> tags;
	
	
	public User() {
	}
	
	public User(String email, String password, String fullname) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
	}
	
	public User(int id, String email, String password, String fullname) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
	}
	
	public User(String email, String password, String fullname, Boolean gender) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.gender = gender;
	}
	
	public User(String email, String password, String salt, String fullname, Boolean gender) {
		super();
		this.email = email;
		this.password = password;
		this.salt = salt;
		this.fullname = fullname;
		this.gender = gender;
	}
	
	public User(int id, String email, String password, String fullname, Boolean gender) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.gender = gender;
	}
	
	public User(String email, String password, String fullname, Boolean gender, Date birthdate) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.gender = gender;
		this.birthdate = birthdate;
	}
	
	public User(int id, String email, String password, String fullname, Boolean gender, Date birthdate) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.gender = gender;
		this.birthdate = birthdate;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public void setSall(String salt) {
		this.salt = salt;
	}
	
	public String getFullname() {
		return fullname;
	}
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public Boolean getGender() {
		return gender;
	}
	
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	
	public Date getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public List<Todo> getTodos(){
		return todos;
	}
	
	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}
	
	public List<Tag> getTags() {
		return tags;
	}
	
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
}
