package main.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import main.dao.Dao;
import main.utils.C3P0Util;
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
			double total_all_price = rs.getDouble("total_all_price");
			int total_all_product = rs.getInt("total_all_product");
			int size = rs.getInt("size");
			int status_id = rs.getInt("status_id");
			
			Timestamp created_at = rs.getTimestamp("created_at");
			Timestamp updated_at = rs.getTimestamp("updated_at");
			
			main.entities.Transaction transaction = new main.entities.Transaction(id, transaction_id, user_id,
					product_id, quantity, color_id, discount, price, total_all_price, total_all_product, size, status_id, created_at, updated_at);
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
			double total_all_price = rs.getDouble("total_all_price");
			int total_all_product = rs.getInt("total_all_product");
			int size = rs.getInt("size");
			int status_id = rs.getInt("status_id");
			
			Timestamp created_at = rs.getTimestamp("created_at");
			Timestamp updated_at = rs.getTimestamp("updated_at");
			
			main.entities.Transaction transaction = new main.entities.Transaction(id, transaction_id, res_user_id,
					product_id, quantity, color_id, discount, price, total_all_price, total_all_product, size, status_id, created_at, updated_at);
			transactions.add(transaction);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Transaction>) transactions;
	}
	
	public <Transaction> List<Transaction> findByTransactionId(int transaction_id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "SELECT * FROM transactions WHERE transaction_id = "+ transaction_id +
				" GROUP BY transactions.transaction_id ORDER BY transactions.updated_at";
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
			double total_all_price = rs.getDouble("total_all_price");
			int total_all_product = rs.getInt("total_all_product");
			int size = rs.getInt("size");
			int status_id = rs.getInt("status_id");
			
			Timestamp created_at = rs.getTimestamp("created_at");
			Timestamp updated_at = rs.getTimestamp("updated_at");
			
			main.entities.Transaction transaction = new main.entities.Transaction(id, res_transaction_id, res_user_id,
					product_id, quantity, color_id, discount, price, total_all_price, total_all_product, size, status_id, created_at, updated_at);
			transactions.add(transaction);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Transaction>) transactions;
	}
	
	public <Transaction> List<Transaction> findRunningTransactionByUserId(int user_id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "SELECT * FROM transactions WHERE user_id = "+ user_id + " AND status_id != 4 ";
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
			double total_all_price = rs.getDouble("total_all_price");
			int total_all_product = rs.getInt("total_all_product");
			int size = rs.getInt("size");
			int status_id = rs.getInt("status_id");
			
			Timestamp created_at = rs.getTimestamp("created_at");
			Timestamp updated_at = rs.getTimestamp("updated_at");
			
			main.entities.Transaction transaction = new main.entities.Transaction(id, res_transaction_id, res_user_id,
					product_id, quantity, color_id, discount, price, total_all_price, total_all_product, size, status_id, created_at, updated_at);
			transactions.add(transaction);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Transaction>) transactions;
	}
	
	public <Transaction> List<Transaction> findUserTransactionByUserIdAndStatus(int user_id, int status) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "SELECT * FROM transactions WHERE user_id = "+ user_id + " AND status_id = "+ status +
				" GROUP BY transactions.transaction_id ORDER BY transactions.updated_at";
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
			double total_all_price = rs.getDouble("total_all_price");
			int total_all_product = rs.getInt("total_all_product");
			int size = rs.getInt("size");
			int status_id = rs.getInt("status_id");
			
			Timestamp created_at = rs.getTimestamp("created_at");
			Timestamp updated_at = rs.getTimestamp("updated_at");
			
			main.entities.Transaction transaction = new main.entities.Transaction(id, res_transaction_id, res_user_id,
					product_id, quantity, color_id, discount, price, total_all_price, total_all_product, size, status_id, created_at, updated_at);
			transactions.add(transaction);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Transaction>) transactions;
	}
	
	public <Transaction> int insert(Transaction data) throws SQLException {
		String sql = "insert into transactions (id, transaction_id, user_id, product_id, quantity, color_id, discount, price, "
				+ "total_all_price, total_all_product, size, status_id, pay_method) "
				+ "values(null,?,?,?,?,?,?,?,?,?,?,?,?)";
		QueryRunner run = new QueryRunner(C3P0Util.getDataSource());
		return run.update(sql, 
				((main.entities.Transaction) data).getTransaction_id(),
				((main.entities.Transaction) data).getUser_id(),
				((main.entities.Transaction) data).getProduct_id(),
				((main.entities.Transaction) data).getQuantity(),
				((main.entities.Transaction) data).getColor_id(),
				((main.entities.Transaction) data).getDiscount(),
				((main.entities.Transaction) data).getPrice(),
				((main.entities.Transaction) data).getTotalAllPrice(),
				((main.entities.Transaction) data).getTotalAllProduct(),
				((main.entities.Transaction) data).getSize(),
				((main.entities.Transaction) data).getStatus_id(),
				"CreditCard");
	}
	
	public int delete(String transaction_id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from transactions where transaction_id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, transaction_id);
		int n = pst.executeUpdate();
		JDBCUtil.close(conn, pst);
		return n;
	}

}
