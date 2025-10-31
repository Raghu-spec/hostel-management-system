import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {
    public MainMenuFrame() {
        setTitle("🏠 Hostel Management System");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        JLabel title = new JLabel("Hostel Management System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        JButton registerBtn = new JButton("User Registration");
        JButton loginBtn = new JButton("User Login");
        JButton adminBtn = new JButton("Admin Login");

        registerBtn.addActionListener(e -> {
            dispose();
            new RegisterFrame();
        });

        loginBtn.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        adminBtn.addActionListener(e -> {
            dispose();
            new AdminLoginFrame();
        });

        add(title);
        add(registerBtn);
        add(loginBtn);
        add(adminBtn);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenuFrame());
    }
}
