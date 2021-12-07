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
}
