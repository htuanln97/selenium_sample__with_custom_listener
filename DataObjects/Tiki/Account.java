package Tiki;

/**
 * @author Tuan.Le
 *
 */
public class Account {
	private String username;
	private String password;
	
	//Constructor
	public Account() {
		
	}
	
	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	//Setter
	public void setUserName(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	//Getter
	public String getUserName() {
		return username;
	}
	
	public String getPassword() {
		return password;
		
	}
	

}
