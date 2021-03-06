package XCBVisualEditor.XCBVisualEditor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import XCBVisualEditor.XCustomizedMainLoader;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class XCBAbout {
	SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    Date getDate = new Date();
	String lisence=String.format("  感谢您选择XCustomizedCraft VisualEditor，XCustomizedCraft VisualEditor隶属于XCustomizedCraft系列下可视化编辑软件，\r\n" + 
			"旨于更好简化服主使用/开发者开发/MOD整合包制作者制作等过程。 \r\n" + 
			"XCustomizedCraft VisualEditor 的官方网址是： http://lrsoft.azurewebsites.net/\r\n" + 
			"为了使您正确并合法的使用本软件，请您在使用前务必阅读清楚下面的协议条款：\r\n" + 
			" 一、本授权协议适用且仅适用于XCustomizedCraft VisualEditor，XCustomizedCraft VisualEditor官方对本授权协议拥有最终解释权。\r\n" + 
			" 二、协议许可的权利 \r\n" + 
			"     1、您可以在完全遵守本最终用户授权协议的基础上，将本软件应用于非商业用途，而不必支付软件版权授权费用。 \r\n" + 
			"     2、您拥有使用本软件构建的软件全部内容所有权，并独立承担与这些内容的相关法律义务。 \r\n" + 
			"     3、获得商业授权之后，您可以将本软件应用于商业用途，同时依据所购买的授权类型中确定的技术支持内容，自购买时刻起，在技术支持期限内拥有通过指定的方式获得指定范围内的技术支持服务。商业授权用户享有反映和提出意见的权力，相关意见将被作为首要考虑，但没有一定被采纳的承诺或保证。 \r\n" + 
			"     4、本协议仅且适用于XCustomizedCraft VisualEditor及其衍生产品，XCustomizedBlade等Minecraft模组不受本协议限制。\r\n" + 
			" 三、协议规定的约束和限制 \r\n" + 
			"     1、未获商业授权之前，不得将本软件用于商业用途。购买商业授权请登陆http://lrsoft.azurewebsites.net/了解最新说明。\r\n" + 
			"     2、未经官方许可，不得对本软件或与之关联的商业授权进行出租、出售、抵押或发放子许可证。\r\n" + 
			"     3、未经官方许可，禁止在 XCustomizedCraft VisualEditor 的整体或任何部分基础上以发展任何派生版本、修改版本或第三方版本用于重新分发。\r\n" + 
			"     4、如果您未能遵守本协议的条款，您的授权将被终止，所被许可的权利将被收回，并承担相应法律责任。 \r\n" + 
			" 四、有限担保和免责声明 \r\n" + 
			"     1、本软件及所附带的文件是作为不提供任何明确的或隐含的赔偿或担保的形式提供的。 \r\n" + 
			"     2、用户出于自愿而使用本软件，您必须了解使用本软件的风险，在尚未购买产品技术服务之前，我们不承诺对免费用户提供任何形式的技术支持、使用担保，也不承担任何因使用本软件而产生问题的相关责任。 \r\n" + 
			"     3、电子文本形式的授权协议如同双方书面签署的协议一样，具有完全的和等同的法律效力。您一旦开始确认本协议并使用XCustomizedCraft VisualEditor，即被视为完全理解并接受本协议的各项条款，在享有上述条款授予的权力的同时，受到相关的约束和限制。协议许可范围以外的行为，将直接违反本授权协议并构成侵权，我们有权随时终止授权，责令停止损害，并保留追究相关责任的权力。\r\n" + 
			"     4、如果本软件带有其它软件的整合API示范例子包，这些文件版权不属于本软件官方，并且这些文件是没经过授权发布的，请参考相关软件的使用许可合法的使用。\r\n" + 
			" 版权所有 © 2012-%s，LT_lrsoft 保留所有权利。 \r\n" + 
			" 协议发布时间：2014年08月19日\r\n" + 
			" 2012 ~ %s First-Logical Technology Studio,LT_lrsoft. All rights reserved.",yearFormat.format(getDate),yearFormat.format(getDate));
	public JFrame frmXcustomizedcraftVisualeditorFor;
	public XCBAbout() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmXcustomizedcraftVisualeditorFor = new JFrame();
		frmXcustomizedcraftVisualeditorFor.getContentPane().setFont(new Font("宋体", Font.PLAIN, 8));
		frmXcustomizedcraftVisualeditorFor.setTitle("XCustomizedCraft VisualEditor for XCB");
		frmXcustomizedcraftVisualeditorFor.setResizable(false);
		frmXcustomizedcraftVisualeditorFor.setBounds(100, 100, 502, 302);
		frmXcustomizedcraftVisualeditorFor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmXcustomizedcraftVisualeditorFor.getContentPane().setLayout(null);
		
		JLabel lblXcustomizedcraftVisualedtorFor = new JLabel("XCustomizedCraft VisualEditor "+XCustomizedMainLoader.XCCVEVersion);
		lblXcustomizedcraftVisualedtorFor.setFont(new Font("宋体", Font.BOLD, 16));
		lblXcustomizedcraftVisualedtorFor.setBounds(20, 20, 398, 15);
		frmXcustomizedcraftVisualeditorFor.getContentPane().add(lblXcustomizedcraftVisualedtorFor);
		
		JLabel lblNewLabel = new JLabel("USER LISCENCE");
		lblNewLabel.setBounds(20, 45, 159, 15);
		frmXcustomizedcraftVisualeditorFor.getContentPane().add(lblNewLabel);
		
		JLabel lblCopyrightByLtlrsoft = new JLabel(String.format("CopyRight by LT_lrsoft,2014~%s",yearFormat.format(getDate)));
		lblCopyrightByLtlrsoft.setBounds(20, 241, 229, 15);
		frmXcustomizedcraftVisualeditorFor.getContentPane().add(lblCopyrightByLtlrsoft);
		
		JScrollPane scrollPane=new JScrollPane();
		scrollPane.setBounds(20, 70, 467, 159);
		frmXcustomizedcraftVisualeditorFor.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setText(lisence);
		textArea.setFont(new Font("宋体", Font.PLAIN, 12));
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("取消");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmXcustomizedcraftVisualeditorFor.setVisible(false);
			}
		});
		btnNewButton.setBounds(388, 237, 99, 23);
		frmXcustomizedcraftVisualeditorFor.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmXcustomizedcraftVisualeditorFor.setVisible(false);
			}
		});
		button.setBounds(279, 237, 99, 23);
		frmXcustomizedcraftVisualeditorFor.getContentPane().add(button);
		
	}
}
