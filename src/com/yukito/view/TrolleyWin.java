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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import com.yukito.dao.BookDao;
import com.yukito.dao.TrolleyDao;
import com.yukito.model.Trolley;
import com.yukito.util.DbUtil;
import java.awt.event.MouseAdapter;

public class TrolleyWin extends JFrame {

	private JPanel contentPane;
	private static JTable trolleyTable;

	private static DbUtil dbUtil = new DbUtil();
	private BookDao bookDao = new BookDao();
	private static TrolleyDao trolleyDao = new TrolleyDao();
	static JLabel amountLabel = new JLabel("0.00");
	
	public static String sp = null; // 总金额
	public static int rowNum = -1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrolleyWin frame = new TrolleyWin();
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
	public TrolleyWin() {
		setTitle("购物车");
		setResizable(false);
		setBounds(100, 100, 800, 450);
		setLocationRelativeTo(null); // 窗体居中
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel titleLabel = new JLabel("总金额：");
		titleLabel.setFont(new Font("楷体", Font.BOLD, 20));
		
		amountLabel.setFont(new Font("楷体", Font.BOLD, 20));
		
		JLabel unitLabel = new JLabel("元");
		unitLabel.setFont(new Font("楷体", Font.BOLD, 20));
		
		JButton settleButton = new JButton("结  算");
		settleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settleActionPerformed(e);
			}
		});
		settleButton.setBackground(new Color(255, 255, 255));
		settleButton.setFont(new Font("楷体", Font.BOLD, 16));
		
		JButton removeButton = new JButton("移出购物车");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeActionPerformed(e);
			}
		});
		removeButton.setBackground(new Color(255, 255, 255));
		removeButton.setFont(new Font("楷体", Font.BOLD, 16));
		
		JButton backButton = new JButton("返  回");
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
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 602, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(titleLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(55)
							.addComponent(amountLabel)
							.addGap(18)
							.addComponent(unitLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(33)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(removeButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(settleButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(backButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(41)
					.addComponent(titleLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(amountLabel)
						.addComponent(unitLabel))
					.addGap(92)
					.addComponent(settleButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addComponent(removeButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(48, Short.MAX_VALUE))
		);
		
		trolleyTable = new JTable();
		trolleyTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				trolleyTableMousePressed(e);
			}
		});
		trolleyTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ISBN\u53F7", "\u4E66\u540D", "\u4F5C\u8005", "\u51FA\u7248\u793E", "\u4EF7\u683C", "\u8D2D\u4E70\u6570\u91CF"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		trolleyTable.setFont(new Font("宋体", Font.PLAIN, 12));
		scrollPane.setViewportView(trolleyTable);
		contentPane.setLayout(gl_contentPane);
		
		this.fillTable();
	}

	/**
	 * 打开结算界面
	 * @param e
	 */
	private void settleActionPerformed(ActionEvent e) {
		if (amountLabel.getText().equals("0.00")) {
			JOptionPane.showMessageDialog(null, "当前购物车为空！");
			return;
		}
		SettlementWin settlementWin = new SettlementWin();
		settlementWin.setVisible(true);
	}

	/**
	 * 移出购物车
	 * @param e
	 */
	private void removeActionPerformed(ActionEvent e) {
		if (rowNum == -1) { // 未选中任何行
			JOptionPane.showMessageDialog(null, "请先选择准备移出购物车的书籍！");
			return;
		}
		
		String isbn = (String)TrolleyWin.trolleyTable.getValueAt(TrolleyWin.rowNum, 0);
		int num = (int)TrolleyWin.trolleyTable.getValueAt(TrolleyWin.rowNum, 5);
		
		Trolley trolley = new Trolley(isbn, num);
		
		if (JOptionPane.showConfirmDialog(null, "是否将本书移出购物车？", "提示信息", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
			Connection conn = null;
			try {
				conn = dbUtil.getConn();
				int flag = trolleyDao.remove(conn, trolley);
				if (flag == 3) {
					JOptionPane.showMessageDialog(null, "移除成功！");
					rowNum = -1;
					this.fillTable();
				} else {
					JOptionPane.showMessageDialog(null, "移除失败！");
				}
			} catch (Exception e1){
				JOptionPane.showMessageDialog(null, "移除失败！");
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
	 * 选中表格一行
	 * @param e
	 */
	private void trolleyTableMousePressed(MouseEvent e) {
		int row = trolleyTable.getSelectedRow();
		rowNum = row;
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
	public static void fillTable() {
		DefaultTableModel dtm = (DefaultTableModel) trolleyTable.getModel();
		dtm.setRowCount(0); // 清空表格
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			ResultSet rs1 = trolleyDao.list(conn);
			ResultSet rs2 = trolleyDao.sumPrice(conn);
			while (rs1.next()) {
				Vector v = new Vector();
				v.add(rs1.getString("isbn"));
				v.add(rs1.getString("book_name"));
				v.add(rs1.getString("author"));
				v.add(rs1.getString("publisher"));
				v.add(rs1.getBigDecimal("price"));
				v.add(rs1.getInt("num"));
				dtm.addRow(v);
			}
			while (rs2.next()) {
				amountLabel.setText(rs2.getBigDecimal("sum(price * num)").toString());
				sp = amountLabel.getText();
			}
		} catch (Exception e) {
			// Do not do anything here, or something terrible would happen. 
		} finally {
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
