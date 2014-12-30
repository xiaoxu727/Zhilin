package DataBase;

import java.sql.*;

public class DataAccessorFactory {

	private Connection conn = null;
	private java.sql.Statement stmt = null;
	// java.sql.CallableStatement stmt = null;
//	private ResultSet rs = null;
	private String driver = "org.postgresql.Driver";
	private String url = "jdbc:postgresql://localhost:5432/postgis";
	private String user = "postgres";
	private String passwd = "123";
	
	/**
	 * 连接数据库
	 * 
	 * @return
	 */
	public Connection getConn() {
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("database connected successfull");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭数据库
	 */
	public void closeConn() {
		try {
			if (!this.conn.isClosed()) {
				this.conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取statement对象
	 * 
	 * @return
	 */
	public java.sql.Statement getStatement() {

		try {
			if (this.stmt.isClosed()) {
				if (this.conn.isClosed()) {
					this.getConn();
				}
				if (conn != null) {
					stmt = conn.createStatement();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}

	/**
	 * 关闭statement 对象
	 */
	public void closeStatement() {
		try {
			if (!this.stmt.isClosed()) {
				this.stmt.close();
				this.closeConn();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
