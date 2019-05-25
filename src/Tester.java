
public class Tester {
	
	/* Checking: 20%  Savings: 60%
	 * New car:  20%  Phone:  $30
	 */
	
	public static void main(String args[]) {
		Vault vault = new Vault("Vault 1");
		vault.createAccount("Checking", "C", null, 20.0); // TODO change nulls to 0's
		vault.createAccount("Savings", "S", null, 60.0);
		vault.createAccount("New Car", "Car", null, 20.0);
		vault.createAccount("Phone", "P", 30.0, null);
		
		vault.printVault();
		
		DistributionManager.printDistribution(new Paycheck(100), vault);
	}
}
