package views;

import java.awt.BorderLayout;
import java.awt.Font;


import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionEvent;

public class EmployeeGUI extends JDialog{

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
	private JLabel lblSearch;
	///////////BUTTONS///////////
	private JButton btnSellBook;
	private JTextField fldSearch;
	private JButton btnSearch;
	private JButton btnSignOut;
	/////////////////////////////
	
	////////RADIO BUTTONS//////
	private JRadioButton radioGenre;
	private JRadioButton radioTitle;
	private JRadioButton radioAuthor;
	private JButton btnSeeAll;
	//////////////////////////
	

	
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	private void groupButton(){
		ButtonGroup bg1 = new ButtonGroup();
			bg1.add(radioGenre);
			bg1.add(radioTitle);
			bg1.add(radioAuthor);
		}
	
	
	public EmployeeGUI(java.awt.Frame parent, boolean modal, String title){
		super(parent, modal);
		setTitle(title);
		setSize(1374, 436);
		
		
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
		scrollPane.setBounds(286, 11, 1064, 368);
		panel.add(scrollPane);
		
	    btnSellBook = new JButton("Sell Book!");
		btnSellBook.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		btnSellBook.setBounds(44, 342, 211, 31);
		panel.add(btnSellBook);
		
		lblInfo = new JLabel("<html>Select a book from<br/>tabel for operation below!</html>");
		lblInfo.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		lblInfo.setBounds(32, 284, 223, 64);
		panel.add(lblInfo);
		
		fldSearch = new JTextField();
		fldSearch.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		fldSearch.setBounds(51, 90, 211, 31);
		panel.add(fldSearch);
		fldSearch.setColumns(10);
		
		lblSearch = new JLabel("Search!");
		lblSearch.setFont(new Font("Linux Biolinum G", Font.BOLD, 27));
		lblSearch.setBounds(51, 54, 152, 37);
		panel.add(lblSearch);
		
		 radioGenre = new JRadioButton("Genre");
		 radioGenre.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		radioGenre.setBounds(51, 131, 109, 23);
		panel.add(radioGenre);
		
		 radioTitle = new JRadioButton("Title");
		 radioTitle.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		radioTitle.setBounds(51, 157, 109, 23);
		panel.add(radioTitle);
		
		 radioAuthor = new JRadioButton("Author");
		 radioAuthor.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		radioAuthor.setBounds(51, 183, 109, 23);
		panel.add(radioAuthor);
		
		groupButton();
		
		 btnSearch = new JButton("Search!");
		btnSearch.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		btnSearch.setBounds(61, 213, 168, 23);
		panel.add(btnSearch);
		
		btnSeeAll = new JButton("See All Books");
		btnSeeAll.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		btnSeeAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSeeAll.setBounds(90, 253, 139, 31);
		panel.add(btnSeeAll);
		
		btnSignOut = new JButton("Log Out");
		btnSignOut.setFont(new Font("Linux Biolinum G", Font.BOLD, 17));
		btnSignOut.setBounds(24, 11, 99, 44);
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
	
	
	
	
    
	public JButton getBtnSellBook() {
		return btnSellBook;
	}

	public void setBtnSellBook(JButton btnSellBook) {
		this.btnSellBook = btnSellBook;
	}

	
	
	public JTextField getFldSearch() {
		return fldSearch;
	}

	public void setFldSearch(JTextField fldSearch) {
		this.fldSearch = fldSearch;
	}
	
	public JRadioButton getRadioGenre() {
		return radioGenre;
	}

	public void setRadioGenre(JRadioButton radioGenre) {
		this.radioGenre = radioGenre;
	}

	public JRadioButton getRadioTitle() {
		return radioTitle;
	}

	public void setRadioTitle(JRadioButton radioTitle) {
		this.radioTitle = radioTitle;
	}

	public JRadioButton getRadioAuthor() {
		return radioAuthor;
	}

	public void setRadioAuthor(JRadioButton radioAuthor) {
		this.radioAuthor = radioAuthor;
	}

	
	
	public JButton getBtnSearch() {
		return btnSearch;
	}

	public void setBtnSearch(JButton btnSearch) {
		this.btnSearch = btnSearch;
	}

	
	
	public JButton getBtnSeeAll() {
		return btnSeeAll;
	}

	public void setBtnSeeAll(JButton btnSeeAll) {
		this.btnSeeAll = btnSeeAll;
	}
	
	public JButton getBtnSignOut() {
		return btnSignOut;
	}

	public void setBtnSignOut(JButton btnSignOut) {
		this.btnSignOut = btnSignOut;
	}

	public void addButtonActionListener(ActionListener listener) {
	    btnSellBook.addActionListener(listener);
	    btnSearch.addActionListener(listener);
	    btnSeeAll.addActionListener(listener);
	    btnSignOut.addActionListener(listener);
	  }
}






