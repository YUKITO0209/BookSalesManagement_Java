package com.yukito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.yukito.model.Record;
import com.yukito.util.StringUtil;

/**
 * 订单Dao类
 * @author wjw20
 *
 */
public class RecordDao {
	/**
	 * 增加订单记录
	 * @param conn
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public int add(Connection conn, Record record) throws Exception {
		String sql = "insert into t_record values (?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, record.getOrderNum());
		pstmt.setString(2, record.getPayer());
		pstmt.setBigDecimal(3, record.getPaymentAmount());
		pstmt.setString(4, record.getPaymentTime());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 查询订单记录
	 * @param conn
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public ResultSet check(Connection conn, Record record) throws Exception {
		StringBuffer sb = new StringBuffer("select * from t_record");
		// 如果输入了订单号，后接以下SQL语句
		if (StringUtil.isNotEmpty(record.getOrderNum())) {
			sb.append(" and order_num like '%" + record.getOrderNum() + "%'");
		}
		// 如果输入了交易人，后接以下SQL语句
		if (StringUtil.isNotEmpty(record.getPayer())) {
			sb.append(" and payer like '%" + record.getPayer() + "%'");
		}
		// replaceFirst("and", "where")将最终SQL语句中的第一个and替换为where，由此实现任意输入的查询
		PreparedStatement pstmt = conn.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery(); // 返回查询的结果集
	}
}
