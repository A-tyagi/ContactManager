package contacts.backend;

import java.io.IOException;
import java.util.ArrayList;

import contacts.model.Contact;

public interface ContactManagerInterface {

	ArrayList<Contact> getContactsList();

	long addContact(Contact aNewContact);
	
	void updateContact(Contact updatedContact);

	void deleteContact(long id);
	
	void saveContactList() throws IOException;

}