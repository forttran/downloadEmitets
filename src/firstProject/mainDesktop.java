/*
 * Класс создает GUI главной формы.
 * Тут же происходит назначение элементом меню функциональности
 */
package firstProject;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


@SuppressWarnings("serial")
public class mainDesktop extends javax.swing.JFrame {
	
	private static mainDesktop instance;
	
	public JFrame frame = new JFrame("Загрузка котировок");
	public JPanel JP;
	public JPanel JP1;
	public JPanel JP2;
	public JProgressBar JPr;
	public ArrayList<JMenu> menu = new ArrayList<JMenu>();
	
	public static mainDesktop  getInstance() {
		if (instance == null) {
			instance = new  mainDesktop();
		}
		return instance;
	}
	
	private mainDesktop(){
		createGUI();
	}
		
	public JMenuItem addMenuItem(JMenu Menu, String name, Font font, boolean enable, Event ev){//создание элемента подменю и события.
		JMenuItem newMenu = new JMenuItem(name);
		newMenu.setEnabled(enable);
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
		
		addMenuItem(StructureMenu,"Download structure", font, !сreateStructureEmitets.isCreate(), new Event(){ 
			public void events() {
				menu.get(0).getItem(0).setEnabled(false);
				menu.get(0).getItem(1).setEnabled(true);
				menu.get(1).getItem(0).setEnabled(true);
				new сreateStructureEmitets();
			}
		});	
		
		addMenuItem(StructureMenu,"Delete structure", font, сreateStructureEmitets.isCreate(), new Event(){ 
			public void events() {
				menu.get(0).getItem(0).setEnabled(true);
				menu.get(0).getItem(1).setEnabled(false);
				menu.get(1).getItem(0).setEnabled(false);
				new DeleteStructureEmitets();
			}
		});	
		
		addMenuItem(StructureMenu,"Exit", font, true, new Event(){ 
			public void events() {
				Close();
			}
		});	
		
		return StructureMenu;
	}
	
	public void CreateJListEmitets() {//Cоздание списка эмитетов
		JP = new JPanel(new GridLayout(2,0));
		JP1 = new JPanel(new FlowLayout());
		JP2 = new JPanel(new GridLayout(1,0));
		JPr = new JProgressBar();
		JPr.setMinimum(0);
		JP1.add(JPr);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		JList<String> JL = new JList<String>(model);
		JScrollPane JS = new JScrollPane(JL);
		try {
			ArrayList<emitets> AL = new listEmitets().getEmitets();
			for(emitets em: AL){
				model.addElement(em.codes);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		JL.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Object element = JL.getSelectedValue();
				JOptionPane.showMessageDialog(frame, element);
			}
		});
		
		JP.add(JP1);
		JP.add(JP2);
		JP2.add(JS);
		frame.getContentPane().add(JP);
		frame.repaint();
		frame.revalidate();    
	}
		
	public JMenu DownloadMenu(Font font){//меню загрузки
		JMenu DownloadMenu = new JMenu("Download");
		DownloadMenu.setFont(font);
		addMenuItem(DownloadMenu,"Download", font, false, new Event(){ 
			public void events() {
				menu.get(1).getItem(0).setEnabled(false);
				Thread loadEmitets = new Thread(new ThreadDownloadEmitets());
				controlThread cTh = controlThread.getInstance();
				cTh.getMap("loadEmitets", loadEmitets);
				cTh.setMap("loadEmitets").start();
			}
		});	
		
		addMenuItem(DownloadMenu,"Reload", font, false, new Event(){ 
			public void events() {
				System.exit(0);
			}
		});	
		
		addMenuItem(DownloadMenu,"Verification", font, false, new Event(){ 
			public void events() {
				System.exit(0);
			}
		});	
		
		return DownloadMenu;
	}
	
	public JMenu PreferencesMenu(Font font){//меню настроек
		JMenu PreferencesMenu = new JMenu("Preferences");
		PreferencesMenu.setFont(font);	
		
		addMenuItem(PreferencesMenu,"Preferences", font, false, new Event(){ 
			public void events() {
				System.exit(0);
			}
		});	
		
		return PreferencesMenu;
	}
	
	public void createGUI() {//Создаем меню с помощбю вспомогательных методов.
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		Font font = new Font("Verdana", Font.PLAIN, 11);
		
		JMenuBar menuBar = new JMenuBar();
		
		menu.add(StructureMenu(font));
		menu.add(DownloadMenu(font));
		menu.add(PreferencesMenu(font));
		
		for(JMenu m:menu)
			menuBar.add(m);
		menu.get(1).getItem(0).setEnabled(сreateStructureEmitets.isCreate());
		frame.setJMenuBar(menuBar);
		frame.setPreferredSize(new Dimension(270, 225));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent event) {
				Close();
			}
		});
	}
	
	public void Close(){
		Thread cTh = controlThread.getInstance().setMap("loadEmitets");
		if(cTh == null){
			System.exit(0);
		}else{
			Object[] options = { "Да", "Нет!" };
			int n = JOptionPane.showOptionDialog(
					null, 
					"Идет скачивание котировок. Остановить процесс?",
					"Подтверждение", 
					JOptionPane.YES_NO_OPTION, 
					JOptionPane.QUESTION_MESSAGE, 
					null, options, options[0]);
			if(n==0){
				cTh.interrupt();
				menu.get(1).getItem(0).setEnabled(true);
			}
		}
	}
	
}
