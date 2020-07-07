package com.example.demo.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	
	//private static List<User> userspost = new ArrayList<>();
	
	private static List<Post> posts1 = new ArrayList<>();
	private static List<Post> posts2 = new ArrayList<>();
	private static List<Post> posts3 = new ArrayList<>();
	private static List<Post> posts4 = new ArrayList<>();
	
	private static int usercount = 4;
	
	static{
		
		/*posts1.add(new Post(1, "my first phots", "like from yaried", "good looking"));
		posts1.add(new Post(2, "my second phots", "like from hani", "nice looking"));
		
		posts2.add(new Post(1, "my car photos", "like your car, owesome", "wow nice car"));
		posts2.add(new Post(2, "my bycle photos", "like your bycle, owesome", "i like it"));
		
		posts3.add(new Post(1, "my cat photos", "his name is kemal", "good bro"));
		posts3.add(new Post(2, "my dog photos", "his name is mak", "strong dog"));
		
		posts4.add(new Post(1, "my fist house photo", " 2600 at matthew", "beatutiful house"));
		posts4.add(new Post(2, "my second house photo", "1010 w trade st", "very clean house"));*/
		
		/*
		 * userspost.add(new User(1, "yaried tekie", new Date(), posts1));
		 * userspost.add(new User(2, "Saliem Bokre", new Date(), posts2));
		 * userspost.add(new User(3, "Hannah Kidane", new Date(), posts3));
		 * userspost.add(new User(4, "Bethel Kidane", new Date(), posts4));
		 */
		users.add(new User(1, "yaried tekie", "10/09/1988"));
		users.add(new User(2, "Saliem Bokre", "03/24/1987"));
		users.add(new User(3, "Hannah Kidane", "11/12/2016"));
		users.add(new User(4, "Bethel Kidane", "09/20/2019"));
		
	}
	
 
	 //findbyID user
	
	public User  findOneById(Integer id) {
		
		for(User user : users) {
			
			if(user.getId() == id) {
				
				return user;
			}
		}
		
		return null;
	}
	
	//Save usedr
	
	public User Save (User user) {
		
		if(user.getId() == null) {
			
			user.setId(++usercount);
		}
		
		users.add(user);
		
		return user;
		
	}
	
	public List<User> findAll(){
		
		return users;
	}
	
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
	//
	
	/*
	 * public List<Post> getAllPosts(Integer id) {
	 * 
	 * for(int i = 0; i < users.size(); i++) { if(users.get(i).getId() == id) {
	 * 
	 * return users.get(i).getPosts(); } }
	 * 
	 * return null;
	 * 
	 * }
	 */
	
	/*
	 * public Post getPost(Integer id, Integer postid) {
	 * 
	 * Post post = null; for(int i = 0; i < users.size(); i++) { if(
	 * users.get(i).getId() == id) {
	 * 
	 * for(int j = 0; j < users.get(i).getPosts().size(); j++) {
	 * 
	 * if(users.get(i).getPosts().get(j).getId() == postid) {
	 * 
	 * post = users.get(i).getPosts().get(j); } } } }
	 * 
	 * return post; }
	 */
}
