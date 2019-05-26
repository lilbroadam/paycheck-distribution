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
		// read in vaults from memory TODO
		
		// initialize front end
		JFrame gui = new JFrame("Paycheck Distribution");
		
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
		
		// Set the viewSelector and detailViewer
		JPanel viewSelector = new JPanel(); // TODO create a wrapper class for viewSelector like DetailViewer
		DetailViewer detailViewer = new DetailViewer(null);
		viewSelector.setLayout(new BoxLayout(viewSelector, BoxLayout.Y_AXIS));
		viewSelector.setBorder(BorderFactory.createTitledBorder("Your vaults"));
		if(vaults.isEmpty())
			viewSelector.add(new JLabel("No vaults found"));
		else {
			for(Vault vault : vaults) { // add a button for every vault
				JButton vaultButton = new JButton(vault.getName());
				vaultButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						detailViewer.setItem(vault);
					}
				});
				viewSelector.add(vaultButton);
			}
		}
		
		// set everything into the frame
		Container c = gui.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(viewSelector, BorderLayout.WEST);
		c.add(detailViewer);
		
		gui.setSize(600, 400);
		gui.setLocationRelativeTo(null); // open window in center of screen
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
	}
	
	// A wrapper class for the JPanel that shows the details of the selected Vault or Paycheck
	private static class DetailViewer extends JPanel {
		public DetailViewer(Object viewItem) {
			setItem(viewItem);
		}
		
		public void setItem(Object viewItem) {
			this.removeAll();
			
			if(viewItem == null) {
				setBorder("Select a vault or paycheck to view");
			} else if (viewItem instanceof Vault) {
				setBorder(((Vault) viewItem).getName());
				
				// sliders: https://docs.oracle.com/javase/tutorial/uiswing/components/slider.html
	 			// 			https://www.google.com/search?q=JXMultiThumbSlider&rlz=1C1CHBF_enUS810US810&oq=JXMultiThumbSlider&aqs=chrome..69i57&sourceid=chrome&ie=UTF-8
			} else if (viewItem instanceof Paycheck) {
				// TODO
			} else {
				throw new IllegalArgumentException("DetailViewer can only view Vault and Paycheck, received ");
			}
			
			this.revalidate();
		}
		
		private void setBorder(String borderTitle) {
			this.setBorder(BorderFactory.createTitledBorder(borderTitle));
		}
	}
	
	// A method to run the program with testing data
	public static void testingRun(List<Vault> vaults) {
		PaycheckDistribution.vaults = vaults;
		main(new String[] {});
	}
	
}
