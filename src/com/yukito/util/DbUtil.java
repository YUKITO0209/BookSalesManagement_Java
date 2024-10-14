package com.yukito.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库连接
 * @author wjw20
 *
 */
public class DbUtil {
	private String dbUrl = "jdbc:mysql://localhost:3306/books_management_database?autoReconnect=true&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
	private String jdbcName = "com.mysql.cj.jdbc.Driver";
	private String dbUserName = "root";
	private String dbPassword = "1012wjwykd";
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public Connection getConn() throws Exception {
		Class.forName(jdbcName);
		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return conn;
	}
	
	/**
	 * 关闭数据库连接
	 * @param conn
	 * @throws Exception
	 */
	public void closeConn(Connection conn) throws Exception {
		if (conn != null) {
			conn.close();
		}
	}
}