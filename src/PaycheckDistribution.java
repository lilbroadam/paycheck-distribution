import java.awt.BorderLayout;
import java.awt.Container;

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
	
	public static void main(String args[]) {
		JFrame gui = new JFrame("Paycheck Distribution");
		gui.setLocationRelativeTo(null); // open window in center of screen
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Set the menu bar
		 * https://www.leepoint.net/GUI/components/menus/menus.html
		 * https://www.javatpoint.com/java-jmenuitem-and-jmenu
		 * 
		 * View  Settings  Help
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
		
		// Set the view selector
		JPanel viewSelector = new JPanel();
		viewSelector.setBorder(BorderFactory.createTitledBorder("Your vaults"));
		viewSelector.setLayout(new BoxLayout(viewSelector, BoxLayout.Y_AXIS));
		viewSelector.add(new JButton("Vault 1"));
		viewSelector.add(new JButton("Vault 2"));
		
		JPanel viewDetails = new JPanel();
		viewDetails.setBorder(BorderFactory.createTitledBorder("Selected vault/pc from viewSelector"));
		
		// set everything into the frame
		Container c = gui.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(viewSelector, BorderLayout.WEST);
		c.add(viewDetails);
		
//		gui.pack();
		gui.setSize(600, 400);
		gui.setVisible(true);
	}
	
}
