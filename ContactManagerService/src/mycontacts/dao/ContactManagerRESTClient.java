package mycontacts.dao;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mycontacts.model.Contact;

public class ContactManagerRESTClient implements ContactsDAOInterface {

	Client client;
	WebTarget webTarget;

	public ContactManagerRESTClient() {
		webTarget = ClientBuilder.newClient().target("http://localhost:8080/ContactsManager/rest");
//		webTarget = ClientBuilder.newClient().target("http://mycontacts.us-west-1.elasticbeanstalk.com/rest");
	}
	
	@Override
	public long addContact(Contact aNewContact) throws Exception {
		Response response;
		response = webTarget.path("/contacts")
				.request( MediaType.APPLICATION_JSON)
				.post(Entity.entity(aNewContact, MediaType.APPLICATION_JSON));
		return response.readEntity(Long.class);
	}

	@Override
	public Contact getContact(long id) throws Exception {
		return webTarget.path("/contacts/" + id).request(MediaType.APPLICATION_JSON).get(Contact.class);
	}

	@Override
	public List<Contact> getContactsList() throws Exception {
		return webTarget.path("/contacts").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Contact>>(){});
	}

	@Override
	public void updateContact(Contact updatedContact) throws Exception {
		webTarget.path("/contacts/" + updatedContact.getId()).request(MediaType.APPLICATION_JSON).put(Entity.entity(updatedContact, MediaType.APPLICATION_JSON));
	}

	@Override
	public void deleteContact(long id) throws Exception {
		webTarget.path("/contacts/" + id).request().delete();
	}

	@Override
	public void saveContactList() throws Exception {
		throw new UnsupportedOperationException();
	}
	
	public static void main(String[] args) {
		ContactManagerRESTClient contactsDAO = new ContactManagerRESTClient();
		
//        Contact friend1 = new FriendContact("name1", "phone1", "address1", "email1", "nick1", "social1", "emergency1");
//        Contact friend5 = new FriendContact("name1", "phone1","address1","email1", "nick1", "social1", "emergency1");

        try {
//			long response = contactsDAO.addContact(friend1);
        	List<Contact> myContacts = contactsDAO.getContactsList();
        	for (int i = 0; i < myContacts.size(); i++){
        		System.out.println(myContacts.get(i));
        	}
        	
            Contact friend38 = contactsDAO.getContact(38);
            System.out.println(friend38);
            friend38.setName("Name38");
            friend38.setAddress("address38");
            contactsDAO.updateContact(friend38);
            Contact updated_friend38 = contactsDAO.getContact(38);
            System.out.println(updated_friend38);
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
