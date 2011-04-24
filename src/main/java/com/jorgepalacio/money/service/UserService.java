package com.jorgepalacio.money.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.jorgepalacio.money.dao.impl.JpaDaoTemplate;
import com.jorgepalacio.money.model.User;

public class UserService {
	JpaDaoTemplate<User> userDao;

	@Autowired
	public void setUserDao(@Qualifier("userDao") JpaDaoTemplate<User> userDao) {
		this.userDao = userDao;
	}

	public void createUser(final String name, final String login,
			final String email) {
		createUser(name, login, email, "");
	}

	@Transactional
	public void createUser(final String name, final String login,
			final String email, final String password) {
		User user = new User();
		user.setFullName(name);
		user.setEmail(email);
		user.setLogin(login);
		user.setPassword(password);

		userDao.add(user);
	}

	public List<User> all() {
		return userDao.all();
	}

	@Transactional
	public void createUser(User user) {
		createUser(user.getFullName(), user.getLogin(), user.getEmail(), user
				.getPassword());
	}

	public User getUser(Long id) {
		return userDao.byId(id);
	}

	public User getCurrentUser() {
		String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		return byLogin(currentUser);
	}

	@Transactional
	public void updateUser(final User user) {

		User original = getUser(user.getId());
		original.setFullName(user.getFullName());
		original.setEmail(user.getEmail());

		if (user.getLogin() != null && user.getLogin().length() > 0) {
			original.setLogin(user.getLogin());
		}
		if (user.getPassword() != null && user.getPassword().length() > 0) {
			original.setPassword(user.getPassword());
		}

		userDao.edit(original);
	}

	public User byLogin(String username) {
		List<User> users = userDao.byParams(
				"SELECT u FROM User u WHERE u.login=?1", username);
		if (users.size() == 1) {
			return users.get(0);
		}
		return null;
	}
}
