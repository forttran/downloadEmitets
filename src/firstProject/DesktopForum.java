package firstProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class DesktopForum {
	public JFrame frame;
	
	public DesktopForum(){
		//http://forum.mfd.ru/forum/poster/posts/?id=72262
				//http://forum.mfd.ru/forum/poster/posts/?id=73591
				//http://forum.mfd.ru/forum/poster/posts/?id=73555
				
				String http ="http://forum.mfd.ru/forum/poster/posts/?id=72262";
				String s;
				String TITLE = "Форум";


				JFrame frame = new JFrame();
				frame.setVisible(true);
				frame.setTitle(TITLE);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
				JPanel mainPanel = new JPanel();
		        mainPanel.setLayout(new BorderLayout());
		         
		        JPanel alignmentPanel = new JPanel(new FlowLayout());
		        alignmentPanel.setBorder(BorderFactory.createTitledBorder("Последние добавленные"));
		        
		        DateInsert date = new DateInsert(http);
				s = date.Insert();
				String[] res = date.RegSplit(s,"<div class='mfd-post-body-right'>");
				JLabel centerLabel[]=new JLabel[res.length];
				alignmentPanel.setLayout(new GridLayout(res.length,1));
				for(int i = 0; i <res.length; i++){
					centerLabel[i] = new JLabel(date.RegSearch(res[i]));
			        centerLabel[i].setVerticalAlignment(JLabel.CENTER);
			        centerLabel[i].setHorizontalAlignment(JLabel.CENTER);
			        centerLabel[i].setBorder(solidBorder);
			        alignmentPanel.add(centerLabel[i]);
					System.out.println(date.RegSearch(res[i]));
				}
				
				/*
		        centerLabel[1] = new JLabel("gfgfdgfdgf");
		        centerLabel[1].setVerticalAlignment(JLabel.CENTER);
		        centerLabel[1].setHorizontalAlignment(JLabel.CENTER);
		        alignmentPanel.add(centerLabel[1]);
		      */   
		        mainPanel.add(alignmentPanel, BorderLayout.NORTH);

		        frame.getContentPane().add(mainPanel);
		        frame.setPreferredSize(new Dimension(450, 485));
		        frame.pack();
		        frame.setLocationRelativeTo(null);
		        frame.setVisible(true);
	}
}
