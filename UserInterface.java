import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * UserInterface class represents the graphical user interface for the Library Management System.
 */
public class UserInterface {
    private JButton exitProgramButton; // Button to exit the program
    private JButton checkOutBookButton; // Button to check out a book
    private JButton checkInBookButton; // Button to check in a book
    private JButton deleteBookButton; // Button to delete a book
    private JButton addBookButton; // Button to add a new book
    private JButton displayAllBooksButton; // Button to display all books
    private JPanel LMS; // Panel for the library management system interface
    private JButton deleteAllBooksButton; // Button to delete all books
    private Catalog catalog; // Catalog object to interact with

    /**
     * Constructor for UserInterface class.
     *
     * @param catalog The catalog to interact with.
     */
    public UserInterface(Catalog catalog) {
        this.catalog = catalog;
        {
            // Create the main frame
            JFrame frame = new JFrame("Library Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Set font
            Font font = new Font("Century Gothic", Font.PLAIN, 14);

            // Add image label
            JLabel imageLabel = new JLabel();
            ImageIcon imageIcon = new ImageIcon("src/Untitled design-5.png");
            imageLabel.setIcon(imageIcon);
            frame.setLocationRelativeTo(null);
            frame.getContentPane().add(imageLabel, BorderLayout.NORTH);
            frame.setResizable(true);

            // Action listener for checking out a book
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

            // Action listener for checking in a book
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

            // Action listener for displaying all books
            displayAllBooksButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTextArea inventoryArea = new JTextArea();
                    inventoryArea.setEditable(false);
                    inventoryArea.setRows(20);
                    inventoryArea.setColumns(40);

                    for (Book book : catalog.inventory()) {
                        String status = book.getStatus();
                        String dueDate = book.getDueDate() != null ? book.getDueDate().toString() : "N/A";
                        inventoryArea.append(book.getId() + ", " + book.getTitle() + ", " + book.getAuthor() + ", Genre: " + book.getGenre() + ", Status: " + status + ", Due Date: " + dueDate + "\n");
                    }

                    JScrollPane scrollPane = new JScrollPane(inventoryArea);
                    scrollPane.setPreferredSize(new Dimension(400, 300));

                    JOptionPane.showMessageDialog(null, scrollPane, "Inventory", JOptionPane.INFORMATION_MESSAGE);
                    refreshBookList();
                }
            });

            // Action listener for exiting the program
            exitProgramButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Closing LMS...");
                    System.exit(0);
                }
            });

            // Action listener for adding a book
            addBookButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel inputPanel = new JPanel(new GridLayout(4, 2));
                    JTextField barcodeField = new JTextField(10);
                    JTextField titleField = new JTextField(20);
                    JTextField authorField = new JTextField(20);

                    String[] genres = {"Select Genre", "Fiction", "Non-fiction"};
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

            // Action listener for deleting a book
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

        // Action listener for deleting all books
        deleteAllBooksButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show confirmation dialog
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all books?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Delete all books from the database
                    catalog.deleteAllBooksFromDatabase();
                    // Show deletion success message
                    JOptionPane.showMessageDialog(null, "All books deleted successfully.");
                    // Refresh the book list display
                    refreshBookList();
                }
            }
        });
    }

    /**
     * Refreshes the book list display.
     */
    private void refreshBookList() {
        JTextArea inventoryArea = new JTextArea();
        inventoryArea.setEditable(false);
        inventoryArea.setRows(20);
        inventoryArea.setColumns(40);

        for (Book book : catalog.inventory()) {
            String status = book.getStatus();
            String dueDate = book.getDueDate() != null ? book.getDueDate().toString() : "N/A";
            inventoryArea.append(book.getId() + ", " + book.getTitle() + ", " + book.getAuthor() + ", Genre: " + book.getGenre() + ", Status: " + status + ", Due Date: " + dueDate + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(inventoryArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(null, scrollPane, "Inventory", JOptionPane.INFORMATION_MESSAGE);
    }
}
