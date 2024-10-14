package com.yukito.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.yukito.dao.BookDao;
import com.yukito.model.Book;
import com.yukito.util.DbUtil;
import com.yukito.util.StringUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class SalesEditWin extends JFrame {

	private JPanel contentPane;
	private JTextField salesVolumeTxt;
	
	private DbUtil dbUtil = new DbUtil();
	private BookDao bookDao = new BookDao();
	
	public static int idNum = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesEditWin frame = new SalesEditWin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SalesEditWin() {
		setTitle("编辑销量");
		setResizable(false);
		setBounds(100, 100, 290, 210);
		setLocationRelativeTo(null); // 窗体居中
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("更新销量：");
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 17));
		
		salesVolumeTxt = new JTextField();
		salesVolumeTxt.setFont(new Font("宋体", Font.PLAIN, 12));
		salesVolumeTxt.setColumns(10);
		
		JButton EditButton = new JButton("确定");
		EditButton.setBackground(new Color(255, 255, 255));
		EditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditActionPerformed(e);
			}
		});
		EditButton.setFont(new Font("楷体", Font.BOLD, 16));
		
		JButton backButton = new JButton("返回");
		backButton.setBackground(new Color(255, 255, 255));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backActionPerformed(e);
			}
		});
		backButton.setFont(new Font("楷体", Font.BOLD, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(salesVolumeTxt, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(35, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(15)
							.addComponent(EditButton)
							.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
							.addComponent(backButton)
							.addGap(43))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(salesVolumeTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(EditButton)
						.addComponent(backButton))
					.addGap(31))
		);
		contentPane.setLayout(gl_contentPane);
		
		showBookInfo();
	}
	
	/**
	 * 编辑销量
	 * @param e
	 */
	private void EditActionPerformed(ActionEvent e) {
		idNum = (int)SalesManageWin.salesTable.getValueAt(SalesManageWin.rowNum, 0);
		String salesVolume = this.salesVolumeTxt.getText();
		
		if (StringUtil.isEmpty(salesVolume)) {
			JOptionPane.showMessageDialog(null, "销量不能为空，请重新输入！");
			return;
		}
		
		try {
			if (Integer.parseInt(salesVolume) < 0) {
				JOptionPane.showMessageDialog(null, "销量不能为负数，请重新输入！");
				return;
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "编辑失败！"); // 防止输入为小数或其他
		}
		
		Book book = new Book(idNum, Integer.parseInt(salesVolume));
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			int flag = bookDao.editSales(conn, book);
			if (flag == 1) {
				JOptionPane.showMessageDialog(null, "编辑成功！");
				this.fillTable(new Book());
			} else {
				JOptionPane.showMessageDialog(null, "编辑失败！");
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "编辑失败！");
		} finally {
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 将表中选中行的信息加入输入框中以待编辑
	 */
	private void showBookInfo() {
		idNum = (int)SalesManageWin.salesTable.getValueAt(SalesManageWin.rowNum, 0);
		Object o_salesVolume = SalesManageWin.salesTable.getValueAt(SalesManageWin.rowNum, 6);
		salesVolumeTxt.setText(o_salesVolume.toString());
	}

	/**
	 * 关闭窗口
	 * @param e
	 */
	private void backActionPerformed(ActionEvent e) {
		this.dispose();
	}
	
	/**
	 * 初始化表格
	 * @param book
	 */
	private void fillTable(Book book) {
		DefaultTableModel dtm = (DefaultTableModel) SalesManageWin.salesTable.getModel();
		dtm.setRowCount(0); // 清空表格
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			ResultSet rs = bookDao.list(conn, book);
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("id"));
				v.add(rs.getString("isbn"));
				v.add(rs.getString("book_name"));
				v.add(rs.getString("author"));
				v.add(rs.getString("publisher"));
				v.add(rs.getBigDecimal("price"));
				v.add(rs.getInt("sales_volume"));
				v.add(rs.getBigDecimal("sales"));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
