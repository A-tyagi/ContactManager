package contacts.gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import contacts.backend.ContactManagerInterface;
import contacts.model.BusinessContact;
import contacts.model.Contact;
import contacts.model.FriendContact;
import contacts.model.WorkContact;

public class GuiManager {

	private JFrame frmContactManager;
	private JTable showAllTable;
	private JTable friendTable;
	private JTable workTable;
	private JTable businessTable;
	private ContactManagerInterface contactManager;
	DefaultTableModel allTableModel;
	DefaultTableModel friendsTableModel;
	DefaultTableModel workTableModel; 
	DefaultTableModel businessTableModel;
	JTabbedPane tabbedPane;
	JButton btnDeleteContact;
	
	/**
	 * Create the application.
	 */
	public GuiManager(ContactManagerInterface contactManager) {
		this.contactManager = contactManager;
		initialize(); 
		frmContactManager.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmContactManager = new JFrame();
		frmContactManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmContactManager.setSize(1000,550);
		frmContactManager.setTitle("Contact Manager");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 123, 723, 0};
		gridBagLayout.rowHeights = new int[]{97, 29, 0, 217, 137, -55};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmContactManager.getContentPane().setLayout(gridBagLayout);
		
		JButton btnAddContact = new JButton("Add Contact");		
		btnAddContact.addActionListener(new AddContactButtonListener());
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 1;
		frmContactManager.getContentPane().add(horizontalStrut, gbc_horizontalStrut);
		btnAddContact.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		GridBagConstraints gbc_btnAddContact = new GridBagConstraints();
		gbc_btnAddContact.anchor = GridBagConstraints.NORTH;
		gbc_btnAddContact.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddContact.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddContact.gridx = 1;
		gbc_btnAddContact.gridy = 1;
		frmContactManager.getContentPane().add(btnAddContact, gbc_btnAddContact);
		
		JButton btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.addActionListener(new SaveButtonListener());
		
		btnDeleteContact = new JButton("Delete Contact");
		btnDeleteContact.addActionListener(new DeleteContactButtonListener());
		
		GridBagConstraints gbc_btnDeleteContact = new GridBagConstraints();
		gbc_btnDeleteContact.anchor = GridBagConstraints.NORTH;
		gbc_btnDeleteContact.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteContact.gridx = 1;
		gbc_btnDeleteContact.gridy = 2;
		frmContactManager.getContentPane().add(btnDeleteContact, gbc_btnDeleteContact);
		btnSaveChanges.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		GridBagConstraints gbc_btnSaveChanges = new GridBagConstraints();
		gbc_btnSaveChanges.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSaveChanges.anchor = GridBagConstraints.NORTH;
		gbc_btnSaveChanges.insets = new Insets(0, 0, 5, 5);
		gbc_btnSaveChanges.gridx = 1;
		gbc_btnSaveChanges.gridy = 4;
		frmContactManager.getContentPane().add(btnSaveChanges, gbc_btnSaveChanges);
		btnDeleteContact.setEnabled(false);
		
		String[] showAllColumnNames = {"Name",
                "Phone Number",
                "Email",
                "Address",
                "Id"};
		
		String[] friendColumnNames = {"Name",
              
                "Phone Number",
                "Email",
                "Address",
                "Nickname",
                "Social Media",
                "Emergency Cont.",
                "Id"};

		String[] workColumnNames = {"Name",
                "Phone Number",
                "Email",
                "Address",
                "Department",
                "Working Hours",
                "Extension #",
                "Id"};

		String[] businessColumnNames = {"Business",
                "Phone Number",
                "Email",
                "Address",
                "Hours Open",
                "Website",
                "Id"};
		
		allTableModel = new MyTableModel(null, showAllColumnNames); // Show All contacts table cells are non editable;
		friendsTableModel = new DefaultTableModel(null, friendColumnNames);
		workTableModel = new DefaultTableModel(null, workColumnNames);
		businessTableModel = new DefaultTableModel(null, businessColumnNames);
		
		friendsTableModel.addTableModelListener(new FriendsChangeListener());
		workTableModel.addTableModelListener(new WorkChangeListener());
		businessTableModel.addTableModelListener(new BusinessChangeListener());
	
		populateTableRows();
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new TabbePaneChangeListener());
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridwidth = 5;
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridheight = 6;
		gbc_tabbedPane.gridx = 2;
		gbc_tabbedPane.gridy = 0;
		frmContactManager.getContentPane().add(tabbedPane, gbc_tabbedPane);
		
		showAllTable = new JTable(allTableModel);
		showAllTable.setRowSelectionAllowed(false);
		TableColumnModel showAllTcm = showAllTable.getColumnModel();
		showAllTcm.removeColumn(showAllTcm.getColumn(4));
		JScrollPane showAllPane = new JScrollPane(showAllTable);
		tabbedPane.addTab("Show All", null, showAllPane, null);
		
		friendTable = new JTable(friendsTableModel);
		friendTable.getSelectionModel().addListSelectionListener(new RowSelectedListener());
		TableColumnModel friendTcm = friendTable.getColumnModel();
		friendTcm.removeColumn(friendTcm.getColumn(7));
		friendTable.setRowSelectionAllowed(true);
		JScrollPane friendPane = new JScrollPane(friendTable);
		tabbedPane.addTab("Friend", null, friendPane, null);
		
		workTable = new JTable(workTableModel);
		workTable.getSelectionModel().addListSelectionListener(new RowSelectedListener());
		TableColumnModel workTcm = workTable.getColumnModel();
		workTcm.removeColumn(workTcm.getColumn(7));
		workTable.setRowSelectionAllowed(true);
		JScrollPane workPane = new JScrollPane(workTable);
		tabbedPane.addTab("Work", null, workPane, null);
		
		businessTable = new JTable(businessTableModel);
		businessTable.getSelectionModel().addListSelectionListener(new RowSelectedListener());
		TableColumnModel businessTcm = businessTable.getColumnModel();
		businessTcm.removeColumn(businessTcm.getColumn(6));
		JScrollPane businessPane = new JScrollPane(businessTable);
		tabbedPane.addTab("Business", null, businessPane, null);

	}
	
	
	private void populateTableRows() {
		ArrayList<Contact> contcts = contactManager.getContactsList();
		for (Contact aContact:contcts) {
			if (aContact instanceof FriendContact) {
				friendsTableModel.addRow(createFriendContactRow((FriendContact)aContact));
				allTableModel.addRow(createAllContactRow(aContact));
			}
			else if (aContact instanceof WorkContact) {
				workTableModel.addRow(createWorkContactRow((WorkContact)aContact));
				allTableModel.addRow(createAllContactRow(aContact));
			}
			else { // if BusinessContact
				businessTableModel.addRow(createBusinessContactRow((BusinessContact) aContact));
				allTableModel.addRow(createAllContactRow(aContact));
			}
		}
	}
	
	private void repopulateShowAllTableRows() {
		ArrayList<Contact> contcts = contactManager.getContactsList();
		allTableModel.setRowCount(0);
		for (Contact aContact:contcts) {
			allTableModel.addRow(createAllContactRow(aContact));
		}
	}
	
	public void addFriendContact(FriendContact tempFriend) {
		contactManager.addContact(tempFriend);
		friendsTableModel.addRow(createFriendContactRow(tempFriend));
		allTableModel.addRow(createAllContactRow(tempFriend));
	}
	
	public void addWorkContact(WorkContact tempWork) {
		contactManager.addContact(tempWork);
		workTableModel.addRow(createWorkContactRow(tempWork));
		allTableModel.addRow(createAllContactRow(tempWork));
	}
	
	public void addBusinessContact(BusinessContact tempBusiness) {
		contactManager.addContact(tempBusiness);
		businessTableModel.addRow(createBusinessContactRow(tempBusiness));
		allTableModel.addRow(createAllContactRow(tempBusiness));
	}
	
	private Object[] createFriendContactRow(FriendContact aFriendContact) {
		Object[] data = { aFriendContact.getName(), aFriendContact.getPhoneNumber(), aFriendContact.getEmail(),
				aFriendContact.getAddress(), aFriendContact.getNickname(), aFriendContact.getSocialMedia(),
				aFriendContact.getEmergencyContact(), aFriendContact.getId()};
		return data;
	}

	Object[] createWorkContactRow(WorkContact aWorkContact) {
		Object[] data = { aWorkContact.getName(), aWorkContact.getPhoneNumber(), aWorkContact.getEmail(),
				aWorkContact.getAddress(), aWorkContact.getDepartment(), aWorkContact.getWorkingHours(),
				aWorkContact.getExtensionNumber(), aWorkContact.getId()};
		return data;
	}

	Object[] createBusinessContactRow(BusinessContact aBusinessContact) {
		Object[] data = { aBusinessContact.getName(), aBusinessContact.getPhoneNumber(), aBusinessContact.getEmail(),
				aBusinessContact.getAddress(), aBusinessContact.getBusinessHours(), aBusinessContact.getWebsite(), aBusinessContact.getId() };
		return data;
	}
	
	Object[] createAllContactRow(Contact aContact) {
		Object[] data = { aContact.getName(), aContact.getPhoneNumber(), aContact.getEmail(),
				aContact.getAddress(), aContact.getId() };
		return data;
	}
	
	@SuppressWarnings("serial")
	private class MyTableModel extends DefaultTableModel {
		
		public MyTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}
	
	// Listens to change in tab selection
	// If Show All tab, Delete button is set to disabled always 
	// For all other tabs Delete button is disabled if no row is selected
	private class TabbePaneChangeListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
        	int currentTabIndex = tabbedPane.getSelectedIndex();
        	int selectedRow = -1;
        	
			if (currentTabIndex == 0) {
				btnDeleteContact.setEnabled(false);
			}
			else if (currentTabIndex == 1){
				selectedRow = friendTable.getSelectedRow();
				if (selectedRow == -1)
					btnDeleteContact.setEnabled(false);
				else
					btnDeleteContact.setEnabled(true);
			}
			else if (currentTabIndex == 2) {
				selectedRow = workTable.getSelectedRow();
				if (selectedRow == -1)
					btnDeleteContact.setEnabled(false);
				else
					btnDeleteContact.setEnabled(true);
			}
			else {
				selectedRow = businessTable.getSelectedRow();
				if (selectedRow == -1)
					btnDeleteContact.setEnabled(false);
				else
					btnDeleteContact.setEnabled(true);
			}
        }
    }
	
	private class RowSelectedListener implements ListSelectionListener{
	        public void valueChanged(ListSelectionEvent event) {
	        	btnDeleteContact.setEnabled(true);
	        }
	    }
	 
	private class AddContactButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			NewContact myAddcontactPane = new NewContact(GuiManager.this);
			myAddcontactPane.setVisible(true);
		}
	}
	
	private class DeleteContactButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int currentTabIndex = tabbedPane.getSelectedIndex();
			System.out.println("selected pane index : " + currentTabIndex);
			String idToDelete = null;
			int selectedRow = -1;
			
			if (currentTabIndex == 0) {
				selectedRow = showAllTable.getSelectedRow();
				if (selectedRow != -1)
					idToDelete = allTableModel.getValueAt(selectedRow, 4).toString();
			}
			else if (currentTabIndex == 1) {
				selectedRow = friendTable.getSelectedRow();
				if (selectedRow != -1)
					idToDelete = friendsTableModel.getValueAt(selectedRow, 7).toString();
			}
			else if (currentTabIndex == 2) {
				selectedRow = workTable.getSelectedRow();
				if (selectedRow != -1)
					idToDelete = workTableModel.getValueAt(selectedRow, 7).toString();
			}
			else {
				selectedRow = businessTable.getSelectedRow();
				if (selectedRow != -1)
					idToDelete = businessTableModel.getValueAt(selectedRow, 6).toString();
			}
			
			System.out.println("delete" + " Row: " + selectedRow);
			System.out.println("id of the row deleted: " + idToDelete);
			
			if (selectedRow != -1) {
				contactManager.deleteContact(Long.parseLong(idToDelete));
				
				if (currentTabIndex == 0) {
					allTableModel.removeRow(selectedRow);
				}
				else if (currentTabIndex == 1) {
					friendsTableModel.removeRow(selectedRow);
				}
				else if (currentTabIndex == 2) {
					workTableModel.removeRow(selectedRow);
				}
				else {
					businessTableModel.removeRow(selectedRow);
				}
				
				repopulateShowAllTableRows();
			}
		}
	}
	
	private class SaveButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				contactManager.saveContactList();
				JOptionPane.showMessageDialog(null, "Saved contact succesfully!");
			} catch (IOException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error, failed to save contact!");
			}
			
		}
	}
	
	private class FriendsChangeListener implements TableModelListener {
		public void tableChanged(TableModelEvent e) {
			if (e.getType() == TableModelEvent.UPDATE) {
				System.out.println("update" + " Row: " + e.getFirstRow() + " Col: " + e.getColumn());
				System.out.println("id of the row updated: " + friendsTableModel.getValueAt(e.getFirstRow(), 7));
				int row = e.getFirstRow();
				FriendContact updatedContact = new FriendContact(
						friendsTableModel.getValueAt(row, 0).toString(),  // Name
						friendsTableModel.getValueAt(row, 1).toString(),  // Phone
						friendsTableModel.getValueAt(row, 3).toString(),  // Address
						friendsTableModel.getValueAt(row, 2).toString(),  // Email
						friendsTableModel.getValueAt(row, 4).toString(),  // Nick
						friendsTableModel.getValueAt(row, 5).toString(),  // Social
						friendsTableModel.getValueAt(row, 6).toString()); // Emergency
				updatedContact.setId(Long.parseLong(friendsTableModel.getValueAt(row, 7).toString()));
				contactManager.updateContact(updatedContact);
				repopulateShowAllTableRows();
			}
		}
	}
	
	private class WorkChangeListener implements TableModelListener {
		public void tableChanged(TableModelEvent e) {
			if (e.getType() == TableModelEvent.UPDATE) {
				System.out.println("update" + " Row: " + e.getFirstRow() + " Col: " + e.getColumn());
				System.out.println("id of the row updated: " + workTableModel.getValueAt(e.getFirstRow(), 7));
				int row = e.getFirstRow();
				// WorkContact(String name, String phoneNumber, String address, String email, String workingHours, String extensionNumber, String department)
				WorkContact updatedContact = new WorkContact(
						workTableModel.getValueAt(row, 0).toString(),  // Name
						workTableModel.getValueAt(row, 1).toString(),  // Phone
						workTableModel.getValueAt(row, 3).toString(),  // Address
						workTableModel.getValueAt(row, 2).toString(),  // Email
						workTableModel.getValueAt(row, 5).toString(),  // Working Hours
						workTableModel.getValueAt(row, 6).toString(),  // Extension
						workTableModel.getValueAt(row, 4).toString()); // Department
				updatedContact.setId(Long.parseLong(workTableModel.getValueAt(row, 7).toString()));
				contactManager.updateContact(updatedContact);
				repopulateShowAllTableRows();
			}
		}
	}
	
	private class BusinessChangeListener implements TableModelListener {
		public void tableChanged(TableModelEvent e) {
			if (e.getType() == TableModelEvent.UPDATE) {
				System.out.println("update" + " Row: " + e.getFirstRow() + " Col: " + e.getColumn());
				System.out.println("id of the row updated: " + businessTableModel.getValueAt(e.getFirstRow(), 6));
				int row = e.getFirstRow();
				// BusinessContact(String name, String phoneNumber, String address, String email, String businessHours, String website)
				BusinessContact updatedContact = new BusinessContact(
						businessTableModel.getValueAt(row, 0).toString(),  // Name
						businessTableModel.getValueAt(row, 1).toString(),  // Phone
						businessTableModel.getValueAt(row, 3).toString(),  // Address
						businessTableModel.getValueAt(row, 2).toString(),  // Email
						businessTableModel.getValueAt(row, 4).toString(),  // Business Hrs
						businessTableModel.getValueAt(row, 5).toString()); // Website
				
				updatedContact.setId(Long.parseLong(businessTableModel.getValueAt(row, 6).toString()));
				contactManager.updateContact(updatedContact);
				repopulateShowAllTableRows();
			}
		}
	}
	
}
