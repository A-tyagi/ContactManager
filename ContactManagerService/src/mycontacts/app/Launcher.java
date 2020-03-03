package mycontacts.app;

import java.awt.*;

import mycontacts.dao.ContactManagerRESTClient;
import mycontacts.dao.ContactsDAOInterface;
import mycontacts.app.gui.GuiManager;

public class Launcher {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactsDAOInterface contactManager = new ContactManagerRESTClient();
					new GuiManager(contactManager);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
