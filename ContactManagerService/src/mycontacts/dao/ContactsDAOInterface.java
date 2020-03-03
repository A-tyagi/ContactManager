package mycontacts.dao;
import java.util.List;

import mycontacts.model.Contact;

public interface ContactsDAOInterface {
	long addContact(Contact aNewContact) throws Exception;
	Contact getContact(long id) throws Exception;
	List<Contact> getContactsList() throws Exception;
	void updateContact(Contact updatedContact) throws Exception;
	void deleteContact(long id) throws Exception;
	void saveContactList() throws Exception;
}