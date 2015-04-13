package models;

import org.w3c.dom.*;


public class BookDOM {
	public void addBook(){
		try{
				Document d=DOMHelper.getDocument("src\\models\\books.xml");
				Element books=d.getDocumentElement();
				//Create book tag
				Element book=d.createElement("book"); 
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
				quantity.appendChild(d.createTextNode("TEST_QUANTITY"));
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
}
