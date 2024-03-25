import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserInterface {
    private JButton exitProgramButton;
    private JButton checkOutBookButton;
    private JButton checkInBookButton;
    private JButton deleteBookButton;
    private JButton addBookButton;
    private JButton displayAllBooksButton;
    private JPanel LMS;
    private Catalog catalog;

    public UserInterface(Catalog catalog) {
        this.catalog = catalog;
        catalog.readFromFile("catalog.txt");
        //catalog.displayAllBooks();
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
                catalog.checkOutBook(id);
            }
        });

        checkInBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the book barcode:"));
                catalog.checkInBook(id);
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
                        inventoryArea.append(book.getId() + ", " + book.getTitle() + ", " + book.getAuthor() + "\n");
                    }

                    JScrollPane scrollPane = new JScrollPane(inventoryArea);
                    scrollPane.setPreferredSize(new Dimension(400, 300));

                    JOptionPane.showMessageDialog(null, scrollPane, "Inventory", JOptionPane.INFORMATION_MESSAGE);
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
                JPanel inputPanel = new JPanel(new GridLayout(3, 2));

                JTextField barcodeField = new JTextField(10);
                JTextField titleField = new JTextField(20);
                JTextField authorField = new JTextField(20);

                inputPanel.add(new JLabel("Barcode:"));
                inputPanel.add(barcodeField);
                inputPanel.add(new JLabel("Title:"));
                inputPanel.add(titleField);
                inputPanel.add(new JLabel("Author:"));
                inputPanel.add(authorField);

                int result = JOptionPane.showConfirmDialog(null, inputPanel, "Add Book", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        int id = Integer.parseInt(barcodeField.getText());
                        String title = titleField.getText();
                        String author = authorField.getText();
                        catalog.addBook(new Book(id, title, author));
                        JOptionPane.showMessageDialog(null, "Book added successfully!");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid barcode format. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
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
                }
            }
        });

        ImageIcon icon = new ImageIcon("/Users/ayana/IdeaProjects/stjean_ayana_LMS/src/Untitled design-5.png"); // Replace with the correct path to your icon image
        frame.setIconImage(icon.getImage());


        frame.getContentPane().add(LMS);
        frame.pack();
        frame.setVisible(true);
    }

        LMS.addComponentListener(new ComponentAdapter() {
            /**
             * Invoked when the component's size changes.
             *
             * @param e
             */
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }}

