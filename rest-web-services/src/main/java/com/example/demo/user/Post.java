package com.example.demo.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="post")
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Size(min=5)
	private String message;
	
	
	private int likes;
	
	@Size(min = 4, message ="comment should be at least 4 characters")
	private String comment;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	//@JoinColumn(name="user_id")
	private User user;

	public Post() {
		super();
		
	}
    
	
	public Post(Integer id, @Size(min = 5) String message, int likes, String comment, User user) {
		super();
		this.id = id;
		this.message = message;
		this.likes = likes;
		this.comment = comment;
		this.user = user;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	@Override
	public String toString() {
		return "Post [id=" + id + ", message=" + message + ", likes=" + likes + ", comment=" + comment + "]";
	}
   
	
	

}
