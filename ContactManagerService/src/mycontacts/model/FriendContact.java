package mycontacts.model;


public class FriendContact extends Contact {
	private String nickname;
	private String socialMedia;
	private String emergencyContact;
	
	public FriendContact() {
		
	}
	
	public FriendContact(String name, String phoneNumber, String address, String email, String nickname, String socialMedia, String emergencyContact) {
		super(name,phoneNumber,address,email);
		this.nickname = nickname;
		this.socialMedia = socialMedia;
		this.emergencyContact = emergencyContact;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSocialMedia() {
		return socialMedia;
	}

	public void setSocialMedia(String socialMedia) {
		this.socialMedia = socialMedia;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	@Override
	public String toString() {
		//  type | id | name | phoneNumber | address | email | nickname | socialMedia | emergencyContact
		StringBuilder recordBuilder = new StringBuilder("0").append("|")
				.append(super.toString()).append("|")
				.append(getNickname()).append("|")
				.append(getSocialMedia()).append("|")
				.append(getEmergencyContact());
		return recordBuilder.toString();
	}
	
	
}
