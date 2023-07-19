import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddressBookGUI extends JFrame implements ActionListener {
    private List<Contact> contacts;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextArea displayArea;

    public AddressBookGUI() {
        this.contacts = new ArrayList<>();

        setTitle("Address Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(15, 10, 10, 10));
        inputPanel.add(new JLabel("Name: "));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Phone: "));
        phoneField = new JTextField();
        inputPanel.add(phoneField);
        inputPanel.add(new JLabel("Email: "));
        emailField = new JTextField();
        inputPanel.add(emailField);
        JButton addButton = new JButton("Add Contact");
        addButton.addActionListener(this);
        inputPanel.add(addButton);
        add(inputPanel, BorderLayout.SOUTH);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Contact")) {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();

            if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                Contact contact = new Contact(name, phone, email);
                contacts.add(contact);
                displayContacts();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all the fields.");
            }
        }
    }

    private void displayContacts() {
        displayArea.setText("");
        for (Contact contact : contacts) {
            displayArea.append("Name: " + contact.getName() + "\n");
            displayArea.append("Phone: " + contact.getPhone() + "\n");
            displayArea.append("Email: " + contact.getEmail() + "\n");
            displayArea.append("\n");
        }
    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AddressBookGUI().setVisible(true);
            }
        });
    }
}

class Contact {
    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}