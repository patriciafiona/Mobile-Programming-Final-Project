package main.service.implementation;

import java.sql.SQLException;
import java.util.List;

import main.dao.implementation.TransactionDaoImp;
import main.service.Service;

public class TransactionServiceImpl implements Service{

	TransactionDaoImp dao = new TransactionDaoImp();
	
	@Override
	public <Transaction> List<Transaction> findAll() throws SQLException {
		return dao.findAll();
	}

	@Override
	public <Transaction> List<Transaction> findById(int id) throws SQLException {
		return null;
	}

	@Override
	public <Transaction> List<Transaction> findByName(String name) throws SQLException {
		return null;
	}
	
	public <Transaction> List<Transaction> findByUserId(int user_id) throws SQLException {
		return dao.findByUserId(user_id);
	}
	
	public <Transaction> List<Transaction> findByTransactionId(int transaction_id) throws SQLException {
		return dao.findByTransactionId(transaction_id);
	}
	
	public <Transaction> List<Transaction> findRunningTransactionByUserId(int user_id) throws SQLException {
		return dao.findRunningTransactionByUserId(user_id);
	}
	
	public int delete(String transaction_id) throws SQLException {
		return dao.delete(transaction_id);
	}
	
	public <Transaction> int insert(Transaction data) throws SQLException {
		return dao.insert(data);
	}
	
	public <Transaction> List<Transaction> findUserTransactionByUserIdAndStatus(int user_id, int status) throws SQLException {
		return dao.findUserTransactionByUserIdAndStatus(user_id, status);
	}

}
