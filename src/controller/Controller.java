package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import models.User;
import models.UserDOM;
import views.TheFrame;

public class Controller {
	  private final TheFrame frame = new TheFrame("The Frame");

	  public Controller() {
	  
	    frame.addButtonActionListener(
	      new ActionListener() {
	        public void actionPerformed(ActionEvent evt) 
	        { 
	        	if (evt.getSource()==frame.getButton()){
				String infoText=frame.getMyUsernameText();
	        	String infoPassword=frame.getMyPasswordText();
	        		logIn(infoText, infoPassword);
	        	}
	        }
	      }
	    );

	  }

	  public boolean logIn(String username, String password){
		  boolean logInOK=false;
		  UserDOM uDOM = new UserDOM();
		  ArrayList<User> allUsers = uDOM.getAllUsers();
		  	for (int i = 0; i < allUsers.size(); i++) {
		  		User aUser = allUsers.get(i);//we retrieved a User from list of all
		  		if(aUser.getUsername().equals(username)){
		  			if(aUser.getPassword().equals(password)){
		  				logInOK=true;
		  			}else logInOK=false;
		  		}
		  	}
		  	System.out.println(logInOK);
	  return logInOK;
	  }
	  
	  public static void main(String[] args) { 
	    Controller app = new Controller(); 
	    app.frame.setVisible(true); // make visual component appear
	  	}
	}
