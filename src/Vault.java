import java.util.ArrayList;
import java.util.NoSuchElementException;

/* Vaults contain and manage accounts.
 * Vaults can have different configurations of accounts
 * Paychecks will be distributed among the accounts in a vault
 * The vault makes sure that the sum percentage of all accounts adds up to 100%, 
 * 	if not, it will treat the total as 100%
 */

/* TO-DO LIST:
 * - make createAccount(String name) and figure out how to manage an account with no quota
 * - better account ID management. Account ID's should be based off the vault, not accounts purely individual
 * - search for account by ID number
 * - can't let flat rates add up to > a paycheck
 *   - idk if this task goes in the vault
 * - Add order of accounts (this is not ID numbers)
 *   - a way to reorder accounts
 */

public class Vault {
	
	private String name;
	private ArrayList<Account> accounts = new ArrayList<Account>();
	
	private double percTotal;

	public Vault(String vaultName) {
		name = vaultName;
	}
	
	public Vault(String vaultName, Account ... initialAccounts) {
		this(vaultName);
		
		for(Account acc : initialAccounts)
			accounts.add(acc);
	}
	
	public void createAccount(String name, String abbrv, Double flat, Double perc) {
		// throw exception if name or abbreviation is already taken
		if(searchForAccount(name) != null || searchForAccount(abbrv) != null)
			throw new IllegalArgumentException("The account name \"" + name + "\" and/or abbreviation \"" + abbrv + "\" is already in use");
		
		accounts.add(new Account(name, abbrv, flat, perc));
	}
	
	public void createAccount(String name, Double flat, Double perc) {
		createAccount(name, name, flat, perc);
	}
	
	public void addAccount(Account ... account) {
		for(Account acc : account)
			accounts.add(acc);
	}
	
	public int numAccounts() { return accounts.size(); }
	
	public void calculatePercTotal() {
		percTotal = 0;
		
		for(Account acc : accounts) {
			percTotal += acc.getPercRate();
		}
	}
	
	public double percTotal() { 
		calculatePercTotal();
		return percTotal;
	}
	
	public boolean checkPercTotal() {
		return percTotal() == 100;
	}
	
	public String getName() { return name; }
	
	public ArrayList<Account> getAccounts() { return accounts; }
	
	// search for and return the given account by name or abbreviation.
	// return a NoSuchElementException if account isn't found
	public Account getAccount(String searchToken) {
		Account acc = searchForAccount(searchToken);
		
		// if (acc == null) { throw exception } else {return acc}
		
		try {
			acc.getName();
		} catch (NullPointerException e) {
			throw new NoSuchElementException("Account \"" + searchToken + "\" wasn't found");
		}
		
		return acc;
	}
	
	// searches for the given account and returns it if found, returns null if it isn't found
	private Account searchForAccount(String searchToken) {
		for(Account acc : accounts) {
			if(acc.getName().equalsIgnoreCase(searchToken)) {
				return acc;
			} else if (acc.getAbbreviation().equalsIgnoreCase(searchToken)) {
				return acc;
			}
		}
		
		return null;
	}
	
	public void printVault() {
		System.out.println(name + ":");
		System.out.println("----------");
		
		for(Account acc : accounts)
			System.out.printf("%3s: $%.2f | %.2f%%\n", acc.getAbbreviation(), acc.getFlatRate(), acc.getPercRate());
		
		if(!checkPercTotal()) {
			System.out.println("Warning: the total percentage of the volt is " + percTotal + "%, not 100%");
		}
	}
}
