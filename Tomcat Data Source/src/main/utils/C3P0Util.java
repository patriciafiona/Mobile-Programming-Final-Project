package main.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Util {
	static ComboPooledDataSource cpds=null;
	static {
		cpds = new ComboPooledDataSource();
	}
	public static Connection getConnection() throws SQLException {		
		Connection conn = cpds.getConnection();
		return conn;
	}

	public static DataSource getDataSource() {
		return cpds;
	}
}
