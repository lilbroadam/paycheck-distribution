import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

	public static Distribution distribute(Paycheck paycheck, Vault vault) {
		Distribution distribution = new Distribution(vault);

		List<Account> accounts = vault.getAccounts();
		double paycheckRemaining = paycheck.getPayAmount();

		// distribute flat rates first
		for(Account acc : accounts) {
			distribution.addAmount(acc.getName(), acc.getFlatRate());
			paycheckRemaining -= acc.getFlatRate();
		}

		// distribute perc rates second (last)
		double percDistPoolAmount = paycheckRemaining;
		for(Account acc : accounts) {
			double percDist = percDistPoolAmount * (acc.getPercRate() / 100.0);
			distribution.addAmount(acc.getName(), percDist);
			paycheckRemaining -= percDist;
		}

		// TODO check for arithmatic/rounding errors
		System.out.println(paycheckRemaining == 0.0 ? "No remainder left over" : "remainder: " + paycheckRemaining);

		// TODO delete temporary console output
		distribution.printDistribution();
		
		// TODO write to disk
		recordDistribution(distribution);

		return distribution;
	}
	
	private static void recordDistribution(Distribution distribution) {
		// TODO record distribution (write to disk)
	}
	
	// print the distribution amounts for the given paycheck into the given vault
	// TODO probably should delete this; out dated
	public static void printDistribution(Paycheck paycheck, Vault vault) {
		final double PAYCHECK_AMOUNT = paycheck.getPayAmount();
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

class Distribution {
	
	Map<String, Double> distribution;
	
	public Distribution(Vault vault) {
		distribution = new TreeMap<>();
	}
	
	public void addAmount(String account, double amount) {
		// populate the distribution map
		if(!distribution.containsKey(account))
			distribution.put(account, new Double(amount));
		else
			distribution.put(account, new Double(distribution.get(account) + amount));
	}
	
	public void printDistribution() {
		for(String acc : distribution.keySet())
			System.out.println(acc + ": $" + distribution.get(acc));
	}
	
	public String toString() {
		return distribution.toString();
	}
}
