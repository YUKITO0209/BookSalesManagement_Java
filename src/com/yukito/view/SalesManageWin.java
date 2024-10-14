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
import com.yukito.model.Book;
import com.yukito.util.DbUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;

public class SalesManageWin extends JFrame {

	private JPanel contentPane;
	public static JTable salesTable;
	private JTextField isbnTxt;
	private JTextField bookNameTxt;
	
	private DbUtil dbUtil = new DbUtil();
	private BookDao bookDao = new BookDao();
	
	public static int rowNum = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesManageWin frame = new SalesManageWin();
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
	public SalesManageWin() {
		setTitle("销售管理");
		setResizable(false);
		setBounds(100, 100, 800, 450);
		setLocationRelativeTo(null); // 窗体居中
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("书籍销量一览：");
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 25));
		
		JLabel lblNewLabel_1 = new JLabel("ISBN号：");
		lblNewLabel_1.setFont(new Font("楷体", Font.BOLD, 13));
		
		isbnTxt = new JTextField();
		isbnTxt.setFont(new Font("宋体", Font.PLAIN, 12));
		isbnTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("书名：");
		lblNewLabel_2.setFont(new Font("楷体", Font.BOLD, 13));
		
		bookNameTxt = new JTextField();
		bookNameTxt.setFont(new Font("宋体", Font.PLAIN, 12));
		bookNameTxt.setColumns(10);
		
		JButton searchButton = new JButton("查找书籍");
		searchButton.setBackground(new Color(255, 255, 255));
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		searchButton.setFont(new Font("楷体", Font.BOLD, 13));
		
		JButton editSalesButton = new JButton("编辑销量");
		editSalesButton.setBackground(new Color(255, 255, 255));
		editSalesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editSalesActionPerformed(e);
			}
		});
		editSalesButton.setFont(new Font("楷体", Font.BOLD, 13));
		
		JButton orderButton = new JButton("订单查询");
		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderCheckButton(e);
			}
		});
		orderButton.setBackground(new Color(255, 255, 255));
		orderButton.setFont(new Font("楷体", Font.BOLD, 13));
		
		JButton backButton = new JButton("返回");
		backButton.setBackground(new Color(255, 255, 255));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backActionPerformed(e);
			}
		});
		backButton.setFont(new Font("楷体", Font.BOLD, 13));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel)
					.addGap(588))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(isbnTxt, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(editSalesButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(orderButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(isbnTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(editSalesButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(orderButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		
		salesTable = new JTable();
		salesTable.setBackground(new Color(255, 255, 255));
		salesTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				salesTableMousePressed(e);
			}
		});
		salesTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5E8F\u53F7", "ISBN\u53F7", "\u4E66\u540D", "\u4F5C\u8005", "\u51FA\u7248\u793E", "\u4EF7\u683C", "\u9500\u91CF", "\u9500\u552E\u989D"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		salesTable.setFont(new Font("宋体", Font.PLAIN, 12));
		scrollPane.setViewportView(salesTable);
		contentPane.setLayout(gl_contentPane);
		
		this.fillTable(new Book());
	}

	/**
	 * 打开订单查询界面
	 * @param e
	 */
	private void orderCheckButton(ActionEvent e) {
		RecordCheckWin recordCheckWin = new RecordCheckWin();
		recordCheckWin.setVisible(true);
	}

	/**
	 * 打开编辑销量界面
	 * @param e
	 */
	private void editSalesActionPerformed(ActionEvent e) {
		if (SalesManageWin.rowNum == -1) { // 未选中任何行
			JOptionPane.showMessageDialog(null, "请先选择要修改的项目！");
			return;
		}
		SalesEditWin salesEditWin = new SalesEditWin();
		salesEditWin.setVisible(true);
	}
	
	/**
	 * 选中表格一行
	 * @param e
	 */
	private void salesTableMousePressed(MouseEvent e) {
		int row = salesTable.getSelectedRow();
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
		DefaultTableModel dtm = (DefaultTableModel) salesTable.getModel();
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
