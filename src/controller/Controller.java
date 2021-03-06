package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import models.Book;
import models.BookDOM;
import models.User;
import models.UserDOM;
import views.EmployeeGUI;
import views.ManagerGUI;
import views.LogInGUI;

public class Controller {
	  private  LogInGUI logInWindow = new LogInGUI("Log In Panel");
	  private  ManagerGUI managerWindow = new ManagerGUI(logInWindow,false,"Manager Panel");
	  private  EmployeeGUI employeeWindow = new EmployeeGUI(logInWindow,false,"Employee Panel");
	  public Controller() {
		  employeeWindow.setResizable(false);
		  managerWindow.setResizable(false);
		  logInWindow.setResizable(false);
		  employeeWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		  managerWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		  
		///Action Listener for LogIn Panel///////////////////////////
	    logInWindow.addButtonActionListener(
	      new ActionListener() {
	        public void actionPerformed(ActionEvent evt) 
	        { 
	        	if (evt.getSource()==logInWindow.getButton()){
				String infoText=logInWindow.getMyUsernameText();
	        	String infoPassword=logInWindow.getMyPasswordText();
	        		String checkLogIn=logIn(infoText, infoPassword);
	        		
		        	if (checkLogIn.equals("OKmanager")){
		        		logInWindow.setVisible(false);
		        		managerWindow.setVisible(true);
		        		managerWindow.setTitle("MANAGER PANEL!");
		        		updateManagerTable();
		        		updateManagerTable1();
		        	}
		        	
		        	if (checkLogIn.equals("OKdesk")){
		        		logInWindow.setVisible(false);
		        		employeeWindow.setVisible(true);
		        		employeeWindow.setTitle("EMPLOYEE PANEL!");
		        		BookDOM bd = new BookDOM();
		        		updateEmployeeTable(bd.getAllBooks());
		        	
		        	}
		        	
	        	}
	        }
	      }
	    );
	    ////////////////////////////////////////////////////////////
	    
	    
	    ///Action Listener for Admin Panel/////////////////////////////////
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
										updateManagerTable();
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
															updateManagerTable();
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
									   updateManagerTable();
									   
									}
							}
						}
									
						
						if (evt.getSource()==managerWindow.getBtnAddEmployee()){
							
							   JTextField fieldSSN = new JTextField();
							   JTextField fieldName= new JTextField();
							   JTextField fieldUsername= new JTextField();
							   JTextField fieldPassword= new JTextField();
							   
							   Object[] message = {"new SSN:", fieldSSN, "new Name:", fieldName, "new Username", fieldUsername, 
									   "new Passwod", fieldPassword};
								Object[] options = { "ADD!", "Cancel" };
								int n = JOptionPane.showOptionDialog(new JFrame(), message,"Add New User", JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE, null, options,options[1]);
								if (n == JOptionPane.OK_OPTION) 
								{ 
									if (fieldName.getText().trim().length()>0 && fieldUsername.getText().trim().length()>0 &&
											fieldPassword.getText().trim().length()>0 && fieldSSN.getText().trim().length()>0){
									if (checkSSN(fieldSSN.getText()))
									{
										   String newSSN= fieldSSN.getText();
										   String newName= fieldName.getText();
										   String newUsername= fieldUsername.getText();
										   String newPassword= fieldPassword.getText();
										  
										   											   
										UserDOM ud = new UserDOM();
										User newUser = new User(newSSN,newName,newUsername,newPassword,"desk");
										ud.addUser(newUser);
										updateManagerTable1();
											//default title and icon
											JOptionPane.showMessageDialog(managerWindow,"Successfully added user:"+newUsername+" called "+newName+"!");
									} else{
										JOptionPane.showMessageDialog(managerWindow,"SSN Taken!","SSN TAKEN!",JOptionPane.ERROR_MESSAGE);
									}
									}
									else{
										//custom title, error icon
										JOptionPane.showMessageDialog(managerWindow,"Please leave no field empty!","ERROR!",JOptionPane.ERROR_MESSAGE);
									}
								}

					}
						
						if (evt.getSource()==managerWindow.getBtnUpdateEmployee())
						{
							if (managerWindow.getTable1().getSelectedRow()!=-1)
							{
							
								  
								   JTextField fieldName= new JTextField();
								   JTextField fieldUsername= new JTextField();
								   JTextField fieldPassword= new JTextField();
								  
								 Object[] message = {"updated Employee Name:", fieldName, "updated Employee Username", 
										 fieldUsername, "updated Employee Password", fieldPassword};

									Object[] options = { "UPDATE!", "Cancel" };
									int n = JOptionPane.showOptionDialog(new JFrame(), message,"Update Employee Info", JOptionPane.YES_NO_OPTION,
											JOptionPane.QUESTION_MESSAGE, null, options,options[1]);
									if (n == JOptionPane.OK_OPTION) 
									{ 
										if (fieldName.getText().trim().length()>0 && fieldUsername.getText().trim().length()>0 &&
												fieldPassword.getText().trim().length()>0){
											
											 
											   String updName = fieldName.getText();
											   String updUsername = fieldUsername.getText();
											   String updPassword= fieldPassword.getText();
											   
														UserDOM ud = new UserDOM();
															User newUser= new User((String)managerWindow.getTable().getValueAt(managerWindow.getTable1().getSelectedRow(),0),
																					updName, updUsername, updPassword, "desk");
															User oldUser = new User((String)managerWindow.getTable1().getValueAt(managerWindow.getTable1().getSelectedRow(),0),
																					(String)managerWindow.getTable1().getValueAt(managerWindow.getTable1().getSelectedRow(),1),
																					(String)managerWindow.getTable1().getValueAt(managerWindow.getTable1().getSelectedRow(),2), 
																					(String)managerWindow.getTable1().getValueAt(managerWindow.getTable1().getSelectedRow(),3), 
																					(String)managerWindow.getTable1().getValueAt(managerWindow.getTable1().getSelectedRow(),4)
																				);
															ud.updateUser(oldUser, newUser);
															updateManagerTable1();
															//default title and icon
															JOptionPane.showMessageDialog(managerWindow,"Successfully updated user:"+updUsername+" named "+updName+"!");
									
										}
									   
									      else{
														//custom title, error icon
														JOptionPane.showMessageDialog(managerWindow,"Please leave no field empty!","ERROR!",JOptionPane.ERROR_MESSAGE);
													}
									}
									
						
							}
						}
						
						
						if (evt.getSource()==managerWindow.getBtnDeleteEmployee())
						{
				
							if(managerWindow.getTable1().getSelectedRow()!=-1){
								   String toDelSSN= ((String)managerWindow.getTable1().getValueAt(managerWindow.getTable1().getSelectedRow(),0));
								   String toDelName= ((String)managerWindow.getTable1().getValueAt(managerWindow.getTable1().getSelectedRow(),1));
								   String toDelUsername= ((String)managerWindow.getTable1().getValueAt(managerWindow.getTable1().getSelectedRow(),2));
								   String toDelPassword= ((String)managerWindow.getTable1().getValueAt(managerWindow.getTable1().getSelectedRow(),3));
								   
								   User aUser= new User(toDelSSN, toDelName, toDelUsername, toDelPassword, "desk");
								   UserDOM ud = new UserDOM();
								   
									int n = JOptionPane.showConfirmDialog(managerWindow,
										    "Are you sure you want to remove User: "+aUser.toString()+" ??",
										    "Please confirm deletion!",
										    JOptionPane.YES_NO_OPTION);	
									if (n==JOptionPane.YES_OPTION)
									{
									   ud.removeUser(aUser);
									   updateManagerTable1();
									   
									}
							}
						}
						
						
						if (evt.getSource()==managerWindow.getBtnCreateReport()){
							createReport();
						}
						
						
						if (evt.getSource()==managerWindow.getBtnSignOut()){
							managerWindow.setVisible(false);
							logInWindow.setVisible(true);
						}
						
					}
				});
	    ////////////////////////////////////////////////////////////////////////
	    
	    

	    ///Action Listener for Employee Panel/////////////////////////////////
	    employeeWindow.addButtonActionListener(
	    		new ActionListener() {
					public void actionPerformed(ActionEvent evt) {

						if (evt.getSource()==employeeWindow.getBtnSellBook())
						{
							try{
							if(employeeWindow.getTable().getSelectedRow()!=-1){
								String toSellISBN = ((String)employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),0));
								   String toSellTitle = ((String)employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),1));
								   String toSellAuthor = ((String)employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),2));
								   String toSellGenre= ((String)employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),3));
								   int toSellQuantity = ((Integer)(employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),4)));
								   double toSellPrice = ((Double)(employeeWindow.getTable().getValueAt(employeeWindow.getTable().getSelectedRow(),5)));
								   
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
										{//enough books to sell at once
											Book newBook = new Book(aBook.getISBN(), aBook.getTitle(), aBook.getAuthor(), 
													aBook.getGenre(), aBook.getQuantity(), aBook.getPrice());
											newBook.setQuantity(newBook.getQuantity()-number);
										    	bd.updateBook(aBook, newBook);
										    	updateEmployeeTable(bd.getAllBooks());
										    	JOptionPane.showMessageDialog(employeeWindow,"Successfully Sold "+number+" pieces of book: "+aBook.getTitle()+" by "+
										    	aBook.getAuthor()+" Total SUM: "+number*aBook.getPrice());
										    	
										}else
										{//couldn't sell enough books
											int actuallySold = aBook.getQuantity();
											Book newBook = new Book(aBook.getISBN(), aBook.getTitle(), aBook.getAuthor(), 
													aBook.getGenre(), aBook.getQuantity(), aBook.getPrice());
											newBook.setQuantity(0);
										    	bd.updateBook(aBook, newBook);
										    	updateEmployeeTable(bd.getAllBooks());
										    	JOptionPane.showMessageDialog(employeeWindow,"Only "+actuallySold+" books out of "+number+" sold!! TOTAL SUM:"+aBook.getPrice()*actuallySold,"NOT ENOUGH BOOKS SOLD!",JOptionPane.ERROR_MESSAGE);
										}
									}else{
										JOptionPane.showMessageDialog(employeeWindow,"Sorry, book is Completelely OUT OF STOCK!","BOOKS IS OUT OF STOCK!",JOptionPane.ERROR_MESSAGE);
											}
								}
							}
						}
							catch(NumberFormatException e){
								JOptionPane.showMessageDialog(employeeWindow,"Please type number, not letters!","INPUT ERROR!",JOptionPane.ERROR_MESSAGE);
								}
					}
						if (evt.getSource()==employeeWindow.getBtnSearch())
						{
							if(employeeWindow.getRadioAuthor().isSelected()||
								employeeWindow.getRadioGenre().isSelected()||
								 employeeWindow.getRadioTitle().isSelected())
							{
							if(employeeWindow.getFldSearch().getText().trim().length()>0)
							{
								String searchWhat = new String();
								if(employeeWindow.getRadioAuthor().isSelected()){
									searchWhat="author";
								}
								if(employeeWindow.getRadioGenre().isSelected()){
									searchWhat="genre";
								}
								if(employeeWindow.getRadioTitle().isSelected()){
									searchWhat="title";
								}	
								
								BookDOM bd = new BookDOM();
								ArrayList<Book> allBooks=bd.getAllBooks();
								ArrayList<Book> searchRes=new ArrayList<Book>();
								searchRes=bd.searchBookBy(employeeWindow.getFldSearch().getText(), searchWhat, allBooks);
								
								updateEmployeeTable(searchRes);
							}
						}
						}
						
						if (evt.getSource()==employeeWindow.getBtnSeeAll())
						{
							BookDOM bd = new BookDOM();
							updateEmployeeTable(bd.getAllBooks());
						}
						
						
						if (evt.getSource()==employeeWindow.getBtnSignOut())
						{
							employeeWindow.setVisible(false);
							logInWindow.setVisible(true);
						}
						
						
					}
				});
	    ////////////////////////////////////////////////////////////////////////
	    
	    
	  }

		private void  fillManagerTable()
		{
			//////////////////////////////////////////////////////
			DefaultTableModel dtm=managerWindow.getdTableModel();
			//////////////////////////////////////////////////////
			int[] columnsWidth = { 100, 400, 200, 150, 100, 100};
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
		
		private void updateManagerTable()
		{
			DefaultTableModel dtm = managerWindow.getdTableModel();
			int c = dtm.getRowCount();
				for (int i=c-1; i>=0; i--)
				{
					dtm.removeRow(i);
				}
				managerWindow.setdTableModel(dtm);
				fillManagerTable();
		}	
		
		private void  fillManagerTable1()
		{
			//////////////////////////////////////////////////////
			DefaultTableModel dtm=managerWindow.getdTableModel1();
			//////////////////////////////////////////////////////
			int[] columnsWidth = { 130, 370, 200, 175,175};
			int ii = 0;
			for (int width : columnsWidth) {
				TableColumn column = managerWindow.getTable1().getColumnModel().getColumn(ii++);
				column.setMinWidth(width);
				column.setMaxWidth(width);
				column.setPreferredWidth(width);
			}
				Object[] row;
				int index=0;
				UserDOM ud = new UserDOM();

				ArrayList<User> result = ud.getAllUsers();
				User aUser;
				if(result.size()!=0)
				{
					while (index < result.size()) {
						aUser= result.get(index);
						
						row = new Object[]{aUser.getSSN(),aUser.getName(),aUser.getUsername(),aUser.getPassword(), 
								aUser.getRole()};
						if (aUser.getRole().equals("desk"))
						dtm.addRow(row);
						index++;
					}
				}
				///////////////////////////////////
				managerWindow.setdTableModel1(dtm);
				///////////////////////////////////
		}
		
		private void updateManagerTable1()
		{
			DefaultTableModel dtm = managerWindow.getdTableModel1();
			int c = dtm.getRowCount();
				for (int i=c-1; i>=0; i--)
				{
					dtm.removeRow(i);
				}
				managerWindow.setdTableModel1(dtm);
				fillManagerTable1();
		}	
		
		private void  fillEmployeeTable(ArrayList<Book> data)
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
				
				Book aBook;
				if(data.size()!=0)
				{
					while (index < data.size()) {
						aBook = data.get(index);
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
		
		private void updateEmployeeTable(ArrayList<Book> data)
		{
			DefaultTableModel dtm = employeeWindow.getdTableModel();
			int c = dtm.getRowCount();
				for (int i=c-1; i>=0; i--)
				{
					dtm.removeRow(i);
				}
				employeeWindow.setdTableModel(dtm);
				fillEmployeeTable(data);
		}	

	  
	  public String logIn(String username, String password){
		  String logInStatus="notOK";
		  UserDOM uDOM = new UserDOM();
		  ArrayList<User> allUsers = uDOM.getAllUsers();
		  	for (int i = 0; i < allUsers.size(); i++) {
		  		User aUser = allUsers.get(i);//we retrieved a User from list of all
		  		if(aUser.getUsername().equals(username)){
		  			if(aUser.getPassword().equals(password)){
		  				if(aUser.getRole().equals("desk"))
		  				{
		  					logInStatus="OKdesk";
		  				}
		  				if(aUser.getRole().equals("manager"))
		  				{
		  					logInStatus="OKmanager";
		  				}
		  			}
		  		}
		  	}
	  return logInStatus;
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
	  
	  public boolean checkSSN(String SSN){
		  boolean okSSN= true;
		  
		  UserDOM bd = new UserDOM();
		  ArrayList<User> checkMyUsers=bd.getAllUsers();
		  int iter=0;
			  while(iter<checkMyUsers.size()){
				  User aUser= checkMyUsers.get(iter);
				 if (SSN.equals(aUser.getSSN())){
					 okSSN=false;
					 break;
				 }
				 iter++;
			  }
		  return okSSN;
	  }
	  
	  protected void createReport(){
			BufferedWriter writer = null;
			try{
				BookDOM bd= new BookDOM();
   	            ArrayList<Book> allBooks = bd.getAllBooks();
   	            ArrayList<Book> emptyStock = new ArrayList<Book>();
				File logFile;
   	            
				int count=0;
				while (count < allBooks.size())
            	{
            		Book aBook = allBooks.get(count);
            		if (aBook.getQuantity()==0)
            		{
            			emptyStock.add(aBook);
            		}
            		count++;
            	}

	            if( emptyStock.size()>0)
	            {
	            	String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	   	            logFile = new File(timeLog+"_"+".txt");
	   	            int index = 0;       
	   	            writer = new BufferedWriter(new FileWriter(logFile));
	   	            writer.write("Books with 0 stock:\n");
	            	
	   	            int cnt =0;
	   	            
	            	while (index < allBooks.size())
	            	{
	            		Book aBook = allBooks.get(index);
	            		if (aBook.getQuantity()==0)
	            		{
	            			cnt++;
	            			emptyStock.add(aBook);
	            			writer.write(cnt+". =>"+aBook.toString()+"\n");
	            		}
	            		index++;
	            	}
	            	
	            	// This will output the full path where the file will be written to...
	            	//default title and icon
	            	JOptionPane.showMessageDialog(employeeWindow,
	            	    "Successfully Created LOG. \n FILE Location:"+logFile.getCanonicalPath());
	            }else
	            {
	            	JOptionPane.showMessageDialog(employeeWindow,
		            	    "There is NO book out of STOCK!");
	            }
	            
	          
	            

	        } catch (Exception e) {
	        } 
			finally {
	            try {
	                // Close the writer regardless of what happens...
	                writer.close();
	            } catch (Exception e) { }
	            }
	  }
	  
	  public static void main(String[] args) { 
	    Controller app = new Controller(); 
	    app.logInWindow.setVisible(true); // make visual component appear
	  	}
	}