package main.service.implementation;

import java.sql.SQLException;
import java.util.List;

import main.service.Service;
import main.dao.implementation.*;

public class ProductsServiceImpl implements Service{
	ProductsDaoImp dao = new ProductsDaoImp();
	
	public <Product> List<Product> findAll() throws SQLException {
		return dao.findAll();
	}
	
	@Override
	public <Product> List<Product> findById(int id) throws SQLException {
		return dao.findById(id);
	}
	
	@Override
	public <Product> List<Product> findByName(String name) throws SQLException {
		return dao.findByName(name);
	}
	
	public <Product> Product findByDetailID(int id) throws SQLException {
		return dao.findByDetailID(id);
	}
	
	public <Product> List<Product> findByCategory(int category_id) throws SQLException{
		return dao.findByCategory(category_id);
	}
	
	public <Product> List<Product> findByCategoryAndType(int category_id, String type_name) throws SQLException{
		return dao.findByCategoryAndType(category_id, type_name);
	}
	
	
	//Using limit
	public <Product> List<Product> findAllLimit(int limit) throws SQLException {
		return dao.findAllLimit(limit);
	}
	
	public <Product> List<Product> findByCategoryLimit(int category_id, int limit) throws SQLException{
		return dao.findByCategoryLimit(category_id, limit);
	}
	
	public <Product> List<Product> findByCategoryAndTypeLimit(int category_id, String type_name, int limit) throws SQLException{
		return dao.findByCategoryAndTypeLimit(category_id, type_name, limit);
	}
}
