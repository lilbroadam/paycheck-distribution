import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class PaycheckDistribution {
	
	private static List<Vault> vaults;
	
	public static void main(String args[]) {
		// initialize back end
		if(vaults == null) // don't overwrite testing data
			vaults = new ArrayList<>();
		// read in vaults from memory
		
		// initialize front end
		JFrame gui = new JFrame("Paycheck Distribution");
		gui.setLocationRelativeTo(null); // open window in center of screen
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Set the menu bar
		 * https://www.leepoint.net/GUI/components/menus/menus.html
		 * https://www.javatpoint.com/java-jmenuitem-and-jmenu
		 * 
		 * File  Settings  Help
		 */
		JMenuBar menuBar = new JMenuBar();
			JMenu fileMenu = new JMenu("File");
				fileMenu.add(new JMenu("New..."));
				fileMenu.add(new JMenu("Open..."));
				menuBar.add(fileMenu);
			JMenu settingsMenu = new JMenu("Settings");
				settingsMenu.add(new JMenuItem("Window size"));
				menuBar.add(settingsMenu);
			JMenu helpMenu = new JMenu("Help");
				helpMenu.add(new JMenuItem("Vault..."));
				helpMenu.add(new JMenuItem("Paycheck..."));
				menuBar.add(helpMenu);
		gui.setJMenuBar(menuBar);
		
		JPanel viewDetails = new JPanel();
		viewDetails.setBorder(BorderFactory.createTitledBorder("Select a vault or paycheck to view"));
		
		// Set the view selector
		JPanel viewSelector = new JPanel();
		String viewing = "vaults";
		viewSelector.setLayout(new BoxLayout(viewSelector, BoxLayout.Y_AXIS));
		viewSelector.setBorder(BorderFactory.createTitledBorder("Your " + viewing));
		for(Vault vault : vaults) {
			JButton vaultButton = new JButton(vault.getName());
			vaultButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					viewDetails.removeAll();
					viewDetails.setBorder(BorderFactory.createTitledBorder(vault.getName()));
					viewDetails.revalidate();
				}
			});
			viewSelector.add(vaultButton);
		}
		
		// set everything into the frame
		Container c = gui.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(viewSelector, BorderLayout.WEST);
		c.add(viewDetails);
		
//		gui.pack();
		gui.setSize(600, 400);
		gui.setVisible(true);
	}
	
	public static void testingRun(List<Vault> vaults) {
		PaycheckDistribution.vaults = vaults;
		main(new String[] {});
	}
	
}
