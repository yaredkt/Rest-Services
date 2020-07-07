package com.example.demo.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.security.AuthenticationRequest;
import com.example.demo.security.AuthenticationResponse;
import com.example.demo.security.JwtUtil;
import com.example.demo.security.myUserDetailsService;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService UserDaoService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private myUserDetailsService userDetailService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping(path = "/users" )
	public List<User> retreiveAll() {

		return UserDaoService.findAll();
	}

	
	@GetMapping(path = "users/{id}")
	public User findById(@PathVariable Integer id) {

		User user = UserDaoService.findOneById(id);

		if (id <= 0) {

			throw new BadUserRequestException("Such method is not allowed");
		}
		if (user == null) {

			throw new UserNotFoundException("id " + id);

		}
		;
		
		
		/*EntityModel<User> model = EntityModel.of(user);
		 
		
		Link linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserResource.class, retreiveAll())).withRel("all-user");
	 
		model.add(linkTo);*/
	 
		return user; 
	}

	/*@PostMapping(path = "/users", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })*/
	// @ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

		User createdUser = UserDaoService.Save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdUser.getId()).toUri();

		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

	}

	@DeleteMapping("/users/{id}")
	public User deleteUser(@PathVariable int id) {
		User user = UserDaoService.deleteById(id);

		if (user == null)
			throw new UserNotFoundException("id-" + id);

		return user;
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?>  createAuthentication(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		}
		catch(BadCredentialsException ex) {
			throw new Exception("Incorrect User Name or Password", ex);
		}
		
		final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
	}

	// Post

	/*
	 * @GetMapping(path = "/users/{id}/posts") public List<Post>
	 * getAllPosts(@PathVariable Integer id) {
	 * 
	 * 
	 * 
	 * List<Post> posts = UserDaoService.getAllPosts(id);
	 * 
	 * if(posts == null) { throw new UserNotFoundException("id " + id); }
	 * 
	 * return posts; }
	 */

	/*
	 * @GetMapping(path = "/users/{id}/posts/{postid}") public Post
	 * getPost(@PathVariable Integer id, @PathVariable Integer postid) {
	 * 
	 * 
	 * 
	 * Post post = UserDaoService.getPost(id, postid);
	 * 
	 * if(post == null) { throw new UserNotFoundException("postid " + postid); }
	 * 
	 * return post; }
	 */

}
