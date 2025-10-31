import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("User Login - Hostel Portal");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Back");
        add(loginButton);
        add(backButton);

        loginButton.addActionListener(e -> loginUser());
        backButton.addActionListener(e -> {
            dispose();
            new MainMenuFrame();
        });

        setVisible(true);
    }

    private void loginUser() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hostel_db", "root", "GRANDfather#18"
            );
            String query = "SELECT * FROM students WHERE email=? AND password=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "✅ Login Successful!");
                dispose();
                new UserDashboard(email);
            } else {
                JOptionPane.showMessageDialog(this, "❌ Invalid Email or Password");
            }
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
