import javax.swing.*;
import java.awt.*;

public class PaymentFrame extends JFrame {
    private JTextField cardNumberField, nameField, cvvField;
    private String email, hostel, roomType, price;

    public PaymentFrame(String email, String hostel, String roomType, String price) {
        this.email = email;
        this.hostel = hostel;
        this.roomType = roomType;
        this.price = price;

        setTitle("Payment Page");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2, 10, 10));
        setLocationRelativeTo(null);

        add(new JLabel("Card Holder Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Card Number:"));
        cardNumberField = new JTextField();
        add(cardNumberField);

        add(new JLabel("CVV:"));
        cvvField = new JTextField();
        add(cvvField);

        add(new JLabel("Amount:"));
        add(new JLabel(price));

        JButton payButton = new JButton("Pay Now");
        payButton.addActionListener(e -> openConfirmationFrame());
        add(payButton);

        setVisible(true);
    }

    private void openConfirmationFrame() {
        JOptionPane.showMessageDialog(this, "✅ Payment Successful!");
        new ConfirmationFrame(email, hostel, roomType, price);
        dispose();
    }
}
