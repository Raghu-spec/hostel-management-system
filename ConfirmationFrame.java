import javax.swing.*;
import java.awt.*;

public class ConfirmationFrame extends JFrame {
    public ConfirmationFrame(String email, String hostel, String roomType, String price) {
        setTitle("Booking Confirmation");
        setSize(400, 200);
        setLayout(new GridLayout(5, 1));
        setLocationRelativeTo(null);

        add(new JLabel("✅ Booking Confirmed!", SwingConstants.CENTER));
        add(new JLabel("Email: " + email, SwingConstants.CENTER));
        add(new JLabel("Hostel: " + hostel, SwingConstants.CENTER));
        add(new JLabel("Room Type: " + roomType, SwingConstants.CENTER));
        add(new JLabel("Price: " + price, SwingConstants.CENTER));

        setVisible(true);
    }
}
