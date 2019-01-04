package XCBVisualEditor.XCBVisualEditor;

import java.awt.EventQueue;

import javax.swing.JFrame;

import XCBVisualEditor.XCDJsonInfo;
import XCBVisualEditor.XCustomizedMainLoader;
import XCBVisualEditor.XCBJson.ConfigJsonReader;
import XCBVisualEditor.XCBJson.SAConfigJsonReader;
import XCBVisualEditor.XCBJson.SyncJsonReader;

import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
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

public class XCBVisualEditorMain {
	private XCBVisualEditorMain classTemp;
	public JFrame frmXCBVisualEditorMain;
	private JLabel ValueLabel;
	private SyncJsonReader serverdata;
	private ConfigJsonReader jsondata;
	private SAConfigJsonReader sadata;
	private DefaultListModel BladeDLM=new DefaultListModel();
	private DefaultListModel SADLM=new DefaultListModel();
	
	public XCBVisualEditorMain(ConfigJsonReader data,SAConfigJsonReader sadata) {
		this.jsondata=data;
		this.sadata=sadata;
		this.classTemp=this;
		getInfoFromJson();
		initialize();
	}
	private void getInfoFromJson() {
		XCDJsonInfo[] info=jsondata.GetInfo();
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
	}
	private void reloadFromJson(JList bladeList,JList saList) {
		jsondata.readFromJson();
		BladeDLM=new DefaultListModel();
		SADLM=new DefaultListModel();
		getInfoFromJson();
		bladeList.setModel(BladeDLM);
		saList.setModel(SADLM);
	}
	private void initialize() {
	
		frmXCBVisualEditorMain = new JFrame();
		frmXCBVisualEditorMain.setResizable(false);
		frmXCBVisualEditorMain.setType(Type.POPUP);
		frmXCBVisualEditorMain.setTitle("XCustomizedCraft VisualEditor for XCustomizedBlade "+XCustomizedMainLoader.XCCVEVersion);
		frmXCBVisualEditorMain.setBounds(100, 100, 709, 435);
		frmXCBVisualEditorMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmXCBVisualEditorMain.getContentPane().setLayout(null);
		
		JScrollPane scrollPane=new JScrollPane();
		scrollPane.setBounds(22, 41, 323, 122);
		frmXCBVisualEditorMain.getContentPane().add(scrollPane);
		
		JScrollPane scrollPaneSA=new JScrollPane();
		scrollPaneSA.setBounds(22, 213, 323, 122);
		frmXCBVisualEditorMain.getContentPane().add(scrollPaneSA);
		
		JList salist = new JList();
		salist.setModel(SADLM);
		scrollPaneSA.setViewportView(salist);
		
		JList bladelist = new JList();
		bladelist.setModel(BladeDLM);
		scrollPane.setViewportView(bladelist);
		
		JLabel label = new JLabel("配置内拔刀");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(22, 10, 97, 21);
		frmXCBVisualEditorMain.getContentPane().add(label);
		
		JLabel lblsa = new JLabel("配置内SA");
		lblsa.setFont(new Font("宋体", Font.PLAIN, 14));
		lblsa.setBounds(22, 182, 97, 21);
		frmXCBVisualEditorMain.getContentPane().add(lblsa);
		
		JButton button = new JButton("删除选中");
		button.addActionListener(new ActionListener() {
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
		button.setBounds(355, 107, 125, 23);
		frmXCBVisualEditorMain.getContentPane().add(button);
		
		JButton btnNewButton = new JButton("修改选中");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(bladelist.getSelectedValue()!=null) {
					String sendName=bladelist.getSelectedValue().toString();
					XCDJsonInfo bladeInfo=jsondata.GetBladeInfo(sendName);
					XCBVisualBlade addWindow=new XCBVisualBlade(jsondata,BladeDLM,bladeInfo);
					addWindow.frmXCBVisualBlade.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "未选中任何拔刀！");
				}
			}
		});
		btnNewButton.setBounds(355, 41, 125, 23);
		frmXCBVisualEditorMain.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("添加新拔刀");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				XCBVisualBlade addWindow=new XCBVisualBlade(jsondata,BladeDLM,null);
				addWindow.frmXCBVisualBlade.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(355, 74, 125, 23);
		frmXCBVisualEditorMain.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("刷新拔刀列表");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadFromJson(bladelist,salist);
			}
		});
		btnNewButton_2.setBounds(355, 140, 125, 23);
		frmXCBVisualEditorMain.getContentPane().add(btnNewButton_2);
		
		JButton btnsa = new JButton("添加新SA");
		btnsa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XCBVisualSpecialAttack saWindow=new XCBVisualSpecialAttack(sadata,null);
				saWindow.frmXCBVisualSA.setVisible(true);
			}
		});
		btnsa.setBounds(355, 213, 125, 23);
		frmXCBVisualEditorMain.getContentPane().add(btnsa);
		
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
		btnsa_1.setBounds(355, 246, 125, 23);
		frmXCBVisualEditorMain.getContentPane().add(btnsa_1);
		
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
		btnsa_2.setBounds(355, 279, 125, 23);
		frmXCBVisualEditorMain.getContentPane().add(btnsa_2);
		
		JButton btnsa_3 = new JButton("刷新SA列表");
		btnsa_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadFromJson(bladelist,salist);
			}
		});
		btnsa_3.setBounds(355, 312, 125, 23);
		frmXCBVisualEditorMain.getContentPane().add(btnsa_3);
		
		JLabel lblForXcustomizedbladeVer = new JLabel("For XCustomizedBlade VER TinyCore");
		lblForXcustomizedbladeVer.setBounds(22, 345, 269, 15);
		frmXCBVisualEditorMain.getContentPane().add(lblForXcustomizedbladeVer);
		
		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("配置信息") {
				{
					DefaultMutableTreeNode node_1;
					add(new DefaultMutableTreeNode("拔刀数量"));
					add(new DefaultMutableTreeNode("SA数量"));
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
		tree.setBounds(497, 41, 176, 271);
		frmXCBVisualEditorMain.getContentPane().add(tree);
		
		ValueLabel = new JLabel("Not selected");
		ValueLabel.setBounds(507, 320, 139, 15);
		frmXCBVisualEditorMain.getContentPane().add(ValueLabel);
		
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
}
