package pojo;

public class TransactionPojo {
	private int transactionID;
	private int accountID;
	private String transactionDate;
	private String transactionType;
	private double transactionAmount;
	
	
	
	
	public TransactionPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TransactionPojo(int transactionID, int accountID, String transactionDate, String transactionType,
			double transactionAmount) {
		super();
		this.transactionID = transactionID;
		this.accountID = accountID;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
	}

	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	@Override
	public String toString() {
		String padString = "";
		if(transactionType.equals("DEPOSIT")) padString = " ";
		return "transactionDate= "
				+ transactionDate + ", transactionType=" + transactionType + "," + padString + "transactionAmount=" + padString + transactionAmount;
	}
	
	
}
