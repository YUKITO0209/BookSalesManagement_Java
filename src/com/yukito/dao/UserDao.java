package com.yukito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.yukito.model.User;

/**
 * 用户Dao类
 * @author wjw20
 *
 */
public class UserDao {
	/**
	 * 登录验证
	 * @param conn
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(Connection conn, User user) throws Exception {
		User resultUser = null;
		
		String sql = "select * from t_user where user_name = ? and pwd = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		
		ResultSet rs = pstmt.executeQuery(); // executeQuery()返回SQL语句执行后输出的结果集
		if (rs.next()) {
			resultUser = new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("user_name"));
			resultUser.setPhoneNumber(rs.getString("phone_number"));
			resultUser.setPassword(rs.getString("pwd"));
			resultUser.setAuthority(rs.getInt("authority"));
		}	
		return resultUser;
	}
	
	/**
	 * 注册验证
	 * @param conn
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int register(Connection conn, User user) throws Exception {
		int flag = 0;
		
		String sql = "insert into t_user (user_name, phone_number, pwd, authority) values (?, ?, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPhoneNumber());
		pstmt.setString(3, user.getPassword());
		pstmt.setInt(4, user.getAuthority());
		
		int rs = pstmt.executeUpdate(); // executeUpdate()返回数据库表中受影响的行数（int类型）
		if (rs > 0) { // 大于0条记录受影响，即SQL语句执行成功
			flag = 1;
		}
		return flag;
	}
}
