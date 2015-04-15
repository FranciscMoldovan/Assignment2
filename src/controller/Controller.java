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
							try{
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
									if (checkISBN(fieldISBN.getText()))
									{
										   String newISBN = fieldISBN.getText();
										   String newTitle = fieldTitle.getText();
										   String newAuthor = fieldAuthor.getText();
										   String newGenre = fieldGenre.getText();
										   int newQuantity = Integer.valueOf(fieldQuantity.getText());
										   double newPrice = Double.valueOf(fieldPrice.getText());
										   
										BookDOM bd = new BookDOM();
										Book newBook = new Book(newISBN,newTitle,newAuthor,newGenre,newQuantity,newPrice);
										bd.addBook(newBook);
											updateTable();
											//default title and icon
											JOptionPane.showMessageDialog(employeeWindow,"Successfully added book:"+newTitle+" by "+newAuthor+"!");
									} else{
										JOptionPane.showMessageDialog(employeeWindow,"ISBN Taken!","ISBN TAKEN!",JOptionPane.ERROR_MESSAGE);
									}
									}
									else{
										//custom title, error icon
										JOptionPane.showMessageDialog(employeeWindow,"Please leave no field empty!","ERROR!",JOptionPane.ERROR_MESSAGE);
									}
								}
							}catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(employeeWindow,"Please input integer quantity and double price!","PLEASE INPUT NUMERS!",JOptionPane.ERROR_MESSAGE);
							}
						}
						
						
						if (evt.getSource()==employeeWindow.getBtnUpdateBook())
						{
							if (employeeWindow.getTable().getSelectedRow()!=-1)
							{
							try{
								  
								   JTextField fieldTitle = new JTextField((String)employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),1));
								   JTextField fieldAuthor = new JTextField((String)employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),2));
								   JTextField fieldGenre= new JTextField((String)employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),3));
								   JTextField fieldQuantity = new JTextField(String.valueOf(employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),4)));
								   JTextField fieldPrice = new JTextField(String.valueOf(employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),5)));
								   
								  
								 Object[] message = {"updated Book Title:", fieldTitle, "updated Book Author:", fieldAuthor,
										 			"updated Book Genre:", fieldGenre, "updated Book Quantity:", fieldQuantity, "updated Book Price", fieldPrice};

									Object[] options = { "UPDATE!", "Cancel" };
									int n = JOptionPane.showOptionDialog(new JFrame(), message,"Update Book Info", JOptionPane.YES_NO_OPTION,
											JOptionPane.QUESTION_MESSAGE, null, options,options[1]);
									if (n == JOptionPane.OK_OPTION) 
									{ 
										if (fieldTitle.getText().trim().length()>0 && fieldAuthor.getText().trim().length()>0 &&
												fieldGenre.getText().trim().length()>0 && fieldQuantity.getText().trim().length()>0 && fieldPrice.getText().trim().length()>0){
											
											 
											   String updTitle = fieldTitle.getText();
											   String updAuthor = fieldAuthor.getText();
											   String updGenre = fieldGenre.getText();
											   int updQuantity = Integer.valueOf(fieldQuantity.getText());
											   double updPrice = Double.valueOf(fieldPrice.getText());
										
														BookDOM bd = new BookDOM();
															Book newBook = new Book((String)employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),0),updTitle,updAuthor,updGenre,updQuantity,updPrice);
															Book oldBook = new Book((String)employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),0),
																					(String)employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),1),
																					(String)employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),2), 
																					(String)employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),3), 
																					((Integer)employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),4)),
																					((Double)employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),5)));
															bd.updateBook(oldBook, newBook);
															updateTable();
															//default title and icon
															JOptionPane.showMessageDialog(employeeWindow,"Successfully updated book:"+updTitle+" by "+updAuthor+"!");
									
										}
									   
									      else{
														//custom title, error icon
														JOptionPane.showMessageDialog(employeeWindow,"Please leave no field empty!","ERROR!",JOptionPane.ERROR_MESSAGE);
													}
									}
									
								}catch (NumberFormatException e) {
									JOptionPane.showMessageDialog(employeeWindow,"Please input integer quantity and double price!","PLEASE INPUT NUMERS!",JOptionPane.ERROR_MESSAGE);
								}
							}
						}
						
						
						if (evt.getSource()==employeeWindow.getBtnDeleteBook())
						{
				
							if(employeeWindow.getTable().getSelectedRow()!=-1){
								   String toDelISBN = ((String)employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),0));
								   String toDelTitle = ((String)employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),1));
								   String toDelAuthor = ((String)employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),2));
								   String toDelGenre= ((String)employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),3));
								   int toDelQuantity = ((Integer)(employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),4)));
								   double toDelPrice = ((Double)(employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),5)));
								   
								   Book aBook = new Book(toDelISBN, toDelTitle, toDelAuthor, toDelGenre, toDelQuantity, toDelPrice);
								   BookDOM bd = new BookDOM();
								   
									int n = JOptionPane.showConfirmDialog(employeeWindow,
										    "Are you sure you want to remove Book: "+aBook.toString()+" ??",
										    "Please confirm deletion!",
										    JOptionPane.YES_NO_OPTION);	
									if (n==JOptionPane.YES_OPTION)
									{
									   bd.removeBook(aBook);
									   updateTable();
									   
									}
							}
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
			int[] columnsWidth = { 100, 400, 200, 150, 50,80};
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

	  public boolean checkISBN(String ISBN){
		  boolean okISBN = true;
		  
		  BookDOM bd = new BookDOM();
		  ArrayList<Book> checkMyBooks=bd.getAllBooks();
		  int iter=0;
			  while(iter<checkMyBooks.size()){
				  Book aBook = checkMyBooks.get(iter);
				 if (ISBN.equals(aBook.getISBN())){
					 okISBN=false;
					 break;
				 }
				 iter++;
			  }
		  return okISBN;
	  }
	  
	  public static void main(String[] args) { 
	    Controller app = new Controller(); 
	    app.logInWindow.setVisible(true); // make visual component appear
	  	}
	}