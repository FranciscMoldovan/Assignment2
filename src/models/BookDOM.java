package models;

import java.util.ArrayList;

import org.w3c.dom.*;


public class BookDOM {
	public void addBook(){
		try{
				Document d=DOMHelper.getDocument("src\\models\\books.xml");
				Element books=d.getDocumentElement();
				//Create book tag
				Element book=d.createElement("book"); 
				//Create ISBN tag
				Element ISBN=d.createElement("ISBN");
				ISBN.appendChild(d.createTextNode("4444444444"));
				book.appendChild(ISBN);
				//Create title tag
				Element title=d.createElement("title"); 
				title.appendChild(d.createTextNode("TEST_TITLE"));
				book.appendChild(title);
				//Create author tag
				Element author=d.createElement("author"); 
				author.appendChild(d.createTextNode("TEST_AUTHOR"));
				book.appendChild(author);
				//Create genre tag
				Element genre=d.createElement("genre"); 
				genre.appendChild(d.createTextNode("TEST_AUTHOR"));
				book.appendChild(genre);
				//Create quantity tag
				Element quantity=d.createElement("quantity"); 
				quantity.appendChild(d.createTextNode("99999"));
				book.appendChild(quantity);
				//Create price tag
				Element price=d.createElement("price"); 
				price.appendChild(d.createTextNode("25.5"));
				book.appendChild(price);
				books.appendChild(book);
				DOMHelper.saveXMLContent(d,"src\\models\\books.xml");
			}catch(Exception e){
			System.out.println(e.getMessage());
		}
		}

	public void removeBook(String ISBN){
		try{
				Document d = DOMHelper.getDocument("src\\models\\books.xml");
				NodeList n1=d.getElementsByTagName("book"); 
				for (int i = 0; i < n1.getLength(); i++) {
					Element ebook=(Element) n1.item(i);
					if(ebook.getElementsByTagName("ISBN").item(0).getTextContent().equals(ISBN)){
						ebook.getParentNode().removeChild(ebook);
					}
				}
				//Write to file
				DOMHelper.saveXMLContent(d, "src\\models\\books.xml");
			}catch (Exception e){
				System.out.println(e.getMessage());
		}
		}
	
	public void updateBook(String ISBN, String title, String author, String genre, int quantity, double price){
		try{
			Document d = DOMHelper.getDocument("src\\models\\books.xml");
			NodeList n1=d.getElementsByTagName("book"); 
			for (int i = 0; i < n1.getLength(); i++) {
				Element ebook=(Element) n1.item(i);
				if(ebook.getElementsByTagName("ISBN").item(0).getTextContent().equals(ISBN)){
					ebook.getElementsByTagName("title").item(0).setTextContent(title);
					ebook.getElementsByTagName("author").item(0).setTextContent(author);
					ebook.getElementsByTagName("genre").item(0).setTextContent(genre);
					 
					ebook.getElementsByTagName("quantity").item(0).setTextContent(String.valueOf(quantity));	
					ebook.getElementsByTagName("price").item(0).setTextContent(String.valueOf(price));		 
				}
			}
			//Write to file
			DOMHelper.saveXMLContent(d, "src\\models\\books.xml");
		}catch (Exception e){
			System.out.println(e.getMessage());
	}
	}

	public ArrayList<Book> getAllBooks(){
		ArrayList<Book> allBooks = new ArrayList<Book>();
		
		try{
			Document d = DOMHelper.getDocument("src\\models\\books.xml");
			NodeList n1=d.getElementsByTagName("book"); 
			for (int i = 0; i < n1.getLength(); i++) {
				Element ebook=(Element) n1.item(i);
					String ISBN   = String.valueOf(ebook.getElementsByTagName("ISBN").item(0).getTextContent());
					String title = String.valueOf(ebook.getElementsByTagName("title").item(0).getTextContent());
					String author= String.valueOf(ebook.getElementsByTagName("author").item(0).getTextContent());
					String genre =String.valueOf(ebook.getElementsByTagName("genre").item(0).getTextContent());
					int quantity=Integer.valueOf(ebook.getElementsByTagName("quantity").item(0).getTextContent());
					double price=Double.valueOf(ebook.getElementsByTagName("price").item(0).getTextContent());
				Book aBook = new Book(ISBN,title,author,genre,quantity,price);
				allBooks.add(aBook);
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
	}
		System.out.println(allBooks.toString());
		return allBooks;
	}
	

}



















