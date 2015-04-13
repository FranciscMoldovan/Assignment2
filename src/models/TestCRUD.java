package models;

public class TestCRUD {
	public static void main (String[] Args){
	BookDOM bd = new BookDOM();
	//bd.addBook();
	//bd.removeBook("4444444444");
	//bd.updateBook("4444444444", "x", "xx", "xxx", 20, 22.55);
	UserDOM ud = new UserDOM();
	//ud.addUser();
	//ud.removeUser("1939999999");
	ud.updateUser("1939999999", "x", "xx", "xxx", "boss");
	}
}
