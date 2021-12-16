package main.dao.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import main.dao.Dao;
import main.utils.JDBCUtil;

public class TransactionDaoImp implements Dao {

	@Override
	public <Transaction> List<Transaction> findAll() throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "SELECT * FROM transactions";
		ResultSet rs = st.executeQuery(sql);
		
		ArrayList<main.entities.Transaction> transactions = new ArrayList<main.entities.Transaction>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String transaction_id = rs.getString("transaction_id");
			int user_id = rs.getInt("user_id");
			int product_id = rs.getInt("product_id");
			int quantity = rs.getInt("quantity");
			int color_id = rs.getInt("color_id");
			int discount = rs.getInt("discount");
			double price = rs.getDouble("price");
			int size = rs.getInt("size");
			int status_id = rs.getInt("status_id");
			
			Timestamp created_at = rs.getTimestamp("created_at");
			Timestamp updated_at = rs.getTimestamp("updated_at");
			
			main.entities.Transaction transaction = new main.entities.Transaction(id, transaction_id, user_id,
					product_id, quantity, color_id, discount, price, size, status_id, created_at, updated_at);
			transactions.add(transaction);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Transaction>) transactions;
	}

	@Override
	public <Transaction> List<Transaction> findById(int id) throws SQLException {
		return null;
	}

	@Override
	public <Transaction> List<Transaction> findByName(String name) throws SQLException {
		//we dont have find by nave function here
		return null;
	}
	
	public <Transaction> List<Transaction> findByUserId(int user_id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "SELECT * FROM transactions WHERE user_id = "+ user_id;
		ResultSet rs = st.executeQuery(sql);
		
		ArrayList<main.entities.Transaction> transactions = new ArrayList<main.entities.Transaction>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String transaction_id = rs.getString("transaction_id");
			int res_user_id = rs.getInt("user_id");
			int product_id = rs.getInt("product_id");
			int quantity = rs.getInt("quantity");
			int color_id = rs.getInt("color_id");
			int discount = rs.getInt("discount");
			double price = rs.getDouble("price");
			int size = rs.getInt("size");
			int status_id = rs.getInt("status_id");
			
			Timestamp created_at = rs.getTimestamp("created_at");
			Timestamp updated_at = rs.getTimestamp("updated_at");
			
			main.entities.Transaction transaction = new main.entities.Transaction(id, transaction_id, res_user_id,
					product_id, quantity, color_id, discount, price, size, status_id, created_at, updated_at);
			transactions.add(transaction);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Transaction>) transactions;
	}
	
	public <Transaction> List<Transaction> findByTransactionId(int transaction_id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "SELECT * FROM transactions WHERE transaction_id = "+ transaction_id;
		ResultSet rs = st.executeQuery(sql);
		
		ArrayList<main.entities.Transaction> transactions = new ArrayList<main.entities.Transaction>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String res_transaction_id = rs.getString("transaction_id");
			int res_user_id = rs.getInt("user_id");
			int product_id = rs.getInt("product_id");
			int quantity = rs.getInt("quantity");
			int color_id = rs.getInt("color_id");
			int discount = rs.getInt("discount");
			double price = rs.getDouble("price");
			int size = rs.getInt("size");
			int status_id = rs.getInt("status_id");
			
			Timestamp created_at = rs.getTimestamp("created_at");
			Timestamp updated_at = rs.getTimestamp("updated_at");
			
			main.entities.Transaction transaction = new main.entities.Transaction(id, res_transaction_id, res_user_id,
					product_id, quantity, color_id, discount, price, size, status_id, created_at, updated_at);
			transactions.add(transaction);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Transaction>) transactions;
	}
	
	public <Transaction> List<Transaction> findRunningTransactionByUserId(int user_id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "SELECT * FROM transactions WHERE user_id = "+ user_id + " AND status_id != 4";
		ResultSet rs = st.executeQuery(sql);
		
		ArrayList<main.entities.Transaction> transactions = new ArrayList<main.entities.Transaction>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String res_transaction_id = rs.getString("transaction_id");
			int res_user_id = rs.getInt("user_id");
			int product_id = rs.getInt("product_id");
			int quantity = rs.getInt("quantity");
			int color_id = rs.getInt("color_id");
			int discount = rs.getInt("discount");
			double price = rs.getDouble("price");
			int size = rs.getInt("size");
			int status_id = rs.getInt("status_id");
			
			Timestamp created_at = rs.getTimestamp("created_at");
			Timestamp updated_at = rs.getTimestamp("updated_at");
			
			main.entities.Transaction transaction = new main.entities.Transaction(id, res_transaction_id, res_user_id,
					product_id, quantity, color_id, discount, price, size, status_id, created_at, updated_at);
			transactions.add(transaction);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Transaction>) transactions;
	}

}
