import java.util.ArrayList;

/* A profile is essentially the account of a user
 * Profiles contain the user's different vaults
 */

public class Profile {
	private String name;
	private ArrayList<Vault> vaults = new ArrayList<Vault>();
	
	Profile(String name){
		this.name = name;
	}
	
	public void addProfile(Vault vault) {
		vaults.add(vault);
	}
}
