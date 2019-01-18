package XCBVisualEditor.XCBVisualEditor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;

import XCBVisualEditor.XCBJson.InitConfigJson;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class XCBConfigInit {

	public JFrame frmXccveConfigInit;
	private InitConfigJson configfile;
	public XCBConfigInit(String path) {
		configfile=new InitConfigJson(path);
		initialize();
	}
	private void initialize() {
		frmXccveConfigInit = new JFrame();
		frmXccveConfigInit.setTitle("XCC-VE Config Init");
		frmXccveConfigInit.setResizable(false);
		frmXccveConfigInit.setBounds(100, 100, 430, 230);
		frmXccveConfigInit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmXccveConfigInit.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("拔刀剑配置初始化");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "是否初始化拔刀剑信息？\n该操作不可逆！", "操作确认", 0)==0)//Yes
					configfile.BladeInit();
			}
		});
		btnNewButton.setBounds(21, 61, 175, 35);
		frmXccveConfigInit.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("SA配置初始化");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "是否初始化SA信息？\n该操作不可逆！", "操作确认", 0)==0)//Yes
					configfile.SAInit();
			}
		});
		btnNewButton_1.setBounds(21, 106, 175, 35);
		frmXccveConfigInit.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("SE配置初始化");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "是否初始化SE信息？\n该操作不可逆！", "操作确认", 0)==0)//Yes
					configfile.SEInit();
			}
		});
		btnNewButton_2.setBounds(206, 106, 175, 35);
		frmXccveConfigInit.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("配置版本号更新");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double version=Double.valueOf(JOptionPane.showInputDialog(null,"输入新版本号","1.53"));
				configfile.VerInit(version);
			}
		});
		btnNewButton_3.setBounds(206, 61, 175, 35);
		frmXccveConfigInit.getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("XCustomizedBlade 配置文件初始化");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(21, 21, 360, 30);
		frmXccveConfigInit.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("警告：初始化将删除该项所有信息！");
		lblNewLabel_1.setBounds(21, 161, 360, 15);
		frmXccveConfigInit.getContentPane().add(lblNewLabel_1);
	}
}
