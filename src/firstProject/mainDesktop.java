/*
 * Класс создает GUI главной формы.
 * Тут же происходит назначение элементом меню функциональности
 */
package firstProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


@SuppressWarnings("serial")
public class mainDesktop extends javax.swing.JFrame {
	
	public JFrame frame = new JFrame("Загрузка котировок");
	
	public mainDesktop(){
		createGUI();
	}
	public void addEvent(JMenuItem menu, Event ev){
		menu.addActionListener(new ActionListener() {           
			public void actionPerformed(ActionEvent e) {
				ev.events();             
			}           
		});
	}
	public JMenu StructureMenu(Font font){//меню генерации структуры
		JMenu StructureMenu = new JMenu("Generate");
		StructureMenu.setFont(font);
		
		JMenuItem newMenu = new JMenuItem("Download structure");
		newMenu.setFont(font);
		StructureMenu.add(newMenu);		
		
		JMenuItem openItem = new JMenuItem("Delete structure");
		openItem.setFont(font);
		StructureMenu.add(openItem);
				
		StructureMenu.addSeparator();
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setFont(font);
		StructureMenu.add(exitItem);
		addEvent(exitItem,new Event(){ 
			public void events() {
				System.exit(0);
			}
		});
		
		return StructureMenu;
	}
	
	public JMenu DownloadMenu(Font font){//меню загрузки
		JMenu DownloadMenu = new JMenu("Download");
		DownloadMenu.setFont(font);
		
		JMenuItem loadMenu = new JMenuItem("Download");
		loadMenu.setFont(font);
		DownloadMenu.add(loadMenu);		
		
		JMenuItem reloadItem = new JMenuItem("Reload");
		reloadItem.setFont(font);
		DownloadMenu.add(reloadItem);
				
		
		JMenuItem verificationItem = new JMenuItem("Verification");
		verificationItem.setFont(font);
		DownloadMenu.add(verificationItem);
		
		return DownloadMenu;
	}
	
	public JMenu PreferencesMenu(Font font){//меню настроек
		JMenu PreferencesMenu = new JMenu("Preferences");
		PreferencesMenu.setFont(font);	
		return PreferencesMenu;
	}
	
	public void createGUI() {//Создаем меню с помощбю вспомогательных методов.
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font font = new Font("Verdana", Font.PLAIN, 11);
		
		JMenuBar menuBar = new JMenuBar();
		ArrayList<JMenu> menu = new ArrayList<JMenu>();
		
		menu.add(StructureMenu(font));
		menu.add(DownloadMenu(font));
		menu.add(PreferencesMenu(font));
		
		for(JMenu m:menu)
			menuBar.add(m);
		
		frame.setJMenuBar(menuBar);
		frame.setPreferredSize(new Dimension(270, 225));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
