package XCBVisualEditor.XCBVisualEditor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;

import com.google.gson.JsonSyntaxException;

import XCBVisualEditor.XCustomizedMainLoader;
import XCBVisualEditor.XCBJson.ConfigJsonReader;
import XCBVisualEditor.XCBJson.EditorConfigJson;
import XCBVisualEditor.XCBJson.SAConfigJsonReader;
import XCBVisualEditor.XCBJson.SEConfigJsonReader;
import XCBVisualEditor.XCBUtil.XCBFileFilter;
import XCBVisualEditor.XCBUtil.XCBSelectBox;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class XCBConfigLoad {
	public ConfigJsonReader jsonreader;
	public JFrame frmXcustomizedbladeVisualeditor;
	private JTextField pathField;
	private EditorConfigJson configJson;
	private XCBConfigLoad thisClass;
	public XCBConfigLoad() {
		configJson=new EditorConfigJson();
		this.jsonreader=null;
		initialize();
		this.thisClass=this;
	}
	private void initialize() {
		frmXcustomizedbladeVisualeditor = new JFrame();
		frmXcustomizedbladeVisualeditor.setTitle("XCustomizedBlade VisualEditor");
		frmXcustomizedbladeVisualeditor.setResizable(false);
		frmXcustomizedbladeVisualeditor.setBounds(100, 100, 473, 200);
		frmXcustomizedbladeVisualeditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmXcustomizedbladeVisualeditor.getContentPane().setLayout(null);
		
		pathField = new JTextField();
		pathField.setBounds(99, 30, 236, 28);
		frmXcustomizedbladeVisualeditor.getContentPane().add(pathField);
		pathField.setColumns(10);
		
		JLabel label = new JLabel("配置文件");
		label.setBounds(25, 30, 60, 28);
		frmXcustomizedbladeVisualeditor.getContentPane().add(label);
		
		JButton btnSubmit = new JButton("提交");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pathField.getText()!=null) {
					try {
						jsonreader=new ConfigJsonReader(pathField.getText());
						jsonreader.readFromJson();
					}catch(JsonSyntaxException|NullPointerException|ClassCastException error) {
						jsonreader=null;
						JOptionPane.showMessageDialog(null, "请确认选择文件为XCustomizedBlade配置文件!\n位于.minecraft下的"
								+ "XCustomizedBlade.json!\n亦或者配置文件已损坏！");
						return;
					}
					SAConfigJsonReader sareader=new SAConfigJsonReader(pathField.getText());
					SEConfigJsonReader sereader=new SEConfigJsonReader(pathField.getText());
					if(jsonreader != null) {
						XCustomizedMainLoader.vemain=new XCBVisualEditorMain(jsonreader,sareader,sereader);
						XCustomizedMainLoader.vemain.frmXCBVisualEditorMain.setVisible(true);
						configJson.AddToConfig(pathField.getText());
						frmXcustomizedbladeVisualeditor.setVisible(false);
						try {
							thisClass.finalize();
						} catch (Throwable e1) {}
					}
				}
			}
		});
		btnSubmit.setBounds(188, 112, 117, 28);
		frmXcustomizedbladeVisualeditor.getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("取消");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(317, 112, 117, 28);
		frmXcustomizedbladeVisualeditor.getContentPane().add(btnCancel);
		
		JButton btnNewButton_2 = new JButton("浏览...");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				JFileChooser fd = new JFileChooser();
				fd.setFileFilter(new XCBFileFilter());
				fd.showOpenDialog(null);
				try {
					pathField.setText(fd.getSelectedFile().getPath());
				}catch(Exception e) {
					System.out.println("FileChooser NULL");
				}
				
				
			}
		});
		btnNewButton_2.setBounds(341, 30, 93, 29);
		frmXcustomizedbladeVisualeditor.getContentPane().add(btnNewButton_2);
		
		JComboBox comboBox=new JComboBox();
		comboBox.setEditable(false);
		if(configJson.jsoninfo!=null) {
			comboBox=new JComboBox<>(new XCBSelectBox(configJson.GetConfigList()));
			comboBox.setEnabled(true);
		}
		comboBox.setBounds(99, 71, 337, 28);
		JComboBox temp=comboBox;
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pathField.setText(temp.getSelectedItem().toString());
			}
			
		});
		frmXcustomizedbladeVisualeditor.getContentPane().add(comboBox);
		
		JLabel label_1 = new JLabel("历史记录");
		label_1.setBounds(25, 71, 60, 28);
		frmXcustomizedbladeVisualeditor.getContentPane().add(label_1);
		
	}
}
