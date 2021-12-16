package main.dao.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import main.dao.Dao;
import main.entities.User;
import main.utils.C3P0Util;
import main.utils.JDBCUtil;

public class UserDaoImp implements Dao{

	@Override
	public <User> List<User> findAll() throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "SELECT * FROM users";
		ResultSet rs = st.executeQuery(sql);
		
		ArrayList<main.entities.User> users = new ArrayList<main.entities.User>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String email = rs.getString("email");
			String password = rs.getString("password");
			String phoneNumber = rs.getString("phone_number");
			String address = rs.getString("address");
			String birthday = rs.getString("birthday");
			int isLogin = rs.getInt("isLogin");
			Timestamp created_at = rs.getTimestamp("created_at");
			Timestamp updated_at = rs.getTimestamp("updated_at");
			
			main.entities.User user = new main.entities.User(id, name, email, password, phoneNumber, address, birthday, isLogin, 
					created_at, updated_at);
			users.add(user);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<User>) users;
	}
	
	@Override
	public <User> List<User> findById(int id) throws SQLException {
		return null;
	}

	public <User> User findByUserId(int id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "SELECT * FROM users WHERE id = "+ id;
		ResultSet rs = st.executeQuery(sql);
		
		main.entities.User user = new main.entities.User();
		while (rs.next()) {
			int resId = rs.getInt("id");
			String name = rs.getString("name");
			String email = rs.getString("email");
			String password = rs.getString("password");
			String phoneNumber = rs.getString("phone_number");
			String address = rs.getString("address");
			String birthday = rs.getString("birthday");
			int isLogin = rs.getInt("isLogin");
			Timestamp created_at = rs.getTimestamp("created_at");
			Timestamp updated_at = rs.getTimestamp("updated_at");
			
			user = new main.entities.User(resId, name, email, password, phoneNumber, address, birthday, isLogin, 
					created_at, updated_at);
		}
		JDBCUtil.close(conn, st, rs);
		return (User) user;
	}
	
	public User findByEmail(String email) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from users where email=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, email);
		ResultSet rs = pst.executeQuery();
		User user = null;
		if (rs.next()) {
			int resId = rs.getInt("id");
			String name = rs.getString("name");
			String resEmail = rs.getString("email");
			String password = rs.getString("password");
			String phoneNumber = rs.getString("phone_number");
			String address = rs.getString("address");
			String birthday = rs.getString("birthday");
			int isLogin = rs.getInt("isLogin");
			Timestamp created_at = rs.getTimestamp("created_at");
			Timestamp updated_at = rs.getTimestamp("updated_at");
			
			user = new main.entities.User(resId, name, resEmail, password, phoneNumber, address, birthday, isLogin, 
					created_at, updated_at);
		}
		JDBCUtil.close(conn, pst, rs);
		return user;
	}
	
	public <User> int insert(User user) throws SQLException {
		if(((main.entities.User) user).getPassword() != null && ((main.entities.User) user).getAddress() != null && 
				((main.entities.User) user).getPhoneNumber() != null ) {
			String sql = "insert into users (id, name, email, password, phone_number, address, birthday, isLogin) "
					+ "values(null,?,?,?,?,?,?,0)";
			QueryRunner run = new QueryRunner(C3P0Util.getDataSource());
			return run.update(sql, 
					((main.entities.User) user).getName(),
					((main.entities.User) user).getEmail(),
					((main.entities.User) user).getPassword(),
					((main.entities.User) user).getPhoneNumber(),
					((main.entities.User) user).getAddress(),
					((main.entities.User) user).getBirthday());
		}
		return 0;
	}
	
	public <User> int update(User user) throws SQLException {		
		Connection conn = JDBCUtil.getConnection();
		
		String sql = "update users set name=?, email=?, password=?, phone_number=?, address=?, birthday=?, "
				+ " updated_at=? where email=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		
		System.out.println("ID: "+ ((main.entities.User) user).getId());
		
		pst.setString(1, ((main.entities.User) user).getName());
		pst.setString(2, ((main.entities.User) user).getEmail());
		pst.setString(3, ((main.entities.User) user).getPassword());
		pst.setString(4, ((main.entities.User) user).getPhoneNumber());
		pst.setString(5, ((main.entities.User) user).getAddress());
		pst.setString(6, ((main.entities.User) user).getBirthday());
		
		java.util.Date date= new java.util.Date();
		long time = date.getTime();
		Timestamp timestamp = new Timestamp(time);
		pst.setTimestamp(7, timestamp);
		
		pst.setString(8, ((main.entities.User) user).getEmail());
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}
	
	public int updateLoginStatus(int id, int isLogin) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update users set isLogin=? where id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, isLogin);
		pst.setInt(2, id);
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}
	
	
	public int delete(String email) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from users where email=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, email);
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}
	
	@Override
	public <User> List<User> findByName(String name) throws SQLException {
		return null;
	}

}
