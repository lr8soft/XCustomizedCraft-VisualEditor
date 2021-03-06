package XCBVisualEditor.XCBVisualEditor;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JFrame;

import XCBVisualEditor.XCDJsonDetailInfo;
import XCBVisualEditor.XCBJson.ConfigJsonReader;
import XCBVisualEditor.XCBUtil.XCBSelectBox;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JComboBox;

public class XCBVisualBlade {
	private ConfigJsonReader jsondata;
	private DefaultListModel blademenu;
	public String bladeUuid,bladeName,bladeModel,bladeTexture,SEName;
	public int damage,duration,sa,color=3316172,SELevel=15;
	private boolean iswitched=false;
	public JFrame frmXCBVisualBlade;
	private JLabel ValueLabel;
	private XCDJsonDetailInfo bladeinfo;
	private Vector EnchantList=new Vector();
	private Vector EnchantLevel=new Vector();
	private DefaultListModel EnchantDLM=new DefaultListModel();
	private String[] enchantment= {"power","unbreaking","looting","sharpness","infinity","thorns","knockback","baneOfArthropods",
			"blastProtection","featherFalling","fireAspect","fireProtection","flame","fortune","projectileProtection","protection",
			"punch","respiration","silkTouch","smite"};
	public XCBVisualBlade(ConfigJsonReader data,DefaultListModel input,XCDJsonDetailInfo info) {
		this.bladeinfo=info;
		this.blademenu=input;
		this.jsondata=data;
		EnchantLoadFromInfo();
		initialize();
		dataLoadFromInfo();
	}
	private void dataLoadFromInfo() {
		if(this.bladeinfo!=null) {
			this.bladeName=bladeinfo.showName;
			this.bladeUuid=bladeinfo.bladename;
			this.bladeTexture=bladeinfo.bladeTexture;
			this.bladeModel=bladeinfo.bladeModel;
			this.sa=bladeinfo.sa;
			this.duration=bladeinfo.bladeduration;
			this.damage=(int) bladeinfo.bladedamage;
			this.iswitched=bladeinfo.iswitched;
			this.color=bladeinfo.color;
			this.SELevel=bladeinfo.SELevel;
			this.SEName=bladeinfo.SEName;
			System.out.println("XCC:Try to replace data from config.");
		}else {
			System.out.println("XCC:Null");
		}
	}
	private void EnchantLoadFromInfo() {
		if(this.bladeinfo!=null) {
			if(bladeinfo.EnchantName!=null&&bladeinfo.EnchantLevel!=null) {
				this.EnchantList=bladeinfo.EnchantName;
				this.EnchantLevel=bladeinfo.EnchantLevel;
				for(int i=0;i<bladeinfo.EnchantName.size();i++) {
					System.out.println(EnchantList.get(i).toString()+EnchantLevel.get(i).toString());
					EnchantDLM.addElement("附魔"+EnchantList.get(i).toString()+" 等级"+EnchantLevel.get(i).toString());
				}
			}else {
				System.out.println("XCC Enchantment NULL.");
			}
		}else {
			System.out.println("XCC BladeInfo NULL.");
		}
	}
	private void initialize() {
		XCBVisualBlade tempClass=this;
		frmXCBVisualBlade = new JFrame();
		frmXCBVisualBlade.setResizable(false);
		frmXCBVisualBlade.setTitle("XCBVisualEditor -CreateBlade");
		frmXCBVisualBlade.setBounds(100, 100, 436, 320);
		frmXCBVisualBlade.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmXCBVisualBlade.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 32, 54, 15);
		frmXCBVisualBlade.getContentPane().add(label);
		
		XCBVisualBlade classTemp=this;
		JTree BladeTreeInfo = new JTree();
		BladeTreeInfo.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("拔刀设置-双击更改") {
				{
					DefaultMutableTreeNode node_1;
					add(new DefaultMutableTreeNode("拔刀识别名"));
					add(new DefaultMutableTreeNode("显示名称"));
					add(new DefaultMutableTreeNode("模型地址"));
					add(new DefaultMutableTreeNode("贴图地址"));
					node_1 = new DefaultMutableTreeNode("详细信息");
						node_1.add(new DefaultMutableTreeNode("SA"));
						node_1.add(new DefaultMutableTreeNode("SE"));
						node_1.add(new DefaultMutableTreeNode("耐久"));
						node_1.add(new DefaultMutableTreeNode("基础伤害"));
						node_1.add(new DefaultMutableTreeNode("妖刀"));
						node_1.add(new DefaultMutableTreeNode("剑气颜色"));
					add(node_1);
					add(new DefaultMutableTreeNode("CustomizedRecipe"));
				}
			}
		));
		BladeTreeInfo.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent event) {
				String temp=null;
				if(event.getSource() == BladeTreeInfo) 
					workToLabel(BladeTreeInfo.getLastSelectedPathComponent().toString());
				if(event.getSource() == BladeTreeInfo &&event.getClickCount()==2) {
					if(BladeTreeInfo.getPathForLocation(event.getX(), event.getY())!=null) {
						switch(BladeTreeInfo.getLastSelectedPathComponent().toString()) {
							case "拔刀识别名":
								temp = JOptionPane.showInputDialog(null,"输入拔刀剑识别名",bladeUuid);
								if(temp!=null)
									bladeUuid=temp;
								break;
							case "显示名称":
								temp = JOptionPane.showInputDialog(null,"输入拔刀剑显示名",bladeName);
								if(temp!=null)
									bladeName=temp;
								break;
							case "模型地址":
								temp = JOptionPane.showInputDialog(null,"输入拔刀剑模型路径",bladeModel);
								if(temp!=null)
									bladeModel=temp;
								break;
							case "贴图地址":
								temp = JOptionPane.showInputDialog(null,"输入拔刀剑贴图路径",bladeTexture);
								if(temp!=null)
									bladeTexture=temp;
								break;
							case "SA":
								XCBSpecialAttackSelect saWin=new XCBSpecialAttackSelect(classTemp,sa);
								saWin.frmXCCVESA.setVisible(true);
								break;
							case "SE":
								String input=JOptionPane.showInputDialog(null,"输入SE在拔刀剑中识别名",SEName);
								int inputLevel=Integer.valueOf(JOptionPane.showInputDialog(null,"输入使用SE最低等级",String.valueOf(SELevel)));
								if(input!=null) {
									SEName=input;
									SELevel=inputLevel;
								}
								break;
							case "耐久":
								temp = JOptionPane.showInputDialog(null,"输入拔刀剑耐久",String.valueOf(duration));
								if(temp!=null)
									duration=Integer.valueOf(temp);
								break;
							case "基础伤害":
								temp = JOptionPane.showInputDialog(null,"输入拔刀剑基础威力",String.valueOf(damage));
								if(temp!=null)
									damage=Integer.valueOf(temp);
								break;
							case "妖刀":
								if(JOptionPane.showConfirmDialog(null, "是否为妖刀？", "妖刀确认", 0)==0)//Yes
									iswitched=true;
								else 
									iswitched=false;
								break;
							case "剑气颜色":
								XCBColorSelect colorWin=new XCBColorSelect(classTemp);
								colorWin.frmXccVisualeditorColor.setVisible(true);
								break;
								
						}	
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			});
		BladeTreeInfo.setBounds(20, 44, 172, 200);
		frmXCBVisualBlade.getContentPane().add(BladeTreeInfo);
		
		JButton btnNewButton = new JButton("确认");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XCDJsonDetailInfo info=new XCDJsonDetailInfo(sa,0,duration,color,iswitched,damage,bladeUuid,bladeName,
						bladeModel,bladeTexture,EnchantList,EnchantLevel,SEName,SELevel);
						int ret=jsondata.addToJson(info);
						switch(ret) {
							case 1:
								JOptionPane.showMessageDialog(null, "配置已经保存.");break;
							case 0:
								JOptionPane.showMessageDialog(null, "输入不完整！");return;
							case -1:
								JOptionPane.showMessageDialog(null, "配置文件写入失败！");break;
							case -2:
								JOptionPane.showMessageDialog(null, "拔刀重写完成！");break;
						}
						frmXCBVisualBlade.setVisible(false);
						try {
							tempClass.finalize();
						} catch (Throwable error) {}
			}
		});
		btnNewButton.setBounds(202, 213, 186, 29);
		frmXCBVisualBlade.getContentPane().add(btnNewButton);
		
		ValueLabel = new JLabel("Not selected.");
		ValueLabel.setBounds(30, 254, 358, 15);
		frmXCBVisualBlade.getContentPane().add(ValueLabel);
		
		JLabel label_1 = new JLabel("附魔");
		label_1.setBounds(202, 19, 54, 15);
		frmXCBVisualBlade.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("拔刀信息");
		label_2.setBounds(20, 19, 54, 15);
		frmXCBVisualBlade.getContentPane().add(label_2);
		
		JScrollPane scrollPane=new JScrollPane();
		scrollPane.setBounds(202, 44, 186, 95);
		frmXCBVisualBlade.getContentPane().add(scrollPane);
		
		JList EnchantShowList = new JList();
		scrollPane.setViewportView(EnchantShowList);
		EnchantShowList.setModel(EnchantDLM);
		
		JComboBox<String> EnchantmentSelect = new JComboBox<>(new XCBSelectBox(enchantment));
		EnchantmentSelect.setBounds(202, 145, 186, 24);
		frmXCBVisualBlade.getContentPane().add(EnchantmentSelect);
		
		JButton add = new JButton("添加");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(EnchantmentSelect.getSelectedItem()!=null) {
					int level=1;
					level=Integer.valueOf((JOptionPane.showInputDialog(null,"输入附魔等级","1")));
					EnchantList.add(EnchantmentSelect.getSelectedItem().toString());
					EnchantLevel.add(level);
					EnchantDLM.addElement("附魔"+EnchantmentSelect.getSelectedItem().toString()+" 等级"+level);
					EnchantShowList.setModel(EnchantDLM);
				}else {
					JOptionPane.showMessageDialog(null, "未选择附魔！");
				}
			}
		});
		add.setBounds(202, 179, 88, 24);
		frmXCBVisualBlade.getContentPane().add(add);
		
		JButton del = new JButton("删除");
		del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!EnchantShowList.isSelectionEmpty()) {
					int index=0;
					index=EnchantShowList.getSelectedIndex();
					EnchantList.remove(index);
					EnchantLevel.remove(index);
					EnchantDLM.remove(index);
					EnchantShowList.setModel(EnchantDLM);
					System.out.println(index);
				}else {
					JOptionPane.showMessageDialog(null, "未选择附魔！");
				}
			}
		});
		del.setBounds(300, 179, 88, 24);
		frmXCBVisualBlade.getContentPane().add(del);
	}
	public void workToLabel(String name) {
		switch(name) {
			case "拔刀识别名":
				ValueLabel.setText(name+":"+this.bladeUuid);
				break;
			case "显示名称":
				ValueLabel.setText(name+":"+this.bladeName);
				break;
			case "模型地址":
				ValueLabel.setText(name+":"+this.bladeModel);
				break;
			case "贴图地址":
				ValueLabel.setText(name+":"+this.bladeTexture);
				break;
			case "SA":
				ValueLabel.setText(name+":"+String.valueOf(this.sa));
				break;
			case "SE":
				ValueLabel.setText(name+":"+this.SEName);
				break;
			case "耐久":
				ValueLabel.setText(name+":"+String.valueOf(this.duration));
				break;
			case "基础伤害":
				ValueLabel.setText(name+":"+String.valueOf(this.damage));
				break;
			case "妖刀":
				ValueLabel.setText(name+":"+String.valueOf(this.iswitched));
				break;
			case "剑气颜色":
				ValueLabel.setText(name+":"+String.valueOf(this.color));
				break;
		}	
	}
}
