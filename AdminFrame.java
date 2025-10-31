import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class AdminFrame extends JFrame {

    public AdminFrame() {
        setTitle("Admin Dashboard - Student Details");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columns = {"Reg No", "First Name", "Last Name", "Email", "Contact No", "Hostel", "Payment Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        try {
            // 🔗 Connect to your MySQL
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hostel_db", "root", "GRANDfather#18"
            );

            // ✅ Join tables to show all student + payment info
            String query = """
                SELECT s.reg_no, s.first_name, s.last_name, s.email, s.contact_no,
                       b.hostel_name, p.status
                FROM students s
                LEFT JOIN bookings b ON s.reg_no = b.reg_no
                LEFT JOIN payments p ON s.reg_no = p.reg_no
            """;

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("reg_no"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("contact_no"),
                    rs.getString("hostel_name"),
                    rs.getString("status")
                });
            }

            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminFrame());
    }
}