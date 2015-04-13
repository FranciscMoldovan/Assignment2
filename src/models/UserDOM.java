package models;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class UserDOM {

	public void addUser(){
		try{
				Document d=DOMHelper.getDocument("src\\models\\users.xml");
				
				Element users=d.getDocumentElement();
				
				//Create user tag
				Element user=d.createElement("user"); 
				
				//Create SSN tag
				Element SSN=d.createElement("SSN");
				SSN.appendChild(d.createTextNode("1939999999"));
				user.appendChild(SSN);
				
				//Create name tag
				Element name=d.createElement("name"); 
				name.appendChild(d.createTextNode("TEST_NAME"));
				user.appendChild(name);
				
				//Create username tag
				Element username=d.createElement("username"); 
				username.appendChild(d.createTextNode("TEST_username"));
				user.appendChild(username);
				
				//Create password tag
				Element password=d.createElement("password"); 
				password.appendChild(d.createTextNode("TEST_password"));
				user.appendChild(password);
				
				//Create role tag
				Element role=d.createElement("role");
				role.appendChild(d.createTextNode("TEST_role")); 
				user.appendChild(role);
				
				users.appendChild(user);
				DOMHelper.saveXMLContent(d,"src\\models\\users.xml");
			}catch(Exception e){
			System.out.println(e.getMessage());
		}
		}

	public void removeUser(String SSN){
		try{
				Document d = DOMHelper.getDocument("src\\models\\users.xml");
				NodeList n1=d.getElementsByTagName("user"); 
				for (int i = 0; i < n1.getLength(); i++) {
					Element euser=(Element) n1.item(i);
					if(euser.getElementsByTagName("SSN").item(0).getTextContent().equals(SSN)){
						euser.getParentNode().removeChild(euser);
					}
				}
				//Write to file
				DOMHelper.saveXMLContent(d, "src\\models\\users.xml");
			}catch (Exception e){
				System.out.println(e.getMessage());
		}
		}
	
	public void updateUser(String SSN, String name, String username, String password, String role){
		try{
			Document d = DOMHelper.getDocument("src\\models\\users.xml");
			NodeList n1=d.getElementsByTagName("user"); 
			for (int i = 0; i < n1.getLength(); i++) {
				Element euser=(Element) n1.item(i);
				if(euser.getElementsByTagName("SSN").item(0).getTextContent().equals(SSN)){
					euser.getElementsByTagName("name").item(0).setTextContent(name);
					euser.getElementsByTagName("username").item(0).setTextContent(username);
					euser.getElementsByTagName("password").item(0).setTextContent(password);
					euser.getElementsByTagName("role").item(0).setTextContent(role);		 
				}
			}
			//Write to file
			DOMHelper.saveXMLContent(d, "src\\models\\users.xml");
		}catch (Exception e){
			System.out.println(e.getMessage());
	}
	}
	
}
