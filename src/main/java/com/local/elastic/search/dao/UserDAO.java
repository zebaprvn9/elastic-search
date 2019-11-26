package com.local.elastic.search.dao;

import java.util.List;

import com.local.elastic.search.model.User;

public interface UserDAO {

	List<User> getAllUsers();

	User getUserById(String userId);

	User addNewUser(User user);

	Object getAllUserSettings(String userId);

	String getUserSetting(String userId, String key);

	String addUserSetting(String userId, String key, String value);
}