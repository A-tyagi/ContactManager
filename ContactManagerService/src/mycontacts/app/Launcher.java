import java.awt.*;

import contacts.backend.ContactManager;
import contacts.backend.ContactManagerInterface;
import contacts.gui.GuiManager;

public class Launcher {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactManagerInterface contactManager = new ContactManager();
					new GuiManager(contactManager);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
