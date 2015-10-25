package firstProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


@SuppressWarnings("serial")
public class mainDesktop extends javax.swing.JFrame {
	
	public mainDesktop(){
		createGUI();
	}
	
	public static void createGUI() {
		
		JFrame frame = new JFrame("Загрузка котировок");
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Font font = new Font("Verdana", Font.PLAIN, 11);
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setFont(font);
		
		JMenu newMenu = new JMenu("New");
		newMenu.setFont(font);
		fileMenu.add(newMenu);
		
		JMenuItem txtFileItem = new JMenuItem("Text file");
		txtFileItem.setFont(font);
		newMenu.add(txtFileItem);
		
		JMenuItem imgFileItem = new JMenuItem("Image file");
		imgFileItem.setFont(font);
		newMenu.add(imgFileItem);
		
		JMenuItem folderItem = new JMenuItem("Folder");
		folderItem.setFont(font);
		newMenu.add(folderItem);
		
		JMenuItem openItem = new JMenuItem("Open");
		openItem.setFont(font);
		fileMenu.add(openItem);
		
		JMenuItem closeItem = new JMenuItem("Close");
		closeItem.setFont(font);
		fileMenu.add(closeItem);
		
		JMenuItem closeAllItem = new JMenuItem("Close all");
		closeAllItem.setFont(font);
		fileMenu.add(closeAllItem);
		
		fileMenu.addSeparator();
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setFont(font);
		fileMenu.add(exitItem);
		
		exitItem.addActionListener(new ActionListener() {           
			public void actionPerformed(ActionEvent e) {
				System.exit(0);             
			}           
		});
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		frame.setPreferredSize(new Dimension(270, 225));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
