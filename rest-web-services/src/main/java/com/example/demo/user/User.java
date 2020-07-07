package com.example.demo.user;

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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//@XmlRootElement
//@XmlType(propOrder = {"id", "name", "birtDate"})
@ApiModel(description = "information about the user")
@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Size(min = 2, message = "user most be atleast two characters")
	@ApiModelProperty(notes = "Name shuould be at least two characters")
	private String name;

	@Size(message = "Date should be atleaset 2 character")
	@ApiModelProperty(notes = "date shoud be in the past")
	@Column(name = "birth_date")
	private String birthDate;


	@Valid
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private List<Post> posts;

	public User() {

	}

	public User(Integer id, String name, String birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;

	}

	public User(Integer id, String name, String birtDate, List posts) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birtDate;
		this.posts = posts;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirtDate() {
		return birthDate;
	}

	public void setBirtDate(String birtDate) {
		this.birthDate = birtDate;
	}

	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {

		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birtDate=" + birthDate + "]";
	}

}
