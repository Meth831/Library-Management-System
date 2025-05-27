import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;

public class LibraryGUI extends JFrame{
    private Library library;
    private JTextField titleField,authorField,bookIdField;
    private JTextArea outputArea;
    private JList<String>bookList;
    private DefaultListModel<String>listModel;

    public LibraryGUI(){
        library=new Library();
        setTitle("Library Management System");
        setSize(700,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //Main Panel Colors
        Color bgColor=new Color(240,240,240);
        Color panelColor=new Color(220,220,220);
        Color buttonColor=new Color(83, 141, 189);
        Color buttonTextColor=Color.WHITE;
        getContentPane().setBackground(bgColor);

        //input panel
        JPanel inputPanel=new JPanel(new GridBagLayout());
        inputPanel.setBackground(panelColor);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbc.insets=new Insets(5,5,5,5);

        titleField=new JTextField(20);
        authorField=new JTextField(20);
        bookIdField=new JTextField(20);

        addLabelAndField(inputPanel,gbc,"Title:",titleField,0);
        addLabelAndField(inputPanel,gbc,"Author:",authorField,1);
        addLabelAndField(inputPanel,gbc,"bookId:",bookIdField,2);

        //Button with Colors
        JButton addButton = new JButton("Add Book");
        JButton borrowButton = new JButton("Borrow Book");
        JButton returnButton = new JButton("Return Book");
        JButton deleteButton = new JButton("Delete Book");

        styleButton(addButton,buttonColor,buttonTextColor);
        styleButton(borrowButton,buttonColor,buttonTextColor);
        styleButton(returnButton,buttonColor,buttonTextColor);
        styleButton(deleteButton,buttonColor,buttonTextColor);

        //Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
        buttonPanel.setBackground(bgColor);
        buttonPanel.add(addButton);
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(deleteButton);

        //output area
        outputArea=new JTextArea(2,20);
        outputArea.setEditable(false);
        outputArea.setBackground(Color.WHITE);
        outputArea.setForeground(Color.BLACK);
        outputArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        listModel=new DefaultListModel<>();
        bookList=new JList<>(listModel);
        bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane=new JScrollPane(bookList);

        //center panel
        JPanel centerPanel=new JPanel(new BorderLayout());
        centerPanel.setBackground(bgColor);
        centerPanel.add(buttonPanel,BorderLayout.NORTH);
        centerPanel.add(new JLabel("Book List:"),BorderLayout.WEST);
        centerPanel.add(listScrollPane,BorderLayout.CENTER);

        //Add Components
        add(inputPanel,BorderLayout.NORTH);
        add(centerPanel,BorderLayout.CENTER);
        add(new JScrollPane(outputArea),BorderLayout.SOUTH);

        //Button actions
        addButton.addActionListener(e -> {
            try {
                String title=titleField.getText();
                String author=authorField.getText();
                int bookId=Integer.parseInt(bookIdField.getText());
                library.addBook(new Book(bookId,title,author));
                updateBookList();
                outputArea.setText("Book added successfully!");
                //clear input fields
                titleField.setText("");
                authorField.setText("");
                bookIdField.setText("");
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid Book Id. Please enter a number");
            }
        });

        borrowButton.addActionListener(e -> {
            int selectedIndex=bookList.getSelectedIndex();
            if (selectedIndex!=-1){
                Book selectedBook=library.getBooks().get(selectedIndex);
                library.borrowBook(selectedBook.getBookId());
                updateBookList();
                outputArea.setText("Book borrowed successfully!");
            }else {
                outputArea.setText("Please select a book");
            }
        });

        returnButton.addActionListener(e -> {
            int selectedIndex=bookList.getSelectedIndex();
            if (selectedIndex!=-1){
                Book selectedBook=library.getBooks().get(selectedIndex);
                library.returnBook(selectedBook.getBookId());
                updateBookList();
                outputArea.setText("Book returned successfully!");
            }else {
                outputArea.setText("Please select a book to return!");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedIndex=bookList.getSelectedIndex();
            if (selectedIndex!=-1){
                Book selectedBook=library.getBooks().get(selectedIndex);
                library.removeBook(selectedBook.getBookId());
                updateBookList();
                outputArea.setText("Book deleted successfully!");
            }else {
                outputArea.setText("Please select a book to delete!");
            }
        });
    }




    private void addLabelAndField(JPanel panel,GridBagConstraints gbc,String labelText,JTextField textField,int yPos){
        gbc.gridx=0;
        gbc.gridy=yPos;
        gbc.anchor=GridBagConstraints.WEST;
        JLabel label=new JLabel(labelText);
        label.setFont(new Font("Arial",Font.BOLD,12));
        panel.add(label,gbc);

        gbc.gridx=1;
        gbc.weightx=1;
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(textField,gbc);
    }

    //Helper method to  style button
    private void styleButton(JButton button,Color bgColor,Color textColor){
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial",Font.BOLD,12));
    }

    private void updateBookList(){
        listModel.clear();
        for(Book book:library.getBooks()){
            listModel.addElement(book.bookDetails());
        }
    }

    public static void main(String[] args){
        new LibraryGUI().setVisible(true);
    }
}
