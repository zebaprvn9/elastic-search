package com.local.elastic.search.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.local.elastic.search.dao.UserDAO;
import com.local.elastic.search.model.User;

@RestController
public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserDAO userDAO;

	@RequestMapping("/all")
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	@RequestMapping("/id/{userId}")
	public User getUser(@PathVariable String userId) {
		User user = userDAO.getUserById(userId);
		return user;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public User addNewUsers(@RequestBody User user) {
		userDAO.addNewUser(user);
		return user;
	}

	@RequestMapping(value = "/settings/{name}", method = RequestMethod.GET)
	public Object getAllUserSettings(@PathVariable String name) {
		return userDAO.getAllUserSettings(name);
	}

	@RequestMapping(value = "/settings/{name}/{key}", method = RequestMethod.GET)
	public String getUserSetting(@PathVariable String name, @PathVariable String key) {
		return userDAO.getUserSetting(name, key);
	}

	@RequestMapping(value = "/settings/{name}/{key}/{value}", method = RequestMethod.GET)
	public String addUserSetting(@PathVariable String name, @PathVariable String key, @PathVariable String value) {
		return userDAO.addUserSetting(name, key, value);
	}
}