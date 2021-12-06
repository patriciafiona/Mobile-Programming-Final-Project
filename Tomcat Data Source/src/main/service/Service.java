package main.service;

import java.sql.SQLException;
import java.util.List;

public interface Service {
	public <T> List<T> findAll() throws SQLException ;
	public <T> T findById(int id) throws SQLException ;
	public <T> List<T> findByName(String name) throws SQLException ;
}
