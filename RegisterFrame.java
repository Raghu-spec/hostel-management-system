import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegisterFrame extends JFrame {
    private JTextField regNoField, firstNameField, middleNameField, lastNameField, contactField, emailField;
    private JPasswordField passwordField;
    private JComboBox<String> genderBox;

    public RegisterFrame() {
        setTitle("User Registration - Hostel Portal");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 2, 10, 10));

        add(new JLabel("Reg No:"));
        regNoField = new JTextField();
        add(regNoField);

        add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        add(firstNameField);

        add(new JLabel("Middle Name:"));
        middleNameField = new JTextField();
        add(middleNameField);

        add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        add(lastNameField);

        add(new JLabel("Gender:"));
        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        add(genderBox);

        add(new JLabel("Contact No:"));
        contactField = new JTextField();
        add(contactField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");
        add(registerButton);
        add(backButton);

        registerButton.addActionListener(e -> registerUser());
        backButton.addActionListener(e -> {
            dispose();
            new MainMenuFrame();
        });

        setVisible(true);
    }

    private void registerUser() {
        String regNo = regNoField.getText();
        String first = firstNameField.getText();
        String middle = middleNameField.getText();
        String last = lastNameField.getText();
        String gender = (String) genderBox.getSelectedItem();
        String contact = contactField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hostel_db", "root", "GRANDfather#18"
            );

            String query = "INSERT INTO students VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, regNo);
            pst.setString(2, first);
            pst.setString(3, middle);
            pst.setString(4, last);
            pst.setString(5, gender);
            pst.setString(6, contact);
            pst.setString(7, email);
            pst.setString(8, password);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "✅ Registration Successful! Please Login.");
            con.close();
            dispose();
            new LoginFrame();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
