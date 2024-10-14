package com.yukito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.yukito.model.Trolley;

/**
 * 购物车Dao类
 * @author wjw20
 *
 */
public class TrolleyDao {
	/**
	 * 获取购物车信息
	 * @param conn
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection conn) throws Exception {
		String sql = "select t_book.isbn, t_book.book_name, t_book.author, t_book.publisher, t_book.price, t_trolley.num from t_book, t_trolley where t_book.isbn = t_trolley.isbn";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
	/**
	 * 加入购物车
	 * @param conn
	 * @param trolley
	 * @return
	 * @throws Exception
	 */
	public int addTrolley(Connection conn, Trolley trolley) throws Exception {
		String sql1 = "insert into t_trolley values (?, ?)";
		PreparedStatement pstmt1 = conn.prepareStatement(sql1);
		pstmt1.setString(1, trolley.getIsbn());
		pstmt1.setInt(2, trolley.getNum());
		String sql2 = "update t_book set inventory = inventory - ? where isbn = ?";
		PreparedStatement pstmt2 = conn.prepareStatement(sql2);
		pstmt2.setInt(1, trolley.getNum());
		pstmt2.setString(2, trolley.getIsbn());
		return pstmt1.executeUpdate() + pstmt2.executeUpdate();
	}
	
	/**
	 * 移出购物车
	 * @param conn
	 * @param trolley
	 * @return
	 * @throws Exception
	 */
	public int remove(Connection conn, Trolley trolley) throws Exception {
		String sql1 = "delete from t_trolley where isbn = ?";
		PreparedStatement pstmt1 = conn.prepareStatement(sql1);
		pstmt1.setString(1, trolley.getIsbn());
		String sql2 = "update t_book set inventory = inventory + ? where isbn = ?";
		PreparedStatement pstmt2 = conn.prepareStatement(sql2);
		pstmt2.setInt(1, trolley.getNum());
		pstmt2.setString(2, trolley.getIsbn());
		String sql3 = "update t_book set sales_volume = sales_volume - ? where isbn = ?";
		PreparedStatement pstmt3 = conn.prepareStatement(sql3);
		pstmt3.setInt(1, trolley.getNum());
		pstmt3.setString(2, trolley.getIsbn());
		return pstmt1.executeUpdate() + pstmt2.executeUpdate() + pstmt3.executeUpdate();
	}
	
	/**
	 * 获取总金额
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public ResultSet sumPrice(Connection conn) throws Exception {
		String sql = "select sum(price * num) from t_book, t_trolley where t_book.isbn = t_trolley.isbn";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		return pstmt.executeQuery();
	}
	
	/**
	 * 清空购物车
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int refresh (Connection conn) throws Exception {
		String sql = "truncate table t_trolley";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
}
