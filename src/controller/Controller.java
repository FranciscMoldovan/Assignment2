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
import views.ManagerGUI;
import views.LogInGUI;

public class Controller {
	  private  LogInGUI logInWindow = new LogInGUI("Log In Panel");
	  private  ManagerGUI managerWindow = new ManagerGUI(logInWindow,false,"Employee Panel");

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
		        		managerWindow.setVisible(true);
		        		managerWindow.setTitle("MANAGER PANEL!");
		        		
		        		fillMyTable();
		        	
		        	}
	        	}
	        }
	      }
	    );
	    ////////////////////////////////////////////////////////////
	    
	    
	    ///Action Listener for Employee Panel/////////////////////////////////
	    managerWindow.addButtonActionListener(
	    		new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if (evt.getSource()==managerWindow.getBtnAddNewBook())
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
											JOptionPane.showMessageDialog(managerWindow,"Successfully added book:"+newTitle+" by "+newAuthor+"!");
									} else{
										JOptionPane.showMessageDialog(managerWindow,"ISBN Taken!","ISBN TAKEN!",JOptionPane.ERROR_MESSAGE);
									}
									}
									else{
										//custom title, error icon
										JOptionPane.showMessageDialog(managerWindow,"Please leave no field empty!","ERROR!",JOptionPane.ERROR_MESSAGE);
									}
								}
							}catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(managerWindow,"Please input integer quantity and double price!","PLEASE INPUT NUMERS!",JOptionPane.ERROR_MESSAGE);
							}
						}
						
						
						if (evt.getSource()==managerWindow.getBtnUpdateBook())
						{
							if (managerWindow.getTable().getSelectedRow()!=-1)
							{
							try{
								  
								   JTextField fieldTitle = new JTextField((String)managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),1));
								   JTextField fieldAuthor = new JTextField((String)managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),2));
								   JTextField fieldGenre= new JTextField((String)managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),3));
								   JTextField fieldQuantity = new JTextField(String.valueOf(managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),4)));
								   JTextField fieldPrice = new JTextField(String.valueOf(managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),5)));
								   
								  
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
															Book newBook = new Book((String)managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),0),updTitle,updAuthor,updGenre,updQuantity,updPrice);
															Book oldBook = new Book((String)managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),0),
																					(String)managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),1),
																					(String)managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),2), 
																					(String)managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),3), 
																					((Integer)managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),4)),
																					((Double)managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),5)));
															bd.updateBook(oldBook, newBook);
															updateTable();
															//default title and icon
															JOptionPane.showMessageDialog(managerWindow,"Successfully updated book:"+updTitle+" by "+updAuthor+"!");
									
										}
									   
									      else{
														//custom title, error icon
														JOptionPane.showMessageDialog(managerWindow,"Please leave no field empty!","ERROR!",JOptionPane.ERROR_MESSAGE);
													}
									}
									
								}catch (NumberFormatException e) {
									JOptionPane.showMessageDialog(managerWindow,"Please input integer quantity and double price!","PLEASE INPUT NUMERS!",JOptionPane.ERROR_MESSAGE);
								}
							}
						}
						
						
						if (evt.getSource()==managerWindow.getBtnDeleteBook())
						{
				
							if(managerWindow.getTable().getSelectedRow()!=-1){
								   String toDelISBN = ((String)managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),0));
								   String toDelTitle = ((String)managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),1));
								   String toDelAuthor = ((String)managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),2));
								   String toDelGenre= ((String)managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),3));
								   int toDelQuantity = ((Integer)(managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),4)));
								   double toDelPrice = ((Double)(managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),5)));
								   
								   Book aBook = new Book(toDelISBN, toDelTitle, toDelAuthor, toDelGenre, toDelQuantity, toDelPrice);
								   BookDOM bd = new BookDOM();
								   
									int n = JOptionPane.showConfirmDialog(managerWindow,
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
						
						
						if (evt.getSource()==managerWindow.getBtnSellBook())
						{
							try{
							if(managerWindow.getTable().getSelectedRow()!=-1){
								String toSellISBN = ((String)managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),0));
								   String toSellTitle = ((String)managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),1));
								   String toSellAuthor = ((String)managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),2));
								   String toSellGenre= ((String)managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),3));
								   int toSellQuantity = ((Integer)(managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),4)));
								   double toSellPrice = ((Double)(managerWindow.getTable().getValueAt(managerWindow.getTable().getSelectedRow(),5)));
								   
								   Book aBook = new Book(toSellISBN, toSellTitle, toSellAuthor, toSellGenre, toSellQuantity, toSellPrice);
								   
								     JTextField numerToSell=new JTextField();
									 Object[] message = {"Number of books to sell:",numerToSell};

								Object[] options = { "SELL!", "Cancel" };
								int n = JOptionPane.showOptionDialog(new JFrame(), message,"Sell Book", JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE, null, options,options[1]);
								if (n == JOptionPane.OK_OPTION)
								{
									if(aBook.getQuantity()!=0){
										int number = Integer.valueOf(numerToSell.getText());
										BookDOM bd = new BookDOM();
										if (number<=aBook.getQuantity())
										{//eough books to sell at once
											Book newBook = new Book(aBook.getISBN(), aBook.getTitle(), aBook.getAuthor(), 
													aBook.getGenre(), aBook.getQuantity(), aBook.getPrice());
											newBook.setQuantity(newBook.getQuantity()-number);
										    	bd.updateBook(aBook, newBook);
										    	updateTable();
										    	JOptionPane.showMessageDialog(managerWindow,"Successfully Sold "+number+" pieces of book: "+aBook.getTitle()+" by "+
										    	aBook.getAuthor()+" Total SUM: "+number*aBook.getPrice());
										    	
										}else
										{//couldn't sell enough books
											int actuallySold = aBook.getQuantity();
											Book newBook = new Book(aBook.getISBN(), aBook.getTitle(), aBook.getAuthor(), 
													aBook.getGenre(), aBook.getQuantity(), aBook.getPrice());
											newBook.setQuantity(0);
										    	bd.updateBook(aBook, newBook);
										    	updateTable();
										    	JOptionPane.showMessageDialog(managerWindow,"Only "+actuallySold+" books out of "+number+" sold!! TOTAL SUM:"+aBook.getPrice()*actuallySold,"NOT ENOUGH BOOKS SOLD!",JOptionPane.ERROR_MESSAGE);
										}
									}else{
										JOptionPane.showMessageDialog(managerWindow,"Sorry, book is Completelely OUT OF STOCK!","BOOKS IS OUT OF STOCK!",JOptionPane.ERROR_MESSAGE);
											}
								}
							}
						}
							catch(NumberFormatException e){
								JOptionPane.showMessageDialog(managerWindow,"Please type number, not letters!","INPUT ERROR!",JOptionPane.ERROR_MESSAGE);
								}
					}
//						
						
					}
				});
	    
	    
	    
	    ////////////////////////////////////////////////////////////////////////
	    
	    

	  }

		private void  fillMyTable()
		{
			//////////////////////////////////////////////////////
			DefaultTableModel dtm=managerWindow.getdTableModel();
			//////////////////////////////////////////////////////
			int[] columnsWidth = { 100, 400, 200, 150, 50,80};
			int ii = 0;
			for (int width : columnsWidth) {
				TableColumn column = managerWindow.getTable().getColumnModel().getColumn(ii++);
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
				managerWindow.setdTableModel(dtm);
				///////////////////////////////////
		}
		
		
		private void updateTable()
		{
			DefaultTableModel dtm = managerWindow.getdTableModel();
			int c = dtm.getRowCount();
				for (int i=c-1; i>=0; i--)
				{
					dtm.removeRow(i);
				}
				managerWindow.setdTableModel(dtm);
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