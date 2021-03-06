package XCBVisualEditor.XCBVisualEditor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;

import XCBVisualEditor.XCBUtil.XCBSelectBox;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class XCBSpecialAttackSelect {
	private JComboBox<String> SABox;
	private XCBVisualBlade callback;
	public JFrame frmXCCVESA;
	public int SAID=0;
	private XCBSpecialAttackSelect classTemp;
	String[] SAinfo1710= {"次元斩","幻影刃","平突","幻影刃","波刀龙胆","円刃","急袭幻影剑-衰破","终焉樱","MaximumBet"};
							// 0     1        2     3          4        5       6                7       8
	String[] SAinfo1122= {"次元斩","波刀龙胆","幻影刃","平突","円刃","急袭幻影剑-衰破","终焉樱","MaximumBet"};
						// 0     1          2       3     4        5              6       7   
	Vector showInfo=new Vector();
	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(SAinfo1710);
	public XCBSpecialAttackSelect(XCBVisualBlade callclass,int sa) {
		this.callback=callclass;
		this.SAID=sa;
		initialize();
		loadFromInfo(false);
		selectInit();
		classTemp=this;
	}
	public void selectInit() {
		if(SAID<=this.SAinfo1710.length)
			SABox.setSelectedItem(SAinfo1710[SAID]);
		else
			SABox.setSelectedItem(null);
	}
	public void loadFromInfo(boolean isNew) {
		if(isNew) {
			model = new DefaultComboBoxModel<>(SAinfo1122);
		}else {
			model = new DefaultComboBoxModel<>(SAinfo1710);
		}
		SABox.setModel(model);
	}
	private void initialize() {
		frmXCCVESA = new JFrame();
		frmXCCVESA.setTitle("XCCVE 选择SA");
		frmXCCVESA.setResizable(false);
		frmXCCVESA.setBounds(100, 100, 380, 150);
		frmXCCVESA.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmXCCVESA.getContentPane().setLayout(null);
		
		SABox = new JComboBox(model);
		SABox.setBounds(20, 43, 240, 23);
		frmXCCVESA.getContentPane().add(SABox);
		
		JLabel lblsa = new JLabel("选择SA");
		lblsa.setFont(new Font("宋体", Font.PLAIN, 14));
		lblsa.setBounds(20, 10, 114, 23);
		frmXCCVESA.getContentPane().add(lblsa);
		
		JCheckBox isNewVer = new JCheckBox("1.12.2");
		isNewVer.setBounds(20, 76, 87, 23);
		isNewVer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadFromInfo(isNewVer.isSelected());
			}
		});
		//isNewVer.set
		frmXCCVESA.getContentPane().add(isNewVer);
		
		JCheckBox isManual = new JCheckBox("手动输入");
		isManual.setBounds(109, 76, 103, 23);
		isManual.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(isManual.isSelected()) {
					isNewVer.setEnabled(false);
					SABox.setEnabled(false);
					
				}else {
					SABox.setEnabled(true);
					isNewVer.setEnabled(true);
				}

			}
		});
		frmXCCVESA.getContentPane().add(isManual);
		
		JButton submit = new JButton("确认");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!isManual.isSelected()) {
					SAID=SABox.getSelectedIndex();
					callback.sa=SAID;
				}else {
					String temp = JOptionPane.showInputDialog(null,"输入拔刀剑SA编号",String.valueOf(SAID));
					int ret=Integer.valueOf(temp);
					callback.sa=ret;
				}
				JOptionPane.showMessageDialog(null, "SA已保存！");
				frmXCCVESA.setVisible(false);
				try {classTemp.finalize();} catch (Throwable e) {}
			}
		});
		submit.setBounds(270, 43, 79, 23);
		frmXCCVESA.getContentPane().add(submit);
	}
}
