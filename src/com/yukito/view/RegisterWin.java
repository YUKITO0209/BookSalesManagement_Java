package com.yukito.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.yukito.dao.UserDao;
import com.yukito.model.User;
import com.yukito.util.DbUtil;
import com.yukito.util.StringUtil;

import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class RegisterWin extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JTextField phoneNumTxt;
	private JPasswordField passwordTxt1;
	private JPasswordField passwordTxt2;
	
	private DbUtil dbUtil = new DbUtil();
	private UserDao userDao = new UserDao();
	private ButtonGroup group = new ButtonGroup();
	
	private int checkRadioButton = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterWin frame = new RegisterWin();
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
	public RegisterWin() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterWin.class.getResource("/images/logo.ico")));
		setTitle("用户注册");
		setBounds(100, 100, 800, 450);
		setLocationRelativeTo(null); // 窗体居中
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("用 户 名：");
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 18));
		
		JLabel lblNewLabel_1 = new JLabel("手 机 号：");
		lblNewLabel_1.setFont(new Font("楷体", Font.BOLD, 18));
		
		JLabel lblNewLabel_2 = new JLabel("权    限：");
		lblNewLabel_2.setFont(new Font("楷体", Font.BOLD, 18));
		
		JLabel lblNewLabel_3 = new JLabel("密    码：");
		lblNewLabel_3.setFont(new Font("楷体", Font.BOLD, 18));
		
		JLabel lblNewLabel_4 = new JLabel("确认密码：");
		lblNewLabel_4.setFont(new Font("楷体", Font.BOLD, 18));
		
		userNameTxt = new JTextField();
		userNameTxt.setColumns(10);
		
		phoneNumTxt = new JTextField();
		phoneNumTxt.setColumns(10);
		
		JRadioButton radioButton1 = new JRadioButton("管理员");
		radioButton1.setFont(new Font("楷体", Font.BOLD, 18));
		radioButton1.setOpaque(false);
		group.add(radioButton1);
		
		JRadioButton radioButton2 = new JRadioButton("顾  客");
		radioButton2.setFont(new Font("楷体", Font.BOLD, 18));
		radioButton2.setOpaque(false);
		group.add(radioButton2);
		
		passwordTxt1 = new JPasswordField();
		
		passwordTxt2 = new JPasswordField();
		
		JCheckBox checkBox1 = new JCheckBox("显示密码");
		checkBox1.setOpaque(false);
		checkBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox1.isSelected()) {
					passwordTxt1.setEchoChar((char)0);
				} else {
					passwordTxt1.setEchoChar('•');
				}
			}
		});
		checkBox1.setFont(new Font("楷体", Font.BOLD, 15));
		
		JCheckBox checkBox2 = new JCheckBox("显示密码");
		checkBox2.setOpaque(false);
		checkBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox2.isSelected()) {
					passwordTxt2.setEchoChar((char)0);
				} else {
					passwordTxt2.setEchoChar('•');
				}
			}
		});
		checkBox2.setFont(new Font("楷体", Font.BOLD, 15));
		
		JButton registerButton = new JButton("注册");
		registerButton.setBackground(new Color(255, 255, 255));
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioButton1.isSelected()) {
					checkRadioButton = 1;
				} else if (radioButton2.isSelected()) {
					checkRadioButton = 2;
				} else {
					checkRadioButton = 3;
				}
				registerActionPerformed(e);
			}
		});
		registerButton.setFont(new Font("楷体", Font.BOLD, 18));
		
		JButton exitButton = new JButton("返回");
		exitButton.setBackground(new Color(255, 255, 255));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitActionPerformed(e);
			}
		});
		exitButton.setFont(new Font("楷体", Font.BOLD, 18));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(215)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(2)
									.addComponent(registerButton, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblNewLabel_4)
									.addComponent(lblNewLabel_3)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(passwordTxt2, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
										.addComponent(passwordTxt1, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(checkBox2)
										.addComponent(checkBox1)))
								.addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addGap(18)
							.addComponent(radioButton1)
							.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
							.addComponent(radioButton2))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(userNameTxt))
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(phoneNumTxt, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)))))
					.addGap(227))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(34, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(phoneNumTxt, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(radioButton2)
						.addComponent(radioButton1))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordTxt1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkBox1)
						.addComponent(lblNewLabel_3))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordTxt2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkBox2)
						.addComponent(lblNewLabel_4))
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(49))
		);
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * 用户注册
	 * @param e
	 */
	private void registerActionPerformed(ActionEvent e) {
		String userName = this.userNameTxt.getText();
		String phoneNumber = this.phoneNumTxt.getText();
		int authority = checkRadioButton;
		String password1 = new String(this.passwordTxt1.getPassword());
		String password2 = new String(this.passwordTxt2.getPassword());
		
		// 判断是否有项目为空
		if (StringUtil.isEmpty(userName)
			|| StringUtil.isEmpty(password1) 
			|| StringUtil.isEmpty(password2) 
			|| StringUtil.isEmpty(phoneNumber)
			|| authority == 3
			|| authority == 0) {
			JOptionPane.showMessageDialog(null, "各项不能为空，请重新输入！");
			return;
		}
		// 判断两次输入密码是否一致
		if (!password1.equals(password2)) {
			JOptionPane.showMessageDialog(null, "两次密码输入不一致，请重新输入！");
			return;
		}
		
		User user = new User(userName, phoneNumber, password2, authority);
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			int flag = userDao.register(conn, user);
			if (flag == 1) { // 注册成功
				if (JOptionPane.showConfirmDialog(null, "注册成功，是否返回登录界面？", "提示信息", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					this.dispose();
				}
			} else { // 注册失败
				JOptionPane.showMessageDialog(null, "注册失败，请输入正确的信息！");
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "注册失败，请输入正确的信息！");
		}
	}

	/**
	 * 返回登录界面
	 * @param e
	 */
	private void exitActionPerformed(ActionEvent e) {
		if (JOptionPane.showConfirmDialog(null, "是否返回登录界面？", "提示信息", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
			this.dispose();
		}
	}

}
