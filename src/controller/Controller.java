package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
	  
		///Action Listener for LogIn Panel///////////////////////////
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
	    ////////////////////////////////////////////////////////////
	    
	    
	    ///Action Listener for Employee Panel/////////////////////////////////
	    employeeWindow.addButtonActionListener(
	    		new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if (evt.getSource()==employeeWindow.getBtnAddNewBook())
						{
							
							   JTextField fieldISBN = new JTextField();
							   JTextField fieldTitle = new JTextField();
							   JTextField fieldAuthor = new JTextField();
							   JTextField fieldGenre= new JTextField();
							   JTextField fieldQuantity = new JTextField();
							   JTextField fieldPrice = new JTextField();
							   
							  
							 Object[] message = { "new Book ISBN:", fieldISBN, "new Book Title:", fieldTitle, "new Book Author:", fieldAuthor,
									 			"new Book Genre:", fieldGenre, "new Book Quantity:", fieldQuantity, "new Book Price", fieldPrice};

								Object[] options = { "ADD!", "Cancel" };
								int n = JOptionPane.showOptionDialog(new JFrame(), message,"Add New Book", JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE, null, options,options[1]);
								if (n == JOptionPane.OK_OPTION) 
								{ 
									if (fieldISBN.getText().trim().length()>0 && fieldTitle.getText().trim().length()>0 && fieldAuthor.getText().trim().length()>0 &&
											fieldGenre.getText().trim().length()>0 && fieldQuantity.getText().trim().length()>0 && fieldPrice.getText().trim().length()>0){
										
											
										   String newISBN = fieldISBN.getText();
										   String newTitle = fieldTitle.getText();
										   String newAuthor = fieldAuthor.getText();
										   String newGenre = fieldGenre.getText();
										   int newQuantity = Integer.valueOf(fieldQuantity.getText());
										   double newPrice = Double.valueOf(fieldPrice.getText());
										   
										
										BookDOM bd = new BookDOM();
										Book newBook = new Book(newISBN,newTitle,newAuthor,newGenre,newQuantity,newPrice);
										bd.addBook(newBook);
									}
								}
						}
						
						if (evt.getSource()==employeeWindow.getBtnUpdateBook())
						{
							System.out.println("UPDATE_BTN");
						}
						if (evt.getSource()==employeeWindow.getBtnDeleteBook())
						{
							System.out.println("DELETE_BTN");
						}
					}
				});
	    ////////////////////////////////////////////////////////////////////////
	    
	    

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
