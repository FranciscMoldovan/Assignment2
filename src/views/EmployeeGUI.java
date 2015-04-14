package views;

import javax.swing.JDialog;

public class EmployeeGUI extends JDialog{

	public EmployeeGUI(java.awt.Frame parent, boolean modal, String title){
		super(parent, modal);
		setTitle(title);
		
		setSize(800, 500);
	}
}
