package models;

public class Book {
	
	private String ISBN;
	private String title; 
	private String author; 
	private String genre; 
	private int quantity; 
	private double price;
	
	public Book (String ISBN, String title, String author, String genre, int qty, double price){
		this.ISBN=ISBN;
		this.title=title; 
		this.author=author;
		this.genre=genre;
		this.quantity=qty;
		this.price=price;
	}
	
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [ISBN=" + ISBN + ", title=" + title + ", author=" + author
				+ ", genre=" + genre + ", quantity=" + quantity + ", price="
				+ price + "]";
	}

}
