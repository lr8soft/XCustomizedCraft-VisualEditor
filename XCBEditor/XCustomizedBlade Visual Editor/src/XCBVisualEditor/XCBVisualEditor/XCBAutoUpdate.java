package XCBVisualEditor.XCBVisualEditor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import XCBVisualEditor.XCustomizedMainLoader;
import XCBVisualEditor.XCBUtil.XCBGetPath;
import XCBVisualEditor.XCBUtil.XCBLoadFromNet;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.awt.Component;
import java.awt.Desktop;

import javax.swing.JButton;
import java.awt.TextField;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class XCBAutoUpdate {
	public JFrame frmXccvisualeditorUpdat;
	private String url="https://github.com/lr8soft/XCustomizedCraft-VisualEditor/releases",info,version;
	private String infoURL="http://lrsoft.azurewebsites.net/XCC-VE.txt";
	private URL updateURL=null;
	private JLabel NewVER;
	private JsonObject jsoninfo;
	public XCBAutoUpdate() {
		downloadFromInternet();
		loadFromInternet();
		initialize();
		try {updateURL=new URL(url);} catch (MalformedURLException e) {}
		checkVER();
	}
	public void downloadFromInternet() {
		String info=XCBLoadFromNet.openFile(infoURL);
		FileOutputStream out;
		try {
			out = new FileOutputStream(XCBGetPath.getNowPath()+"/XCCVE_Update.json");
			OutputStreamWriter outwriter=new OutputStreamWriter(out);
			outwriter.write(info);
			outwriter.flush();
			outwriter.close();
			out.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void checkVER(){
		if(version.equals(XCustomizedMainLoader.XCCVEVersion)) {
			JOptionPane.showMessageDialog(null, "已经是最新版本！");
		}else {
			JOptionPane.showMessageDialog(null, "检查到新版本！\n当前版本"+XCustomizedMainLoader.XCCVEVersion+"\n"+"最新版本"+version);
		}
	}
	public void loadFromInternet() {
		JsonParser jp=new JsonParser();
		try {
			jsoninfo=(JsonObject)jp.parse(new FileReader(XCBGetPath.getNowPath()+"/XCCVE_Update.json"));
			try {
				version=jsoninfo.get("XCCVE-VER").getAsString();
				url=jsoninfo.get("XCCVE-URL").getAsString();
				info=jsoninfo.get("ExtraInfo").getAsString();
				info=info.replaceAll( "<br>","\n");
			}
			catch(NullPointerException error) {
				JOptionPane.showMessageDialog(null, "服务器配置文件格式有误！\n"+error.getMessage());
			}
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "读取配置文件失败！\n"+e.getMessage());
		}
		
	}
	private void initialize() {
		frmXccvisualeditorUpdat = new JFrame();
		frmXccvisualeditorUpdat.setTitle("XCCVisualEditor Update");
		frmXccvisualeditorUpdat.setResizable(false);
		frmXccvisualeditorUpdat.setBounds(100, 100, 440, 315);
		frmXccvisualeditorUpdat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmXccvisualeditorUpdat.getContentPane().setLayout(null);
		
		JLabel NowVER = new JLabel("当前版本:"+XCustomizedMainLoader.XCCVEVersion);
		NowVER.setBounds(33, 229, 165, 15);
		frmXccvisualeditorUpdat.getContentPane().add(NowVER);
		
		NewVER = new JLabel("最新版本:"+version);
		NewVER.setBounds(33, 254, 165, 15);
		frmXccvisualeditorUpdat.getContentPane().add(NewVER);
		
		JScrollPane scrollPane=new JScrollPane();
		scrollPane.setBounds(33, 49, 371, 170);
		frmXccvisualeditorUpdat.getContentPane().add(scrollPane);
		
		JTextArea textField = new JTextArea();
		scrollPane.setViewportView(textField);
		textField.setLineWrap(true);
		textField.setFont(new Font("宋体", Font.PLAIN, 12));
		textField.setEditable(false);
		textField.setText(info);
		textField.setWrapStyleWord(true);
	
		JButton btnNewButton = new JButton("前往链接");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
	                   Desktop.getDesktop().browse(updateURL.toURI());
	               } catch (IOException err) {
	                   err.printStackTrace();
	               } catch (URISyntaxException err) {
	                   err.printStackTrace();
	               }
			}
		});
		btnNewButton.setBounds(208, 229, 93, 44);
		frmXccvisualeditorUpdat.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("取消");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmXccvisualeditorUpdat.setVisible(false);
			}
		});
		button.setBounds(311, 229, 93, 44);
		frmXccvisualeditorUpdat.getContentPane().add(button);
		
		JLabel lblNewLabel = new JLabel("更新内容");
		lblNewLabel.setBounds(33, 24, 54, 15);
		frmXccvisualeditorUpdat.getContentPane().add(lblNewLabel);
		
	}
}
