
/* A data type for individual bank accounts (checking, saving, etc)
 * The Quota data type contains the information about the accounts distribution rate
 * 
 * - Account name is not case-sensitive
 * - Account abbreviation has a maximum of 3 characters and is upper case
 * 	- If no abbreviation is provided, first 3 letters of account name is used
 */

/* TO-DO LIST:
 * - 
 */

public class Account {
	
	private static int idCounter = 1;
	
	private String name;
	private String abbrv;
		public static final int MAX_ABBRV_LENGTH = 4;
	private Quota quota;
	private int idNum;
	
	public Account(String accountName, String abbreviation, Double flat, Double perc) {
		name = accountName;
		abbrv = trimAbbreviation(abbreviation);
		quota = new Quota(flat, perc);
		assignIDnum();
	}
	
	// No abbreviation provided
	public Account(String accountName, Double flat, Double perc) {
		this(accountName, accountName, flat, perc);
	}
	
	// No abbreviation or quota provided
	public Account(String accountName) {
		this(accountName, accountName, null, null);
	}
	
	public void setFlatRate(double flat) { quota.setFlatRate(flat); }
	
	public void setPercRate(double perc) { quota.setPercRate(perc); }
	
	public String getName() { return name; }
	
	public String getAbbreviation() { return abbrv; }
	
	public double getFlatRate() { return quota.getFlatRate(); }
	
	public double getPercRate() { return quota.getPercRate(); }
	
	private String trimAbbreviation(String abbrv) {
		if(abbrv.length() > MAX_ABBRV_LENGTH)
			return abbrv.substring(0, MAX_ABBRV_LENGTH).toUpperCase();
		else
			return abbrv.toUpperCase();
	}
	
	private void assignIDnum() {
		idNum = idCounter++;
	}
}
