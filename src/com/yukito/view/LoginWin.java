package com.yukito.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.yukito.dao.TrolleyDao;
import com.yukito.dao.UserDao;
import com.yukito.model.User;
import com.yukito.util.DbUtil;
import com.yukito.util.StringUtil;
import java.awt.SystemColor;
import java.awt.Color;

public class LoginWin extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JPasswordField passwordTxt;
	
	private DbUtil dbUtil = new DbUtil();
	private UserDao userDao = new UserDao();
	private TrolleyDao trolleyDao = new TrolleyDao();
	private ButtonGroup group = new ButtonGroup();

	private int checkRadioButton = 0;
	public static String payer = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWin frame = new LoginWin();
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
	public LoginWin() {
		setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginWin.class.getResource("/images/logo.ico")));
		setTitle("登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		setLocationRelativeTo(null); // 窗体居中
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("书店销售管理系统");
		lblNewLabel.setFont(new Font("隶书", Font.BOLD, 35));
		
		JLabel lblNewLabel_1 = new JLabel("用户名：");
		lblNewLabel_1.setFont(new Font("楷体", Font.BOLD, 18));
		
		JLabel lblNewLabel_2 = new JLabel("密  码：");
		lblNewLabel_2.setFont(new Font("楷体", Font.BOLD, 18));
		
		userNameTxt = new JTextField();
		userNameTxt.setColumns(10);
		
		JCheckBox passwordCheckBox = new JCheckBox("显示密码");
		passwordCheckBox.setOpaque(false); // 设置背景透明
		passwordCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(passwordCheckBox.isSelected()) {
					passwordTxt.setEchoChar((char)0);
				} else {
					passwordTxt.setEchoChar('•');
				}
			}
		});
		passwordCheckBox.setFont(new Font("楷体", Font.BOLD, 15));
		
		JLabel lblNewLabel_3 = new JLabel("权  限：");
		lblNewLabel_3.setFont(new Font("楷体", Font.BOLD, 18));
		
		JRadioButton radioButton1 = new JRadioButton("管理员");
		radioButton1.setBackground(SystemColor.controlLtHighlight);
		radioButton1.setOpaque(false);
		radioButton1.setFont(new Font("楷体", Font.BOLD, 18));
		group.add(radioButton1);
		
		JRadioButton radioButton2 = new JRadioButton("顾  客");
		radioButton2.setBackground(SystemColor.controlLtHighlight);
		radioButton2.setOpaque(false);
		radioButton2.setFont(new Font("楷体", Font.BOLD, 18));
		group.add(radioButton2);
		
		JButton loginButton = new JButton("登录");
		loginButton.setBackground(new Color(255, 255, 255));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioButton1.isSelected()) {
					checkRadioButton = 1;
				} else if (radioButton2.isSelected()) {
					checkRadioButton = 2;
				} else {
					checkRadioButton = 3;
				}
				loginActionPerformed(e);
			}
		});
		loginButton.setFont(new Font("楷体", Font.BOLD, 19));
		
		JButton exitButton = new JButton("退出");
		exitButton.setBackground(new Color(255, 255, 255));
		exitButton.setFont(new Font("楷体", Font.BOLD, 19));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitActionPerformed(e);
			}
		});
		
		JButton registerButton = new JButton("注册");
		registerButton.setBackground(new Color(255, 255, 255));
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerActionPerformed(e);
			}
		});
		registerButton.setFont(new Font("楷体", Font.BOLD, 19));
		
		passwordTxt = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(247)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(registerButton, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
							.addGap(31)
							.addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addGap(18)
									.addComponent(radioButton1)))
							.addGap(2)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(passwordCheckBox, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
								.addComponent(radioButton2)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(userNameTxt, 211, 211, 211))
						.addComponent(lblNewLabel))
					.addGap(242))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(passwordCheckBox)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(radioButton1)
						.addComponent(radioButton2))
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(88, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * 打开注册界面
	 * @param e
	 */
	private void registerActionPerformed(ActionEvent e) {
		RegisterWin registerWin = new RegisterWin();
		registerWin.setVisible(true);
	}

	/**
	 * 登录
	 * @param e
	 */
	private void loginActionPerformed(ActionEvent e) {
		String userName = this.userNameTxt.getText();
		String password = new String(this.passwordTxt.getPassword());
		
		// 判断用户名和密码是否为空
		if(StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "用户名和密码不能为空，请重新输入！");
			return;
		}
		
		User user = new User(userName, password);
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			User currentUser = userDao.login(conn, user);
			if (currentUser != null) {
				if (checkRadioButton == 1 && currentUser.getAuthority() == 1) { // 管理员登录
					AdmFuncWin admFuncWin = new AdmFuncWin();
					this.setVisible(false);
					admFuncWin.setVisible(true);
				} else if (checkRadioButton == 2 && currentUser.getAuthority() == 2) { // 顾客登录
					payer = userName; // 记录用户名以备生成订单
					trolleyDao.refresh(conn);
					CusFuncWin cusFuncWin = new CusFuncWin();
					this.setVisible(false);
					cusFuncWin.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "权限错误！");
				}
			} else {
				JOptionPane.showMessageDialog(null, "用户名或密码错误，请重新输入！");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 退出程序
	 * @param e
	 */
	private void exitActionPerformed(ActionEvent evt) {
		System.exit(0);
	}
}
