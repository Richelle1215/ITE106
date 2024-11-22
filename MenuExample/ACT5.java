import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuExample {
    public static void main(String[] args) {
        final JFrame frame = new JFrame("MyFrame"); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem("New"));
        fileMenu.add(new JMenuItem("Open"));

        JMenuItem closeItem = new JMenuItem("Close");
        closeItem.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showOptionDialog(frame,  
                        "Close the application?", "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (n == JOptionPane.YES_OPTION) {
                    frame.dispose();
                }
            }
        });
        fileMenu.add(closeItem);


        JMenu editMenu = new JMenu("Edit");
        editMenu.add(new JMenuItem("Undo"));
        editMenu.add(new JMenuItem("Redo"));
        editMenu.add(new JMenuItem("Cut"));

        JMenuBar menubar = new JMenuBar();
        menubar.add(fileMenu);
        menubar.add(editMenu);

        frame.setJMenuBar(menubar);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
