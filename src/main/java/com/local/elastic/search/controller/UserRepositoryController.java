package com.local.elastic.search.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.local.elastic.search.model.User;
import com.local.elastic.search.repository.UserRepository;

@RestController
@RequestMapping("/repo")
public class UserRepositoryController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/all")
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@RequestMapping("/id/{userId}")
	public User getUser(@PathVariable String userId) {
		LOG.info("Getting user with ID: {}", userId);
		User user = userRepository.findById(userId).get();
		LOG.info("User with ID: {} is {}", userId, user);
		return user;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public User addNewUsers(@RequestBody User user) {
		LOG.info("Adding user : {}", user);
		userRepository.save(user);
		LOG.info("Added user : {}", user);
		return user;
	}

	@RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
	public Object getAllUserSettings(@PathVariable String userId) {

		User user = userRepository.findById(userId).get();
		if (user != null) {
			return user.getUserSettings();
		} else {
			return "User not found.";
		}
	}

	@RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
	public String getUserSetting(@PathVariable String userId, @PathVariable String key) {
		User user = userRepository.findById(userId).get();
		if (user != null) {
			return user.getUserSettings().get(key);
		} else {
			return "User not found.";
		}
	}

	@RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
	public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
		User user = userRepository.findById(userId).get();
		if (user != null) {
			user.getUserSettings().put(key, value);
			userRepository.save(user);
			return "Key added";
		} else {
			return "User not found.";
		}
	}
}