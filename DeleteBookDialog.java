package LibraryManagement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class DeleteBookDialog extends JDialog {

    private JTextField txtBookID;

    public DeleteBookDialog() {
        setTitle("Delete Book");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(243, 233, 220));

        JLabel lbl = new JLabel("BOOK ID:");
        lbl.setBounds(30, 40, 100, 25);
        add(lbl);

        txtBookID = new JTextField();
        txtBookID.setBounds(120, 40, 150, 25);
        add(txtBookID);

        JButton btnDelete = new JButton("DELETE");
        btnDelete.setBounds(60, 100, 100, 30);
        add(btnDelete);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setBounds(170, 100, 100, 30);
        add(btnCancel);

        btnCancel.addActionListener(e -> dispose());
        btnDelete.addActionListener(e -> deleteBook());
    }

    private Connection connect() throws Exception {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/LibraryManagement",
                "root",
                "hoshi060222"
        );
    }

    private void deleteBook() {
        try (Connection con = connect()) {

            String sql = "DELETE FROM books WHERE book_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtBookID.getText());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Book deleted successfully");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Book not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}