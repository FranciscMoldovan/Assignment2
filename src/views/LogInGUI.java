package views;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class LogInGUI extends JFrame{
	
	 /**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	  private JLabel lblBookStoreLogin;
	  private JButton button;// = new JButton("My Button");
	  private JTextField usernameField;
	  private JTextField passwordField;
	  private JLabel lblUsername;
	  private JLabel lblPassword;
	  
	  public LogInGUI(String title) {
		  super(title);
		  setSize( 500, 500 );	  
		  setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		  getContentPane().setLayout(null);       // set the layout manager
		    
		  button = new JButton("Log In");
		  button.setFont(new Font("Trajan Pro", Font.PLAIN, 18));
		  button.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  	}
		  });
		  button.setBounds(239, 186, 150, 40);
		  getContentPane().add(button);
		
		  usernameField = new JTextField("user");
		  usernameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		  usernameField.setBounds(189, 67, 219, 40);
		  getContentPane().add(usernameField);
		  
		  passwordField = new JTextField("pass");
		  passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		  passwordField.setBounds(189, 123, 219, 40);
		  getContentPane().add(passwordField);
		  
		  lblBookStoreLogin = new JLabel("Book Store LogIn Panel");
		  lblBookStoreLogin.setFont(new Font("BatangChe", Font.BOLD, 27));
		  lblBookStoreLogin.setBounds(33, 11, 356, 45);
		  getContentPane().add(lblBookStoreLogin);
		  
		  lblUsername = new JLabel("Username:");
		  lblUsername.setFont(new Font("DejaVu Sans Light", Font.PLAIN, 20));
		  lblUsername.setBounds(47, 75, 132, 24);
		  getContentPane().add(lblUsername);
		  
		  lblPassword = new JLabel("Password:");
		  lblPassword.setFont(new Font("DejaVu Sans Light", Font.PLAIN, 20));
		  lblPassword.setBounds(47, 128, 118, 30);
		  getContentPane().add(lblPassword);
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
