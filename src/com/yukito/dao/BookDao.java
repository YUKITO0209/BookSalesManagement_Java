package com.yukito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.yukito.model.Book;
import com.yukito.util.StringUtil;

/**
 * 书籍Dao类
 * @author wjw20
 *
 */
public class BookDao {
	/**
	 * 书籍信息查询
	 * @param conn
	 * @param book
	 * @return
	 * @throws Exception	
	 */
	public ResultSet list(Connection conn, Book book) throws Exception {
		StringBuffer sb = new StringBuffer("select * from t_book");
		// 如果输入了ISBN号，后接以下SQL语句
		if (StringUtil.isNotEmpty(book.getIsbn())) {
			sb.append(" and isbn like '%" + book.getIsbn() + "%'");
		}
		// 如果输入了书名，后接以下SQL语句
		if (StringUtil.isNotEmpty(book.getBookName())) {
			sb.append(" and book_name like '%" + book.getBookName() + "%'");
		}
		// 如果输入了作者，后接以下SQL语句
		if (StringUtil.isNotEmpty(book.getAuthor())) {
			sb.append(" and author like '%" + book.getAuthor() + "%'");
		}
		// 如果输入了出版社，后接以下SQL语句
		if (StringUtil.isNotEmpty(book.getPublisher())) {
			sb.append(" and publisher like '%" + book.getPublisher() + "%'");
		}
		// replaceFirst("and", "where")将最终SQL语句中的第一个and替换为where，由此实现任意输入的查询
		PreparedStatement pstmt = conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery(); // 返回查询的结果集
	}
	
	/**
	 * 添加书籍
	 * @param conn
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int add(Connection conn, Book book) throws Exception {
		String sql = "insert into t_book values (null, ?, ?, ?, ?, ?, ?, null, default)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, book.getIsbn());
		pstmt.setString(2, book.getBookName());
		pstmt.setString(3, book.getAuthor());
		pstmt.setString(4, book.getPublisher());
		pstmt.setBigDecimal(5, book.getPrice());
		pstmt.setInt(6, book.getInventory());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 删除书籍
	 * @param conn
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection conn, int id) throws Exception {
		String sql = "delete from t_book where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		return pstmt.executeUpdate();
	}
	
	/**
	 * 修改书籍
	 * @param conn
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int update(Connection conn, Book book) throws Exception {
		String sql = "update t_book set isbn = ?, book_name = ?, author = ?, publisher = ?, price = ?, inventory = ? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, book.getIsbn());
		pstmt.setString(2, book.getBookName());
		pstmt.setString(3, book.getAuthor());
		pstmt.setString(4, book.getPublisher());
		pstmt.setBigDecimal(5, book.getPrice());
		pstmt.setInt(6, book.getInventory());
		pstmt.setInt(7, book.getId());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 编辑销量
	 * @param conn
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int editSales(Connection conn, Book book) throws Exception {
		String sql = "update t_book set sales_volume = ? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, book.getSalesVolume());
		pstmt.setInt(2, book.getId());
		return pstmt.executeUpdate();
	}
}
