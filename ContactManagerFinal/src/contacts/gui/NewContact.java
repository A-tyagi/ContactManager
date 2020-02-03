package contacts.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import contacts.model.BusinessContact;
import contacts.model.FriendContact;
import contacts.model.WorkContact;

import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class NewContact extends JFrame {
	private JTextField friendFirstNameTextField;
	private JTextField friendPhoneNumberTextField;
	private JTextField friendAddressTextField;
	private JTextField friendEmailTextField;
	private JTextField friendNicknameTextField;
	private JTextField friendSocialMediaTextField;
	private JTextField friendEmergencyContactTextField;

	private JTextField workFirstNameTextField;
	private JTextField workPhoneNumberTextField;
	private JTextField workAddressTextField;
	private JTextField workEmailTextField;
	private JTextField workDepartmentTextField;
	private JTextField workWorkingHoursTextField;
	private JTextField workExtensionTextField;
	
	private JTextField businessFirstNameTextField;
	private JTextField businessPhoneNumberTextField;
	private JTextField businessAddressTextField;
	private JTextField businessEmailTextField;
	private JTextField businessBusinessHoursTextField;
	private JTextField businessWebsiteTextField;

	private JTabbedPane tabbedPane;
	private GuiManager guiManager;
	/**
	 * Create the panel.
	 */
	public NewContact(GuiManager guiManager) {
		this.guiManager = guiManager;
		setSize(550,375);
		setTitle("Add Contact");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {2};
		gridBagLayout.rowHeights = new int[] {2};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		getContentPane().setLayout(gridBagLayout);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		getContentPane().add(tabbedPane, gbc_tabbedPane);
		
		JPanel addFriendContactPanel = new JPanel();
		tabbedPane.addTab("Friends", null, addFriendContactPanel, null);
		GridBagLayout gbl_addFriendContactPanel = new GridBagLayout();
		gbl_addFriendContactPanel.columnWidths = new int[]{0, 0, -23, 230, 0};
		gbl_addFriendContactPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_addFriendContactPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_addFriendContactPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		addFriendContactPanel.setLayout(gbl_addFriendContactPanel);
		
		JLabel friendNameLabel = new JLabel("Name:");
		GridBagConstraints gbc_friendNameLabel = new GridBagConstraints();
		gbc_friendNameLabel.anchor = GridBagConstraints.EAST;
		gbc_friendNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_friendNameLabel.gridx = 1;
		gbc_friendNameLabel.gridy = 0;
		addFriendContactPanel.add(friendNameLabel, gbc_friendNameLabel);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 2;
		gbc_horizontalStrut.gridy = 0;
		addFriendContactPanel.add(horizontalStrut, gbc_horizontalStrut);
		
		friendFirstNameTextField = new JTextField();
		friendFirstNameTextField.setColumns(10);
		GridBagConstraints gbc_friendFirstNameTextField = new GridBagConstraints();
		gbc_friendFirstNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_friendFirstNameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_friendFirstNameTextField.gridx = 3;
		gbc_friendFirstNameTextField.gridy = 0;
		addFriendContactPanel.add(friendFirstNameTextField, gbc_friendFirstNameTextField);
		
		JLabel friendPhoneNumberLabel = new JLabel("Phone Number:");
		GridBagConstraints gbc_friendPhoneNumberLabel = new GridBagConstraints();
		gbc_friendPhoneNumberLabel.anchor = GridBagConstraints.EAST;
		gbc_friendPhoneNumberLabel.insets = new Insets(0, 0, 5, 5);
		gbc_friendPhoneNumberLabel.gridx = 1;
		gbc_friendPhoneNumberLabel.gridy = 1;
		addFriendContactPanel.add(friendPhoneNumberLabel, gbc_friendPhoneNumberLabel);
		
		friendPhoneNumberTextField = new JTextField();
		friendPhoneNumberTextField.setColumns(10);
		GridBagConstraints gbc_friendPhoneNumberTextField = new GridBagConstraints();
		gbc_friendPhoneNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_friendPhoneNumberTextField.insets = new Insets(0, 0, 5, 0);
		gbc_friendPhoneNumberTextField.gridx = 3;
		gbc_friendPhoneNumberTextField.gridy = 1;
		addFriendContactPanel.add(friendPhoneNumberTextField, gbc_friendPhoneNumberTextField);
		
		JLabel friendAddressLabel = new JLabel("Address:");
		GridBagConstraints gbc_friendAddressLabel = new GridBagConstraints();
		gbc_friendAddressLabel.anchor = GridBagConstraints.EAST;
		gbc_friendAddressLabel.insets = new Insets(0, 0, 5, 5);
		gbc_friendAddressLabel.gridx = 1;
		gbc_friendAddressLabel.gridy = 2;
		addFriendContactPanel.add(friendAddressLabel, gbc_friendAddressLabel);
		
		friendAddressTextField = new JTextField();
		friendAddressTextField.setColumns(10);
		GridBagConstraints gbc_friendAddressTextField = new GridBagConstraints();
		gbc_friendAddressTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_friendAddressTextField.insets = new Insets(0, 0, 5, 0);
		gbc_friendAddressTextField.gridx = 3;
		gbc_friendAddressTextField.gridy = 2;
		addFriendContactPanel.add(friendAddressTextField, gbc_friendAddressTextField);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_4 = new GridBagConstraints();
		gbc_horizontalStrut_4.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_4.gridx = 0;
		gbc_horizontalStrut_4.gridy = 3;
		addFriendContactPanel.add(horizontalStrut_4, gbc_horizontalStrut_4);
		
		JLabel friendEmailLabel = new JLabel("Email:");
		GridBagConstraints gbc_friendEmailLabel = new GridBagConstraints();
		gbc_friendEmailLabel.anchor = GridBagConstraints.EAST;
		gbc_friendEmailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_friendEmailLabel.gridx = 1;
		gbc_friendEmailLabel.gridy = 3;
		addFriendContactPanel.add(friendEmailLabel, gbc_friendEmailLabel);
		
		friendEmailTextField = new JTextField();
		friendEmailTextField.setColumns(10);
		GridBagConstraints gbc_friendEmailTextField = new GridBagConstraints();
		gbc_friendEmailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_friendEmailTextField.insets = new Insets(0, 0, 5, 0);
		gbc_friendEmailTextField.gridx = 3;
		gbc_friendEmailTextField.gridy = 3;
		addFriendContactPanel.add(friendEmailTextField, gbc_friendEmailTextField);
		
		JLabel friendNicknameLabel = new JLabel("Nickname:");
		GridBagConstraints gbc_friendNicknameLabel = new GridBagConstraints();
		gbc_friendNicknameLabel.anchor = GridBagConstraints.EAST;
		gbc_friendNicknameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_friendNicknameLabel.gridx = 1;
		gbc_friendNicknameLabel.gridy = 4;
		addFriendContactPanel.add(friendNicknameLabel, gbc_friendNicknameLabel);
		
		friendNicknameTextField = new JTextField();
		friendNicknameTextField.setColumns(10);
		GridBagConstraints gbc_nicknameTextField = new GridBagConstraints();
		gbc_nicknameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nicknameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_nicknameTextField.gridx = 3;
		gbc_nicknameTextField.gridy = 4;
		addFriendContactPanel.add(friendNicknameTextField, gbc_nicknameTextField);
		
		JLabel friendSocialMediaLabel = new JLabel("Social Media:");
		GridBagConstraints gbc_friendSocialMediaLabel = new GridBagConstraints();
		gbc_friendSocialMediaLabel.anchor = GridBagConstraints.EAST;
		gbc_friendSocialMediaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_friendSocialMediaLabel.gridx = 1;
		gbc_friendSocialMediaLabel.gridy = 5;
		addFriendContactPanel.add(friendSocialMediaLabel, gbc_friendSocialMediaLabel);
		
		friendSocialMediaTextField = new JTextField();
		friendSocialMediaTextField.setColumns(10);
		GridBagConstraints gbc_friendSocialMediaTextField = new GridBagConstraints();
		gbc_friendSocialMediaTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_friendSocialMediaTextField.insets = new Insets(0, 0, 5, 0);
		gbc_friendSocialMediaTextField.gridx = 3;
		gbc_friendSocialMediaTextField.gridy = 5;
		addFriendContactPanel.add(friendSocialMediaTextField, gbc_friendSocialMediaTextField);
		
		
		JLabel emergencyContactLabel = new JLabel("Emergency Contact:");
		GridBagConstraints gbc_emergencyContactLabel = new GridBagConstraints();
		gbc_emergencyContactLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emergencyContactLabel.gridx = 1;
		gbc_emergencyContactLabel.gridy = 6;
		addFriendContactPanel.add(emergencyContactLabel, gbc_emergencyContactLabel);
		
		friendEmergencyContactTextField = new JTextField();
		GridBagConstraints gbc_emergencyContactTextField = new GridBagConstraints();
		gbc_emergencyContactTextField.insets = new Insets(0, 0, 5, 0);
		gbc_emergencyContactTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emergencyContactTextField.gridx = 3;
		gbc_emergencyContactTextField.gridy = 6;
		addFriendContactPanel.add(friendEmergencyContactTextField, gbc_emergencyContactTextField);
		friendEmergencyContactTextField.setColumns(10);
		
		JButton clearFriendButton = new JButton("Clear");
		clearFriendButton.addActionListener(new ClearFriendButtonListener());
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 1;
		gbc_verticalStrut.gridy = 7;
		addFriendContactPanel.add(verticalStrut, gbc_verticalStrut);
		GridBagConstraints gbc_clearButton = new GridBagConstraints();
		gbc_clearButton.anchor = GridBagConstraints.EAST;
		gbc_clearButton.insets = new Insets(0, 0, 5, 5);
		gbc_clearButton.gridx = 1;
		gbc_clearButton.gridy = 8;
		addFriendContactPanel.add(clearFriendButton, gbc_clearButton);
		
		JButton addFriendButton = new JButton("Add");
		addFriendButton.addActionListener(new AddFriendButtonListener());
		GridBagConstraints gbc_saveButton = new GridBagConstraints();
		gbc_saveButton.insets = new Insets(0, 0, 5, 0);
		gbc_saveButton.gridx = 3;
		gbc_saveButton.gridy = 8;
		addFriendContactPanel.add(addFriendButton, gbc_saveButton);
		
		
		
		/////
		/////
		/////
		
		JPanel addWorkContactPanel = new JPanel();
		tabbedPane.addTab("Work", null, addWorkContactPanel, null);
		GridBagLayout gbl_addWorkContactPanel = new GridBagLayout();
		gbl_addWorkContactPanel.columnWidths = new int[]{0, 0, -23, 230, 0};
		gbl_addWorkContactPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_addWorkContactPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_addWorkContactPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		addWorkContactPanel.setLayout(gbl_addWorkContactPanel);
		
		JLabel workNameLabel = new JLabel("Name:");
		GridBagConstraints gbc_workNameLabel = new GridBagConstraints();
		gbc_workNameLabel.anchor = GridBagConstraints.EAST;
		gbc_workNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_workNameLabel.gridx = 1;
		gbc_workNameLabel.gridy = 0;
		addWorkContactPanel.add(workNameLabel, gbc_workNameLabel);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_1.gridx = 2;
		gbc_horizontalStrut_1.gridy = 0;
		addWorkContactPanel.add(horizontalStrut_1, gbc_horizontalStrut_1);
		
		workFirstNameTextField = new JTextField();
		workFirstNameTextField.setColumns(10);
		GridBagConstraints gbc_workFirstNameTextField = new GridBagConstraints();
		gbc_workFirstNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_workFirstNameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_workFirstNameTextField.gridx = 3;
		gbc_workFirstNameTextField.gridy = 0;
		addWorkContactPanel.add(workFirstNameTextField, gbc_workFirstNameTextField);
		
		JLabel workExtensionLabel = new JLabel("Extension #:");
		GridBagConstraints gbc_workExtensionLabel = new GridBagConstraints();
		gbc_workExtensionLabel.anchor = GridBagConstraints.EAST;
		gbc_workExtensionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_workExtensionLabel.gridx = 1;
		gbc_workExtensionLabel.gridy = 1;
		addWorkContactPanel.add(workExtensionLabel, gbc_workExtensionLabel);
		
		workExtensionTextField = new JTextField();
		GridBagConstraints gbc_workExtensionTextField = new GridBagConstraints();
		gbc_workExtensionTextField.insets = new Insets(0, 0, 5, 0);
		gbc_workExtensionTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_workExtensionTextField.gridx = 3;
		gbc_workExtensionTextField.gridy = 1;
		addWorkContactPanel.add(workExtensionTextField, gbc_workExtensionTextField);
		workExtensionTextField.setColumns(10);
		
		JLabel workPhoneNumberLabel = new JLabel("Phone Number:");
		GridBagConstraints gbc_workPhoneNumberLabel = new GridBagConstraints();
		gbc_workPhoneNumberLabel.anchor = GridBagConstraints.EAST;
		gbc_workPhoneNumberLabel.insets = new Insets(0, 0, 5, 5);
		gbc_workPhoneNumberLabel.gridx = 1;
		gbc_workPhoneNumberLabel.gridy = 2;
		addWorkContactPanel.add(workPhoneNumberLabel, gbc_workPhoneNumberLabel);
		
		workPhoneNumberTextField = new JTextField();
		workPhoneNumberTextField.setColumns(10);
		GridBagConstraints gbc_workPhoneNumberTextField = new GridBagConstraints();
		gbc_workPhoneNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_workPhoneNumberTextField.insets = new Insets(0, 0, 5, 0);
		gbc_workPhoneNumberTextField.gridx = 3;
		gbc_workPhoneNumberTextField.gridy = 2;
		addWorkContactPanel.add(workPhoneNumberTextField, gbc_workPhoneNumberTextField);
		
		JLabel workAddressLabel = new JLabel("Address:");
		GridBagConstraints gbc_workAddressLabel = new GridBagConstraints();
		gbc_workAddressLabel.anchor = GridBagConstraints.EAST;
		gbc_workAddressLabel.insets = new Insets(0, 0, 5, 5);
		gbc_workAddressLabel.gridx = 1;
		gbc_workAddressLabel.gridy = 3;
		addWorkContactPanel.add(workAddressLabel, gbc_workAddressLabel);
		
		workAddressTextField = new JTextField();
		workAddressTextField.setColumns(10);
		GridBagConstraints gbc_workAddressTextField = new GridBagConstraints();
		gbc_workAddressTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_workAddressTextField.insets = new Insets(0, 0, 5, 0);
		gbc_workAddressTextField.gridx = 3;
		gbc_workAddressTextField.gridy = 3;
		addWorkContactPanel.add(workAddressTextField, gbc_workAddressTextField);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_3 = new GridBagConstraints();
		gbc_horizontalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_3.gridx = 0;
		gbc_horizontalStrut_3.gridy = 4;
		addWorkContactPanel.add(horizontalStrut_3, gbc_horizontalStrut_3);
		
		JLabel workEmailLabel = new JLabel("Email:");
		GridBagConstraints gbc_workEmailLabel = new GridBagConstraints();
		gbc_workEmailLabel.anchor = GridBagConstraints.EAST;
		gbc_workEmailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_workEmailLabel.gridx = 1;
		gbc_workEmailLabel.gridy = 4;
		addWorkContactPanel.add(workEmailLabel, gbc_workEmailLabel);
		
		workEmailTextField = new JTextField();
		workEmailTextField.setColumns(10);
		GridBagConstraints gbc_workEmailTextField = new GridBagConstraints();
		gbc_workEmailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_workEmailTextField.insets = new Insets(0, 0, 5, 0);
		gbc_workEmailTextField.gridx = 3;
		gbc_workEmailTextField.gridy = 4;
		addWorkContactPanel.add(workEmailTextField, gbc_workEmailTextField);
		
		JLabel workDepartmentLabel = new JLabel("Department:");
		GridBagConstraints gbc_workDepartmentLabel = new GridBagConstraints();
		gbc_workDepartmentLabel.anchor = GridBagConstraints.EAST;
		gbc_workDepartmentLabel.insets = new Insets(0, 0, 5, 5);
		gbc_workDepartmentLabel.gridx = 1;
		gbc_workDepartmentLabel.gridy = 5;
		addWorkContactPanel.add(workDepartmentLabel, gbc_workDepartmentLabel);
		
		workDepartmentTextField = new JTextField();
		workDepartmentTextField.setColumns(10);
		GridBagConstraints gbc_workDepartmentTextField = new GridBagConstraints();
		gbc_workDepartmentTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_workDepartmentTextField.insets = new Insets(0, 0, 5, 0);
		gbc_workDepartmentTextField.gridx = 3;
		gbc_workDepartmentTextField.gridy = 5;
		addWorkContactPanel.add(workDepartmentTextField, gbc_workDepartmentTextField);
		
		JLabel workWorkingHoursLabel = new JLabel("Working Hours:");
		GridBagConstraints gbc_workWorkingHoursLabel = new GridBagConstraints();
		gbc_workWorkingHoursLabel.insets = new Insets(0, 0, 5, 5);
		gbc_workWorkingHoursLabel.gridx = 1;
		gbc_workWorkingHoursLabel.gridy = 6;
		addWorkContactPanel.add(workWorkingHoursLabel, gbc_workWorkingHoursLabel);
		
		workWorkingHoursTextField = new JTextField();
		workWorkingHoursTextField.setColumns(10);
		GridBagConstraints gbc_workWorkingHoursTextField = new GridBagConstraints();
		gbc_workWorkingHoursTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_workWorkingHoursTextField.insets = new Insets(0, 0, 5, 0);
		gbc_workWorkingHoursTextField.gridx = 3;
		gbc_workWorkingHoursTextField.gridy = 6;
		addWorkContactPanel.add(workWorkingHoursTextField, gbc_workWorkingHoursTextField);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 1;
		gbc_verticalStrut_1.gridy = 7;
		addWorkContactPanel.add(verticalStrut_1, gbc_verticalStrut_1);
		
		JButton clearWorkbutton = new JButton("Clear");
		clearWorkbutton.addActionListener(new ClearWorkButtonListener());
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.EAST;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 8;
		addWorkContactPanel.add(clearWorkbutton, gbc_button);
		
		JButton addWorkbutton = new JButton("Add");
		addWorkbutton.addActionListener(new AddWorkButtonListener());
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 5, 0);
		gbc_button_1.gridx = 3;
		gbc_button_1.gridy = 8;
		addWorkContactPanel.add(addWorkbutton, gbc_button_1);
		
		/////
		/////
		/////
		
		
		JPanel addBusinessContactPanel = new JPanel();
		tabbedPane.addTab("Business", null, addBusinessContactPanel, null);
		GridBagLayout gbl_addBusinessContactPanel = new GridBagLayout();
		gbl_addBusinessContactPanel.columnWidths = new int[]{0, 0, -23, 230, 0};
		gbl_addBusinessContactPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_addBusinessContactPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_addBusinessContactPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		addBusinessContactPanel.setLayout(gbl_addBusinessContactPanel);
		
		JLabel businessNameLabel = new JLabel("Name:");
		GridBagConstraints gbc_businessNameLabel = new GridBagConstraints();
		gbc_businessNameLabel.anchor = GridBagConstraints.EAST;
		gbc_businessNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_businessNameLabel.gridx = 1;
		gbc_businessNameLabel.gridy = 0;
		addBusinessContactPanel.add(businessNameLabel, gbc_businessNameLabel);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_2.gridx = 2;
		gbc_horizontalStrut_2.gridy = 0;
		addBusinessContactPanel.add(horizontalStrut_2, gbc_horizontalStrut_2);
		
		businessFirstNameTextField = new JTextField();
		businessFirstNameTextField.setColumns(10);
		GridBagConstraints gbc_businessFirstNameTextField = new GridBagConstraints();
		gbc_businessFirstNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_businessFirstNameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_businessFirstNameTextField.gridx = 3;
		gbc_businessFirstNameTextField.gridy = 0;
		addBusinessContactPanel.add(businessFirstNameTextField, gbc_businessFirstNameTextField);
		
		JLabel businessPhoneNumberLabel = new JLabel("Phone Number:");
		GridBagConstraints gbc_businessPhoneNumberLabel = new GridBagConstraints();
		gbc_businessPhoneNumberLabel.anchor = GridBagConstraints.EAST;
		gbc_businessPhoneNumberLabel.insets = new Insets(0, 0, 5, 5);
		gbc_businessPhoneNumberLabel.gridx = 1;
		gbc_businessPhoneNumberLabel.gridy = 1;
		addBusinessContactPanel.add(businessPhoneNumberLabel, gbc_businessPhoneNumberLabel);
		
		businessPhoneNumberTextField = new JTextField();
		businessPhoneNumberTextField.setColumns(10);
		GridBagConstraints gbc_businessPhoneNumberTextField = new GridBagConstraints();
		gbc_businessPhoneNumberTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_businessPhoneNumberTextField.insets = new Insets(0, 0, 5, 0);
		gbc_businessPhoneNumberTextField.gridx = 3;
		gbc_businessPhoneNumberTextField.gridy = 1;
		addBusinessContactPanel.add(businessPhoneNumberTextField, gbc_businessPhoneNumberTextField);
		
		JLabel businessAddressLabel = new JLabel("Address:");
		GridBagConstraints gbc_businessAddressLabel = new GridBagConstraints();
		gbc_businessAddressLabel.anchor = GridBagConstraints.EAST;
		gbc_businessAddressLabel.insets = new Insets(0, 0, 5, 5);
		gbc_businessAddressLabel.gridx = 1;
		gbc_businessAddressLabel.gridy = 2;
		addBusinessContactPanel.add(businessAddressLabel, gbc_businessAddressLabel);
		
		businessAddressTextField = new JTextField();
		businessAddressTextField.setColumns(10);
		GridBagConstraints gbc_businessAddressTextField = new GridBagConstraints();
		gbc_businessAddressTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_businessAddressTextField.insets = new Insets(0, 0, 5, 0);
		gbc_businessAddressTextField.gridx = 3;
		gbc_businessAddressTextField.gridy = 2;
		addBusinessContactPanel.add(businessAddressTextField, gbc_businessAddressTextField);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_5 = new GridBagConstraints();
		gbc_horizontalStrut_5.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_5.gridx = 0;
		gbc_horizontalStrut_5.gridy = 3;
		addBusinessContactPanel.add(horizontalStrut_5, gbc_horizontalStrut_5);
		
		JLabel businessEmailLabel = new JLabel("Email:");
		GridBagConstraints gbc_businessEmailLabel = new GridBagConstraints();
		gbc_businessEmailLabel.anchor = GridBagConstraints.EAST;
		gbc_businessEmailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_businessEmailLabel.gridx = 1;
		gbc_businessEmailLabel.gridy = 3;
		addBusinessContactPanel.add(businessEmailLabel, gbc_businessEmailLabel);
		
		businessEmailTextField = new JTextField();
		businessEmailTextField.setColumns(10);
		GridBagConstraints gbc_businessEmailTextField = new GridBagConstraints();
		gbc_businessEmailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_businessEmailTextField.insets = new Insets(0, 0, 5, 0);
		gbc_businessEmailTextField.gridx = 3;
		gbc_businessEmailTextField.gridy = 3;
		addBusinessContactPanel.add(businessEmailTextField, gbc_businessEmailTextField);
		
		JLabel businessBusinessHoursLabel = new JLabel("Business Hours:");
		GridBagConstraints gbc_businessBusinessHoursLabel = new GridBagConstraints();
		gbc_businessBusinessHoursLabel.anchor = GridBagConstraints.EAST;
		gbc_businessBusinessHoursLabel.insets = new Insets(0, 0, 5, 5);
		gbc_businessBusinessHoursLabel.gridx = 1;
		gbc_businessBusinessHoursLabel.gridy = 4;
		addBusinessContactPanel.add(businessBusinessHoursLabel, gbc_businessBusinessHoursLabel);
		
		businessBusinessHoursTextField = new JTextField();
		businessBusinessHoursTextField.setColumns(10);
		GridBagConstraints gbc_businessBusinessHoursTextField = new GridBagConstraints();
		gbc_businessBusinessHoursTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_businessBusinessHoursTextField.insets = new Insets(0, 0, 5, 0);
		gbc_businessBusinessHoursTextField.gridx = 3;
		gbc_businessBusinessHoursTextField.gridy = 4;
		addBusinessContactPanel.add(businessBusinessHoursTextField, gbc_businessBusinessHoursTextField);
		
		JLabel businessWebsiteLabel = new JLabel("Website:");
		GridBagConstraints gbc_businessWebsiteLabel = new GridBagConstraints();
		gbc_businessWebsiteLabel.anchor = GridBagConstraints.EAST;
		gbc_businessWebsiteLabel.insets = new Insets(0, 0, 5, 5);
		gbc_businessWebsiteLabel.gridx = 1;
		gbc_businessWebsiteLabel.gridy = 5;
		addBusinessContactPanel.add(businessWebsiteLabel, gbc_businessWebsiteLabel);
		
		businessWebsiteTextField = new JTextField();
		businessWebsiteTextField.setColumns(10);
		GridBagConstraints gbc_businessWebsiteTextField = new GridBagConstraints();
		gbc_businessWebsiteTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_businessWebsiteTextField.insets = new Insets(0, 0, 5, 0);
		gbc_businessWebsiteTextField.gridx = 3;
		gbc_businessWebsiteTextField.gridy = 5;
		addBusinessContactPanel.add(businessWebsiteTextField, gbc_businessWebsiteTextField);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_2.gridx = 1;
		gbc_verticalStrut_2.gridy = 7;
		addBusinessContactPanel.add(verticalStrut_2, gbc_verticalStrut_2);
		
		JButton clearBusinessButton = new JButton("Clear");
		clearBusinessButton.addActionListener(new ClearBusinessButtonListener());
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.anchor = GridBagConstraints.EAST;
		gbc_button_2.insets = new Insets(0, 0, 0, 5);
		gbc_button_2.gridx = 1;
		gbc_button_2.gridy = 8;
		addBusinessContactPanel.add(clearBusinessButton, gbc_button_2);
		
		JButton addBusinessButton = new JButton("Add");
		addBusinessButton.addActionListener(new AddBusinessButtonListener());
		GridBagConstraints gbc_button_3 = new GridBagConstraints();
		gbc_button_3.gridx = 3;
		gbc_button_3.gridy = 8;
		addBusinessContactPanel.add(addBusinessButton, gbc_button_3);

	}
	
	private class AddFriendButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			FriendContact newFriendContact = new FriendContact(friendFirstNameTextField.getText(),friendPhoneNumberTextField.getText(),friendAddressTextField.getText(),friendEmailTextField.getText(),
					friendNicknameTextField.getText(),friendSocialMediaTextField.getText(), friendEmergencyContactTextField.getText());
			guiManager.addFriendContact(newFriendContact);
			JOptionPane.showMessageDialog(null, "Friend contact added.");
			friendFirstNameTextField.setText(null);
			friendPhoneNumberTextField.setText(null);
			friendAddressTextField.setText(null);
			friendEmailTextField.setText(null);
			friendSocialMediaTextField.setText(null);
			friendNicknameTextField.setText(null);
			friendEmergencyContactTextField.setText(null);
			
		}
	}
	
	private class AddWorkButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			WorkContact newWorkContact = new WorkContact(workFirstNameTextField.getText(),workPhoneNumberTextField.getText(),workAddressTextField.getText(),workEmailTextField.getText(),
					workDepartmentTextField.getText(), workWorkingHoursTextField.getText(),workExtensionTextField.getText());
			guiManager.addWorkContact(newWorkContact);
			JOptionPane.showMessageDialog(null, "Work contact added.");
			workFirstNameTextField.setText(null);
			workPhoneNumberTextField.setText(null);
			workAddressTextField.setText(null);
			workEmailTextField.setText(null);
			workDepartmentTextField.setText(null);
			workWorkingHoursTextField.setText(null);
			workExtensionTextField.setText(null);
		}
	}
	
	private class AddBusinessButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			BusinessContact newBusinessContact = new BusinessContact(businessFirstNameTextField.getText(),businessPhoneNumberTextField.getText(),businessAddressTextField.getText(),businessEmailTextField.getText(),
					businessBusinessHoursTextField.getText(),businessWebsiteTextField.getText());
			guiManager.addBusinessContact(newBusinessContact);
			JOptionPane.showMessageDialog(null, "Business contact added.");
			businessFirstNameTextField.setText(null);
			businessPhoneNumberTextField.setText(null);
			businessAddressTextField.setText(null);
			businessEmailTextField.setText(null);
			businessBusinessHoursTextField.setText(null);
			businessWebsiteTextField.setText(null);
			
		}
	}
	
	private class ClearFriendButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {		
			friendFirstNameTextField.setText(null);
			friendPhoneNumberTextField.setText(null);
			friendAddressTextField.setText(null);
			friendEmailTextField.setText(null);
			friendSocialMediaTextField.setText(null);
			friendNicknameTextField.setText(null);
			friendEmergencyContactTextField.setText(null);
		}
	}
	
	private class ClearWorkButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			workFirstNameTextField.setText(null);
			workPhoneNumberTextField.setText(null);
			workAddressTextField.setText(null);
			workEmailTextField.setText(null);
			workDepartmentTextField.setText(null);
			workWorkingHoursTextField.setText(null);
			workExtensionTextField.setText(null);
		}
	}
	
	private class ClearBusinessButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			businessFirstNameTextField.setText(null);
			businessPhoneNumberTextField.setText(null);
			businessAddressTextField.setText(null);
			businessEmailTextField.setText(null);
			businessBusinessHoursTextField.setText(null);
			businessWebsiteTextField.setText(null);
		}
	}

}
