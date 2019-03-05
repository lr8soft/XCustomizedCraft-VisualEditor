package XCBVisualEditor;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import XCBVisualEditor.XCBVisualEditor.XCBConfigLoad;
import XCBVisualEditor.XCBVisualEditor.XCBVisualEditorMain;

public class XCustomizedMainLoader {
	public static XCBConfigLoad configload;
	public static XCBVisualEditorMain vemain;
	public final static String XCCVEVersion="v1.48";
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		configload = new XCBConfigLoad();
		configload.frmXcustomizedbladeVisualeditor.setVisible(true);
	}
}