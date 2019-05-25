import java.util.ArrayList;
import java.util.HashMap;

/* To-do list:
 * - make sure percentages add up to 100%;
 * 
 * 
 */
public class AccountManager {
	
	private HashMap<String, Double> accounts;
	private ArrayList<String> accountNames;
	
	public AccountManager() {
		accounts = new HashMap<String, Double>();
		accountNames = new ArrayList<String>();
	}
	
	public void addAccount(String accountName, Double rate) {
		if(accounts.containsKey(accountName)) {
			throw new IllegalArgumentException();
		} else {
			accounts.put(accountName, rate);
			accountNames.add(accountName);
		}
	}
	public void addAccount(String accountName) {
		addAccount(accountName, null);
	}
	public void addAccount(Double rate, String ... accountName) {
		for(String accName : accountName)
			addAccount(accName, rate);
	}
	public void addAccount(String ... accountName) {
		for(String accName : accountName)
			addAccount(accName, null);
	}
	
	public void setDisRate(String accountName, double rate) {
		accounts.put(accountName, rate);
	}
	
	public double getDisRate(String accountName) {
		return accounts.get(accountName);
	}
	
	public String toString() {
		for(int i = 0; i < accountNames.size(); i++) {
			System.out.println(accountNames.get(i));
		}
		return accounts.toString();
	}
}
