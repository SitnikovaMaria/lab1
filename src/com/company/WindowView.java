package com.company;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Map;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WindowView extends JFrame implements View, ListSelectionListener {
    private static int serverPort = 6666;
    private static String address = "127.0.0.1";
    private Control controller;
    private Storage storage = Storage.getInstance();
    private DefaultTableModel bookTableModel = new DefaultTableModel();
    private DefaultTableModel copyBookTableModel = new DefaultTableModel();
    private DefaultTableModel saveAndLoadTableModel = new DefaultTableModel();
    private JButton addBookButton;
    private JButton addCopyButton;
    private JButton changeBookButton;
    private JButton changeCopyButton;
    private JButton deleteBook;
    private JButton deleteCopy;
    private JTextField saveLine = new JTextField();
    private JTextField searchBook = new JTextField(40);
    private JTextField searchCopyOfTheBook = new JTextField(40);
    private JTable saveAndLoad;

    public String getFileName() {
        return (saveLine.getText());
    }

    private String[] itemsBook = {
            "ID",
            "Name",
            "Authors",
            "Year",
            "Pages"
    };

    private String[] itemsCopyOfTheBook = {
            "Inventory Number",
            "Book ID",
            "Issue",
            "Reader"
    };

    private String[] itemsIssue = {
            "true",
            "false",
    };

    private JComboBox searchParameterBook = new JComboBox(itemsBook);
    private JComboBox searchParameterCopyOfTheBook = new JComboBox(itemsCopyOfTheBook);
    private JComboBox forIssue = new JComboBox(itemsIssue);

    private ActionListener actionListenerBook = new TestActionListenerBook();
    private ActionListener actionListenerCopyOfTheBook = new TestActionListenerCopyOfTheBook();

    private JTable bookTable;
    private JTable copyOfTheBookTable;

    public WindowView() {
        setTitle("Library");
        controller = new Control(storage, this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel bookTab = new JPanel();
        bookTab.setLayout(new BorderLayout());
        JPanel copyTab = new JPanel();
        copyTab.setLayout(new BorderLayout());
        JPanel saveTab = new JPanel();
        saveTab.setLayout(new BorderLayout());

        bookTable = new JTable(bookTableModel);
        copyOfTheBookTable = new JTable(copyBookTableModel);
        saveAndLoad = new JTable(saveAndLoadTableModel);
        bookTable.getSelectionModel().addListSelectionListener(this);
        copyOfTheBookTable.getSelectionModel().addListSelectionListener(this);

        JScrollPane scrollPaneB = new JScrollPane(bookTable);
        JScrollPane scrollPaneC = new JScrollPane(copyOfTheBookTable);
        JScrollPane scrollPaneS = new JScrollPane(saveAndLoad);

        JPanel bookFunctionButtons = new JPanel();
        bookFunctionButtons.setLayout(new FlowLayout());

        deleteBook = new JButton("Delete");
        deleteBook.setEnabled(false);
        deleteBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.operation(7, (String) bookTableModel.getValueAt(bookTable.getSelectedRow(), 0), "", "", "", "");
            }
        });

        addBookButton = new JButton("Add");
        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JFrame addForm = new JFrame("ADD BOOK");
                addForm.setBounds(400, 300, 600, 150);
                JPanel addPanel = new JPanel();
                addPanel.setLayout(new BorderLayout());
                final JTextField nameLine = new JTextField(9);
                final JTextField authorLine = new JTextField(9);
                final JTextField yearLine = new JTextField(9);
                final JTextField pagesLine = new JTextField(9);
                final JTextField bookIdLine = new JTextField(9);
                JPanel labelPanel = new JPanel();
                labelPanel.setLayout(new FlowLayout());
                labelPanel.add(bookIdLine);
                labelPanel.add(nameLine);
                labelPanel.add(authorLine);
                labelPanel.add(yearLine);
                labelPanel.add(pagesLine);
                JPanel buttonPanel = new JPanel();
                JPanel textPanel = new JPanel(new FlowLayout());
                addPanel.add(labelPanel, BorderLayout.CENTER);
                addPanel.add(textPanel, BorderLayout.NORTH);
                JLabel nameLabel = new JLabel("Name");
                nameLabel.setPreferredSize(new Dimension(100, 20));
                nameLabel.setHorizontalAlignment(JLabel.CENTER);
                JLabel authorLabel = new JLabel("Authors");
                authorLabel.setPreferredSize(new Dimension(100, 20));
                authorLabel.setHorizontalAlignment(JLabel.CENTER);
                JLabel yearLabel = new JLabel("Year");
                yearLabel.setPreferredSize(new Dimension(100, 20));
                yearLabel.setHorizontalAlignment(JLabel.CENTER);
                JLabel pagesLabel = new JLabel("Pages");
                pagesLabel.setPreferredSize(new Dimension(100, 20));
                pagesLabel.setHorizontalAlignment(JLabel.CENTER);
                JLabel idBookLabel = new JLabel("IdBook");
                idBookLabel.setPreferredSize(new Dimension(100, 20));
                idBookLabel.setHorizontalAlignment(JLabel.CENTER);
                textPanel.add(idBookLabel);
                textPanel.add(nameLabel);
                textPanel.add(authorLabel);
                textPanel.add(yearLabel);
                textPanel.add(pagesLabel);
                JButton ok = new JButton();
                ok.setText("OK");
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Pattern p = Pattern.compile("^[0-9]{1,15}$");
                        Matcher bookId = p.matcher(bookIdLine.getText());
                        Matcher year = p.matcher(yearLine.getText());
                        Matcher pages = p.matcher(pagesLine.getText());
                        if (year.matches() && pages.matches() && bookId.matches()) {
                            controller.operation(3, bookIdLine.getText(), authorLine.getText(), nameLine.getText(), yearLine.getText(), pagesLine.getText());
                            addForm.setVisible(false);
                        }
                        else
                        {
                            if (!year.matches())
                            {
                                yearLine.setBackground(Color.red);
                            }
                            else
                            {
                                yearLine.setBackground(Color.white);
                            }
                            if (!pages.matches())
                            {
                                pagesLine.setBackground(Color.red);
                            }
                            else
                            {
                                pagesLine.setBackground(Color.white);
                            }
                            if (!bookId.matches())
                            {
                                bookIdLine.setBackground(Color.red);
                            }
                            else
                            {
                                bookIdLine.setBackground(Color.white);
                            }
                        }

                    }
                });
                ok.setSize(10, 5);
                buttonPanel.add(ok);
                addPanel.add(buttonPanel, BorderLayout.SOUTH);
                addForm.add(addPanel);
                addForm.setVisible(true);
            }
        });

        changeBookButton = new JButton("Change");
        changeBookButton.setEnabled(false);
        changeBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JFrame addForm = new JFrame("CHANGE BOOK");
                addForm.setBounds(400, 300, 600, 150);
                JPanel addPanel = new JPanel();
                addPanel.setLayout(new BorderLayout());
                final JTextField nameLine = new JTextField(9);
                nameLine.setText((String) bookTableModel.getValueAt(bookTable.getSelectedRow(), 1));
                final JTextField authorLine = new JTextField(9);
                authorLine.setText((String) bookTableModel.getValueAt(bookTable.getSelectedRow(), 2));
                final JTextField yearLine = new JTextField(9);
                yearLine.setText((String) bookTableModel.getValueAt(bookTable.getSelectedRow(), 3));
                final JTextField pagesLine = new JTextField(9);
                pagesLine.setText((String) bookTableModel.getValueAt(bookTable.getSelectedRow(), 4));
                JPanel labelPanel = new JPanel();
                labelPanel.setLayout(new FlowLayout());
                labelPanel.add(nameLine);
                labelPanel.add(authorLine);
                labelPanel.add(yearLine);
                labelPanel.add(pagesLine);
                JPanel buttonPanel = new JPanel();
                JPanel textPanel = new JPanel(new FlowLayout());
                addPanel.add(labelPanel, BorderLayout.CENTER);
                addPanel.add(textPanel, BorderLayout.NORTH);
                final JLabel nameLabel = new JLabel("Name");
                nameLabel.setPreferredSize(new Dimension(100, 20));
                nameLabel.setHorizontalAlignment(JLabel.CENTER);
                JLabel authorLabel = new JLabel("Authors");
                authorLabel.setPreferredSize(new Dimension(100, 20));
                authorLabel.setHorizontalAlignment(JLabel.CENTER);
                JLabel yearLabel = new JLabel("Year");
                yearLabel.setPreferredSize(new Dimension(100, 20));
                yearLabel.setHorizontalAlignment(JLabel.CENTER);
                JLabel pagesLabel = new JLabel("Pages");
                pagesLabel.setPreferredSize(new Dimension(100, 20));
                pagesLabel.setHorizontalAlignment(JLabel.CENTER);
                textPanel.add(nameLabel);
                textPanel.add(authorLabel);
                textPanel.add(yearLabel);
                textPanel.add(pagesLabel);
                JButton ok = new JButton();
                ok.setText("OK");
                ok.setSize(10, 5);
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Pattern p = Pattern.compile("^[0-9]{1,15}$");
                        Matcher year = p.matcher(yearLine.getText());
                        Matcher pages = p.matcher(pagesLine.getText());
                        if (year.matches() && pages.matches()) {
                            controller.operation(5, (String) bookTableModel.getValueAt(bookTable.getSelectedRow(), 0), authorLine.getText(), nameLine.getText(), yearLine.getText(), pagesLine.getText());
                            addForm.setVisible(false);
                        }
                        else
                        {
                            if (!year.matches())
                            {
                                yearLine.setBackground(Color.red);
                            }
                            else
                            {
                                yearLine.setBackground(Color.white);
                            }
                            if (!pages.matches())
                            {
                                pagesLine.setBackground(Color.red);
                            }
                            else
                            {
                                pagesLine.setBackground(Color.white);
                            }
                        }
                    }
                });
                buttonPanel.add(ok);
                addPanel.add(buttonPanel, BorderLayout.SOUTH);
                addForm.add(addPanel);
                addForm.setVisible(true);
            }
        });

        bookFunctionButtons.add(addBookButton);
        bookFunctionButtons.add(changeBookButton);
        bookFunctionButtons.add(deleteBook);

        JPanel copyFunctionButtons = new JPanel();
        copyFunctionButtons.setLayout(new FlowLayout());

        deleteCopy = new JButton("Delete");
        deleteCopy.setEnabled(false);
        deleteCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.operation(8, (String) copyBookTableModel.getValueAt(copyOfTheBookTable.getSelectedRow(), 0), "", "", "", "");
            }
        });

        addCopyButton = new JButton("Add");
        addCopyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JFrame addFormCopy = new JFrame("ADD COPY OF BOOK");
                addFormCopy.setBounds(400, 300, 400, 150);
                JPanel addPanel = new JPanel();
                addPanel.setLayout(new BorderLayout());
                final JTextField inventoryNumberLine = new JTextField(7);
                final JTextField bookIdLine = new JTextField(7);
                final JTextField readerLine = new JTextField(7);
                JPanel labelPanel = new JPanel();
                JPanel textPanel = new JPanel(new FlowLayout());
                addPanel.add(labelPanel, BorderLayout.CENTER);
                addPanel.add(textPanel, BorderLayout.NORTH);
                labelPanel.setLayout(new FlowLayout());
                labelPanel.add(inventoryNumberLine);
                labelPanel.add(bookIdLine);
                labelPanel.add(forIssue);
                labelPanel.add(readerLine);
                JPanel buttonPanel = new JPanel();
                JLabel inventoryNumberLabel = new JLabel("Inv. Number");
                inventoryNumberLabel.setPreferredSize(new Dimension(70,10));
                inventoryNumberLabel.setHorizontalAlignment(JLabel.CENTER);
                JLabel idBookLabel = new JLabel("Book ID");
                idBookLabel.setPreferredSize(new Dimension(80, 10));
                idBookLabel.setHorizontalAlignment(JLabel.CENTER);
                JLabel issueLabel = new JLabel("Issue");
                issueLabel.setPreferredSize(new Dimension(55, 10));
                issueLabel.setHorizontalAlignment(JLabel.CENTER);
                JLabel readerLabel = new JLabel("Reader");
                readerLabel.setPreferredSize(new Dimension(70, 10));
                readerLabel.setHorizontalAlignment(JLabel.CENTER);
                textPanel.add(inventoryNumberLabel);
                textPanel.add(idBookLabel);
                textPanel.add(issueLabel);
                textPanel.add(readerLabel);
                JButton ok = new JButton();
                ok.setText("OK");
                ok.setSize(10, 5);
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Pattern p = Pattern.compile("^[0-9]{1,15}$");
                        Matcher bookId = p.matcher(bookIdLine.getText());
                        Matcher inventoryNumber = p.matcher(inventoryNumberLine.getText());
                        if (bookId.matches() && inventoryNumber.matches()) {
                            if (forIssue.getSelectedItem().toString().equals("false")) {
                                controller.operation(4, inventoryNumberLine.getText(), bookIdLine.getText(), "false", "нет", "");
                            } else {

                                if (readerLine.getText().equals("нет")) {
                                    controller.operation(2, "", "", "", "", "");
                                } else {
                                    controller.operation(4, inventoryNumberLine.getText(), bookIdLine.getText(), "true", readerLine.getText(), "");
                                }
                            }
                            addFormCopy.setVisible(false);
                        }
                        else
                        {
                            if (!bookId.matches())
                            {
                                bookIdLine.setBackground(Color.red);
                            }
                            else
                            {
                                bookIdLine.setBackground(Color.white);
                            }
                            if (!inventoryNumber.matches())
                            {
                                inventoryNumberLine.setBackground(Color.red);
                            }
                            else
                            {
                                inventoryNumberLine.setBackground(Color.white);
                            }
                        }
                    }
                });
                buttonPanel.add(ok);
                addPanel.add(buttonPanel, BorderLayout.SOUTH);
                addFormCopy.add(addPanel);
                addFormCopy.setVisible(true);
            }
        });

        changeCopyButton = new JButton("Change");
        changeCopyButton.setEnabled(false);
        changeCopyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JFrame addFormCopy = new JFrame("CHANGE COPY OF BOOK");
                addFormCopy.setBounds(400, 300, 400, 150);
                JPanel addPanel = new JPanel();
                addPanel.setLayout(new BorderLayout());
                final JTextField readerLine = new JTextField(9);
                JPanel labelPanel = new JPanel();
                JPanel textPanel = new JPanel(new FlowLayout());
                addPanel.add(labelPanel, BorderLayout.CENTER);
                addPanel.add(textPanel, BorderLayout.NORTH);
                labelPanel.setLayout(new FlowLayout());
                labelPanel.add(forIssue);
                labelPanel.add(readerLine);
                JPanel buttonPanel = new JPanel();
                JLabel issueLabel = new JLabel("Issue");
                issueLabel.setPreferredSize(new Dimension(105, 20));
                issueLabel.setHorizontalAlignment(JLabel.CENTER);
                JLabel readerLabel = new JLabel("Reader");
                readerLabel.setPreferredSize(new Dimension(105, 20));
                readerLabel.setHorizontalAlignment(JLabel.CENTER);
                textPanel.add(issueLabel);
                textPanel.add(readerLabel);
                JButton ok = new JButton();
                ok.setText("OK");
                ok.setSize(10, 5);
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (forIssue.getSelectedItem().toString().equals("false")) {
                            controller.operation(6, (String) bookTableModel.getValueAt(copyOfTheBookTable.getSelectedRow(), 0),(String) bookTableModel.getValueAt(copyOfTheBookTable.getSelectedRow(), 1), "false", "нет", "");
                        } else {
                            if (readerLine.getText().equals("нет")) {
                                controller.operation(2, "", "", "", "", "");
                            } else {
                                controller.operation(6, (String) bookTableModel.getValueAt(copyOfTheBookTable.getSelectedRow(), 0), (String) bookTableModel.getValueAt(copyOfTheBookTable.getSelectedRow(), 1), "true", readerLine.getText(), "");
                            }
                        }
                        addFormCopy.setVisible(false);
                    }
                });
                buttonPanel.add(ok);
                addPanel.add(buttonPanel, BorderLayout.SOUTH);
                addFormCopy.add(addPanel);
                addFormCopy.setVisible(true);
            }
        });

        copyFunctionButtons.add(addCopyButton);
        copyFunctionButtons.add(changeCopyButton);
        copyFunctionButtons.add(deleteCopy);

        JPanel saveAndLoadButtons = new JPanel();
        saveAndLoadButtons.setLayout(new FlowLayout());
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton saveAndMergeButton = new JButton("Merge");
        saveAndLoadButtons.add(saveButton);
        saveAndLoadButtons.add(loadButton);
        saveAndLoadButtons.add(saveAndMergeButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveToFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFromFile();
            }
        });

        saveAndMergeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAndMergeWithFile();
            }
        });

        saveLine = new JTextField();

        bookTab.add(bookFunctionButtons, BorderLayout.SOUTH);
        copyTab.add(copyFunctionButtons, BorderLayout.SOUTH);
        saveTab.add(saveAndLoadButtons, BorderLayout.SOUTH);

        addSearchPanelBook(bookTab);

        addSearchPanelCopyOfBook(copyTab);

        saveTab.add(saveLine, BorderLayout.NORTH);
        JTabbedPane jtp = new JTabbedPane();
        bookTab.add(scrollPaneB, BorderLayout.CENTER);
        copyTab.add(scrollPaneC, BorderLayout.CENTER);
        saveTab.add(scrollPaneS, BorderLayout.CENTER);

        jtp.addTab("Book", bookTab);
        jtp.addTab("CopyOfBook", copyTab);
        jtp.addTab("Save and Load", saveTab);

        bookTableModel.addColumn("ID");
        bookTableModel.addColumn("Name");
        bookTableModel.addColumn("Authors");
        bookTableModel.addColumn("Year");
        bookTableModel.addColumn("Pages");

        copyBookTableModel.addColumn("Inventory Number");
        copyBookTableModel.addColumn("Book ID");
        copyBookTableModel.addColumn("Issue");
        copyBookTableModel.addColumn("Reader");

        saveAndLoadTableModel.addColumn("File Name");
        saveAndLoadTableModel.addColumn("Date change");

        File folderLoad = new File("../nc");
        File[] files = folderLoad.listFiles();
        for (File f : files) {
            if (f.getName().endsWith("ini")) {
                Vector<String> newrow = new Vector<>();
                newrow.add(f.getName());
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                String reportDate = df.format(f.lastModified());
                newrow.add(reportDate);
                saveAndLoadTableModel.addRow(newrow);
            }
        }

        add(jtp);

        Book book1 = new Book(1, "И. С. Тургенев", "Отцы и дети", 1971, 188);
        Book book2 = new Book(2, "Н. В. Гоголь", "Мертвые души", 1972, 416);
        CopyOfTheBook copyOfTheBook1 = new CopyOfTheBook(1, 1, true, "Ю. А. Петрова");
        CopyOfTheBook copyOfTheBook2 = new CopyOfTheBook(2, 1, false, "нет");
        CopyOfTheBook copyOfTheBook3 = new CopyOfTheBook(3, 1, false, "нет");
        CopyOfTheBook copyOfTheBook4 = new CopyOfTheBook(4, 2, true, "Н. И. Васина");
        CopyOfTheBook copyOfTheBook5 = new CopyOfTheBook(5, 2, false, "нет");
        HashMap<Long, Book> bookList = new HashMap<>();
        bookList.put(book1.getIdBook(), book1);
        bookList.put(book2.getIdBook(), book2);
        HashMap<Long, CopyOfTheBook> copyOfTheBookList = new HashMap<>();
        copyOfTheBookList.put(copyOfTheBook1.getInventoryNumber(), copyOfTheBook1);
        copyOfTheBookList.put(copyOfTheBook2.getInventoryNumber(), copyOfTheBook2);
        copyOfTheBookList.put(copyOfTheBook3.getInventoryNumber(), copyOfTheBook3);
        copyOfTheBookList.put(copyOfTheBook4.getInventoryNumber(), copyOfTheBook4);
        copyOfTheBookList.put(copyOfTheBook5.getInventoryNumber(), copyOfTheBook5);
        Storage storage = Storage.getInstance();
        storage.setBookList(bookList);
        storage.setCopyOfTheBookList(copyOfTheBookList);
        fillTableBook(bookList);
        fillTableCopyOfTheBook(copyOfTheBookList);
    }

    private void addSearchPanelBook(JPanel bookTab) {
        JPanel searchPanelBook = new JPanel();
        bookTab.add(searchPanelBook, BorderLayout.NORTH);
        searchPanelBook.setLayout(new FlowLayout());
        JButton searchBookButton = new JButton("Search");
        searchPanelBook.add(searchBookButton);
        searchPanelBook.add(searchBook);
        searchPanelBook.add(searchParameterBook);
        searchBookButton.addActionListener(actionListenerBook);
    }

    /**
     *
     * @param copyTab - this is table of book copy
     */
    private void addSearchPanelCopyOfBook(JPanel copyTab){
        JPanel searchPanelCopyOfTheBook = new JPanel();
        copyTab.add(searchPanelCopyOfTheBook, BorderLayout.NORTH);
        searchPanelCopyOfTheBook.setLayout(new FlowLayout());
        JButton searchCopyOfTheBookButton = new JButton("Search");
        searchPanelCopyOfTheBook.add(searchCopyOfTheBookButton);
        searchPanelCopyOfTheBook.add(searchCopyOfTheBook);
        searchPanelCopyOfTheBook.add(searchParameterCopyOfTheBook);
        searchCopyOfTheBookButton.addActionListener(actionListenerCopyOfTheBook);
    }

    public void viewBook(Book book) {
        Vector<String> newRow = new Vector<String>();
        newRow.add(book.getName());
        newRow.add(book.getAuthors());
        newRow.add(Integer.toString(book.getYear()));
        newRow.add(Integer.toString(book.getPages()));
        bookTableModel.addRow(newRow);
    }

    public void viewCopyOfTheBook(CopyOfTheBook book) {
        Vector<String> newRow = new Vector<String>();
        newRow.clear();
        newRow.add(Long.toString(book.getInventoryNumber()));
        newRow.add(Long.toString(book.getIdBook()));
        newRow.add(Boolean.toString(book.getIssue()));
        copyBookTableModel.addRow(newRow);
    }

    public void saveAndMergeWithFile() {
        Storage tmp = new Storage();
        ObjectOutputStream output;
        ObjectInputStream input;
        Socket connection;
        try {
            connection = new Socket(InetAddress.getByName("127.0.0.1"), 6666);
            output = new ObjectOutputStream(connection.getOutputStream());
            input = new ObjectInputStream(connection.getInputStream());
            output.flush();
            output.writeObject("saveandmerge");
            output.flush();
            output.writeObject((String) saveAndLoadTableModel.getValueAt(saveAndLoad.getSelectedRow(), 0));
            output.flush();


            Storage tmpNew = new Storage();
            HashMap<Long, Book> tmpBook = new HashMap<>();

            for (int i = 0; i < bookTableModel.getRowCount(); i++) {
                Book tmpB = new Book();
                tmpB.setIdBook(Long.valueOf((String) (bookTableModel.getValueAt(i, 0))));
                tmpB.setName((String) bookTableModel.getValueAt(i, 1));
                tmpB.setAuthors((String) bookTableModel.getValueAt(i, 2));
                tmpB.setYear(Integer.valueOf((String) (bookTableModel.getValueAt(i, 3))));
                tmpB.setPages(Integer.valueOf((String) (bookTableModel.getValueAt(i, 4))));
                tmpBook.put(tmpB.getIdBook(), tmpB);
            }

            tmpNew.setBookList(tmpBook);

            HashMap<Long, CopyOfTheBook> tmpCopy = new HashMap<>();
            for (int i = 0; i < copyBookTableModel.getRowCount(); i++) {
                CopyOfTheBook tmpC = new CopyOfTheBook();
                tmpC.setIdBook(Long.valueOf((String) copyBookTableModel.getValueAt(i, 0)));
                tmpC.setInventoryNumber(Long.valueOf((String) copyBookTableModel.getValueAt(i, 1)));
                tmpC.setIssue(Boolean.valueOf((String) copyBookTableModel.getValueAt(i, 2)));
                tmpC.setReader((String) copyBookTableModel.getValueAt(i,3));
                tmpCopy.put(tmpC.getIdBook(), tmpC);
            }

            tmpNew.setCopyOfTheBookList(tmpCopy);
            tmp = (Storage) input.readObject();
            tmpBook = new HashMap<>();
            tmpBook = tmp.getBookList();
            HashMap<Long, Book> tmpNewBook = new HashMap<>();
            tmpNewBook = tmpNew.getBookList();
            for (Book tmpNewB : tmpNewBook.values()) {
                boolean check = true;
                for (Book tmpB : tmpBook.values()) {
                    if (tmpB.getYear() == tmpNewB.getYear())
                        if (tmpB.getPages() == tmpNewB.getPages())
                            if (tmpB.getAuthors().equals(tmpNewB.getAuthors()))
                                if (tmpB.getName().equals(tmpNewB.getName())) {
                                    check = false;
                                }
                }
                if (check == true) {
                    tmpBook.put(tmpNewB.getIdBook(), tmpNewB);
                }
            }

            tmpCopy = new HashMap<>();
            tmpCopy = tmp.getCopyOfTheBookList();
            HashMap<Long, CopyOfTheBook> tmpNewCopy = new HashMap<>();
            tmpNewCopy = tmpNew.getCopyOfTheBookList();
            for (CopyOfTheBook tmpNewC : tmpNewCopy.values()) {
                boolean check = true;
                for (CopyOfTheBook tmpC : tmpCopy.values()) {
                    if (tmpC.getReader().equals(tmpNewC.getReader()))
                    if (tmpC.getInventoryNumber() == tmpNewC.getInventoryNumber() && tmpC.getIssue() == tmpNewC.getIssue()) {
                        check = false;
                    }
                }
                if (check == true) {
                    tmpCopy.put(tmpNewC.getIdBook(), tmpNewC);
                }
            }
            tmp.setBookList(tmpBook);
            tmp.setCopyOfTheBookList(tmpCopy);
            output.writeObject(tmp);
            output.flush();
            output.close();
            input.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveToFile() throws IOException {
        ObjectOutputStream output;
        ObjectInputStream input;
        Socket connection;
        try {
            connection = new Socket(InetAddress.getByName("127.0.0.1"), 6666);
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();

            output.writeObject("save");
            output.flush();
            output.writeObject(getFileName() + ".ini");
            output.flush();

            Storage tmp = new Storage();
            HashMap<Long, Book> tmpBook = new HashMap<>();

            for (int i = 0; i < bookTableModel.getRowCount(); i++) {
                Book tmpB = new Book();
                tmpB.setIdBook(Long.valueOf((String) (bookTableModel.getValueAt(i, 0))));
                tmpB.setName((String) bookTableModel.getValueAt(i, 1));
                tmpB.setAuthors((String) bookTableModel.getValueAt(i, 2));
                tmpB.setYear(Integer.valueOf((String) (bookTableModel.getValueAt(i, 3))));
                tmpB.setPages(Integer.valueOf((String) (bookTableModel.getValueAt(i, 4))));
                tmpBook.put(tmpB.getIdBook(), tmpB);
            }

            tmp.setBookList(tmpBook);

            HashMap<Long, CopyOfTheBook> tmpCopy = new HashMap<>();
            for (int i = 0; i < copyBookTableModel.getRowCount(); i++) {
                CopyOfTheBook tmpC = new CopyOfTheBook();
                tmpC.setIdBook(Long.valueOf((String) copyBookTableModel.getValueAt(i, 0)));
                tmpC.setInventoryNumber(Long.valueOf((String) copyBookTableModel.getValueAt(i, 1)));
                tmpC.setIssue(Boolean.valueOf((String) copyBookTableModel.getValueAt(i, 2)));
                tmpC.setReader((String) copyBookTableModel.getValueAt(i,3));
                tmpCopy.put(tmpC.getIdBook(), tmpC);
            }

            tmp.setCopyOfTheBookList(tmpCopy);

            output.writeObject(tmp);
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            output.close();
            input.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        while (saveAndLoadTableModel.getRowCount() > 0) {
            saveAndLoadTableModel.removeRow(0);
        }
        File folderLoad = new File("../nc");
        File[] files = folderLoad.listFiles();
        for (File f : files) {
            if (f.getName().endsWith(".ini")) {
                Vector<String> newr = new Vector<String>();
                newr.add(f.getName());
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                String reportDate = df.format(f.lastModified());
                newr.add(reportDate);
                saveAndLoadTableModel.addRow(newr);
            }
        }
    }

    public void loadFromFile() {
        if (saveAndLoad.getSelectedRowCount() != 0) {
            while (bookTableModel.getRowCount() > 0) {
                bookTableModel.removeRow(0);
            }
            while (copyBookTableModel.getRowCount() > 0) {
                copyBookTableModel.removeRow(0);
            }
            Storage tmp = new Storage();
            ObjectOutputStream output;
            ObjectInputStream input;
            Socket connection;
            try {
                connection = new Socket(InetAddress.getByName("127.0.0.1"), 6666);
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                output.flush();
                output.writeObject("load");
                output.flush();

                output.writeObject((String) saveAndLoadTableModel.getValueAt(saveAndLoad.getSelectedRow(), 0));
                output.flush();

                tmp = (Storage) input.readObject();
                HashMap<Long, Book> tmpBook = new HashMap<>();
                tmpBook = tmp.getBookList();

                for (Book tmpB : tmpBook.values()) {
                    Vector<String> newRow = new Vector<String>();
                    newRow.add(Long.toString(tmpB.getIdBook()));
                    newRow.add(tmpB.getName());
                    newRow.add(tmpB.getAuthors());
                    newRow.add(Integer.toString(tmpB.getYear()));
                    newRow.add(Integer.toString(tmpB.getPages()));
                    bookTableModel.addRow(newRow);
                }

                HashMap<Long, CopyOfTheBook> tmpCopy = new HashMap<>();
                tmpCopy = tmp.getCopyOfTheBookList();

                for (CopyOfTheBook tmpC : tmpCopy.values()) {
                    Vector<String> newRow = new Vector<String>();
                    newRow.add(Long.toString(tmpC.getIdBook()));
                    newRow.add(Long.toString(tmpC.getInventoryNumber()));
                    newRow.add(Boolean.toString(tmpC.getIssue()));
                    newRow.add(tmpC.getReader());
                    copyBookTableModel.addRow(newRow);
                }
                output.close();
                input.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void clearTableBook() {
        if (bookTableModel.getRowCount() > 0) {
            for (int i = bookTableModel.getRowCount() - 1; i > -1; i--) {
                bookTableModel.removeRow(i);
            }
        }
    }

    public void clearTableCopyOfTheBook() {
        if (copyBookTableModel.getRowCount() > 0) {
            for (int i = copyBookTableModel.getRowCount() - 1; i > -1; i--) {
                copyBookTableModel.removeRow(i);
            }
        }
    }

    public void fillTableBook(HashMap<Long, Book> result) {
        clearTableBook();
        for (Map.Entry<Long, Book> entry : result.entrySet()) {
            Vector<String> newRow = new Vector<String>();
            newRow.add(Long.toString(entry.getKey()));
            newRow.add(entry.getValue().getName());
            newRow.add(entry.getValue().getAuthors());
            newRow.add(Integer.toString(entry.getValue().getYear()));
            newRow.add(Integer.toString(entry.getValue().getPages()));
            bookTableModel.addRow(newRow);
        }
    }

    public void fillTableCopyOfTheBook(HashMap<Long, CopyOfTheBook> result) {
        clearTableCopyOfTheBook();
        for (Map.Entry<Long, CopyOfTheBook> entry : result.entrySet()) {
            Vector<String> newRow = new Vector<String>();
            newRow.add(Long.toString(entry.getKey()));
            newRow.add(Long.toString(entry.getValue().getIdBook()));
            newRow.add(Boolean.toString(entry.getValue().getIssue()));
            newRow.add(entry.getValue().getReader());
            copyBookTableModel.addRow(newRow);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == bookTable.getSelectionModel() && e.getFirstIndex() >= 0) {
            if (bookTable.getSelectedRowCount() != 0) {
                changeBookButton.setEnabled(true);
                deleteBook.setEnabled(true);
            } else {
                changeBookButton.setEnabled(false);
                deleteBook.setEnabled(false);
            }
        }
        if (e.getSource() == copyOfTheBookTable.getSelectionModel() && e.getFirstIndex() >= 0) {
            if (copyOfTheBookTable.getSelectedRowCount() != 0) {
                changeCopyButton.setEnabled(true);
                deleteCopy.setEnabled(true);
            } else {
                changeCopyButton.setEnabled(false);
                deleteCopy.setEnabled(false);
            }
        }
    }

    public class TestActionListenerBook implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String date = searchBook.getText();
            int act = 0;
            String action = searchParameterBook.getSelectedItem().toString();
            if (date.equals("")) {
                act = 1;
            } else {
                switch (action) {
                    case "ID":
                        act = 9;
                        break;
                    case "Name":
                        act = 10;
                        break;
                    case "Authors":
                        act = 11;
                        break;
                    case "Year":
                        act = 12;
                        break;
                    case "Pages":
                        act = 13;
                        break;
                }
            }
            controller.operation(act, date, "", "", "", "");
        }
    }

    public class TestActionListenerCopyOfTheBook implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String date = searchCopyOfTheBook.getText();
            int act = 0;
            String action = searchParameterCopyOfTheBook.getSelectedItem().toString();
            if (date.equals("")) {
                act = 2;
            } else {
                switch (action) {
                    case "Inventory Number":
                        act = 14;
                        break;
                    case "Book ID":
                        act = 15;
                        break;
                    case "Issue":
                        act = 16;
                        break;
                    case "Reader":
                        act = 17;
                        break;

                }
            }
            controller.operation(act, date, "", "", "", "");
        }
    }

    public static void main(String[] ar) {
        WindowView w = new WindowView();
        w.setBounds(400, 250, 700, 300);
        w.setVisible(true);
    }
}



