import java.util.ArrayList;
import java.util.Arrays;

public class PaycheckDistribution {
	
	 /* C:   20%  GS:  20%
	  * LP:  20%  SS:  10%
	  * LTS: 20%  CRT: 10%
	  */
	
	public static void main(String args[]) {
		// initialize a vault
		Vault vault = new Vault("Vault 1");
		vault.createAccount("Checking", "C", null, 20.0);
		vault.createAccount("General savings", "GS", null, 20.0);
		vault.createAccount("Car", "CAR", null, 20.0); // TODO, default name is abbreviation
		vault.createAccount("Special savings", "SS", null, 10.0);
		
		// add an account object to the vault
		Account acc1 = new Account("Longterm savings", "LTS", 50.0, null);
		vault.addAccount(acc1);
		
		vault.printVault();
		
		// add an anonymous object to the vault
		vault.addAccount(new Account("Certificate", "CRT", null, 10.0));
		
		vault.printVault();
		
		acc1.setPercRate(20.0);
		
		vault.printVault();
		
		DistributionManager.printDistribution(new Paycheck(100), vault);
		
		
		
		Vault uln = new Vault("ULN scholarship");
		uln.createAccount("Chegg subscription", "CHG", 15.94, null);
		uln.createAccount("Spotify", "Sfy", 5.40, null);
		uln.createAccount("Loans", "LN", 40.0, null);
		uln.createAccount("Car", "car", 10.0, null);
		
//		DistributionManager.printDistribution(new Paycheck(500), uln);
		
		
		
		
		// initialize a vault with account objects
//		Account acc2 = new Account("Account2", "Ac2", null, 50.00);
//		Account acc3 = new Account("Account3", "Ac3", null, 50.0);
//		Account acc4 = new Account("Account4", "Ac4", 20.0, null);
//		Vault vault2 = new Vault("Vault 2", acc2, acc3, acc4);
//		vault2.printVault();
		
	}
}
