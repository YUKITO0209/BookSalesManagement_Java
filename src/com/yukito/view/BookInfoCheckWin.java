package com.yukito.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.yukito.dao.BookDao;
import com.yukito.model.Book;
import com.yukito.util.DbUtil;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class BookInfoCheckWin extends JFrame {

	private JPanel contentPane;
	private JTable bookInfoTable;
	
	private DbUtil dbUtil = new DbUtil();
	private BookDao bookDao = new BookDao();
	private JTextField isbnTxt;
	private JTextField bookNameTxt;
	private JTextField authorTxt;
	private JTextField publisherTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookInfoCheckWin frame = new BookInfoCheckWin();
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
	public BookInfoCheckWin() {
		setResizable(false);
		setTitle("书籍信息查询");
		setBounds(100, 100, 800, 450);
		setLocationRelativeTo(null); // 窗体居中
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("ISBN号：");
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 17));
		
		JLabel lblNewLabel_1 = new JLabel("书  名：");
		lblNewLabel_1.setFont(new Font("楷体", Font.BOLD, 17));
		
		JLabel lblNewLabel_2 = new JLabel("作  者：");
		lblNewLabel_2.setFont(new Font("楷体", Font.BOLD, 17));
		
		JLabel lblNewLabel_3 = new JLabel("出版社：");
		lblNewLabel_3.setFont(new Font("楷体", Font.BOLD, 17));
		
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
		
		JButton checkButton = new JButton("查询");
		checkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkBookActionPerformed(e);
			}
		});
		checkButton.setBackground(new Color(255, 255, 255));
		checkButton.setFont(new Font("楷体", Font.BOLD, 17));
		
		JButton cleanButton = new JButton("清除");
		cleanButton.setBackground(new Color(255, 255, 255));
		cleanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cleanActionPerformed(e);
			}
		});
		cleanButton.setFont(new Font("楷体", Font.BOLD, 17));
		
		JButton backButton = new JButton("返回");
		backButton.setBackground(new Color(255, 255, 255));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backActionPerformed(e);
			}
		});
		backButton.setFont(new Font("楷体", Font.BOLD, 17));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 551, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bookNameTxt, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(authorTxt, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(publisherTxt, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(isbnTxt, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(cleanButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
							.addGap(61))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(isbnTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(authorTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(publisherTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addComponent(checkButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(cleanButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(35, Short.MAX_VALUE))
		);
		
		bookInfoTable = new JTable();
		bookInfoTable.setForeground(new Color(0, 0, 0));
		bookInfoTable.setBackground(new Color(255, 255, 255));
		bookInfoTable.setFont(new Font("宋体", Font.PLAIN, 12));
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
		scrollPane.setViewportView(bookInfoTable);
		contentPane.setLayout(gl_contentPane);
		
		this.fillTable(new Book());
	}
	
	/**
	 * 查询书籍
	 * @param e
	 */
	private void checkBookActionPerformed(ActionEvent e) {
		String isbn = this.isbnTxt.getText();
		String bookName = this.bookNameTxt.getText();
		String author = this.authorTxt.getText();
		String publisher = this.publisherTxt.getText();
		
		Book book = new Book(isbn, bookName, author, publisher);
		this.fillTable(book);
	}

	/**
	 * 清除输入框中的内容
	 * @param e
	 */
	private void cleanActionPerformed(ActionEvent e) {
		isbnTxt.setText("");
		bookNameTxt.setText("");
		authorTxt.setText("");
		publisherTxt.setText("");
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
