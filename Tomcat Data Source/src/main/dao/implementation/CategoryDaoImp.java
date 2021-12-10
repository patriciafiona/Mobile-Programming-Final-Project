package main.dao.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.dao.Dao;
import main.entities.Category;
import main.utils.JDBCUtil;

public class CategoryDaoImp implements Dao{
	
	@Override
	public <Category> List<Category> findAll() throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "SELECT * FROM categories";
		ResultSet rs = st.executeQuery(sql);
		
		ArrayList<main.entities.Category> categories = new ArrayList<main.entities.Category>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			
			main.entities.Category category = new main.entities.Category(id, name);
			categories.add(category);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Category>) categories;
	}

	@Override
	public <T> List<T> findById(int id) throws SQLException {
		return null;
	}

	@Override
	public <T> List<T> findByName(String name) throws SQLException {
		return null;
	}

}
