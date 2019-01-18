package XCBVisualEditor.XCBVisualEditor;

import java.awt.EventQueue;

import javax.swing.JFrame;

import XCBVisualEditor.XCDJsonDetailInfo;
import XCBVisualEditor.XCustomizedMainLoader;
import XCBVisualEditor.XCBJson.ConfigJsonReader;
import XCBVisualEditor.XCBJson.SAConfigJsonReader;
import XCBVisualEditor.XCBJson.SEConfigJsonReader;
import XCBVisualEditor.XCBJson.SyncJsonReader;

import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JSeparator;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.tree.DefaultTreeModel;

import com.google.gson.JsonObject;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JMenuItem;
import java.awt.Button;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class XCBVisualEditorMain {
	private XCBVisualEditorMain classTemp;
	public JFrame frmXCBVisualEditorMain;
	private SyncJsonReader serverdata;
	private ConfigJsonReader jsondata;
	private SAConfigJsonReader sadata;
	private SEConfigJsonReader sedata;
//	private SEConfigJsonReader sedata;
	private DefaultListModel BladeDLM=new DefaultListModel();
	private DefaultListModel SADLM=new DefaultListModel();
	private DefaultListModel SEDLM=new DefaultListModel();
	private JLabel ValueLabel;
	private JList seList;
	private JPanel SAPane,SEPane;
	public XCBVisualEditorMain(ConfigJsonReader data,SAConfigJsonReader sadata,SEConfigJsonReader sedata) {
		this.jsondata=data;
		this.sadata=sadata;
		this.sedata=sedata;
		this.classTemp=this;
		getInfoFromJson();
		initialize();
	}
	private void getInfoFromJson() {
		XCDJsonDetailInfo[] info=jsondata.GetInfo();
		for(int i=0;i<info.length;i++) {
			try {
				BladeDLM.addElement(info[i].bladename);
			}catch(NullPointerException e) {
				continue;
			}
		}
		if(sadata.willSARun) {
			for(int j=0;j<sadata.jsondata.size();j++) {
				try {
					JsonObject temp=sadata.jsondata.get(j).getAsJsonObject();
					SADLM.addElement(temp.get("SAName").getAsString());
				}catch(NullPointerException e) {
					continue;
				}
			}
		}
		if(sedata.willSERun) {
			for(int j=0;j<sedata.jsondata.size();j++) {
				try {
					JsonObject temp=sedata.jsondata.get(j).getAsJsonObject();
					SEDLM.addElement(temp.get("SEName").getAsString());
				}catch(NullPointerException e) {
					continue;
				}
			}
		}
	}
	private void initialize() {
	
		frmXCBVisualEditorMain = new JFrame();
		frmXCBVisualEditorMain.setResizable(false);
		frmXCBVisualEditorMain.setType(Type.POPUP);
		frmXCBVisualEditorMain.setTitle("XCustomizedCraft VisualEditor for XCustomizedBlade "+XCustomizedMainLoader.XCCVEVersion);
		frmXCBVisualEditorMain.setBounds(100, 100, 690, 410);
		frmXCBVisualEditorMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmXCBVisualEditorMain.getContentPane().setLayout(null);
////////////////////////////////////////////////////Blade part init		
		JScrollPane scrollPane=new JScrollPane();
		scrollPane.setBounds(10, 10, 280, 252);
		
		
		JList bladelist = new JList();
		bladelist.setModel(BladeDLM);
		scrollPane.setViewportView(bladelist);
		
		JPanel BladePane=new JPanel();
		BladePane.setLayout(null);
		BladePane.add(scrollPane);
////////////////////////////////////////////////////SE Part init	
		JScrollPane scrollPaneSE=new JScrollPane();
		scrollPaneSE.setBounds(10, 10, 280, 252);
		
		SEPane=new JPanel();
		SEPane.setLayout(null);
		SEPane.add(scrollPaneSE);
		
		seList = new JList();
		seList.setModel(SEDLM);
		scrollPaneSE.setViewportView(seList);
		
		JTabbedPane XCCMenu=new JTabbedPane();
		XCCMenu.addTab("拔刀配置", BladePane);
		/////////////////////////////////////////////////////SA Part init
				JScrollPane scrollPaneSA=new JScrollPane();
				scrollPaneSA.setBounds(10, 10, 280, 252);
				
				SAPane=new JPanel();
				SAPane.setLayout(null);
				SAPane.add(scrollPaneSA);
				
				JList salist = new JList();
				scrollPaneSA.setViewportView(salist);
				salist.setModel(SADLM);
				XCCMenu.addTab("SA特殊攻击配置", SAPane);
				
				JButton btnsa = new JButton("添加新SA");
				btnsa.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						XCBVisualSpecialAttack saWindow=new XCBVisualSpecialAttack(sadata,null);
						saWindow.frmXCBVisualSA.setVisible(true);
					}
				});
				btnsa.setBounds(300, 8, 115, 23);
				SAPane.add(btnsa);
				
				JButton btnsa_1 = new JButton("修改选中SA");
				btnsa_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(salist.getSelectedValue()!=null) {
							String updateName=null;
							updateName=salist.getSelectedValue().toString();
							System.out.println(updateName);
							XCBVisualSpecialAttack saWindow=new XCBVisualSpecialAttack(sadata,updateName);
							saWindow.frmXCBVisualSA.setVisible(true);
						}else {
							JOptionPane.showMessageDialog(null, "未选中任何SA！");
						}
					}
				});
				btnsa_1.setBounds(300, 41, 115, 23);
				SAPane.add(btnsa_1);
				
				JButton btnsa_2 = new JButton("删除选中SA");
				btnsa_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(salist.getSelectedValue()!=null) {
							String delName=salist.getSelectedValue().toString();
							int ret=JOptionPane.showConfirmDialog(null, "你确定删除选中SA么","XCC VisualEditor",JOptionPane.YES_NO_CANCEL_OPTION);
							if(ret==JOptionPane.YES_OPTION) {
								int r=sadata.deleteToJson(delName);
								if(r==1) {
									JOptionPane.showMessageDialog(null, "SA已删除！");
								}else {
									JOptionPane.showMessageDialog(null, "删除失败！");
								}
							}						
						}else {
							JOptionPane.showMessageDialog(null, "未选中SA！");
						}
					}
				});
				btnsa_2.setBounds(300, 74, 115, 23);
				SAPane.add(btnsa_2);
				
				JButton btnsa_3 = new JButton("刷新SA列表");
				btnsa_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						reloadFromJson(bladelist,salist);
					}
				});
				btnsa_3.setBounds(300, 107, 115, 23);
				SAPane.add(btnsa_3);
		XCCMenu.addTab("SE特殊效果配置", SEPane);
		
		JButton btnSE1 = new JButton("添加SE");
		btnSE1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XCBVisualSpecialEffect seWindow=new XCBVisualSpecialEffect(sedata,null);
				seWindow.frmXCBVisualSE.setVisible(true);
			}
		});
		btnSE1.setBounds(300, 8, 115, 23);
		SEPane.add(btnSE1);
		
		JButton btnSE2 = new JButton("修改选中SE");
		btnSE2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(seList.getSelectedValue()!=null) {
					String updateName=null;
					updateName=seList.getSelectedValue().toString();
					System.out.println(updateName);
					XCBVisualSpecialEffect seWindow=new XCBVisualSpecialEffect(sedata,updateName);
					seWindow.frmXCBVisualSE.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "未选中任何SE！");
				}
			}
		});
		btnSE2.setBounds(300, 41, 115, 23);
		SEPane.add(btnSE2);
		
		JButton btnSE3 = new JButton("删除选中SE");
		btnSE3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(seList.getSelectedValue()!=null) {
					String delName=seList.getSelectedValue().toString();
					int ret=JOptionPane.showConfirmDialog(null, "你确定删除选中SE么","XCC VisualEditor",JOptionPane.YES_NO_CANCEL_OPTION);
					if(ret==JOptionPane.YES_OPTION) {
						int r=sedata.deleteToJson(delName);
						if(r==1) {
							JOptionPane.showMessageDialog(null, "SE已删除！");
						}else {
							JOptionPane.showMessageDialog(null, "删除失败！");
						}
					}						
				}else {
					JOptionPane.showMessageDialog(null, "未选中SE！");
				}
			}
		});
		btnSE3.setBounds(300, 74, 115, 23);
		SEPane.add(btnSE3);
		
		JButton btnSE4 = new JButton("刷新SE列表");
		btnSE4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadFromJson(bladelist,salist);
			}
		});
		btnSE4.setBounds(300, 107, 115, 23);
		SEPane.add(btnSE4);
		
		JButton btnNewButton = new JButton("修改选中");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(bladelist.getSelectedValue()!=null) {
					String sendName=bladelist.getSelectedValue().toString();
					XCDJsonDetailInfo bladeInfo=jsondata.GetBladeInfo(sendName);
					XCBVisualBlade addWindow=new XCBVisualBlade(jsondata,BladeDLM,bladeInfo);
					addWindow.frmXCBVisualBlade.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "未选中任何拔刀！");
				}
			}
		});
		btnNewButton.setBounds(300, 8, 115, 23);
		BladePane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("添加新拔刀");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				XCBVisualBlade addWindow=new XCBVisualBlade(jsondata,BladeDLM,null);
				addWindow.frmXCBVisualBlade.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(300, 41, 115, 23);
		BladePane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("删除选中");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(bladelist.getSelectedValue()!=null) {
					String delName=bladelist.getSelectedValue().toString();
					int ret=JOptionPane.showConfirmDialog(null, "你确定删除选中拔刀么","XCC VisualEditor",JOptionPane.YES_NO_CANCEL_OPTION);
					if(ret==JOptionPane.YES_OPTION) {
						int r=jsondata.deleteToJson(delName);
						if(r==1) {
							JOptionPane.showMessageDialog(null, "拔刀已删除！");
						}else {
							JOptionPane.showMessageDialog(null, "删除失败！");
						}
					}						
				}else {
					JOptionPane.showMessageDialog(null, "未选中拔刀！");
				}
			}
		});
		btnNewButton_2.setBounds(300, 74, 115, 23);
		BladePane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("刷新拔刀列表");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadFromJson(bladelist,salist);
			}
		});
		btnNewButton_3.setBounds(300, 107, 115, 23);
		BladePane.add(btnNewButton_3);
	/*	XCCMenu.addTab("拔刀配置", createTextPanel("拔刀配置"));
		XCCMenu.addTab("SA配置", createTextPanel("SA配置"));
		XCCMenu.addTab("SE配置", createTextPanel("SE配置"));*/
		XCCMenu.setSize(439, 301);
		XCCMenu.setLocation(10, 10);
		frmXCBVisualEditorMain.getContentPane().add(XCCMenu);
		JTree tree = new JTree();
		tree.setBorder(new LineBorder(new Color(0, 0, 0)));
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("配置信息") {
				{
					DefaultMutableTreeNode node_1;
					add(new DefaultMutableTreeNode("拔刀数量"));
					add(new DefaultMutableTreeNode("SA数量"));
					add(new DefaultMutableTreeNode("SE数量"));
					add(new DefaultMutableTreeNode("配置版本"));
					node_1 = new DefaultMutableTreeNode("服务器配置");
						node_1.add(new DefaultMutableTreeNode("网络地址"));
						node_1.add(new DefaultMutableTreeNode("开放端口"));
						node_1.add(new DefaultMutableTreeNode("数据同步"));
					add(node_1);
					add(new DefaultMutableTreeNode("TinyCore超微核心"));
				}
			}
		));
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				workToLabel(tree.getLastSelectedPathComponent().toString());
			}
		});
		tree.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if(event.getSource() == tree &&event.getClickCount()==2) {
					if(tree.getPathForLocation(event.getX(), event.getY())!=null) {
						String name=tree.getLastSelectedPathComponent().toString();
						switch(name) {
							case "网络地址":
								String temp= JOptionPane.showInputDialog(null,"输入服务器网络地址",serverdata.getServerAddress());
								if(temp!=null)
									serverdata.writeAddress(temp);
								break;
							case "开放端口":
								String port=JOptionPane.showInputDialog(null,"输入服务器端口号",serverdata.getServerPort());
								if(port!=null)
									serverdata.writePort(Integer.valueOf(port));
								break;
							case "数据同步":
								boolean sync=false;
								if(JOptionPane.showConfirmDialog(null, "是否开启数据同步？", "XCB Server/Client Sync", 0)==0)//Yes
									sync=true;
								else 
									sync=false;
								serverdata.writeSync(sync);
								break;
							case "TinyCore超微核心":
								boolean tc=false;
								if(JOptionPane.showConfirmDialog(null, "是否开启TinyCore支持？", "XCB TinyCore", 0)==0)//Yes
									tc=true;
								else 
									tc=false;
								jsondata.changeTinyCore(tc);
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
		tree.setBounds(459, 13, 205, 298);
		frmXCBVisualEditorMain.getContentPane().add(tree);
		
		ValueLabel = new JLabel("Not selected.");
		ValueLabel.setBounds(459, 321, 206, 15);
		frmXCBVisualEditorMain.getContentPane().add(ValueLabel);
		
		JLabel xccInfo = new JLabel("For XCustomizedBlade TinyCore 1.51 (1.7.10/1.12.2)");
		xccInfo.setBounds(20, 321, 429, 15);
		frmXCBVisualEditorMain.getContentPane().add(xccInfo);
		
		JMenu mnNewMenu = new JMenu("配置文件...");
		mnNewMenu.setBounds(0, 0, 111, 22);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("新建...");
		mntmNewMenuItem_1.setEnabled(false);
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JRadioButtonMenuItem rdbtnmntmNewRadioItem = new JRadioButtonMenuItem("打开...	");
		mnNewMenu.add(rdbtnmntmNewRadioItem);
		rdbtnmntmNewRadioItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				XCustomizedMainLoader newConfig=new XCustomizedMainLoader();
				newConfig.main(null);
				frmXCBVisualEditorMain.setVisible(false);
				try {classTemp.finalize();} catch (Throwable e1) {}
			}
		});
		
		JMenu mnNewMenu_2 = new JMenu("关于...");
		mnNewMenu_2.setBounds(242, 0, 111, 22);
		//frmXCBVisualEditorMain.getContentPane().add(mnNewMenu_2);
		JMenuBar jMenuBar = new JMenuBar();
		jMenuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("初始化...");
		mnNewMenu.add(mntmNewMenuItem_3);
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				XCBConfigInit initWin=new XCBConfigInit(jsondata.path);
				initWin.frmXccveConfigInit.setVisible(true);
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("编辑器设置...");
		jMenuBar.add(mnNewMenu_1);
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("版本 "+XCustomizedMainLoader.XCCVEVersion);
		mntmNewMenuItem_2.setEnabled(false);
		mnNewMenu_1.add(mntmNewMenuItem_2);
		JMenuItem menuItem = new JMenuItem("检查更新");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				XCBAutoUpdate updateWin=new XCBAutoUpdate();
				updateWin.frmXccvisualeditorUpdat.setVisible(true);
			}
		});
		mnNewMenu_1.add(menuItem);
		jMenuBar.add(mnNewMenu_2);
		JMenuItem mntmNewMenuItem = new JMenuItem("关于XCC VisualEditor");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				XCBAbout winAbout=new XCBAbout();
				winAbout.frmXcustomizedcraftVisualeditorFor.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem);
		frmXCBVisualEditorMain.setJMenuBar(jMenuBar);
		
		{
			if(!this.sadata.willSARun) {
				btnsa.setEnabled(false);
				btnsa_1.setEnabled(false);
				btnsa_2.setEnabled(false);
				btnsa_3.setEnabled(false);
				salist.setEnabled(false);
			}
			if(!this.sedata.willSERun) {
				btnSE1.setEnabled(false);
				btnSE2.setEnabled(false);
				btnSE3.setEnabled(false);
				btnSE4.setEnabled(false);
				seList.setEnabled(false);
			}
		}
	}
	public void workToLabel(String name) {
		this.serverdata=new SyncJsonReader(this.jsondata.path);
		switch(name) {
			case "拔刀数量":
				ValueLabel.setText(name+":"+this.jsondata.GetBladeCount());
				break;
			case "SA数量":
				ValueLabel.setText(name+":"+this.sadata.GetSACount());
				break;
			case "SE数量":
				ValueLabel.setText(name+":"+this.sedata.GetSECount());
				break;
			case "配置版本":
				ValueLabel.setText(name+":"+this.jsondata.GetConfigVersion());
				break;
			case "网络地址":
				ValueLabel.setText(name+":"+this.serverdata.getServerAddress());
				break;
			case "开放端口":
				ValueLabel.setText(name+":"+this.serverdata.getServerPort());
				break;
			case "数据同步":
				ValueLabel.setText(name+":"+this.serverdata.getServerSync());
				break;
			case "TinyCore超微核心":
				ValueLabel.setText(name+":"+this.jsondata.TinyCoreSupport());
				break;
		}
	}	
	private void reloadFromJson(JList bladeList,JList saList) {
		jsondata.readFromJson();
		BladeDLM=new DefaultListModel();
		SADLM=new DefaultListModel();
		SEDLM=new DefaultListModel();
		getInfoFromJson();
		bladeList.setModel(BladeDLM);
		saList.setModel(SADLM);
		seList.setModel(SEDLM);
	}
}
