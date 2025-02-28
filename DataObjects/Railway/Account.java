package Railway;

/**
 * @author Tuan.Le
 *
 */
public class Account {
	private String username;
	private String password;
	private String pid;
	
	//Constructor
	public Account() {
		
	}
	
	public Account(String username, String password, String pid) {
		this.username = username;
		this.password = password;
		this.pid = pid;
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
	
	public void setPassportNumber(String pid) {
		this.pid = pid;
	}
	
	//Getter
	public String getUserName() {
		return username;
	}
	
	public String getPassword() {
		return password;
		
	}
	
	public String getPassportNumber() {
		return pid;
		
	}

}
