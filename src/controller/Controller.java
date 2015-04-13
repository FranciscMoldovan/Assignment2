package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.TheFrame;

public class Controller {
	  private final TheFrame frame = new TheFrame("The Frame");

	  public Controller() {
	  
	    frame.addButtonActionListener(
	      new ActionListener() {
	        public void actionPerformed(ActionEvent evt) 
	        { 
				String infoText=frame.getMyUsernameText();
	        	String infoPassword=frame.getMyPasswordText();
	        }
	      }
	    );

	  }

	  public static void main(String[] args) { 
	    Controller app = new Controller(); 
	    app.frame.setVisible(true); // make visual component appear
	  }
	}
