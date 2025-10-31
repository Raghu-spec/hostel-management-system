import javax.swing.*;
import java.awt.*;

public class UserDashboard extends JFrame {
    public UserDashboard(String email) {
        setTitle("User Dashboard");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        JLabel welcome = new JLabel("Welcome, " + email, SwingConstants.CENTER);
        JButton bookBtn = new JButton("Book Hostel Room");
        JButton logoutBtn = new JButton("Logout");

        bookBtn.addActionListener(e -> {
            dispose();
            new BookingFrame(email);
        });

        logoutBtn.addActionListener(e -> {
            dispose();
            new MainMenuFrame();
        });

        add(welcome);
        add(bookBtn);
        add(logoutBtn);
        setVisible(true);
    }
}
