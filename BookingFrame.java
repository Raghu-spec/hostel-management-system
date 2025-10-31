import javax.swing.*;
import java.awt.*;

public class BookingFrame extends JFrame {
    private JComboBox<String> hostelCombo;
    private JComboBox<String> roomTypeCombo;
    private JLabel priceLabel;
    private String email;

    public BookingFrame(String email) {
        this.email = email;

        setTitle("Hostel Booking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel hostelLabel = new JLabel("Select Hostel:");
        hostelCombo = new JComboBox<>(new String[]{"A", "B", "C", "D"});

        JLabel roomTypeLabel = new JLabel("Select Room Type:");
        String[] roomTypes = {
            "AC with Attached - ₹12000",
            "AC Non-Attached - ₹10000",
            "Non-AC with Attached - ₹8000",
            "Non-AC Non-Attached - ₹6000"
        };
        roomTypeCombo = new JComboBox<>(roomTypes);

        priceLabel = new JLabel("Price: ₹12000");
        roomTypeCombo.addActionListener(e -> updatePrice());

        JButton nextBtn = new JButton("Next →");
        nextBtn.addActionListener(e -> openPaymentFrame());

        add(hostelLabel);
        add(hostelCombo);
        add(roomTypeLabel);
        add(roomTypeCombo);
        add(priceLabel);
        add(new JLabel(""));
        add(nextBtn);

        setVisible(true);
    }

    private void updatePrice() {
        String selected = (String) roomTypeCombo.getSelectedItem();
        if (selected != null) priceLabel.setText("Price: " + selected.split("-")[1].trim());
    }

    private void openPaymentFrame() {
        new PaymentFrame(email, (String) hostelCombo.getSelectedItem(), (String) roomTypeCombo.getSelectedItem(), priceLabel.getText());
        dispose();
    }
}
