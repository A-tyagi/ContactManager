package mycontacts.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(
	  use = JsonTypeInfo.Id.NAME, 
	  include = JsonTypeInfo.As.PROPERTY, 
	  property = "type")
	@JsonSubTypes({ 
	  @Type(value = FriendContact.class, name = "Friend"), 
	  @Type(value = WorkContact.class, name = "Work"),
	  @Type(value = BusinessContact.class, name = "Business")
	})
public abstract class Contact {
	private String name;
	private String phoneNumber;
	private String address;
	private String email;
	private long id;
	
	public Contact() {
		
	}
	
	public Contact(String name, String phoneNumber, String address, String email) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String firstName) {
		this.name = firstName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String toString() {
		// id | name | phoneNumber | address | email 
		StringBuilder recordBuilder = new StringBuilder()
				.append(getId()).append("|")
				.append(getName()).append("|")
				.append(getPhoneNumber()).append("|")
				.append(getAddress()).append("|")
				.append(getEmail());
		return recordBuilder.toString();
	}
}
