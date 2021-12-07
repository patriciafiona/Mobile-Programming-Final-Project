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

public class ProductsDaoImp implements Dao{
	@Override
	public <Product> List<Product> findAll() throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "SELECT products.id as id, product_details.id as product_detail_id, categories.id as category_id, "
				+ "products.name, products.price, products.description, "
				+ "product_details.rating, product_details.style, product_details.color_description, "
				+ "product_details.stock, product_details.photo_01, product_details.photo_02, "
				+ "product_details.photo_03, product_details.photo_04, product_details.photo_05, "
				+ "categories.name as categories_name, colors.color_code FROM products "
				+ "INNER JOIN product_details ON "
				+ "products.id = product_details.product_id "
				+ "INNER JOIN categories ON "
				+ "products.category_id = categories.id "
				+ "INNER JOIN colors ON "
				+ "colors.id = product_details.color_id "
				+ "GROUP BY products.id";
		ResultSet rs = st.executeQuery(sql);
		
		ArrayList<main.entities.Product> products = new ArrayList<main.entities.Product>();
		while (rs.next()) {
			int id = rs.getInt("id");
			int product_detail_id = rs.getInt("product_detail_id");
			int category_id = rs.getInt("category_id");
			String name = rs.getString("name");
			float price  = rs.getFloat("price");
			String description = rs.getString("description");
			double rating  = rs.getDouble("rating");
			String style = rs.getString("style");
			int stocks = rs.getInt("stock");
			String color_description = rs.getString("color_description");
			String photo_01 = rs.getString("photo_01");
			String photo_02 = rs.getString("photo_02");
			String photo_03 = rs.getString("photo_03");
			String photo_04 = rs.getString("photo_04");
			String photo_05 = rs.getString("photo_05");
			String categories_name = rs.getString("categories_name");
			String color_code = rs.getString("color_code");
			
			main.entities.Product product = new main.entities.Product(id, product_detail_id, category_id, name, price, description, rating, style, color_description,
					stocks, photo_01, photo_02, photo_03, photo_04, photo_05, categories_name, color_code);
			products.add(product);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Product>) products;
	}
	
	@Override
	public <Product> List<Product> findById(int id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "SELECT products.id as id, product_details.id as product_detail_id, categories.id as category_id,  "
				+ "products.name, products.price, products.description, "
				+ "product_details.rating, product_details.style, product_details.color_description, "
				+ "product_details.stock, product_details.photo_01, product_details.photo_02, "
				+ "product_details.photo_03, product_details.photo_04, product_details.photo_05, "
				+ "categories.name as categories_name, colors.color_code FROM products "
				+ "INNER JOIN product_details ON "
				+ "products.id = product_details.product_id "
				+ "INNER JOIN categories ON "
				+ "products.category_id = categories.id "
				+ "INNER JOIN colors ON "
				+ "colors.id = product_details.color_id "
				+ "WHERE products.id = '"+ id +"'";
		ResultSet rs = st.executeQuery(sql);
		
		ArrayList<main.entities.Product> products = new ArrayList<main.entities.Product>();
		while (rs.next()) {
			int res_id = rs.getInt("id");
			int product_detail_id = rs.getInt("product_detail_id");
			int category_id = rs.getInt("category_id");
			String name = rs.getString("name");
			float price  = rs.getFloat("price");
			String description = rs.getString("description");
			double rating  = rs.getDouble("rating");
			String style = rs.getString("style");
			int stocks = rs.getInt("stock");
			String color_description = rs.getString("color_description");
			String photo_01 = rs.getString("photo_01");
			String photo_02 = rs.getString("photo_02");
			String photo_03 = rs.getString("photo_03");
			String photo_04 = rs.getString("photo_04");
			String photo_05 = rs.getString("photo_05");
			String categories_name = rs.getString("categories_name");
			String color_code = rs.getString("color_code");
			
			main.entities.Product product = new main.entities.Product(res_id, product_detail_id, category_id, name, price, description, rating, style, color_description,
					stocks, photo_01, photo_02, photo_03, photo_04, photo_05, categories_name, color_code);
			products.add(product);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Product>) products;
	}
	
	@Override
	public <Product> List<Product> findByName(String name) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "SELECT products.id as id, product_details.id as product_detail_id, categories.id as category_id, "
				+ "products.name, products.price, products.description, "
				+ "product_details.rating, product_details.style, product_details.color_description, "
				+ "product_details.stock, product_details.photo_01, product_details.photo_02, "
				+ "product_details.photo_03, product_details.photo_04, product_details.photo_05, "
				+ "categories.name as categories_name, colors.color_code FROM products "
				+ "INNER JOIN product_details ON "
				+ "products.id = product_details.product_id "
				+ "INNER JOIN categories ON "
				+ "products.category_id = categories.id "
				+ "INNER JOIN colors ON "
				+ "colors.id = product_details.color_id "
				+ "WHERE products.name LIKE '%"+ name +"%'";
		ResultSet rs = st.executeQuery(sql);
		
		ArrayList<main.entities.Product> products = new ArrayList<main.entities.Product>();
		while (rs.next()) {
			int id = rs.getInt("id");
			int product_detail_id = rs.getInt("product_detail_id");
			int category_id = rs.getInt("category_id");
			String res_name = rs.getString("name");
			float price  = rs.getFloat("price");
			String description = rs.getString("description");
			double rating  = rs.getDouble("rating");
			String style = rs.getString("style");
			int stocks = rs.getInt("stock");
			String color_description = rs.getString("color_description");
			String photo_01 = rs.getString("photo_01");
			String photo_02 = rs.getString("photo_02");
			String photo_03 = rs.getString("photo_03");
			String photo_04 = rs.getString("photo_04");
			String photo_05 = rs.getString("photo_05");
			String categories_name = rs.getString("categories_name");
			String color_code = rs.getString("color_code");
			
			main.entities.Product product = new main.entities.Product(id, product_detail_id, category_id, res_name, price, description, rating, style, color_description,
					stocks, photo_01, photo_02, photo_03, photo_04, photo_05, categories_name, color_code);
			products.add(product);
		}
		JDBCUtil.close(conn, st, rs);
		return (List<Product>) products;
	}
	
	public <Product> Product findByDetailID(int id) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		
		Statement st = conn.createStatement();
		String sql = "SELECT products.id as id, product_details.id as product_detail_id, categories.id as category_id, "
				+ "products.name, products.price, products.description, "
				+ "product_details.rating, product_details.style, product_details.color_description, "
				+ "product_details.stock, product_details.photo_01, product_details.photo_02, "
				+ "product_details.photo_03, product_details.photo_04, product_details.photo_05, "
				+ "categories.name as categories_name, colors.color_code FROM products "
				+ "INNER JOIN product_details ON "
				+ "products.id = product_details.product_id "
				+ "INNER JOIN categories ON "
				+ "products.category_id = categories.id "
				+ "INNER JOIN colors ON "
				+ "colors.id = product_details.color_id "
				+ "WHERE product_details.id = '"+ id +"'";
		ResultSet rs = st.executeQuery(sql);
		
		main.entities.Product product = null;
		if (rs.next()) {
			int res_id = rs.getInt("id");
			int product_detail_id = rs.getInt("product_detail_id");
			int category_id = rs.getInt("category_id");
			String name = rs.getString("name");
			float price  = rs.getFloat("price");
			String description = rs.getString("description");
			double rating  = rs.getDouble("rating");
			String style = rs.getString("style");
			int stocks = rs.getInt("stock");
			String color_description = rs.getString("color_description");
			String photo_01 = rs.getString("photo_01");
			String photo_02 = rs.getString("photo_02");
			String photo_03 = rs.getString("photo_03");
			String photo_04 = rs.getString("photo_04");
			String photo_05 = rs.getString("photo_05");
			String categories_name = rs.getString("categories_name");
			String color_code = rs.getString("color_code");
			
			product = new main.entities.Product(res_id, product_detail_id, category_id, name, price, description, rating, style, color_description,
					stocks, photo_01, photo_02, photo_03, photo_04, photo_05, categories_name, color_code);
		}
		JDBCUtil.close(conn, st, rs);
		return (Product) product;
	}
}
