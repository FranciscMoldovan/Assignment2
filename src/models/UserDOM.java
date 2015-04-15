package models;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class UserDOM {

	public void addUser(User newUser){
		try{
				Document d=DOMHelper.getDocument("src\\models\\users.xml");
				
				Element users=d.getDocumentElement();
				
				//Create user tag
				Element user=d.createElement("user"); 
				
				//Create SSN tag
				Element SSN=d.createElement("SSN");
				SSN.appendChild(d.createTextNode(newUser.getSSN()));
				user.appendChild(SSN);
				
				//Create name tag
				Element name=d.createElement("name"); 
				name.appendChild(d.createTextNode(newUser.getName()));
				user.appendChild(name);
				
				//Create username tag
				Element username=d.createElement("username"); 
				username.appendChild(d.createTextNode(newUser.getUsername()));
				user.appendChild(username);
				
				//Create password tag
				Element password=d.createElement("password"); 
				password.appendChild(d.createTextNode(newUser.getPassword()));
				user.appendChild(password);
				
				//Create role tag
				Element role=d.createElement("role");
				role.appendChild(d.createTextNode(newUser.getRole())); 
				user.appendChild(role);
				
				users.appendChild(user);
				DOMHelper.saveXMLContent(d,"src\\models\\users.xml");
			}catch(Exception e){
			System.out.println(e.getMessage());
		}
		}

	public void removeUser(User remUser){
		try{
				Document d = DOMHelper.getDocument("src\\models\\users.xml");
				NodeList n1=d.getElementsByTagName("user"); 
				for (int i = 0; i < n1.getLength(); i++) {
					Element euser=(Element) n1.item(i);
					if(euser.getElementsByTagName("SSN").item(0).getTextContent().equals(remUser.getSSN())&&
						euser.getElementsByTagName("name").item(0).getTextContent().equals(remUser.getName())&&
						 euser.getElementsByTagName("username").item(0).getTextContent().equals(remUser.getUsername())&&
						  euser.getElementsByTagName("password").item(0).getTextContent().equals(remUser.getPassword())&&
							euser.getElementsByTagName("role").item(0).getTextContent().equals(remUser.getRole())
							){
						euser.getParentNode().removeChild(euser);
					}
				}
				//Write to file
				DOMHelper.saveXMLContent(d, "src\\models\\users.xml");
			}catch (Exception e){
				System.out.println(e.getMessage());
		}
		}
	
	public void updateUser(User oldUser, User newUser){
		try{
			//we leave role unchanged
			Document d = DOMHelper.getDocument("src\\models\\users.xml");
			NodeList n1=d.getElementsByTagName("user"); 
			for (int i = 0; i < n1.getLength(); i++) {
				Element euser=(Element) n1.item(i);
				if(euser.getElementsByTagName("SSN").item(0).getTextContent().equals(oldUser.getSSN())&&
						euser.getElementsByTagName("name").item(0).getTextContent().equals(oldUser.getName())&&
						 euser.getElementsByTagName("username").item(0).getTextContent().equals(oldUser.getUsername())&&
						  euser.getElementsByTagName("password").item(0).getTextContent().equals(oldUser.getPassword())&&
							euser.getElementsByTagName("role").item(0).getTextContent().equals(oldUser.getRole())
							){
					
					euser.getElementsByTagName("name").item(0).setTextContent(newUser.getName());
					euser.getElementsByTagName("username").item(0).setTextContent(newUser.getUsername());
					euser.getElementsByTagName("password").item(0).setTextContent(newUser.getPassword());
					euser.getElementsByTagName("role").item(0).setTextContent(oldUser.getRole());		 
				}
			}
			//Write to file
			DOMHelper.saveXMLContent(d, "src\\models\\users.xml");
		}catch (Exception e){
			System.out.println(e.getMessage());
	}
	}
	
	
	public ArrayList<User> getAllUsers(){
		ArrayList<User> allUsers= new ArrayList<User>();
		
		try{
			Document d = DOMHelper.getDocument("src\\models\\users.xml");
			NodeList n1=d.getElementsByTagName("user"); 
			for (int i = 0; i < n1.getLength(); i++) {
				Element ebook=(Element) n1.item(i);
					String SSN   = String.valueOf(ebook.getElementsByTagName("SSN").item(0).getTextContent());
					String name = String.valueOf(ebook.getElementsByTagName("name").item(0).getTextContent());
					String username= String.valueOf(ebook.getElementsByTagName("username").item(0).getTextContent());
					String password =String.valueOf(ebook.getElementsByTagName("password").item(0).getTextContent());
					String role=String.valueOf(ebook.getElementsByTagName("role").item(0).getTextContent());
					
					User aUser= new User(SSN,name,username,password,role);
				allUsers.add(aUser);
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
	}
		//System.out.println(allUsers.toString());
		return allUsers;
	}
	
	
	
	
	
	
}














