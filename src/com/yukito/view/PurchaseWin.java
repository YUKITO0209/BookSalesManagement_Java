package com.yukito.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import com.yukito.dao.BookDao;
import com.yukito.dao.TrolleyDao;
import com.yukito.model.Book;
import com.yukito.model.Trolley;
import com.yukito.util.DbUtil;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PurchaseWin extends JFrame {

	private JPanel contentPane;
	private static JTable bookInfoTable;
	private JTextField isbnTxt;
	private JTextField bookNameTxt;
	private JTextField numTxt;
	
	private DbUtil dbUtil = new DbUtil();
	private TrolleyDao trolleyDao = new TrolleyDao();
	private BookDao bookDao = new BookDao();
	
	public static int rowNum = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PurchaseWin frame = new PurchaseWin();
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
	public PurchaseWin() {
		setTitle("书籍购买");
		setResizable(false);
		setBounds(100, 100, 800, 450);
		setLocationRelativeTo(null); // 窗体居中
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("ISBN号：");
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 16));
		
		JLabel lblNewLabel_1 = new JLabel("书  名：");
		lblNewLabel_1.setFont(new Font("楷体", Font.BOLD, 16));
		
		isbnTxt = new JTextField();
		isbnTxt.setColumns(10);
		
		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);
		
		JButton searchButton = new JButton("查找书籍");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		searchButton.setBackground(new Color(255, 255, 255));
		searchButton.setFont(new Font("楷体", Font.BOLD, 16));
		
		JLabel lblNewLabel_2 = new JLabel("购买数量：");
		lblNewLabel_2.setFont(new Font("楷体", Font.BOLD, 16));
		
		numTxt = new JTextField();
		numTxt.setColumns(10);
		
		JButton addTrolleyButton = new JButton("加入购物车");
		addTrolleyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTrolleyActionPerformed(e);
			}
		});
		addTrolleyButton.setBackground(new Color(255, 255, 255));
		addTrolleyButton.setFont(new Font("楷体", Font.BOLD, 16));
		
		JButton backButton = new JButton("返回");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backActionPerformed(e);
			}
		});
		backButton.setBackground(new Color(255, 255, 255));
		backButton.setFont(new Font("楷体", Font.BOLD, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(isbnTxt, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel_1)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(bookNameTxt, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel_2)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(numTxt, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)))
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(62)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
									.addComponent(addTrolleyButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
								.addGap(62)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(62)
							.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(isbnTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(numTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addComponent(addTrolleyButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		
		bookInfoTable = new JTable();
		bookInfoTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bookInfoTableMousePressed(e);
			}
		});
		bookInfoTable.setBackground(new Color(255, 255, 255));
		bookInfoTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5E8F\u53F7", "ISBN\u53F7", "\u4E66\u540D", "\u4F5C\u8005", "\u51FA\u7248\u793E", "\u4EF7\u683C", "\u5E93\u5B58"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookInfoTable.setFont(new Font("宋体", Font.PLAIN, 12));
		scrollPane.setViewportView(bookInfoTable);
		contentPane.setLayout(gl_contentPane);
		
		this.fillTable(new Book());
	}
	
	/**
	 * 加入购物车
	 * @param e
	 */
	private void addTrolleyActionPerformed(ActionEvent e) {
		if (rowNum == -1) { // 未选中任何行
			JOptionPane.showMessageDialog(null, "请先选择想要购买的书籍！");
			return;
		}
		
		String isbn = (String)PurchaseWin.bookInfoTable.getValueAt(PurchaseWin.rowNum, 1);
		int inventory = (int)PurchaseWin.bookInfoTable.getValueAt(PurchaseWin.rowNum, 6);
		String num = this.numTxt.getText();
		
		if (num == null || "".equals(num.trim())) {
			JOptionPane.showMessageDialog(null, "请输入购买书籍的数量！");
			return;
		}
		
		if (Integer.parseInt(num) > inventory) {
			JOptionPane.showMessageDialog(null, "该书库存不足！");
			return;
		}
		
		if (Integer.parseInt(num) <= 0) {
			JOptionPane.showMessageDialog(null, "请输入大于0的数！");
			return;
		}
		
		Trolley trolley = new Trolley(isbn, Integer.parseInt(num));
		
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			int flag = trolleyDao.addTrolley(conn, trolley);
			if (flag == 2) { // 不止一条记录受影响
				JOptionPane.showMessageDialog(null, "加入购物车成功！");
				rowNum = -1;
				this.fillTable(new Book());
			} else {
				JOptionPane.showMessageDialog(null, "加入购物车失败！");
			}
		} catch (Exception e1){
			JOptionPane.showMessageDialog(null, "加入购物车失败！");
		} finally {
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 选中表格一行
	 * @param e
	 */
	private void bookInfoTableMousePressed(MouseEvent e) {
		int row = bookInfoTable.getSelectedRow();
		rowNum = row;
	}

	/**
	 * 查找书籍
	 * @param e
	 */
	private void searchActionPerformed(ActionEvent e) {
		String isbn = this.isbnTxt.getText();
		String bookName = this.bookNameTxt.getText();
		
		Book book = new Book(isbn, bookName);
		this.fillTable(book);
	}

	/**
	 * 返回上一级界面
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
		DefaultTableModel dtm = (DefaultTableModel) bookInfoTable.getModel();
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
