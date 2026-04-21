package LibraryManagement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ViewBooksDialog extends JDialog {

    private JTextArea textArea;

    public ViewBooksDialog() {
        setTitle("View Books");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        loadBooks();
    }

    private Connection connect() throws Exception {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/LibraryManagement",
                "root",
                "hoshi060222"
        );
    }

    private void loadBooks() {
        try (Connection con = connect()) {

            String sql = "SELECT * FROM books";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            StringBuilder sb = new StringBuilder();

            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("book_id"))
                  .append(" | Title: ").append(rs.getString("title"))
                  .append(" | Author: ").append(rs.getString("author"))
                  .append("\n");
            }

            textArea.setText(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}