package mycontacts.test;

import mycontacts.dao.ContactsDAOInterface;
import mycontacts.model.BusinessContact;
import mycontacts.model.Contact;
import mycontacts.model.FriendContact;
import mycontacts.model.WorkContact;

public class TestData {
	public static void createSampleData(ContactsDAOInterface contacsDAO, int count) throws Exception {
		String [] surnames= {"Smith","Johnson","Williams","Brown","Jones","Miller","Davis","Garcia","Rodriguez","Wilson","Martinez","Anderson","Taylor","Thomas","Hernandez","Moore","Martin","Jackson","Thompson"};
		String [] firstNames= {"James", "Mary", "John", "Patricia", "Linda", "Robert", "Barbara", "Michael","Jennifer","William","Maria","David","Susan","Richard","Margaret","Charles", "Dorothy","Joseph","Lisa",
				"Thomas","Nancy","Christopher","Karen","Daniel","Betty","Paul","Helen","Mark","Sandra","Donald","Donna","George","Carol","Kenneth","Ruth","Steven","Sharon","Edward","Michelle","Brian","Ronald"};
		String [] phoneNumberAreaCodes = {"510", "650", "408", "925" };
		String [] emailtypes = {"@gmail.com", "@yahoo.com", "@live.com", "@me.com", "@fb.com"};
		String [] streetNames = {"N Blue Gum St", "Blue Ridge Blvd", "W Cerritos Ave", "Main St", "Center St", "Mcauley Dr", 
				"Eads St", "W Jackson Blvd", "Boston Ave", "Runamuck Pl", "Jerrold Ave", "E 75th St", "Connecticut Ave Nw", "E Morehead St", "State Road",
				"E Carrillo St", "New Horizon Blvd", "Manchester Blvd", "S 33rd St", "Greenleaf Ave","W Yakima Ave"};
		String [] cities = {"New Orleans", "Brighton", "Bridgeport", "Anchorage", "Hamilton", "Ashland", "Chicago", "San Jose", "Sioux Falls", "Baltimore", "Kulpsville",
				"Los Angeles", "Milwaukee", "Rockford", "Aston", "San Jose", "Irving", "Albany", "Shawnee" };
		String [] states = {"AL","AK","AS","AZ","AR","CA","CO","CT","DE","DC","FM","FL","GA","GU","HI","ID","IL","IN","IA","KS","KY","LA","ME","MH","MD","MA","MI","MN","MS",
		                    "MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","MP","OH","OK","OR","PW","PA","PR","RI","SC","SD","TN","TX","UT","VT","VI","VA","WA","WV","WI","WY"};
		
		String [] depts = {"HR", "IT", "FIN", "JAN", "ENG", "MGMT", "PR" };
		String [] business = { "Pizza Hut", "Tutti Frutti", "Shalimar", "Home Depot", "Costco", "Site for Sore Eyes", "Lenscrafters", "Whole Foods", "Supercuts", "Macys", "Footlocker"};
		java.util.Random random = new java.util.Random(System.currentTimeMillis());
		
		for (int i = 0; i < count; i++) {
			Contact contact;
			String phoneNumber = phoneNumberAreaCodes[random.nextInt(phoneNumberAreaCodes.length)] + "-" + String.format("%03d", random.nextInt(1000)) + "-" +  String.format("%04d", random.nextInt(10000));
			String address = String.valueOf(random.nextInt(10000)) + " " + streetNames[random.nextInt(streetNames.length)] + ", " 
					+ cities[random.nextInt(cities.length)] + ", " + states[random.nextInt(states.length)] + " " + String.format("%05d", random.nextInt(100000));

			int type = random.nextInt(3);
			if (type == 0) {
				String name = firstNames[random.nextInt(firstNames.length)] + " " + surnames[random.nextInt(surnames.length)];
				String email = name.replaceAll("[aeiouAEIOU ]", "") + emailtypes[random.nextInt(emailtypes.length)];
				contact = new FriendContact(name, phoneNumber, address, email, name.substring(0, 5), 
						email.substring(email.indexOf('@')+1),
						phoneNumberAreaCodes[random.nextInt(phoneNumberAreaCodes.length)] + "-" + String.valueOf(random.nextInt(1000)) + "-" +  String.valueOf(random.nextInt(10000)));
			} 
			else if (type == 1) {
				String name = firstNames[random.nextInt(firstNames.length)] + " " + surnames[random.nextInt(surnames.length)];
				String email = name.replaceAll("[aeiouAEIOU ]", "") + emailtypes[random.nextInt(emailtypes.length)];
				contact = new WorkContact(name, phoneNumber, address, email, String.valueOf(4 + random.nextInt(7)) + "AM-" + String.valueOf(2 + random.nextInt(9)) + "PM",  
						String.format("%03d", random.nextInt(1000)), depts[random.nextInt(depts.length)]);
			} 
			else {
				String name = business[random.nextInt(business.length)];
				String email = name.replaceAll("[ ]", "") + "@" + name.replaceAll("[ ]", "") + ".biz";
				contact = new BusinessContact(name, phoneNumber, address, email, String.valueOf(4 + random.nextInt(7)) + "AM-" + String.valueOf(2 + random.nextInt(9)) + "PM",  "www." +  name.replaceAll("[ ]", "")+ ".com");
			}
			contacsDAO.addContact(contact);
		}
	}
}
