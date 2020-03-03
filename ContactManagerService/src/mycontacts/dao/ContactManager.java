package contacts.backend;

import contacts.model.*;
import java.util.ArrayList;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Scanner;

public class ContactManager implements ContactManagerInterface {

	ArrayList<Contact> contactsList;

	public ContactManager() throws FileNotFoundException {
		contactsList = new ArrayList<Contact>();
		
		File contactsFile = new File("contact_data.txt");
		Scanner filescanner = new Scanner(contactsFile);
		try {
			while(filescanner.hasNextLine()){
				String contactDataEntry = filescanner.nextLine().trim();
				if (contactDataEntry.length() == 0)
					continue;
				parseContactEntry(contactDataEntry);
			}
		}
		finally {
			filescanner.close();
		}
	}
	
	/* (non-Javadoc)
	 * @see contacts.ContactManagerInterface#getContactsList()
	 */
	@Override
	public ArrayList<Contact> getContactsList() {
		return contactsList;
	}
	
	/* (non-Javadoc)
	 * @see contacts.ContactManagerInterface#addContact(contacts.model.Contact)
	 */
	@Override
	public long addContact(Contact aNewContact) {
		long id = Instant.now().toEpochMilli();
		aNewContact.setId(id);
		contactsList.add(aNewContact);
		return id;
	}
	
	/* (non-Javadoc)
	 * @see contacts.ContactManagerInterface#saveContactList()
	 */
	@Override
	public void saveContactList() throws IOException {
		File contactsFile = new File("contact_data.txt");
		FileWriter fileWriter = new FileWriter(contactsFile, false);
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    String contactRecordString;
	    for (Contact aContact:contactsList) {
	    	contactRecordString = aContact.toString();
	    	printWriter.println(contactRecordString);
	    }
	    printWriter.close();
	    fileWriter.close();
	}
	
	
	@Override
	public void updateContact(Contact updatedContact) {
		int index = 0;
		for (Contact aContact:contactsList) {
			if (aContact.getId() == updatedContact.getId()) {
				contactsList.set(index, updatedContact);
				break;
			}
			index++;
		}
	}

	@Override
	public void deleteContact(long idToDelete) {
		int index = 0;
		for (Contact aContact:contactsList) {
			if (aContact.getId() == idToDelete) {
				contactsList.remove(index);
				break;
			}
			index++;
		}
	}

	private void parseContactEntry(String contactDataEntry) {
		String[] contactTokens = contactDataEntry.split("\\|");
		if (contactTokens.length < 2 ) // ignoring records with no data
			return;
		int firstDigit = Integer.parseInt(contactTokens[0]);
		Contact aNewContact = null;
		if (firstDigit == 0) {
			aNewContact = createFriendContactObject(contactTokens);
		}
		else if (firstDigit == 1) {
			aNewContact = createWorkContactObject(contactTokens);
		}
		else if(firstDigit == 2){
			aNewContact = createBusinessContactObject(contactTokens);
		}
		else {
			throw new IllegalArgumentException("Error, file contains incorrect data!");//runtime exception
		}
		contactsList.add(aNewContact);
	}
	
	private FriendContact createFriendContactObject(String[] anArray) {
		// public FriendContact(String name, String phoneNumber, String address, String email, String nickname, String socialMedia, String emergencyContact)
		FriendContact tempFriendContact = new FriendContact(
				((anArray.length > 2)? anArray[2]:""),  // Name
				((anArray.length > 3)? anArray[3]:""),  // Phone
				((anArray.length > 4)? anArray[4]:""),  // Address
				((anArray.length > 5)? anArray[5]:""),  // Email
				((anArray.length > 6)? anArray[6]:""),  // Nick
				((anArray.length > 7)? anArray[7]:""),  // Social
				((anArray.length > 8)? anArray[8]:"")); // Emergency
		tempFriendContact.setId(Long.parseLong(anArray[1]));
		return tempFriendContact;
	}
	
	private WorkContact createWorkContactObject(String[] anArray) {
		// public WorkContact(String name, String phoneNumber, String address, String email, String workingHours, String extensionNumber, String department)
		WorkContact tempWorkContact = new WorkContact(
				((anArray.length > 2)? anArray[2]:""),
				((anArray.length > 3)? anArray[3]:""),
				((anArray.length > 4)? anArray[4]:""),
				((anArray.length > 5)? anArray[5]:""),
				((anArray.length > 6)? anArray[6]:""),  // Work Hours
				((anArray.length > 7)? anArray[7]:""),  // Extn
				((anArray.length > 8)? anArray[8]:"")); // Department
		tempWorkContact.setId(Long.parseLong(anArray[1]));
		return tempWorkContact;
	}
	
	private BusinessContact createBusinessContactObject(String[] anArray) {
		// public BusinessContact(String name, String phoneNumber, String address, String email, String businessHours, String website)
		BusinessContact tempBusinessContact = new BusinessContact(				
				((anArray.length > 2)? anArray[2]:""),
				((anArray.length > 3)? anArray[3]:""),
				((anArray.length > 4)? anArray[4]:""),
				((anArray.length > 5)? anArray[5]:""),
				((anArray.length > 6)? anArray[6]:""),  // Business Hrs
				((anArray.length > 7)? anArray[7]:"")); // Web site
		tempBusinessContact.setId(Long.parseLong(anArray[1]));
		return tempBusinessContact;
	}
	
}
