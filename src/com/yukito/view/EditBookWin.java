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
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class EditBookWin extends JFrame {

	private JPanel contentPane;
	private JTextField isbnTxt;
	private JTextField bookNameTxt;
	private JTextField authorTxt;
	private JTextField publisherTxt;
	private JTextField priceTxt;
	private JTextField inventoryTxt;
	
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
					EditBookWin frame = new EditBookWin();
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
	public EditBookWin() {
		setResizable(false);
		setTitle("修改图书");
		setBounds(100, 100, 275, 400);
		setLocationRelativeTo(null); // 窗体居中
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("ISBN号：");
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 16));
		
		JLabel lblNewLabel_1 = new JLabel("书  名：");
		lblNewLabel_1.setFont(new Font("楷体", Font.BOLD, 16));
		
		JLabel lblNewLabel_2 = new JLabel("作  者：");
		lblNewLabel_2.setFont(new Font("楷体", Font.BOLD, 16));
		
		JLabel lblNewLabel_3 = new JLabel("出版社：");
		lblNewLabel_3.setFont(new Font("楷体", Font.BOLD, 16));
		
		JLabel lblNewLabel_4 = new JLabel("价  格：");
		lblNewLabel_4.setFont(new Font("楷体", Font.BOLD, 16));
		
		JLabel label = new JLabel("库  存：");
		label.setFont(new Font("楷体", Font.BOLD, 16));
		
		isbnTxt = new JTextField();
		isbnTxt.setFont(new Font("宋体", Font.PLAIN, 12));
		isbnTxt.setColumns(10);
		
		bookNameTxt = new JTextField();
		bookNameTxt.setFont(new Font("宋体", Font.PLAIN, 12));
		bookNameTxt.setColumns(10);
		
		authorTxt = new JTextField();
		authorTxt.setFont(new Font("宋体", Font.PLAIN, 12));
		authorTxt.setColumns(10);
		
		publisherTxt = new JTextField();
		publisherTxt.setFont(new Font("宋体", Font.PLAIN, 12));
		publisherTxt.setColumns(10);
		
		priceTxt = new JTextField();
		priceTxt.setFont(new Font("宋体", Font.PLAIN, 12));
		priceTxt.setColumns(10);
		
		inventoryTxt = new JTextField();
		inventoryTxt.setFont(new Font("宋体", Font.PLAIN, 12));
		inventoryTxt.setColumns(10);
		
		JButton editButton = new JButton("修改");
		editButton.setBackground(new Color(255, 255, 255));
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editActionPerformed(e);
			}
		});
		editButton.setFont(new Font("楷体", Font.BOLD, 16));
		
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
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addComponent(editButton)
					.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
					.addComponent(backButton)
					.addGap(36))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(priceTxt))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(publisherTxt))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(isbnTxt, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bookNameTxt))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(authorTxt))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(inventoryTxt)))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(isbnTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(publisherTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(inventoryTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(backButton)
						.addComponent(editButton))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		showBookInfo();
	}
	
	/**
	 * 修改书籍
	 * @param e
	 */
	private void editActionPerformed(ActionEvent e) {
		int id = idNum;
		String isbn = this.isbnTxt.getText();
		String bookName = this.bookNameTxt.getText();
		String author = this.authorTxt.getText();
		String publisher = this.publisherTxt.getText();
		String s_price = this.priceTxt.getText();
		String s_inventory = this.inventoryTxt.getText();
		
		if (StringUtil.isEmpty(isbn)
			|| StringUtil.isEmpty(bookName)
			|| StringUtil.isEmpty(author)
			|| StringUtil.isEmpty(publisher)
			|| StringUtil.isEmpty(s_price)
			|| StringUtil.isEmpty(s_inventory)) {
			JOptionPane.showMessageDialog(null, "各项不能为空，请重新输入！");
			return;
		}
		
		if (Integer.parseInt(s_inventory) < 0 || s_price.compareTo("0") < 0) {
			JOptionPane.showMessageDialog(null, "库存和价格不能为负数，请重新输入！");
			return;
		}
		
		BigDecimal price = new BigDecimal(s_price);
		
		Book book = new Book(id, isbn, bookName, author, publisher, price, Integer.parseInt(s_inventory));
		
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			int flag = bookDao.update(conn, book);
			if (flag == 1) {
				JOptionPane.showMessageDialog(null, "修改成功！");
				this.fillTable(new Book());
			} else {
				JOptionPane.showMessageDialog(null, "修改失败！");
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "修改失败！");
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
		idNum = (int)InventoryManageWin.bookInfoTable.getValueAt(InventoryManageWin.rowNum, 0);
		isbnTxt.setText((String)InventoryManageWin.bookInfoTable.getValueAt(InventoryManageWin.rowNum, 1));
		bookNameTxt.setText((String)InventoryManageWin.bookInfoTable.getValueAt(InventoryManageWin.rowNum, 2));
		authorTxt.setText((String)InventoryManageWin.bookInfoTable.getValueAt(InventoryManageWin.rowNum, 3));
		publisherTxt.setText((String)InventoryManageWin.bookInfoTable.getValueAt(InventoryManageWin.rowNum, 4));
		Object o_price = InventoryManageWin.bookInfoTable.getValueAt(InventoryManageWin.rowNum, 5);
		priceTxt.setText(o_price.toString());
		Object o_inventory = InventoryManageWin.bookInfoTable.getValueAt(InventoryManageWin.rowNum, 6);
		inventoryTxt.setText(o_inventory.toString());
	}

	/**
	 * 关闭当前窗口
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
		DefaultTableModel dtm = (DefaultTableModel) InventoryManageWin.bookInfoTable.getModel();
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
				v.add(rs.getInt("inventory"));
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
