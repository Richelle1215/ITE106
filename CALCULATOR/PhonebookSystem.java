import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

public class PhonebookSystem {
    private DefaultTableModel tableModel;
    private JTable contactTable;
    private JTextField firstNameField, lastNameField, locationField, phoneField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PhonebookSystem().createAndShowGUI());
    }

    public void createAndShowGUI() {
        
        JFrame frame = new JFrame("PHONEBOOK");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(112,128,144)); 
        
        JLabel titleLabel = new JLabel("PHONEBOOK");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE); 
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(300, 10, 200, 40);
        frame.add(titleLabel);

       
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        searchLabel.setForeground(Color.WHITE); 
        searchLabel.setBounds(20, 60, 60, 25);
        frame.add(searchLabel);

        JTextField searchField = new JTextField();
        searchField.setBounds(80, 60, 200, 25);
        frame.add(searchField);

        
        String[] columnNames = {"FIRSTNAME", "LASTNAME", "LOCATION", "PHONE"};
        tableModel = new DefaultTableModel(columnNames, 0);
        contactTable = new JTable(tableModel);
        contactTable.setBackground(Color.WHITE); 
        contactTable.setForeground(Color.DARK_GRAY); 
        contactTable.setGridColor(Color.LIGHT_GRAY); 
        contactTable.setSelectionBackground(new Color(100, 100, 100));
        JScrollPane tableScrollPane = new JScrollPane(contactTable);
        tableScrollPane.setBounds(20, 100, 500, 400);
        frame.add(tableScrollPane);

       
        loadContactsFromFile();

        
        JLabel detailsLabel = new JLabel("Enter Contact Details");
        detailsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailsLabel.setForeground(Color.WHITE); // Set text color
        detailsLabel.setBounds(540, 100, 200, 25);
        frame.add(detailsLabel);

        JLabel firstNameLabel = new JLabel("FIRST NAME:");
        firstNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        firstNameLabel.setForeground(Color.WHITE); // text color
        firstNameLabel.setBounds(540, 140, 100, 25);
        frame.add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(640, 140, 120, 25);
        frame.add(firstNameField);

        JLabel lastNameLabel = new JLabel("LAST NAME:");
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        lastNameLabel.setForeground(Color.WHITE); 
        lastNameLabel.setBounds(540, 180, 100, 25);
        frame.add(lastNameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(640, 180, 120, 25);
        frame.add(lastNameField);

        JLabel locationLabel = new JLabel("LOCATION:");
        locationLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        locationLabel.setForeground(Color.WHITE); 
        locationLabel.setBounds(540, 220, 100, 25);
        frame.add(locationLabel);

        locationField = new JTextField();
        locationField.setBounds(640, 220, 120, 25);
        frame.add(locationField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        phoneLabel.setForeground(Color.WHITE); 
        phoneLabel.setBounds(540, 260, 100, 25);
        frame.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(640, 260, 120, 25);
        frame.add(phoneField);

        JButton addButton = new JButton("ADD");
        addButton.setBackground(Color.PINK);
        addButton.setForeground(Color.BLACK);
        addButton.setBounds(540, 320, 100, 30);
        frame.add(addButton);

        JButton updateButton = new JButton("UPDATE");
        updateButton.setBackground(Color.PINK);
        updateButton.setForeground(Color.BLACK);
        updateButton.setBounds(650, 320, 100, 30);
        frame.add(updateButton);

        JButton deleteButton = new JButton("DELETE");
        deleteButton.setBackground(Color.PINK);
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setBounds(540, 360, 100, 30);
        frame.add(deleteButton);

        JButton clearButton = new JButton("CLEAR");
        clearButton.setBackground(Color.PINK);
        clearButton.setForeground(Color.BLACK);
        clearButton.setBounds(650, 360, 100, 30);
        frame.add(clearButton);

       
        addButton.addActionListener(e -> addContact());

       
        updateButton.addActionListener(e -> editContact());

       
        deleteButton.addActionListener(e -> deleteContact());

       
        clearButton.addActionListener(e -> clearFields());

       
        searchField.addActionListener(e -> searchContact(searchField.getText()));

        frame.setVisible(true);
    }

    private void addContact() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String location = locationField.getText();
        String phone = phoneField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || location.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            return;
        }

        tableModel.addRow(new Object[]{firstName, lastName, location, phone});
        saveContactsToFile();
        clearFields();
    }

    private void editContact() {
        int selectedRow = contactTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a contact to edit.");
            return;
        }

        tableModel.setValueAt(firstNameField.getText(), selectedRow, 0);
        tableModel.setValueAt(lastNameField.getText(), selectedRow, 1);
        tableModel.setValueAt(locationField.getText(), selectedRow, 2);
        tableModel.setValueAt(phoneField.getText(), selectedRow, 3);

        saveContactsToFile();
        clearFields();
    }

    private void deleteContact() {
        int selectedRow = contactTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a contact to delete.");
            return;
        }

        tableModel.removeRow(selectedRow);
        saveContactsToFile();
        clearFields();
    }

    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        locationField.setText("");
        phoneField.setText("");
    }
	
    private void searchContact(String query) {
		String lowerCaseQuery = query.toLowerCase();

		for (int i = 0; i < tableModel.getRowCount(); i++) {
			String firstName = ((String) tableModel.getValueAt(i, 0)).toLowerCase();
			String lastName = ((String) tableModel.getValueAt(i, 1)).toLowerCase();
			String location = ((String) tableModel.getValueAt(i, 2)).toLowerCase();
			String phone = ((String) tableModel.getValueAt(i, 3)).toLowerCase();

        if (firstName.contains(lowerCaseQuery) || 
            lastName.contains(lowerCaseQuery) || 
            location.contains(lowerCaseQuery) || 
            phone.contains(lowerCaseQuery)) {
            contactTable.setRowSelectionInterval(i, i);
            return;
        }
    }
        JOptionPane.showMessageDialog(null, "Contact not found.");
    }

    private void saveContactsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contacts.txt"))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                Vector<?> row = tableModel.getDataVector().elementAt(i);
                writer.write(String.join(",", row.stream().map(Object::toString).toArray(String[]::new)));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadContactsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("contacts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tableModel.addRow(line.split(","));
            }
        } catch (IOException e) {
            
        }
    }
}