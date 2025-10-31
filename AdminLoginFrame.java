import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminLoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    public AdminLoginFrame() {
        setTitle("Admin Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel titleLabel = new JLabel("Admin Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(titleLabel);
        add(new JLabel("")); // empty space

        JLabel userLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);

        add(userLabel);
        add(usernameField);
        add(passLabel);
        add(passwordField);
        add(new JLabel(""));
        add(loginButton);
        add(statusLabel);
        add(new JLabel(""));

        loginButton.addActionListener(e -> checkLogin());
        setVisible(true);
    }

    private void checkLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            // ✅ JDBC Connection
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hostel_db", "root", "GRANDfather#18"
            );

            // ✅ SQL Query
            String query = "SELECT * FROM admin WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                statusLabel.setText("Login successful!");
                JOptionPane.showMessageDialog(this, "Welcome Admin!");

                dispose(); // close login window
                new AdminFrame(); // redirect to dashboard
            } else {
                statusLabel.setText("Invalid username or password");
            }

            con.close();
        } catch (Exception ex) {
            statusLabel.setText("Database connection error!");
            ex.printStackTrace(); // 💡 See actual issue in terminal
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminLoginFrame());
    }
}