package main.dao.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.dao.Dao;
import main.entities.Type;
import main.utils.JDBCUtil;

public class TypeDaoImp implements Dao{
	@Override
	public <Type> List<Type> findAll() throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "SELECT * FROM types";
		ResultSet rs = st.executeQuery(sql);
		
		ArrayList<main.entities.Type> types = new ArrayList<main.entities.Type>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			
			main.entities.Type type = new main.entities.Type(id, name);
			types.add(type);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Type>) types;
	}
	
	@Override
	public <T> List<T> findById(int id) throws SQLException {
		return null;
	}

	@Override
	public <Type> List<Type> findByName(String name) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "SELECT * FROM types "
				+ "WHERE name LIKE '%"+ name +"%'";
		ResultSet rs = st.executeQuery(sql);
		
		ArrayList<main.entities.Type> types = new ArrayList<main.entities.Type>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String res_name = rs.getString("name");
			
			main.entities.Type type = new main.entities.Type(id, res_name);
			types.add(type);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Type>) types;
	}
	
}
