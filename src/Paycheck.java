
/*
 * 
 */

/* TO-DO LIST:
 * - Paycheck should not hold more than 2 decimal places 
 */

public class Paycheck {

	private double payAmount;
	// payDate;
	
	Paycheck(double amount){
		payAmount = amount;
	}
	
	public double getPayAmmount() { return payAmount; }
}
