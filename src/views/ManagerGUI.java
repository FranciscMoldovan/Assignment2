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
		private Object[] columns1={"SSN", "Name", "Username", "Password","Role"};
	private DefaultTableModel dTableModel = new DefaultTableModel(dummyData, columns);
		private DefaultTableModel dTableModel1 = new DefaultTableModel(dummyData, columns1);
	private JTable table; 
		private JTable table1; 
	private JPanel panel;
	private JScrollPane scrollPane;
		private JScrollPane scrollPane1;
	private JLabel lblInfo;
		private JLabel lblInfo1;
	//////BUTTONS////////////////
	private JButton btnAddNewBook;
	private JButton btnUpdateBook;
	private JButton btnDeleteBook;
	private JButton btnSignOut;
	private JButton btnCreateReport;
	private JButton btnAddEmployee;
	private JButton btnDeleteEmployee;
	private JButton btnUpdateEmployee;
	
	
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
		setSize(1381, 838);
		
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
		
			table1 = new JTable(dTableModel1)
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
	
			table1.setFont(new Font("Serif", Font.PLAIN, 18));
			table1.setRowHeight(table.getRowHeight() + 5);
			table1.setAutoCreateRowSorter(true);
			table1.setColumnSelectionAllowed(false);
			table1.setRowSelectionAllowed(true);
			table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table1.setSize(400, 300);
	
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(286, 42, 1056, 279);
		panel.add(scrollPane);
			scrollPane1 = new JScrollPane(table1);
			scrollPane1.setBounds(286, 414, 1056, 318);
			panel.add(scrollPane1);

		
		btnAddNewBook = new JButton("Add New Book");
		btnAddNewBook.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		btnAddNewBook.setBounds(23, 109, 253, 31);
		panel.add(btnAddNewBook);
		
		btnUpdateBook = new JButton("Update Book Info");
		btnUpdateBook.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		btnUpdateBook.setBounds(23, 206, 253, 31);
		panel.add(btnUpdateBook);
		
		btnDeleteBook = new JButton("Delete Book");
		btnDeleteBook.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		btnDeleteBook.setBounds(23, 250, 253, 31);
		panel.add(btnDeleteBook);
		
		
		lblInfo = new JLabel("<html>Select a book from<br/>tabel for operations below!</html>");
		lblInfo.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		lblInfo.setBounds(23, 151, 223, 64);
		panel.add(lblInfo);
		
		btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSignOut.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		btnSignOut.setBounds(23, 28, 103, 55);
		panel.add(btnSignOut);
		
		btnCreateReport = new JButton("<html>Create Empty<br />Stock Report</html>");
		btnCreateReport.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		btnCreateReport.setBounds(135, 28, 141, 55);
		panel.add(btnCreateReport);
		
		btnAddEmployee = new JButton("Add New Employee");
		btnAddEmployee.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		btnAddEmployee.setBounds(23, 460, 253, 31);
		panel.add(btnAddEmployee);
		
		btnUpdateEmployee = new JButton("Update Employee Info");
		btnUpdateEmployee.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		btnUpdateEmployee.setBounds(23, 608, 253, 31);
		panel.add(btnUpdateEmployee);
		
		btnDeleteEmployee = new JButton("Delete Employee");
		btnDeleteEmployee.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		btnDeleteEmployee.setBounds(23, 666, 253, 31);
		panel.add(btnDeleteEmployee);
		
		lblInfo1 = new JLabel("<html>Select an Employee from<br/>tabel for operations below!</html>");
		lblInfo1.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		lblInfo1.setBounds(23, 546, 223, 64);
		panel.add(lblInfo1);
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

	public JButton getBtnCreateReport() {
		return btnCreateReport;
	}

	public void setBtnCreateReport(JButton btnCreateReport) {
		this.btnCreateReport = btnCreateReport;
	}
	
	public JButton getBtnAddEmployee() {
		return btnAddEmployee;
	}

	public void setBtnAddEmployee(JButton btnAddEmployee) {
		this.btnAddEmployee = btnAddEmployee;
	}

	public JButton getBtnDeleteEmployee() {
		return btnDeleteEmployee;
	}

	public void setBtnDeleteEmployee(JButton btnDeleteEmployee) {
		this.btnDeleteEmployee = btnDeleteEmployee;
	}

	public JButton getBtnUpdateEmployee() {
		return btnUpdateEmployee;
	}

	public void setBtnUpdateEmployee(JButton btnUpdateEmployee) {
		this.btnUpdateEmployee = btnUpdateEmployee;
	}

	
	
	public DefaultTableModel getdTableModel1() {
		return dTableModel1;
	}

	public void setdTableModel1(DefaultTableModel dTableModel1) {
		this.dTableModel1 = dTableModel1;
	}

	
	
	public JTable getTable1() {
		return table1;
	}

	public void setTable1(JTable table1) {
		this.table1 = table1;
	}

	public void addButtonActionListener(ActionListener listener) {
	    btnAddNewBook.addActionListener(listener);
	    btnUpdateBook.addActionListener(listener);
	    btnDeleteBook.addActionListener(listener);
	    btnSignOut.addActionListener(listener);
	    btnCreateReport.addActionListener(listener);
		btnAddEmployee.addActionListener(listener);
		btnDeleteEmployee.addActionListener(listener);
		btnDeleteEmployee.addActionListener(listener);
		btnUpdateEmployee.addActionListener(listener);
	  }
}






