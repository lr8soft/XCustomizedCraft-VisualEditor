package XCBVisualEditor.XCBVisualEditor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import XCBVisualEditor.XCDJsonSAInfo;
import XCBVisualEditor.XCBJson.SAConfigJsonReader;
import XCBVisualEditor.XCBUtil.XCBSelectBox;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JCheckBox;

public class XCBVisualSpecialAttack {
	public JFrame frmXCBVisualSA;
	private JCheckBox attackSelect;
	private JTextField saName;
	private JTextField saUuid;
	private JTextField saCost;
	private SAConfigJsonReader jsondata;
	private String inputName;
	private DefaultTreeModel actionContentTree;
	private Vector stepShow=new Vector();
	private Vector stepName=new Vector();
	private Vector stepCount=new Vector();
	private Vector stepDamage=new Vector();
	private XCBVisualSpecialAttack classTemp;
	private boolean allAttack;
	String[] sainfo= {"幻影剑(一根)","终焉樱","闪电","MaximumBet(快速两斩)","平突","爆炸","次元斩","延时(测试 单机有效)"};
	//                      0          1      2         3               4     5      6         7
	public XCBVisualSpecialAttack(SAConfigJsonReader data,String input) {
		this.classTemp=this;
		this.jsondata=data;
		this.inputName=input;
		initialize();
	}
	private void externInit() {
		if(this.inputName!=null) {
			XCDJsonSAInfo value=jsondata.GetInfo(this.inputName);
			this.saName.setText(value.SAName);
			this.saUuid.setText(String.valueOf(value.SANum));
			this.saCost.setText(String.valueOf(value.SAcost));
			for(int i=0;i<value.StepCount;i++) {
				System.out.println(value.SAStep[i]+" Count:"+value.SACount[i]+" Damage:"+value.StepDamage[i]);
				String temp=value.SAStep[i];
				attackSelect.setSelected(value.allAttack);
				stepName.add(value.SAStep[i]);
				stepCount.add(value.SACount[i]);
				stepDamage.add(value.StepDamage[i]);
				switch(temp) {
					case "PS":
						stepShow.addElement("幻影剑 "+value.SACount[i]+"次，单次威力"+value.StepDamage[i]);
						break;
					case "SE":
						stepShow.addElement("终焉樱 "+value.SACount[i]+"次，总威力"+value.StepDamage[i]);
						break;
					case "LN":
						stepShow.addElement("雷电"+value.SACount[i]+"次，总威力"+value.StepDamage[i]);
						break;
					case "MB":
						stepShow.addElement("快速两连斩"+value.SACount[i]+"次，总威力"+value.StepDamage[i]);
						break;
					case "SP":
						stepShow.addElement("平突"+value.SACount[i]+"次，总威力"+value.StepDamage[i]);
						break;
					case "EP":
						stepShow.addElement("爆炸"+value.SACount[i]+"次，总威力"+value.StepDamage[i]);
						break;
					case "SD":
						stepShow.addElement("次元斩"+value.SACount[i]+"次，总威力"+value.StepDamage[i]);
						break;
					case "DL":
						stepShow.addElement("延时"+value.SACount[i]+"单位，单机有效");
						break;
				}
			}
			setupContentTree();
		}
	}
	private void setupContentTree() {
		actionContentTree=new DefaultTreeModel(
				new DefaultMutableTreeNode(saName.getText()) {
					{
						for(int i=0;i<stepShow.size();i++) {
							add(new DefaultMutableTreeNode((String)stepShow.get(i)));
						}
					}
				}
			);
	}
	private void initialize() {
		frmXCBVisualSA = new JFrame();
		frmXCBVisualSA.setResizable(false);
		frmXCBVisualSA.setTitle("XCBVisualEditor -Create SA");
		frmXCBVisualSA.setBounds(100, 100, 472, 350);
		frmXCBVisualSA.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmXCBVisualSA.getContentPane().setLayout(null);
		
		JTree actionTree = new JTree();
		actionTree.setModel(actionContentTree);
		actionTree.setBounds(27, 23, 186, 257);
		frmXCBVisualSA.getContentPane().add(actionTree);
		
		JLabel lblSa = new JLabel("SA名称");
		lblSa.setBounds(230, 24, 54, 15);
		frmXCBVisualSA.getContentPane().add(lblSa);
		
		saName = new JTextField();
		saName.setBounds(294, 21, 151, 21);
		frmXCBVisualSA.getContentPane().add(saName);
		saName.setColumns(10);
		
		JLabel lblSa_1 = new JLabel("SA数字ID");
		lblSa_1.setBounds(230, 59, 75, 15);
		frmXCBVisualSA.getContentPane().add(lblSa_1);
		
		saUuid = new JTextField();
		saUuid.setBounds(294, 56, 151, 21);
		frmXCBVisualSA.getContentPane().add(saUuid);
		saUuid.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("SA总消耗");
		lblNewLabel.setBounds(230, 95, 54, 15);
		frmXCBVisualSA.getContentPane().add(lblNewLabel);
		
		saCost = new JTextField();
		saCost.setBounds(294, 92, 151, 21);
		frmXCBVisualSA.getContentPane().add(saCost);
		saCost.setColumns(10);
		
		JLabel lblSa_2 = new JLabel("SA步骤动作");
		lblSa_2.setBounds(230, 131, 60, 15);
		frmXCBVisualSA.getContentPane().add(lblSa_2);
		
		JComboBox saStepList = new JComboBox<>(new XCBSelectBox(sainfo));
		saStepList.setBounds(294, 128, 151, 21);
		frmXCBVisualSA.getContentPane().add(saStepList);
		
		JButton addAction = new JButton("添加");
		addAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(saStepList.getSelectedItem()==null) return;
				int count=0,damage=0;
				count=Integer.valueOf((JOptionPane.showInputDialog(null,"输入这一步骤攻击/运行次数","0")));
				damage=Integer.valueOf((JOptionPane.showInputDialog(null,"输入单次攻击威力","0")));
				switch(saStepList.getSelectedIndex()) {
					case 0:
						stepName.add("PS");
						stepShow.addElement("幻影剑 "+count+"次，单次威力"+damage);
						break;
					case 1:
						stepName.add("SE");
						stepShow.addElement("终焉樱 "+count+"次，总威力"+damage);
						break;
					case 2:
						stepName.add("LN");
						stepShow.addElement("雷电"+count+"次，总威力"+damage);
						break;
					case 3:
						stepName.add("MB");
						stepShow.addElement("快速两连斩"+count+"次，总威力"+damage);
						break;
					case 4:
						stepName.add("SP");
						stepShow.addElement("平突"+count+"次，总威力"+damage);
						break;
					case 5:
						stepName.add("EP");
						stepShow.addElement("爆炸"+count+"次，总威力"+damage);
						break;
					case 6:
						stepName.add("SD");
						stepShow.addElement("次元斩"+count+"次，总威力"+damage);
						break;
					case 7:
						stepName.add("DL");
						stepShow.addElement("延时"+count+"单位，单机有效");
						break;
				}
				stepCount.add(count);
				stepDamage.add(damage);
				setupContentTree();
				actionTree.setModel(actionContentTree);
			}
		});
		addAction.setBounds(231, 191, 102, 35);
		frmXCBVisualSA.getContentPane().add(addAction);
		
		JButton delAction = new JButton("删除");
		delAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(actionTree.getLastSelectedPathComponent()!=null) {
					int delIndex=actionTree.getModel().getIndexOfChild( (DefaultMutableTreeNode) actionTree.getSelectionPath().getPathComponent(0)
							,(DefaultMutableTreeNode)actionTree.getLastSelectedPathComponent());
					System.out.println(delIndex);
					try {
						stepShow.remove(delIndex);
						stepName.remove(delIndex);
						stepCount.remove(delIndex);
						stepDamage.remove(delIndex);
						setupContentTree();
						actionTree.setModel(actionContentTree);
						JOptionPane.showMessageDialog(null, "删除成功！");
					}catch(ArrayIndexOutOfBoundsException error) {
						JOptionPane.showMessageDialog(null, String.format("删除失败:%s", error.getMessage()));
					}
				}
			}
		});
		delAction.setBounds(343, 191, 102, 35);
		frmXCBVisualSA.getContentPane().add(delAction);
		
		JLabel actionSelected = new JLabel("Not selected.");
		actionSelected.setBounds(27, 290, 176, 15);
		frmXCBVisualSA.getContentPane().add(actionSelected);
		
		JButton submitSA = new JButton("提交SA");
		submitSA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(saName.getText()==""||saCost.getText()==""||saUuid.getText()==""||stepName.size()==0) {
					JOptionPane.showMessageDialog(null, "配置不完整！");	
					return;
				}
				String[] scontext=new String[stepName.toArray().length];
				int[] scount=new int[stepCount.toArray().length];
				int[] sdamage=new int[stepDamage.toArray().length];
				for(int a=0;a<stepCount.toArray().length;a++) {
					scount[a]=(int)stepCount.get(a);
					sdamage[a]=(int)stepDamage.get(a);
					scontext[a]=String.valueOf(stepName.get(a));
				}
				int ret=jsondata.addToJson(saName.getText(),Integer.valueOf(saUuid.getText()),
						Integer.valueOf(saCost.getText()), scontext,scount, sdamage,attackSelect.isSelected());
				if(ret==1) {
					JOptionPane.showMessageDialog(null, "已添加到配置中！");
					frmXCBVisualSA.setVisible(false);
					try {
						classTemp.finalize();
					} catch (Throwable e1) {}
				}	
				else if(ret==2)
					JOptionPane.showMessageDialog(null, "重写完成！");
				else 
					JOptionPane.showMessageDialog(null, "添加失败！");
			}
		});
		submitSA.setBounds(230, 237, 215, 43);
		frmXCBVisualSA.getContentPane().add(submitSA);
		
		attackSelect = new JCheckBox("对中立生物造成伤害");
		attackSelect.setBounds(230, 162, 215, 23);
		frmXCBVisualSA.getContentPane().add(attackSelect);
		
		if(this.inputName!=null) {
			externInit();
			setupContentTree();
			actionTree.setModel(actionContentTree);
		}
	}
}
