package main.service.implementation;

import java.sql.SQLException;
import java.util.List;

import main.dao.implementation.UserDaoImp;
import main.entities.User;
import main.service.Service;

public class UserServiceImpl implements Service{
	UserDaoImp dao = new UserDaoImp();

	public User findUserByEmail(String email) throws SQLException{
		return dao.findByEmail(email);
	}
	
	public <User> int insert(User user) throws SQLException {
		return dao.insert(user);
	}
	
	public int updateLoginStatus(int id, int isLogin) throws SQLException {
		return dao.updateLoginStatus(id, isLogin);
	}
	
	public <User> int update(User user) throws SQLException {
		return dao.update(user);
	}
	
	public int delete(int id) throws SQLException {
		return dao.delete(id);
	}

	@Override
	public <User> List<User> findAll() throws SQLException {
		List<User> userlist = null;
		userlist = (List<User>) dao.findAll();
		return userlist;
	}

	@Override
	public <User> List<User> findById(int id) throws SQLException {
		return null;
	}
	
	public <User> User findByUserId(int id) throws SQLException {
		User user = null;
		user = (User) dao.findByUserId(id);
		return user;
	}

	@Override
	public <User> List<User> findByName(String name) throws SQLException {
		List<User> userlist = null;
		userlist = dao.findByName(name);
		return userlist;
	}
	
}
