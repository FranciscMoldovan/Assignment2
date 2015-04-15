package models;

import java.util.ArrayList;

import org.w3c.dom.*;


public class BookDOM {
	public void addBook(Book newBook){
		try{
				Document d=DOMHelper.getDocument("src\\models\\books.xml");
				Element books=d.getDocumentElement();
				//Create book tag
				Element book=d.createElement("book"); 
				//Create ISBN tag
				Element ISBN=d.createElement("ISBN");
				ISBN.appendChild(d.createTextNode(newBook.getISBN()));
				book.appendChild(ISBN);
				//Create title tag
				Element title=d.createElement("title"); 
				title.appendChild(d.createTextNode(newBook.getTitle()));
				book.appendChild(title);
				//Create author tag
				Element author=d.createElement("author"); 
				author.appendChild(d.createTextNode(newBook.getAuthor()));
				book.appendChild(author);
				//Create genre tag
				Element genre=d.createElement("genre"); 
				genre.appendChild(d.createTextNode(newBook.getGenre()));
				book.appendChild(genre);
				//Create quantity tag
				Element quantity=d.createElement("quantity"); 
				quantity.appendChild(d.createTextNode(String.valueOf(newBook.getQuantity())));
				book.appendChild(quantity);
				//Create price tag
				Element price=d.createElement("price"); 
				price.appendChild(d.createTextNode(String.valueOf(newBook.getPrice())));
				book.appendChild(price);
				books.appendChild(book);
				DOMHelper.saveXMLContent(d,"src\\models\\books.xml");
			}catch(Exception e){
			System.out.println(e.getMessage());
		}
		}

	public void removeBook(Book aBook){
		try{
				String ISBN = aBook.getISBN();
				String title=aBook.getTitle();
				String author=aBook.getAuthor();
				String genre=aBook.getGenre();
				String quantity=String.valueOf(aBook.getQuantity());
				
			
				Document d = DOMHelper.getDocument("src\\models\\books.xml");
				NodeList n1=d.getElementsByTagName("book"); 
				for (int i = 0; i < n1.getLength(); i++) {
					Element ebook=(Element) n1.item(i);
					if(ebook.getElementsByTagName("ISBN").item(0).getTextContent().equals(ISBN) && 
						ebook.getElementsByTagName("title").item(0).getTextContent().equals(title) &&	
						 ebook.getElementsByTagName("author").item(0).getTextContent().equals(author) &&	
						  ebook.getElementsByTagName("genre").item(0).getTextContent().equals(genre) &&	
						   ebook.getElementsByTagName("quantity").item(0).getTextContent().equals(quantity) 	
							){
						ebook.getParentNode().removeChild(ebook);
					}
				}
				//Write to file
				DOMHelper.saveXMLContent(d, "src\\models\\books.xml");
			}catch (Exception e){
				System.out.println(e.getMessage());
		}
		}
	
	public void updateBook(Book oldBook, Book newBook){
		try{
			String oldISBN = oldBook.getISBN();
			String oldTitle= oldBook.getTitle();
			String oldAuthor=oldBook.getAuthor();
			String oldGenre=oldBook.getGenre();
				String oldQuantity=String.valueOf(oldBook.getQuantity());
			
			
			//String updISBN=newBook.getISBN();
			String updTitle= newBook.getTitle();
			String updAuthor=newBook.getAuthor();
			String updGenre=newBook.getGenre();
			   String updQuantity=String.valueOf(newBook.getQuantity());
			   String updPrice=String.valueOf(newBook.getPrice());
			
			Document d = DOMHelper.getDocument("src\\models\\books.xml");
			NodeList n1=d.getElementsByTagName("book"); 
			for (int i = 0; i < n1.getLength(); i++) {
				Element ebook=(Element) n1.item(i);
				if(ebook.getElementsByTagName("ISBN").item(0).getTextContent().equals(oldISBN)&&
					ebook.getElementsByTagName("title").item(0).getTextContent().equals(oldTitle)&&
					 ebook.getElementsByTagName("author").item(0).getTextContent().equals(oldAuthor)&&
					  ebook.getElementsByTagName("genre").item(0).getTextContent().equals(oldGenre)&&
					   ebook.getElementsByTagName("quantity").item(0).getTextContent().equals(oldQuantity)
						){
					ebook.getElementsByTagName("title").item(0).setTextContent(updTitle);
					ebook.getElementsByTagName("author").item(0).setTextContent(updAuthor);
					ebook.getElementsByTagName("genre").item(0).setTextContent(updGenre);
					ebook.getElementsByTagName("quantity").item(0).setTextContent(String.valueOf(updQuantity));	
					ebook.getElementsByTagName("price").item(0).setTextContent(String.valueOf(updPrice));		 
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
		//System.out.println(allBooks.toString());
		return allBooks;
	}
	

}



















