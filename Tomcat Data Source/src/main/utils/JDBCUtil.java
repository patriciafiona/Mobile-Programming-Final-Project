package main.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {	

	private JDBCUtil() {
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = C3P0Util.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("open");
		return conn;
	}

	public static void close(Connection conn, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
				System.out.println("close");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void close(Connection conn, Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
				System.out.println("close");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("close");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
