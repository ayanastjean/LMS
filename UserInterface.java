import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface {
    private JButton exitProgramButton;
    private JButton checkOutBookButton;
    private JButton checkInBookButton;
    private JButton deleteBookButton;
    private JButton addBookButton;
    private JButton displayAllBooksButton;
    private JPanel LMS;
    private Catalog catalog;
    private Component genreField;

    public UserInterface(Catalog catalog) {
        this.catalog = catalog;
        {
            JFrame frame = new JFrame("Library Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Font font = new Font("Century Gothic", Font.PLAIN, 14);
            JLabel imageLabel = new JLabel();
            ImageIcon imageIcon = new ImageIcon("src/Untitled design-5.png");
            imageLabel.setIcon(imageIcon);
            frame.setLocationRelativeTo(null);
            frame.getContentPane().add(imageLabel, BorderLayout.NORTH);

            checkOutBookButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the book barcode:"));
                    boolean success = catalog.checkOutBook(id);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Book ID# " + id + " checked out successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        refreshBookList();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Book ID# " + id + " is already checked out.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            checkInBookButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the book barcode:"));
                    catalog.checkInBook(id);
                    String statusMessage = "Book with ID# " + id + " has been checked in.\nStatus: Available";
                    JOptionPane.showMessageDialog(null, statusMessage, "Check-in Successful", JOptionPane.INFORMATION_MESSAGE);
                    refreshBookList();
                }
            });

            displayAllBooksButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTextArea inventoryArea = new JTextArea();
                    inventoryArea.setEditable(false);
                    inventoryArea.setRows(20);
                    inventoryArea.setColumns(40);

                    for (Book book : catalog.inventory()) {
                        String status = book.isCheckedOut() ? "Checked Out" : "Available";
                        String dueDate = book.getDueDate() != null ? book.getDueDate().toString() : "N/A"; // Convert Date to String
                        inventoryArea.append(book.getId() + ", " + book.getTitle() + ", " + book.getAuthor() + ", Status: " + status + ", Due Date: " + dueDate + "\n");
                    }

                    JScrollPane scrollPane = new JScrollPane(inventoryArea);
                    scrollPane.setPreferredSize(new Dimension(400, 300));

                    JOptionPane.showMessageDialog(null, scrollPane, "Inventory", JOptionPane.INFORMATION_MESSAGE);
                    refreshBookList();
                }
            });

            exitProgramButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Closing LMS...");
                    System.exit(0);
                }
            });

            addBookButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel inputPanel = new JPanel(new GridLayout(4, 2));

                    JTextField barcodeField = new JTextField(10);
                    JTextField titleField = new JTextField(20);
                    JTextField authorField = new JTextField(20);

                    String[] genres = {"Fiction", "Non-fiction"};
                    JComboBox<String> genreComboBox = new JComboBox<>(genres);

                    inputPanel.add(new JLabel("Barcode:"));
                    inputPanel.add(barcodeField);
                    inputPanel.add(new JLabel("Title:"));
                    inputPanel.add(titleField);
                    inputPanel.add(new JLabel("Author:"));
                    inputPanel.add(authorField);
                    inputPanel.add(new JLabel("Genre:"));
                    inputPanel.add(genreComboBox);

                    int result = JOptionPane.showConfirmDialog(null, inputPanel, "Add Book", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        try {
                            int id = Integer.parseInt(barcodeField.getText());
                            String title = titleField.getText();
                            String author = authorField.getText();
                            String genre = (String) genreComboBox.getSelectedItem(); // Get the selected genre from the dropdown
                            catalog.addBook(new Book(id, title, author, genre, false, null));
                            JOptionPane.showMessageDialog(null, "Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            refreshBookList();
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Invalid barcode format. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error: Book with the same ID already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });

            deleteBookButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel radioPanel = new JPanel(new GridLayout(2, 1));
                    JRadioButton barcodeRadio = new JRadioButton("Delete by Barcode");
                    JRadioButton titleRadio = new JRadioButton("Delete by Title");
                    ButtonGroup group = new ButtonGroup();
                    group.add(barcodeRadio);
                    group.add(titleRadio);
                    radioPanel.add(barcodeRadio);
                    radioPanel.add(titleRadio);

                    JPanel inputPanel = new JPanel(new GridLayout(1, 1));
                    JTextField inputField = new JTextField(20);
                    inputPanel.add(inputField);

                    JPanel dialogPanel = new JPanel(new GridLayout(2, 1));
                    dialogPanel.add(radioPanel);
                    dialogPanel.add(inputPanel);

                    int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Delete Book", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        if (barcodeRadio.isSelected()) {
                            int id = Integer.parseInt(inputField.getText());
                            catalog.deleteBook(id);
                        } else if (titleRadio.isSelected()) {
                            String title = inputField.getText();
                            catalog.deleteBookByTitle(title);
                        } else {
                            JOptionPane.showMessageDialog(null, "Please select delete by Barcode or Title.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        refreshBookList();
                    }
                }
            });

            ImageIcon icon = new ImageIcon("/Users/ayana/IdeaProjects/stjean_ayana_LMS/src/Untitled design-5.png");
            frame.setIconImage(icon.getImage());

            frame.getContentPane().add(LMS);
            frame.pack();
            frame.setVisible(true);
        }
    }


    private void refreshBookList() {
        JTextArea inventoryArea = new JTextArea();
        inventoryArea.setEditable(false);
        inventoryArea.setRows(20);
        inventoryArea.setColumns(40);

        for (Book book : catalog.inventory()) {
            String status = book.getStatus();
            String dueDate = book.getDueDate() != null ? book.getDueDate().toString() : "N/A";
            inventoryArea.append(book.getId() + ", " + book.getTitle() + ", " + book.getAuthor() + ", Status: " + status + ", Due Date: " + dueDate + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(inventoryArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(null, scrollPane, "Inventory", JOptionPane.INFORMATION_MESSAGE);
    }
}
