import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuExample1 {
	static JFrame frame;
	
	public static void main(String[]args){
		JFrame frame = new JFrame ("MyFrame");
		frame.setDefaultCloseOperation(
			JFrame.EXIT_ON_CLOSE);
			
		JMenuBar menuBar = new JMenuBar();
		JMenuBar fileMenu =new JMenu("File");
		JMenuItem newItem = new JMenuItem("New");
			
		JMenu fileMenu = new JMenu ("File");
		fileMenu.add(new JMenuItem ("New"));
		fileMenu.add(new JMenuItem ("Open"));
		fileMenu.add(new JMenuItem ("Close"));		
		item.addAcitonListener(new MenuActionListener());
		fileMenu.add(item);
		
		JMenu editMenu = new JMenu ("Edit");
		editMenu.add(new JMenuItem ("Undo"));
		editMenu.add(new JMenuItem ("Redo"));
		editMenu.add(new JMenuItem ("Cut"));
		
		JMenuBar menubar = new JMenuBar();
		menubar.add(fileMenu);
		menubar.add(editMenu);
		
		frame.setJMenuBar(menubar);
		frame.setVisible(true);
	
	}
}
