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

import com.yukito.dao.RecordDao;
import com.yukito.model.Record;
import com.yukito.util.DbUtil;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class RecordCheckWin extends JFrame {

	private JPanel contentPane;
	private JTable recordTable;
	private JTextField orderNumTxt;
	private JTextField payerTxt;
	
	private DbUtil dbUtil = new DbUtil();
	private RecordDao recordDao = new RecordDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecordCheckWin frame = new RecordCheckWin();
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
	public RecordCheckWin() {
		setTitle("订单查询");
		setResizable(false);
		setBounds(100, 100, 400, 400);
		setLocationRelativeTo(null); // 窗体居中
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("订单号：");
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 16));
		
		JLabel lblNewLabel_1 = new JLabel("交易人：");
		lblNewLabel_1.setFont(new Font("楷体", Font.BOLD, 16));
		
		orderNumTxt = new JTextField();
		orderNumTxt.setColumns(10);
		
		payerTxt = new JTextField();
		payerTxt.setColumns(10);
		
		JButton checkButton = new JButton("查询");
		checkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkActionPerformed(e);
			}
		});
		checkButton.setBackground(new Color(255, 255, 255));
		checkButton.setFont(new Font("楷体", Font.BOLD, 16));
		
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
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(payerTxt))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(orderNumTxt, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(backButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(checkButton, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
					.addGap(26))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(orderNumTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkButton))
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(payerTxt, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(backButton))
					.addGap(25))
		);
		
		recordTable = new JTable();
		recordTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BA2\u5355\u53F7", "\u4EA4\u6613\u4EBA", "\u4EA4\u6613\u989D", "\u4EA4\u6613\u65F6\u95F4"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		recordTable.setFont(new Font("宋体", Font.PLAIN, 12));
		scrollPane.setViewportView(recordTable);
		contentPane.setLayout(gl_contentPane);
		
		this.fillTable(new Record());
	}
	
	/**
	 * 查询订单记录
	 * @param e
	 */
	private void checkActionPerformed(ActionEvent e) {
		String orderNum = this.orderNumTxt.getText();
		String payer = this.payerTxt.getText();
		
		Record record = new Record(orderNum, payer);
		this.fillTable(record);
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
	private void fillTable(Record record) {
		DefaultTableModel dtm = (DefaultTableModel) recordTable.getModel();
		dtm.setRowCount(0); // 清空表格
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			ResultSet rs = recordDao.check(conn, record);
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("order_num"));
				v.add(rs.getString("payer"));
				v.add(rs.getBigDecimal("payment_amount"));
				v.add(rs.getString("payment_time"));
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
