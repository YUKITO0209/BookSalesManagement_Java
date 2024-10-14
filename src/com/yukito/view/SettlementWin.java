package com.yukito.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

import com.yukito.dao.RecordDao;
import com.yukito.dao.TrolleyDao;
import com.yukito.model.Record;
import com.yukito.util.DbUtil;

public class SettlementWin extends JFrame {

	private JPanel contentPane;
	
	private DbUtil dbUtil = new DbUtil();
	private RecordDao recordDao = new RecordDao();
	private TrolleyDao trolleyDao = new TrolleyDao();
	
	public static boolean paid = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettlementWin frame = new SettlementWin();
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
	public SettlementWin() {
		setTitle("结算界面");
		setResizable(false);
		setBounds(100, 100, 400, 450);
		setLocationRelativeTo(null); // 窗体居中
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("应付金额：");
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 17));
		
		JLabel amountLabel = new JLabel("0.00");
		amountLabel.setText(TrolleyWin.sp);
		amountLabel.setFont(new Font("楷体", Font.BOLD, 17));
		
		JLabel lblNewLabel_2 = new JLabel("元");
		lblNewLabel_2.setFont(new Font("楷体", Font.BOLD, 17));
		
		JLabel lblNewLabel_3 = new JLabel("请使用支付宝扫描下方二维码付款：");
		lblNewLabel_3.setFont(new Font("楷体", Font.BOLD, 14));
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(SettlementWin.class.getResource("/images/pay.png")));
		
		JButton paidButton = new JButton("已付款");
		paidButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addActionPerformed(e);
			}
		});
		paidButton.setBackground(new Color(255, 255, 255));
		paidButton.setFont(new Font("楷体", Font.BOLD, 16));
		
		JButton backButton = new JButton("返  回");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backActionPerformed();
			}
		});
		backButton.setBackground(new Color(255, 255, 255));
		backButton.setFont(new Font("楷体", Font.BOLD, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(79)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(47)
							.addComponent(amountLabel)
							.addGap(34)
							.addComponent(lblNewLabel_2))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addComponent(paidButton)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(backButton))
							.addComponent(lblNewLabel_4, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(68, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(amountLabel)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_3)
					.addGap(18)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(paidButton)
						.addComponent(backButton))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * 增加订单记录
	 * @param e
	 */
	private void addActionPerformed(ActionEvent e) {	
		Date date = new Date();
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String orderNum = dateFormat1.format(date).toString(); // 获取当前时间作为订单号
		String payer = LoginWin.payer;
		BigDecimal paymentAmount = new BigDecimal(TrolleyWin.sp);
		String paymentTime = dateFormat2.format(date).toString();
		
		Record record = new Record(orderNum, payer, paymentAmount, paymentTime);
		
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			int flag = recordDao.add(conn, record);
			if (flag == 1) {
				paid = true;
				JOptionPane.showMessageDialog(null, "订单完成，请凭订单号联系工作人员取货！");
				trolleyDao.refresh(conn);
				TrolleyWin.fillTable();
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "验证订单失败，请重试！");
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "验证订单失败，请重试！");
		} finally {
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 返回上一级界面
	 */
	private void backActionPerformed() {
		this.dispose();
	}
}
