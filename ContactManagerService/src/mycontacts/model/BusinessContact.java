package mycontacts.model;


public class BusinessContact extends Contact {
	
	private String businessHours;
	private String website;
	
	public BusinessContact() {
		
	}
	
	public BusinessContact(String name, String phoneNumber, String address, String email, String businessHours, String website) {
		super(name,phoneNumber,address,email);
		this.businessHours = businessHours;
		this.website = website;
	}

	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public String toString() {
	//  type | id | name | phoneNumber | address | email | businessHours | website
		StringBuilder recordBuilder = new StringBuilder("2").append("|")
				.append(super.toString()).append("|")
				.append(getBusinessHours()).append("|")
				.append(getWebsite());
		return recordBuilder.toString();
	}
}
