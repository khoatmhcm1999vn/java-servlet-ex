package com.webmvc.todo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="Todos")
public class Todo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	protected int id;
	
	@Column(name="title")
	protected String title;
	
	@Column(name="priority")
	protected String priority;
	
	@ManyToOne
	@JoinColumn(name="tag_id")
	protected Tag tag;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date")
	protected Date date;
	
	@Temporal(TemporalType.TIME)
	@Column(name="deadline")
	protected Date deadline;
	
	@Temporal(TemporalType.TIME)
	@Column(name="remindat")
	protected Date remindat;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start")
	protected Date start;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end")
	protected Date end;
	
	@Column(name="done", columnDefinition = "bit default 0")
	protected boolean done;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="doneat")
	protected Date doneat;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	protected User user;
	
	public Todo() {
	}
	
	public Todo(String title, String priority, Date date) {
		super();
		this.title = title;
		this.priority = priority;
		this.date = date;
	}
	
	public Todo(String title, String priority) {
		super();
		this.title = title;
		this.priority = priority;
	}
	
	public Todo(int id, String title, String priority) {
		super();
		this.id = id;
		this.title = title;
		this.priority = priority;
	}
	
	public Todo(String title, String priority, User user) {
		super();
		this.title = title;
		this.priority = priority;
		this.user = user;
	}
	
	public Todo(int id, String title, String priority, Tag tag, Date date, Date deadline, Date remindat, Date start, Date end, Boolean done, Date doneat, User user) {
		super();
		this.id = id;
		this.title = title;
		this.priority = priority;
		this.tag = tag;
		this.date = date;
		this.deadline = deadline;
		this.remindat = remindat;
		this.start = start;
		this.end = end;
		this.done = done;
		this.doneat = doneat;
		this.user = user;
	}
	
	public Todo(String title, String priority, Tag tag, Date date, Date deadline, Date remindat, User user) {
		super();
		
		this.title = title;
		this.priority = priority;
		this.tag = tag;
		this.date = date;
		this.deadline = deadline;
		this.remindat = remindat;
		this.user = user;
	}
	
	public Todo(int id, String title, String priority, Date date, Date deadline, User user) {
		super();
		this.id = id;
		this.title = title;
		this.priority = priority;
		this.date = date;
		this.deadline = deadline;
		this.user = user;
	}
	
	public Todo(String title, String priority, Date date, Date deadline, User user) {
		super();
		this.title = title;
		this.priority = priority;
		this.date = date;
		this.deadline = deadline;
		this.user = user;
	}
	
	public Todo(int id, String title, String priority, User user) {
		super();
		this.id = id;
		this.title = title;
		this.priority = priority;
		this.user = user;
	}
	
	public Todo(String title, String priority,Tag tag, Date date, User user) {
		super();
		this.title = title;
		this.priority = priority;
		this.tag=tag;
		this.date = date;
		this.user = user;
	}
	
	public Todo(int id, String title, String priority,Tag tag, Date date) {
		super();
		this.id = id;
		this.title = title;
		this.priority = priority;
		this.tag=tag;
		this.date = date;
	}
	
	public Todo(int id, String title, String priority,Tag tag, Date date, User user) {
		super();
		this.id=id;
		this.title = title;
		this.priority = priority;
		this.tag=tag;
		this.date = date;
		this.user = user;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public Tag getTag() {
		return tag;
	}
	
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDeadline() {
		return deadline;
	}
	
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	public Date getRemindat() {
		return remindat;
	}
	
	public void setRemindat(Date remindat) {
		this.remindat = remindat;
	}
	
	public Date getStart() {
		return start;
	}
	
	public void setStart(Date start) {
		this.start = start;
	}
	
	public Date getEnd() {
		return end;
	}
	
	public void setEnd(Date end) {
		this.end = end;
	}
	
	public boolean getDone() {
		return done;
	}
	
	public void setDone(Boolean done) {
		this.done = done;
	}
	
	public Date getDoneat() {
		return doneat;
	}
	
	public void setDoneat(Date doneat) {
		this.doneat = doneat;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	//hàm lấy cấp độ todo
	public int getLevelTodo() {
		if(this.getPriority().equals("High"))
			return 1;
		else if(this.getPriority().equals("Medium"))
			return 2;
		else 
			return 3;
	}
}
