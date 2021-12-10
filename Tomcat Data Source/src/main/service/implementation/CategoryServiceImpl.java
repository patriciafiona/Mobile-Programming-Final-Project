package main.service.implementation;

import java.sql.SQLException;
import java.util.List;

import main.dao.implementation.CategoryDaoImp;
import main.service.Service;

public class CategoryServiceImpl implements Service{

	CategoryDaoImp dao = new CategoryDaoImp();
	
	@Override
	public <T> List<T> findAll() throws SQLException {
		return dao.findAll();
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
