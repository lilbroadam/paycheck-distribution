import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class PaycheckDistribution {
	
	private static List<Vault> vaults;
	private static int windowWidth = 600;
	private static int windowHeight = 400;
	
	public static void main(String args[]) {
		// initialize back end
		if(vaults == null) // don't overwrite testing data
			vaults = new ArrayList<>();
		// read in vaults from memory TODO
		
		
		// initialize front end
		JFrame gui = new JFrame("Paycheck Distribution");
		
		// Set the viewSelector and detailViewer
		DetailViewer detailViewer = new DetailViewer(null);
		ViewSelector viewSelector = new ViewSelector(detailViewer);
		viewSelector.setPreferredSize(new Dimension(windowWidth / 5, windowHeight)); // TODO needs to adjust when window is resized
		
		/* Set the menu bar
		 * https://www.leepoint.net/GUI/components/menus/menus.html
		 * https://www.javatpoint.com/java-jmenuitem-and-jmenu
		 * 
		 * File  View  Settings  Help
		 */
		JMenuBar menuBar = new JMenuBar();
			JMenu fileMenu = new JMenu("File");
				fileMenu.add(new JMenu("New..."));
				fileMenu.add(new JMenu("Open..."));
				menuBar.add(fileMenu);
			JMenu viewMenu = new JMenu("View");
				for(ViewSelector.Viewable viewable : ViewSelector.Viewable.values()) { // add a view button for every viewable
					JMenuItem menuItem = new JMenuItem(viewable.toString());
					menuItem.addActionListener(new ActionListener() { // MouseListener won't work with JMenuItem, not totally sure why
						public void actionPerformed(ActionEvent e) {
							viewSelector.setItem(viewable);
						}
					});
					viewMenu.add(menuItem);
				}
				menuBar.add(viewMenu);
			JMenu settingsMenu = new JMenu("Settings");
				settingsMenu.add(new JMenuItem("Window size"));
				menuBar.add(settingsMenu);
			JMenu helpMenu = new JMenu("Help");
				helpMenu.add(new JMenuItem("Vault..."));
				helpMenu.add(new JMenuItem("Paycheck..."));
				menuBar.add(helpMenu);
		gui.setJMenuBar(menuBar);
		
		// set everything into the frame
		Container c = gui.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(new JScrollPane(viewSelector), BorderLayout.WEST); // TODO change the scrolling speed and clean this code up
		c.add(detailViewer);
		
		gui.setSize(windowWidth, windowHeight);
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
				Vault vault = (Vault) viewItem;
				
				// prep this JPanel
				setBorder(vault.getName());
				this.setLayout(new BorderLayout(0, 10));
				
				// select the paycheck to apply
				this.add(new JLabel("Select paycheck to apply"), BorderLayout.NORTH);
				// TODO
				
				// display the vault data
				Vector<String> columnNames = new Vector<>();
				columnNames.add("Name"); columnNames.add("Flat"); columnNames.add("Percentage"); columnNames.add("Distribution");
				
				Vector<Vector<String>> vaultData = new Vector<>();
				for(Account acc : vault) {
					Vector<String> accountData = new Vector<>();
					
					accountData.add(acc.getName());
					accountData.add("$" + acc.getFlatRate());
					accountData.add(acc.getPercRate() + "%");
					accountData.add(""); // TODO
					
					vaultData.add(accountData);
				}
				
				JTable vaultDetails = new JTable(vaultData, columnNames);
				// TODO make table non-editable
				
				this.add(new JScrollPane(vaultDetails), BorderLayout.CENTER); // column names won't show up w/o ScrollPane, not sure why
				
			} else if (viewItem instanceof Paycheck) {
				// TODO viewing paycheck details
				
			} else {
				throw new IllegalArgumentException("DetailViewer can only view Vault and Paycheck, received " + viewItem.getClass());
			}
			
			this.revalidate(); // update the gui
		}
		
		private void setBorder(String borderTitle) {
			this.setBorder(BorderFactory.createTitledBorder(borderTitle));
		}
	}
	
	private static class ViewSelector extends JPanel {
		
		private static enum Viewable {
			Vault, Paycheck
		}
		
		DetailViewer viewer;
		
		public ViewSelector(DetailViewer viewer) {
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.viewer = viewer;
			setItem(null);
		}
		
		public void setItem(Viewable viewItem) {
			this.removeAll();
			
			if(viewItem == null) {
				setBorder("Click view to select viewables"); // FIXME doesn't display well
				
			} else if (viewItem == Viewable.Vault) {
				setBorder("Your vaults");
				
				if(vaults.isEmpty())
					this.add(new JLabel("No vaults found"));
				else {
					for(Vault vault : vaults) { // add a button for every vault
						JButton vaultButton = new JButton(vault.getName());
						vaultButton.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								if(SwingUtilities.isLeftMouseButton(e))
									viewer.setItem(vault);
								else if(SwingUtilities.isRightMouseButton(e)) {
									// TODO pop up window that lets you edit the vault
									
									// sliders: https://docs.oracle.com/javase/tutorial/uiswing/components/slider.html
						 			// 			https://www.google.com/search?q=JXMultiThumbSlider&rlz=1C1CHBF_enUS810US810&oq=JXMultiThumbSlider&aqs=chrome..69i57&sourceid=chrome&ie=UTF-8
								}
							}
						});
						
						// TODO set the size of the button to width fill the ViewSelector
						
						this.add(vaultButton);
					}
				}
			} else if (viewItem == Viewable.Paycheck) {
				setBorder("Your paychecks");
				
				// TODO display paychecks
				
			} else {
				throw new IllegalArgumentException("DetailViewer can only view Vault and Paycheck, received " + viewItem.getClass());
			}
			
			this.revalidate(); // update the gui	
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
