package XCBVisualEditor.XCBVisualEditor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class XCBColorSelect {
	private JLabel decshow,hexshow;
	public JFrame frmXccVisualeditorColor;
	private JScrollBar R,G,B;
	private JTextField redText;
	private JTextField blueText;
	private JTextField greenText;
	private String hexcolor;
	private int deccolor;
	private JPanel Colorpreview;
	private XCBVisualBlade callback;
	private XCBColorSelect classTemp;
	public XCBColorSelect(XCBVisualBlade call) {
		this.callback=call;
		classTemp=this;
		initialize();
		dataInit();
	}
	public void dataInit() {
		int[] RGB=null;
		deccolor=callback.color;
		hexcolor=Integer.toHexString(deccolor);
		hexshow.setText(hexcolor);
		decshow.setText(String.valueOf(deccolor));
		RGB=convertHexToRGB(hexcolor);
		Colorpreview.setBackground(new Color(RGB[0],RGB[1],RGB[2]));
		redText.setText(String.valueOf(RGB[0]));
		greenText.setText(String.valueOf(RGB[1]));
		blueText.setText(String.valueOf(RGB[2]));
		R.setValue(Integer.valueOf(redText.getText()));
		G.setValue(Integer.valueOf(greenText.getText()));
		B.setValue(Integer.valueOf(blueText.getText()));
	}
	public void colorRefresh() {
		if(redText.getText()!=null&&greenText.getText()!=null&&blueText.getText()!=null) {
			hexcolor=convertRGBToHex(
					Integer.valueOf(redText.getText()),Integer.valueOf(greenText.getText()),Integer.valueOf(blueText.getText()));
			deccolor=Integer.parseInt(hexcolor,16);
			Colorpreview.setBackground(new Color(Integer.valueOf(redText.getText()),Integer.valueOf(greenText.getText()),Integer.valueOf(blueText.getText())));
			decshow.setText(String.valueOf(deccolor));
			hexshow.setText(hexcolor);
		}
	}
	private void initialize() {
		frmXccVisualeditorColor = new JFrame();
		frmXccVisualeditorColor.setTitle("XCC VisualEditor Color Selector");
		frmXccVisualeditorColor.setResizable(false);
		frmXccVisualeditorColor.setBounds(100, 100, 398, 302);
		frmXccVisualeditorColor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmXccVisualeditorColor.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.RED);
		panel.setBounds(30, 30, 50, 50);
		frmXccVisualeditorColor.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.BLUE);
		panel_1.setBounds(30, 90, 50, 50);
		frmXccVisualeditorColor.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(Color.GREEN);
		panel_2.setBounds(30, 150, 50, 50);
		frmXccVisualeditorColor.getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(97, 30, 180, 50);
		frmXccVisualeditorColor.getContentPane().add(panel_3);
		panel_3.setLayout(new CardLayout(0, 0));
		
		redText = new JTextField();
		redText.setEditable(false);
		redText.setText("0");
		redText.setFont(new Font("宋体", Font.PLAIN, 18));
		redText.setBounds(287, 30, 60, 50);
		frmXccVisualeditorColor.getContentPane().add(redText);
		redText.setColumns(10);
		
		blueText = new JTextField();
		blueText.setEditable(false);
		blueText.setText("0");
		blueText.setFont(new Font("宋体", Font.PLAIN, 18));
		blueText.setColumns(10);
		blueText.setBounds(287, 90, 60, 50);
		frmXccVisualeditorColor.getContentPane().add(blueText);
		
		greenText = new JTextField();
		greenText.setEditable(false);
		greenText.setText("0");
		greenText.setFont(new Font("宋体", Font.PLAIN, 18));
		greenText.setColumns(10);
		greenText.setBounds(287, 150, 60, 50);
		frmXccVisualeditorColor.getContentPane().add(greenText);
		
		R = new JScrollBar();
		R.setOrientation(JScrollBar.HORIZONTAL);
		R.setMinimum(0);
		R.setMaximum(265);
		R.addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				redText.setText(String.valueOf(R.getValue()));
				colorRefresh();
			}
		});
		panel_3.add(R, "name_178428861183357");
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(97, 90, 180, 50);
		frmXccVisualeditorColor.getContentPane().add(panel_4);
		panel_4.setLayout(new CardLayout(0, 0));
		
		B = new JScrollBar();
		B.setMinimum(0);
		B.setMaximum(265);
		B.addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				blueText.setText(String.valueOf(B.getValue()));
				colorRefresh();
			}
		});
		panel_4.add(B, "name_178772857119239");
		B.setOrientation(JScrollBar.HORIZONTAL);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(97, 150, 180, 50);
		frmXccVisualeditorColor.getContentPane().add(panel_5);
		panel_5.setLayout(new CardLayout(0, 0));
		
		G = new JScrollBar();
		G.setOrientation(JScrollBar.HORIZONTAL);
		G.setMinimum(0);
		G.setMaximum(265);
		G.addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				greenText.setText(String.valueOf(G.getValue()));
				colorRefresh();
			}
		});
		panel_5.add(G, "name_178584210226391");
		
		Colorpreview = new JPanel();
		Colorpreview.setBackground(Color.GRAY);
		Colorpreview.setBorder(new LineBorder(new Color(0, 0, 0)));
		Colorpreview.setBounds(30, 215, 111, 39);
		frmXccVisualeditorColor.getContentPane().add(Colorpreview);
		
		JButton button = new JButton("提交");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				callback.color=deccolor;
				classTemp.frmXccVisualeditorColor.setVisible(false);
				try {classTemp.finalize();} catch (Throwable e) {}
			}
		});
		button.setBounds(241, 215, 106, 39);
		frmXccVisualeditorColor.getContentPane().add(button);
		
		decshow = new JLabel("0");
		decshow.setBounds(151, 216, 80, 15);
		frmXccVisualeditorColor.getContentPane().add(decshow);
		
		hexshow = new JLabel("0");
		hexshow.setBounds(151, 241, 80, 15);
		frmXccVisualeditorColor.getContentPane().add(hexshow);
	}
	public  String convertRGBToHex(int r, int g, int b) {
		String define="作者：枫叶点旋律 \r\n" + 
				"		     来源：CSDN \r\n" + 
				"		     原文：https://blog.csdn.net/Jason763/article/details/72844092 \r\n" + 
				"		     版权声明：本文为博主原创文章，转载请附上博文链接！";
        String rFString, rSString, gFString, gSString,  
        bFString, bSString, result;
        int red, green, blue;
        int rred, rgreen, rblue;
        red = r / 16;
        rred = r % 16;
        if (red == 10) rFString = "A";
        else if (red == 11) rFString = "B";
        else if (red == 12) rFString = "C";
        else if (red == 13) rFString = "D";
        else if (red == 14) rFString = "E";
        else if (red == 15) rFString = "F";
        else rFString = String.valueOf(red);

        if (rred == 10) rSString = "A";
        else if (rred == 11) rSString = "B";
        else if (rred == 12) rSString = "C";
        else if (rred == 13) rSString = "D";
        else if (rred == 14) rSString = "E";
        else if (rred == 15) rSString = "F";
        else rSString = String.valueOf(rred);

        rFString = rFString + rSString;

        green = g / 16;
        rgreen = g % 16;

        if (green == 10) gFString = "A";
        else if (green == 11) gFString = "B";
        else if (green == 12) gFString = "C";
        else if (green == 13) gFString = "D";
        else if (green == 14) gFString = "E";
        else if (green == 15) gFString = "F";
        else gFString = String.valueOf(green);

        if (rgreen == 10) gSString = "A";
        else if (rgreen == 11) gSString = "B";
        else if (rgreen == 12) gSString = "C";
        else if (rgreen == 13) gSString = "D";
        else if (rgreen == 14) gSString = "E";
        else if (rgreen == 15) gSString = "F";
        else gSString = String.valueOf(rgreen);

        gFString = gFString + gSString;

        blue = b / 16;
        rblue = b % 16;

        if (blue == 10) bFString = "A";
        else if (blue == 11) bFString = "B";
        else if (blue == 12) bFString = "C";
        else if (blue == 13) bFString = "D";
        else if (blue == 14) bFString = "E";
        else if (blue == 15) bFString = "F";
        else bFString = String.valueOf(blue);

        if (rblue == 10) bSString = "A";
        else if (rblue == 11) bSString = "B";
        else if (rblue == 12) bSString = "C";
        else if (rblue == 13) bSString = "D";
        else if (rblue == 14) bSString = "E";
        else if (rblue == 15) bSString = "F";
        else bSString = String.valueOf(rblue);
        bFString = bFString + bSString;
        result = rFString + gFString + bFString;
        return result;
    }
	public int[] convertHexToRGB(String hex) {
		int[] RGB=new int[3];
		String define="作者：cu_rry \r\n" + 
				"来源：CSDN \r\n" + 
				"原文：https://blog.csdn.net/cu_rry/article/details/52662573 \r\n" + 
				"版权声明：本文为博主原创文章，转载请附上博文链接！";
		String str2 = hex.substring(0,2);
		String str3 = hex.substring(2,4);
		String str4 = hex.substring(4,6);
		RGB[0] = Integer.parseInt(str2,16);
		RGB[1] = Integer.parseInt(str3,16);
		RGB[2] = Integer.parseInt(str4,16);

		return RGB;
	}
}
