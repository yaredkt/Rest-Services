package com.example.demo.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.security.AuthenticationRequest;
import com.example.demo.security.AuthenticationResponse;
import com.example.demo.security.JwtUtil;
import com.example.demo.security.myUserDetailsService;

@RestController
public class UserController {

	@Autowired
	private UserDaoService UserDaoService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private myUserDetailsService userDetailService;

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping(path = "/jpa/users")
	public List<User> retreiveAll() {

		return userRepository.findAll();
	}

	@GetMapping(path = "/jpa/users/{id}")
	public User findById(@PathVariable Integer id) {

		Optional<User> user = userRepository.findById(id);

		if (id <= 0) {

			throw new BadUserRequestException("Such method is not allowed");
		}
		if (!user.isPresent()) {

			throw new UserNotFoundException("id " + id);

		}

		/*
		 * EntityModel<User> model = EntityModel.of(user);
		 * 
		 * 
		 * Link linkTo =
		 * WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserResource.class,
		 * retreiveAll())).withRel("all-user");
		 * 
		 * model.add(linkTo);
		 */

		return user.get();
	}

	/*
	 * @PostMapping(path = "/users", produces = { MediaType.APPLICATION_XML_VALUE,
	 * MediaType.APPLICATION_JSON_VALUE }, consumes = {
	 * MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	 */
	// @ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

		User createdUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdUser.getId()).toUri();

		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

	}

	@DeleteMapping("/jpa/users/{id}")
	public User deleteUser(@PathVariable int id) {

		Optional<User> user = userRepository.findById(id);

		userRepository.deleteById(id);

		if (!user.isPresent())
			throw new UserNotFoundException("id-" + id);

		return user.get();
	}

	@PostMapping("/jpa/authenticate")
	public ResponseEntity<?> createAuthentication(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException ex) {
			throw new Exception("Incorrect User Name or Password", ex);
		}

		final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}

	// Post

	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<Post> getAllPosts(@PathVariable Integer id) {

		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new UserNotFoundException("id " + id);
		}

		return user.get().getPosts();

	}

	@PostMapping(path = "/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable Integer id, @Valid @RequestBody Post post) {

		Optional<User> useroptional = userRepository.findById(id);

		if (id <= 0) {

			throw new BadUserRequestException("Such method is not allowed");
		}
		if (!useroptional.isPresent()) {

			throw new UserNotFoundException("id " + id);

		}

		User user = useroptional.get();

		post.setUser(user);

		postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();

		return new ResponseEntity<>(post, HttpStatus.CREATED);

	}

	@GetMapping(path = "/jpa/users/{id}/posts/{postid}")
	public Post getPost(@PathVariable Integer id, @PathVariable Integer postid) {

		Post post = null;

		Optional<User> useroptional = userRepository.findById(id);

		if (id <= 0) {

			throw new BadUserRequestException("Such method is not allowed");
		}
		if (!useroptional.isPresent()) {

			throw new UserNotFoundException("id " + id);

		}

		User user = useroptional.get();

		for (int i = 0; i <  user.getPosts().size(); i++) {

			if (user.getPosts().get(i).getId() == postid) {

				post = postRepository.findById(postid).get();
			}
		}

		if (post == null) {

			throw new UserNotFoundException("postid " + postid);
		}

		return post;
	}

}
