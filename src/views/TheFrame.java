package views;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class TheFrame extends JFrame{
	
	 /**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	  private JButton button;// = new JButton("My Button");
	  private JTextField usernameField;
	  private JTextField passwordField;
	  
	  public TheFrame(String title) {
		  super(title);
		  setSize( 500, 500 );	  
		  setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		  setLayout(null);       // set the layout manager
		    
		  button = new JButton("My Button");
		  button.setBounds(100, 100, 150, 40);
		  add(button);
		
		  usernameField = new JTextField("user");
		  usernameField.setBounds(100, 150, 150, 40);
		  add(usernameField);
		  
		  passwordField = new JTextField("pass");
		  passwordField.setBounds(100, 200, 150, 40);
		  add(passwordField);
	 }
	  
	public String getMyUsernameText(){
		return usernameField.getText();
	}
	
	public String getMyPasswordText(){
		return passwordField.getText();
	}
	
	public JButton getButton() {
		return button;
	}

	public void addButtonActionListener(ActionListener listener) {
	    button.addActionListener(listener);
	  }




}
