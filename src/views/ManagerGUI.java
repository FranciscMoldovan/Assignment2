package views;

import java.awt.BorderLayout;
import java.awt.Font;


import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;

public class ManagerGUI extends JDialog{

	/**
	 * serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	private Object[][] dummyData;
	private Object[] columns = { "ISBN", "Title", "Author", "Genre", "Quantity", "Price"};
	private DefaultTableModel dTableModel = new DefaultTableModel(dummyData, columns);
	private JTable table; 
	private JPanel panel;
	private JScrollPane scrollPane ; 
	private JLabel lblInfo;
	//////BUTTONS////////////////
	private JButton btnAddNewBook;
	private JButton btnUpdateBook;
	private JButton btnDeleteBook;
	private JButton btnSignOut;
	/////////////////////////////
	
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public ManagerGUI(java.awt.Frame parent, boolean modal, String title){
		super(parent, modal);
		setTitle(title);
		setSize(1381, 378);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		table = new JTable(dTableModel)
		{
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int col)
			{
			return false;
			}
		};
		
		table.setFont(new Font("Serif", Font.PLAIN, 18));
		table.setRowHeight(table.getRowHeight() + 5);
		table.setAutoCreateRowSorter(true);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSize(400, 300);
	

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(286, 42, 1056, 279);
		panel.add(scrollPane);
		
		btnAddNewBook = new JButton("Add New Book");
		btnAddNewBook.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		btnAddNewBook.setBounds(42, 108, 211, 31);
		panel.add(btnAddNewBook);
		
		btnUpdateBook = new JButton("Update Book Info");
		btnUpdateBook.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		btnUpdateBook.setBounds(42, 206, 211, 31);
		panel.add(btnUpdateBook);
		
		btnDeleteBook = new JButton("Delete Book");
		btnDeleteBook.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		btnDeleteBook.setBounds(42, 248, 211, 31);
		panel.add(btnDeleteBook);
		
		
		lblInfo = new JLabel("<html>Select a book from<br/>tabel for operations below!</html>");
		lblInfo.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		lblInfo.setBounds(41, 131, 223, 64);
		panel.add(lblInfo);
		
		btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSignOut.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		btnSignOut.setBounds(42, 28, 108, 55);
		panel.add(btnSignOut);
		
		
		
	}
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public DefaultTableModel getdTableModel() {
		return dTableModel;
	}

	public void setdTableModel(DefaultTableModel dTableModel) {
		this.dTableModel = dTableModel;
	}

	public void setColumns(Object[] columns) {
		this.columns = columns;
	}

	public Object[][] getDummyData() {
		return dummyData;
	}

	public void setDummyData(Object[][] dummyData) {
		this.dummyData = dummyData;
	}

	public Object[] getColumns() {
		return columns;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	
	
	
	public JButton getBtnAddNewBook() {
		return btnAddNewBook;
	}

	public void setBtnAddNewBook(JButton btnAddNewBook) {
		this.btnAddNewBook = btnAddNewBook;
	}

	public JButton getBtnUpdateBook() {
		return btnUpdateBook;
	}

	public void setBtnUpdateBook(JButton btnUpdateBook) {
		this.btnUpdateBook = btnUpdateBook;
	}

	public JButton getBtnDeleteBook() {
		return btnDeleteBook;
	}

	public void setBtnDeleteBook(JButton btnDeleteBook) {
		this.btnDeleteBook = btnDeleteBook;
	}


	public JButton getBtnSignOut() {
		return btnSignOut;
	}

	public void setBtnSignOut(JButton btnSignOut) {
		this.btnSignOut = btnSignOut;
	}

	public void addButtonActionListener(ActionListener listener) {
	    btnAddNewBook.addActionListener(listener);
	    btnUpdateBook.addActionListener(listener);
	    btnDeleteBook.addActionListener(listener);
	    btnSignOut.addActionListener(listener);
	  }
}






