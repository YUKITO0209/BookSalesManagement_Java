package com.yukito.view;

import java.awt.EventQueue;

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
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class InventoryManageWin extends JFrame {

	private JPanel contentPane;
	private JTextField isbnTxt;
	private JTextField bookNameTxt;
	
	private DbUtil dbUtil = new DbUtil();
	private BookDao bookDao = new BookDao();
	public static JTable bookInfoTable;
	
	public static int rowNum = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryManageWin frame = new InventoryManageWin();
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
	public InventoryManageWin() {
		setTitle("库存管理");
		setResizable(false);
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
		
		isbnTxt = new JTextField();
		isbnTxt.setFont(new Font("宋体", Font.PLAIN, 12));
		isbnTxt.setColumns(10);
		
		bookNameTxt = new JTextField();
		bookNameTxt.setFont(new Font("宋体", Font.PLAIN, 12));
		bookNameTxt.setColumns(10);
		
		JButton checkBookButton = new JButton("查找书籍");
		checkBookButton.setBackground(new Color(255, 255, 255));
		checkBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkBookActionPerformed(e);
			}
		});
		checkBookButton.setFont(new Font("楷体", Font.BOLD, 15));
		
		JButton cleanButton = new JButton("清  除");
		cleanButton.setBackground(new Color(255, 255, 255));
		cleanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cleanActionPerformed(e);
			}
		});
		cleanButton.setFont(new Font("楷体", Font.BOLD, 15));
		
		JButton addBookButton = new JButton("添加书籍");
		addBookButton.setBackground(new Color(255, 255, 255));
		addBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBookActionPerformed(e);
			}
		});
		addBookButton.setFont(new Font("楷体", Font.BOLD, 15));
		
		JButton editBookButton = new JButton("修改书籍");
		editBookButton.setBackground(new Color(255, 255, 255));
		editBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editBookActionPerformed(e);
			}
		});
		editBookButton.setFont(new Font("楷体", Font.BOLD, 15));
		
		JButton deleteBookButton = new JButton("删除书籍");
		deleteBookButton.setBackground(new Color(255, 255, 255));
		deleteBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteActionPerformed(e);
			}
		});
		deleteBookButton.setFont(new Font("楷体", Font.BOLD, 15));
		
		JButton backButton = new JButton("返  回");
		backButton.setBackground(new Color(255, 255, 255));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backActionPerformed(e);
			}
		});
		backButton.setFont(new Font("楷体", Font.BOLD, 15));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 527, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(isbnTxt, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bookNameTxt, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(checkBookButton, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
							.addComponent(cleanButton, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(addBookButton, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
							.addComponent(editBookButton, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(deleteBookButton, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
							.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(42)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(isbnTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
							.addGap(36)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
							.addGap(40)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkBookButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(cleanButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
							.addGap(46)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(addBookButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(editBookButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
							.addGap(45)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(deleteBookButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		bookInfoTable = new JTable();
		bookInfoTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bookInfoTableMousePressed(e);
			}
		});
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
	 * 删除书籍
	 * @param e
	 */
	private void deleteActionPerformed(ActionEvent e) {
		if (rowNum == -1) { // 未选中任何行
			JOptionPane.showMessageDialog(null, "请先选择要删除的项目！");
			return;
		}
		int id = (int)InventoryManageWin.bookInfoTable.getValueAt(InventoryManageWin.rowNum, 0);

		if (JOptionPane.showConfirmDialog(null, "是否删除该项？", "提示信息", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
			Connection conn = null;
			try {
				conn = dbUtil.getConn();
				int flag = bookDao.delete(conn, id);
				if (flag == 1) {
					JOptionPane.showMessageDialog(null, "删除成功！");
					rowNum = -1;
					this.fillTable(new Book());
				} else {
					JOptionPane.showMessageDialog(null, "删除失败！");
				}
			} catch (Exception e1){
				JOptionPane.showMessageDialog(null, "删除失败！");
			} finally {
				try {
					dbUtil.closeConn(conn);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 打开修改书籍界面
	 * @param e
	 */
	private void editBookActionPerformed(ActionEvent e) {
		if (InventoryManageWin.rowNum == -1) { // 未选中任何行
			JOptionPane.showMessageDialog(null, "请先选择要修改的项目！");
			return;
		}
		EditBookWin editBookWin = new EditBookWin();
		editBookWin.setVisible(true);
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
	 * 打开添加书籍界面
	 * @param e
	 */
	private void addBookActionPerformed(ActionEvent e) {
		AddBookWin addBookWin = new AddBookWin();
		addBookWin.setVisible(true);
	}

	/**
	 * 查询书籍
	 * @param e
	 */
	private void checkBookActionPerformed(ActionEvent e) {
		String isbn = this.isbnTxt.getText();
		String bookName = this.bookNameTxt.getText();
		
		Book book = new Book(isbn, bookName);
		this.fillTable(book);
	}

	/**
	 * 清除输入框中的内容
	 * @param e
	 */
	private void cleanActionPerformed(ActionEvent e) {
		isbnTxt.setText("");
		bookNameTxt.setText("");
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
