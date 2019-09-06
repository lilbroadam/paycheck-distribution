import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Tester {

	/* Checking: 20%  Savings: 60%
	 * New car:  20%  Phone:  $30
	 */

	private static List<Vault> vaults;

	public static void main(String args[]) {
		Vault vault = new Vault("Vault 1");
		vault.createAccount("Checking", "C", 10.00, 40.00);
		vault.createAccount("Savings", "GS", 20.00, 60.00);

		System.out.print("Paycheck amount: ");
		Paycheck paycheck = new Paycheck(new Scanner(System.in).nextDouble()); // FIXME scanner temp input
		System.out.println();

		DistributionManager.distribute(paycheck, vault); // TODO change to Distributer.distribute(...)

		// testGUI();
	}

	private static void testGUI(){
		vaults = new ArrayList<>();

		Vault vault1 = new Vault("Vault 1");
		vault1.createAccount("Checking", "C", null, 20.0); // TODO change nulls to 0's
		vault1.createAccount("Savings", "S", null, 60.0);
		vault1.createAccount("New Car", "Car", null, 20.0);
		vault1.createAccount("Phone", "P", 30.0, null);

		vault1.printVault();

		DistributionManager.printDistribution(new Paycheck(100), vault1);

		Vault vault2 = new Vault("Vault 2");
		vault2.createAccount("Acc1", null, 100.0);
		vault2.createAccount("Acc2", 10.0, null);

		vaults.add(vault1);
		vaults.add(vault2);

		PaycheckDistribution.testingRun(vaults);
	}

	private static void testViewSelectorScroll() {
		for(int i = 3; i <= 20; i++)
			vaults.add(new Vault("Vault " + i));
	}
}
