package pojo;

public class AccountPojo {
private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String password;
	
	private float balance;
	
	private float balanceChangeAmount;

	public AccountPojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountPojo(int id, String firstName, String lastName, String userName, String password, float balance) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	public float getBalanceChangeAmount() {
		return balanceChangeAmount;
	}

	public void setBalanceChangeAmount(float balanceChangeAmount) {
		this.balanceChangeAmount = balanceChangeAmount;
	}
	

	@Override
	public String toString() {
		return "AccountPojo [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", password=" + password + ", balance=" + balance + "]";
	}

	
	
}