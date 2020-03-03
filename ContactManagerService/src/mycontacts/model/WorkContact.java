package mycontacts.model;


public class WorkContact extends Contact {
	private String department;
	private String workingHours;
	private String extensionNumber;

	public WorkContact() {
		
	}
	
	public WorkContact(String name, String phoneNumber, String address, String email, String workingHours, String extensionNumber, String department) {
		super(name,phoneNumber,address,email);
		this.workingHours = workingHours;
		this.extensionNumber = extensionNumber;
		this.department = department;
	}
	
	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public String getExtensionNumber() {
		return extensionNumber;
	}

	public void setExtensionNumber(String extensionNumber) {
		this.extensionNumber = extensionNumber;
	}
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
	//  type | id | name | phoneNumber | address | email | workingHours | extensionNumber | department
		StringBuilder recordBuilder = new StringBuilder("1").append("|")
				.append(super.toString()).append("|")
				.append(getWorkingHours()).append("|")
				.append(getExtensionNumber()).append("|")
				.append(getDepartment());
		return recordBuilder.toString();
	}
	
}
