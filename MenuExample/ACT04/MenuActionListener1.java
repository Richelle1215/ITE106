import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuActionListener1 implements ActionListener{
	public void actionPerformed(ActionEvent e){
		JOptionPane.showEventMessageDialog(MenuExample.frame,
			"Got an ActionEvent at " + new Date (e.getWhen()) + " from" + e.getSource().getClass());
	}
}
