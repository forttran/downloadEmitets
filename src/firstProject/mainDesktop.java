/*
 * Класс создает GUI главной формы.
 * Тут же происходит назначение элементом меню функциональности
 */
package firstProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;


@SuppressWarnings("serial")
public class mainDesktop extends javax.swing.JFrame {
	
	public JFrame frame = new JFrame("Загрузка котировок");
	
	public mainDesktop(){
		createGUI();
	}

	public JMenuItem addMenuItem(JMenu Menu, String name, Font font, Event ev){//создание элемента подменю и события.
		JMenuItem newMenu = new JMenuItem(name);
		newMenu.setFont(font);
		Menu.add(newMenu);
		newMenu.addActionListener(new ActionListener() {           
			public void actionPerformed(ActionEvent e) {
				ev.events();             
			}           
		});
		return newMenu;
	}
	public JMenu StructureMenu(Font font){//меню генерации структуры
		JMenu StructureMenu = new JMenu("Generate");
		StructureMenu.setFont(font);
		
		addMenuItem(StructureMenu,"Download structure", font, new Event(){ 
			public void events() {
				try {
					new сreateStructureEmitets();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});	
		
		addMenuItem(StructureMenu,"Delete structure", font, new Event(){ 
			public void events() {
				try {
					new DeleteStructureEmitets();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});	
		
		addMenuItem(StructureMenu,"Exit", font, new Event(){ 
			public void events() {
				System.exit(0);
			}
		});	
		
		return StructureMenu;
	}
	
	public JMenu DownloadMenu(Font font){//меню загрузки
		JMenu DownloadMenu = new JMenu("Download");
		DownloadMenu.setFont(font);
		
		addMenuItem(DownloadMenu,"Download", font, new Event(){ 
			public void events() {
				System.exit(0);
			}
		});	
		
		addMenuItem(DownloadMenu,"Reload", font, new Event(){ 
			public void events() {
				System.exit(0);
			}
		});	
		
		addMenuItem(DownloadMenu,"Verification", font, new Event(){ 
			public void events() {
				System.exit(0);
			}
		});	
		
		return DownloadMenu;
	}
	
	public JMenu PreferencesMenu(Font font){//меню настроек
		JMenu PreferencesMenu = new JMenu("Preferences");
		PreferencesMenu.setFont(font);	
		
		addMenuItem(PreferencesMenu,"Preferences", font, new Event(){ 
			public void events() {
				System.exit(0);
			}
		});	
		
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
