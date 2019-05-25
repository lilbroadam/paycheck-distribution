
/* A data type to manage the quotas for accounts
 * Flat rates are applied first when distributing paychecks, then percentages
 */

/* TO-DO LIST:
 * - throw exception if percRate given is > 100
 * 
 */

public class Quota {
	private double flatRate;
	private double percRate;
	
	public Quota(Double flat, Double perc) {
		if(flat != null)
			flatRate = flat;
		if(perc != null)
			percRate = perc;
	}

	public void setFlatRate(double flat) { flatRate = flat; }
	
	public void setPercRate(double perc) { percRate = perc; }
	
	public double getFlatRate() { return flatRate; }
	
	public double getPercRate() { return percRate; }
}
