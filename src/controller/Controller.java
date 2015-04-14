package controller;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


import models.Book;
import models.BookDOM;
import models.User;
import models.UserDOM;
import views.EmployeeGUI;
import views.LogInGUI;

public class Controller {
	  private  LogInGUI logInWindow = new LogInGUI("Log In Panel");
	  private  EmployeeGUI employeeWindow = new EmployeeGUI(logInWindow,false,"Employee Panel");

	  public Controller() {
	  
	    logInWindow.addButtonActionListener(
	      new ActionListener() {
	        public void actionPerformed(ActionEvent evt) 
	        { 
	        	if (evt.getSource()==logInWindow.getButton()){
				String infoText=logInWindow.getMyUsernameText();
	        	String infoPassword=logInWindow.getMyPasswordText();
	        		boolean checkLogIn=logIn(infoText, infoPassword);
	        		
		        	if (checkLogIn){
		        		logInWindow.setVisible(false);
		        		employeeWindow.setVisible(true);
		        		BookDOM bd = new BookDOM();
		        		employeeWindow.setTitle("DRACU!");
		        		
		        		fillMyTable();
		        		
		        	}
	        	}
	        }
	      }
	    );
	    

	  }

		private void  fillMyTable()
		{
			//////////////////////////////////////////////////////
			DefaultTableModel dtm=employeeWindow.getdTableModel();
			//////////////////////////////////////////////////////
			int[] columnsWidth = { 190, 118, 150, 300, 130};
			int ii = 0;
			for (int width : columnsWidth) {
				TableColumn column = employeeWindow.getTable().getColumnModel().getColumn(ii++);
				column.setMinWidth(width);
				column.setMaxWidth(width);
				column.setPreferredWidth(width);
			}
				Object[] row;
				int index=0;
				BookDOM bd = new BookDOM();

				ArrayList<Book> result = bd.getAllBooks();
				Book aBook;
				if(result.size()!=0)
				{
					while (index < result.size()) {
						aBook = result.get(index);
						row = new Object[]{aBook.getISBN(),aBook.getTitle(),aBook.getAuthor(),
								        aBook.getGenre(),aBook.getQuantity(),aBook.getPrice()};
						dtm.addRow(row);
						index++;
					}
				}
				///////////////////////////////////
				employeeWindow.setdTableModel(dtm);
				///////////////////////////////////
		}
		
		
		private void updateTable()
		{
			DefaultTableModel dtm = employeeWindow.getdTableModel();
			int c = dtm.getRowCount();
				for (int i=c-1; i>=0; i--)
				{
					dtm.removeRow(i);
				}
				employeeWindow.setdTableModel(dtm);
				fillMyTable();
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
	    app.logInWindow.setVisible(true); // make visual component appear
	  	}
	}
