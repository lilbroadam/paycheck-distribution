import java.util.ArrayList;
import java.util.List;

/*
 * 
 */

/* TO-DO LIST:
 * - Warning for vaults that don't add up to 100%
 * - When calculating distribution, maybe loop through all the accounts once and store
 *   the data in an array and then extract the data from the array when needed. This
 *   could help avoid looping through the accounts over and over again
 */

public class DistributionManager {
	
	public static double[] distribute(Paycheck paycheck, Vault vault) {
		double[] result = new double[vault.numAccounts()];
		
		
		
		return result;
	}
	
	// print the distribution amounts for the given paycheck into the given vault
	public static void printDistribution(Paycheck paycheck, Vault vault) {
		final double PAYCHECK_AMOUNT = paycheck.getPayAmmount();
		double paycheckRemaining = PAYCHECK_AMOUNT;
		double totalDistributed = 0.00;
		
		
		List<Account> accounts = vault.getAccounts();
		double[] accountDistribution = new double[accounts.size()];
		
		// flat rates are applied first
		for(int i = 0; i < accountDistribution.length; i++) {
			double distributionAmount = accounts.get(i).getFlatRate();
			
			accountDistribution[i] += distributionAmount;
			paycheckRemaining -= distributionAmount;
			totalDistributed += distributionAmount;
		}
		
		// percentage rates are applied last
		double amountForPerc = paycheckRemaining;
		for(int i = 0; i < accountDistribution.length; i++) {
			double distributionPercentage = accounts.get(i).getPercRate() / 100.0; // TODO round down to 2 decimal places
			double distributionAmount = amountForPerc * distributionPercentage;
			
			accountDistribution[i] += distributionAmount;
			paycheckRemaining -= distributionAmount;
			totalDistributed += distributionAmount;
		}
		
		// print
		
		System.out.println("$" + PAYCHECK_AMOUNT + " into " + vault.getName() + ":");
		System.out.println("----------");
		
		for(int i = 0; i < accountDistribution.length; i++) {
			System.out.printf("%" + Account.MAX_ABBRV_LENGTH + "s: $%f\n", accounts.get(i).getAbbreviation(), accountDistribution[i]);
		}
		
		// print total distributed
		System.out.println("----------");
		System.out.println("Amount distributed: " + totalDistributed);
		// display any leftovers and ask the user how they want them to be distributed
		System.out.println("----------");
		System.out.println("Leftovers: " + paycheckRemaining);
	}
}
