package client;


public class Subscriber {
	// Class variables *************************************************
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String role;
	private String email;
	private String phoneNumer;
	private String id;
	private String isLoggedIn;
	private String storeName;
	//******************************************************************
	
	/**
	 * class for saving each subscriber info , 
	 * only have getters and setters and toString
	 * 
	 */
	
	
	

	public Subscriber(String userName, String password, String firstName, String lastName, String role, String email,
			String phoneNumer, String id, String isLoggedIn, String storeName) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.email = email;
		this.phoneNumer = phoneNumer;
		this.id = id;
		this.isLoggedIn = isLoggedIn;
		this.storeName = storeName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumer() {
		return phoneNumer;
	}

	public void setPhoneNumer(String phoneNumer) {
		this.phoneNumer = phoneNumer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setIsLoggedIn(String isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
}
