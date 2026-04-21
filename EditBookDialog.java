package LibraryManagement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class EditBookDialog extends JDialog {

    private JTextField txtBookID, txtTitle, txtAuthor, txtGenre, txtPublisher, txtDate, txtISBN;

    public EditBookDialog() {
        setTitle("Edit Book");
        setSize(450, 450);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(243, 233, 220));

        JLabel lblTitle = new JLabel("EDIT BOOK");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitle.setBounds(150, 10, 200, 30);
        add(lblTitle);

        int x1 = 30, x2 = 150, y = 60, gap = 40;

        addLabel("Book ID:", x1, y);
        txtBookID = addField(x2, y);

        JButton btnLoad = new JButton("Load");
        btnLoad.setBounds(320, y, 80, 25);
        add(btnLoad);

        y += gap;
        addLabel("Title:", x1, y);
        txtTitle = addField(x2, y);

        y += gap;
        addLabel("Author:", x1, y);
        txtAuthor = addField(x2, y);

        y += gap;
        addLabel("Genre:", x1, y);
        txtGenre = addField(x2, y);

        y += gap;
        addLabel("Publisher:", x1, y);
        txtPublisher = addField(x2, y);

        y += gap;
        addLabel("Publish Date:", x1, y);
        txtDate = addField(x2, y);

        y += gap;
        addLabel("ISBN:", x1, y);
        txtISBN = addField(x2, y);

        JButton btnUpdate = new JButton("UPDATE");
        btnUpdate.setBounds(90, 340, 120, 30);
        add(btnUpdate);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setBounds(230, 340, 120, 30);
        add(btnCancel);

        btnCancel.addActionListener(e -> dispose());
        btnLoad.addActionListener(e -> loadBook());
        btnUpdate.addActionListener(e -> updateBook());
    }

    private JTextField addField(int x, int y) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, 200, 25);
        add(tf);
        return tf;
    }

    private void addLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 120, 25);
        add(lbl);
    }

    private Connection connect() throws Exception {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/LibraryManagement",
                "root",
                "hoshi060222"
        );
    }

    private void loadBook() {
        try (Connection con = connect()) {

            String sql = "SELECT * FROM books WHERE book_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtBookID.getText());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                txtTitle.setText(rs.getString("title"));
                txtAuthor.setText(rs.getString("author"));
                txtGenre.setText(rs.getString("genre"));
                txtPublisher.setText(rs.getString("publisher"));
                txtDate.setText(rs.getString("pub_date"));
                txtISBN.setText(rs.getString("bk_num"));
            } else {
                JOptionPane.showMessageDialog(this, "Book not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateBook() {
        try (Connection con = connect()) {

            String sql = "UPDATE books SET title=?, author=?, genre=?, publisher=?, pub_date=?, bk_num=? WHERE book_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtTitle.getText());
            ps.setString(2, txtAuthor.getText());
            ps.setString(3, txtGenre.getText());
            ps.setString(4, txtPublisher.getText());
            ps.setString(5, txtDate.getText());
            ps.setString(6, txtISBN.getText());
            ps.setString(7, txtBookID.getText());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Book updated successfully");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Update failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}