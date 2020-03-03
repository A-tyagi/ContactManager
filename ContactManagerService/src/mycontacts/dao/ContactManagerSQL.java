package mycontacts.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mycontacts.model.BusinessContact;
import mycontacts.model.Contact;
import mycontacts.model.FriendContact;
import mycontacts.model.WorkContact;
import mycontacts.test.TestData;

public class ContactManagerSQL implements ContactsDAOInterface {
	
//  private static String jdbcURL = "jdbc:h2:file:/Users/arjun/apps/contactmanager/h2db";
    private static String jdbcURL = "jdbc:h2:mem:h2db";
    private static String jdbcUsername = "sa";
    private static String jdbcPassword = "";

    private static final String createTableSQL = "CREATE TABLE IF NOT EXISTS mycontacts (" +
            "id bigint auto_increment PRIMARY KEY," +
        	"type ENUM('personal', 'work', 'business')," +
            "name VARCHAR(60)," + 
        	"phone VARCHAR(20)," + 
            "address VARCHAR(240)," +
        	"email VARCHAR(120)," + 
        	"nick VARCHAR(60)," + 
        	"social VARCHAR(120)," + 
        	"emergency VARCHAR(20)," + 
        	"dept VARCHAR(30)," + 
        	"hours VARCHAR(20)," + 
        	"ext VARCHAR(10)" +
        	");"; 
    private static final String createNamedIndexSQL = "CREATE INDEX IF NOT EXISTS NAMEINDEX ON mycontacts (name);";
	private final static String insertStmtSQL = "INSERT INTO mycontacts (type, name, phone, address, email, nick, social, emergency, dept, hours, ext)\n" + 
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private final static String readOneSQL = "SELECT * FROM mycontacts WHERE id = ?;";
	private final static String readAllSQL = "SELECT * FROM mycontacts;";
	private final static String deleteStmtSQL = "DELETE FROM mycontacts WHERE id = ?;";
	private final static String updateStmtSQL = "UPDATE mycontacts SET name = ?, phone = ?, address = ?, email = ?, nick = ?, social = ?, emergency = ?, dept = ?, hours = ?, ext = ? WHERE id = ?;";
	Connection connection;

	public ContactManagerSQL () throws Exception {
		createDBConnection();
    	createTable();
    	TestData.createSampleData(this, 20);
	}
	
    private void createDBConnection() throws SQLException {
        try {
			Class.forName("org.h2.Driver");
	        connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public void createTable() throws SQLException {
        try (Statement statement = connection.createStatement();) {
            System.out.println(createTableSQL);
            statement.execute(createTableSQL);
            
            System.out.println(createNamedIndexSQL);
            statement.execute(createNamedIndexSQL);
        } catch (SQLException e) {
        	printSQLException(e);
        	throw e;
        }
    }
    
	public List<Contact> getContactsList() throws SQLException {
		List<Contact> contacts = new ArrayList<Contact>();
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(readAllSQL);
			ResultSet rs = preparedStatement.executeQuery();) {
			System.out.println(preparedStatement);
			while (rs.next()) {
				Contact contact;
				int contactType = rs.getInt("type");
				if (contactType == 0) {
					contact = new FriendContact(rs.getString("name"), rs.getString("phone"), rs.getString("address"),
							rs.getString("email"), rs.getString("nick"), rs.getString("social"),
							rs.getString("emergency"));
				} else if (contactType == 1) {
					contact = new WorkContact(rs.getString("name"), rs.getString("phone"), rs.getString("address"),
							rs.getString("email"), rs.getString("hours"), rs.getString("ext"), rs.getString("dept"));
				} else {
					contact = new BusinessContact(rs.getString("name"), rs.getString("phone"), rs.getString("address"),
							rs.getString("email"), rs.getString("hours"), rs.getString("social"));
				}
				contact.setId(rs.getInt("id"));
				contacts.add(contact);
			}
			return contacts;
		} catch (SQLException e) {
			printSQLException(e);
			throw e;
		}
	}

	public long addContact(Contact newContact) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertStmtSQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(2, newContact.getName());
            preparedStatement.setString(3, newContact.getPhoneNumber());
            preparedStatement.setString(4, newContact.getAddress());
            preparedStatement.setString(5, newContact.getEmail());
        	if (newContact instanceof FriendContact) {
        		FriendContact friendContact = (FriendContact) newContact;
            	preparedStatement.setInt(1, 0);
        		preparedStatement.setString(6, friendContact.getNickname());
        		preparedStatement.setString(7, friendContact.getSocialMedia());
        		preparedStatement.setString(8, friendContact.getEmergencyContact());
        		preparedStatement.setString(9, null);
        		preparedStatement.setString(10, null);
        		preparedStatement.setString(11, null);
        	} 
        	else if (newContact instanceof WorkContact) {
        		WorkContact workContact = (WorkContact) newContact;
            	preparedStatement.setInt(1, 1);
        		preparedStatement.setString(6, null);
        		preparedStatement.setString(7, null);
        		preparedStatement.setString(8, null);
        		preparedStatement.setString(9, workContact.getDepartment());
        		preparedStatement.setString(10, workContact.getWorkingHours());
        		preparedStatement.setString(11, workContact.getExtensionNumber());
            }
        	else {
        		BusinessContact businessContact = (BusinessContact) newContact;
            	preparedStatement.setInt(1, 2);
        		preparedStatement.setString(6, null);
        		preparedStatement.setString(7, businessContact.getWebsite());
        		preparedStatement.setString(8, null);
        		preparedStatement.setString(9, null);
        		preparedStatement.setString(10, businessContact.getBusinessHours());
        		preparedStatement.setString(11, null);
        	}
        	System.out.println(preparedStatement);
        	preparedStatement.executeUpdate();
        	ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        }
    	catch (SQLException e) {
			printSQLException(e);
			throw e;
        }
	}

	public void updateContact(Contact contact) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateStmtSQL)) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getPhoneNumber());
            preparedStatement.setString(3, contact.getAddress());
            preparedStatement.setString(4, contact.getEmail());
        	if (contact instanceof FriendContact) {
        		FriendContact friendContact = (FriendContact) contact;
        		preparedStatement.setString(5, friendContact.getNickname());
        		preparedStatement.setString(6, friendContact.getSocialMedia());
        		preparedStatement.setString(7, friendContact.getEmergencyContact());
        		preparedStatement.setString(8, null);
        		preparedStatement.setString(9, null);
        		preparedStatement.setString(10, null);
        	} 
        	else if (contact instanceof WorkContact) {
        		WorkContact workContact = (WorkContact) contact;
        		preparedStatement.setString(5, null);
        		preparedStatement.setString(6, null);
        		preparedStatement.setString(7, null);
        		preparedStatement.setString(8, workContact.getDepartment());
        		preparedStatement.setString(9, workContact.getWorkingHours());
        		preparedStatement.setString(10, workContact.getExtensionNumber());
            }
        	else {
        		BusinessContact businessContact = (BusinessContact) contact;
        		preparedStatement.setString(5, null);
        		preparedStatement.setString(6, businessContact.getWebsite());
        		preparedStatement.setString(7, null);
        		preparedStatement.setString(8, null);
        		preparedStatement.setString(9, businessContact.getBusinessHours());
        		preparedStatement.setString(10, null);
        	}
        	preparedStatement.setLong(11, contact.getId());
        	System.out.println(preparedStatement);
        	preparedStatement.executeUpdate();
        }
    	catch (SQLException e) {
			printSQLException(e);
			throw e;
        }
	}

	public void deleteContact(long id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteStmtSQL);) {
            preparedStatement.setLong(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
			printSQLException(e);
			throw e;
        }
	}

	public Contact getContact(long id) throws SQLException {
		Contact contact = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(readOneSQL)) {
			preparedStatement.setLong(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				int contact_type = rs.getInt("type");
				if (contact_type == 0) {
					contact = new FriendContact(rs.getString("name"), rs.getString("phone"), rs.getString("address"),
							rs.getString("email"), rs.getString("nick"), rs.getString("social"),
							rs.getString("emergency"));
				} else if (contact_type == 1) {
					contact = new WorkContact(rs.getString("name"), rs.getString("phone"), rs.getString("address"),
							rs.getString("email"), rs.getString("hours"), rs.getString("ext"), rs.getString("dept"));
				} else {
					contact = new BusinessContact(rs.getString("name"), rs.getString("phone"), rs.getString("address"),
							rs.getString("email"), rs.getString("hours"), rs.getString("social"));
				}
				contact.setId(rs.getInt("id"));
			}
			return contact;
		} catch (SQLException e) {
			printSQLException(e);
			throw e;
		}
	}

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

	@Override
	public void saveContactList() throws Exception {
		throw new UnsupportedOperationException();
	}
	
	

}