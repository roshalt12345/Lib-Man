Add this to Homepage.java:

JButton edit = new JButton("Edit Book");
edit.addActionListener(e -> new EditBookDialog().setVisible(true));

JButton delete = new JButton("Delete Book");
delete.addActionListener(e -> new DeleteBookDialog().setVisible(true));

JButton view = new JButton("View Books");
view.addActionListener(e -> new ViewBooksDialog().setVisible(true));

contentPane.add(edit);
contentPane.add(delete);
contentPane.add(view);
