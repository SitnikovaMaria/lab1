package com.company;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.io.File;
import java.text.DateFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.util.Set;

public class WindowView extends JFrame implements View {
    private Control controller;
    private Storage storage = Storage.getInstance();
    private DefaultTableModel b = new DefaultTableModel();
    private DefaultTableModel c = new DefaultTableModel();
    private DefaultTableModel s = new DefaultTableModel();
    private JTextField saveLine = new JTextField();
    private JTextField searchBook = new JTextField(40);
    private JTextField searchCopyOfTheBook = new JTextField(40);
    private JTable saveAndLoad;


    public String getFileName(){
        return(saveLine.getText());
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
            "Issue"
    };
    private JComboBox searchParameterBook = new JComboBox(itemsBook);
    private JComboBox searchParameterCopyOfTheBook = new JComboBox(itemsCopyOfTheBook);
    private ActionListener actionListenerBook = new TestActionListenerBook();
    private ActionListener actionListenerCopyOfTheBook = new TestActionListenerCopyOfTheBook();
    private JTable bookTable;
    private JTable copyOfTheBookTable;

    /**
     * Конструктор - создание нового объекта
     */
    public WindowView() {
        controller = new Control(storage, this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //остановка программы при закрытии окна
        setTitle("Library");

        JPanel bookTab = new JPanel();
        bookTab.setLayout(new BorderLayout());
        JPanel copyTab = new JPanel();
        copyTab.setLayout(new BorderLayout());
        JPanel saveTab = new JPanel();
        saveTab.setLayout(new BorderLayout());
        saveAndLoad = new JTable(s);
        bookTable = new JTable(b);
        copyOfTheBookTable = new JTable(c);
        JScrollPane scrollPaneS = new JScrollPane(saveAndLoad);
        JScrollPane scrollPaneB = new JScrollPane(bookTable);
        JScrollPane scrollPaneC = new JScrollPane(copyOfTheBookTable);
        JPanel bookFunctionButtons = new JPanel();
        bookFunctionButtons.setLayout(new FlowLayout());
        JButton button1 = new JButton("Add");
        JButton button2 = new JButton("Change");
        bookFunctionButtons.add(button1);
        bookFunctionButtons.add(button2);

        JPanel copyFunctionButtons = new JPanel();
        copyFunctionButtons.setLayout(new FlowLayout());
        JButton addCopyButton = new JButton("Add");
        JButton changeCopyButton = new JButton("Change");
        copyFunctionButtons.add(addCopyButton);
        copyFunctionButtons.add(changeCopyButton);

        JPanel saveAndLoadButtons = new JPanel();
        saveAndLoadButtons.setLayout(new FlowLayout());
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        saveAndLoadButtons.add(saveButton);
        saveAndLoadButtons.add(loadButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFromFile();
            }
        });

        saveLine = new JTextField();

        bookTab.add(bookFunctionButtons, BorderLayout.SOUTH);
        copyTab.add(copyFunctionButtons, BorderLayout.SOUTH);
        saveTab.add(saveAndLoadButtons, BorderLayout.SOUTH);

        /* панель для поиска книг по выбранному параметру */
        JPanel searchPanelBook = new JPanel();
        bookTab.add(searchPanelBook, BorderLayout.NORTH);
        searchPanelBook.setLayout(new FlowLayout());
        JButton searchBookButton = new JButton("Search");
        searchPanelBook.add(searchBookButton);
        searchPanelBook.add(searchBook);
        searchPanelBook.add(searchParameterBook);
        searchBookButton.addActionListener(actionListenerBook);

        /* панель для поиска экземпляров книг по выбранному параметру */
        JPanel searchPanelCopyOfTheBook = new JPanel();
        copyTab.add(searchPanelCopyOfTheBook, BorderLayout.NORTH);
        searchPanelCopyOfTheBook.setLayout(new FlowLayout());
        JButton searchCopyOfTheBookButton = new JButton("Search");
        searchPanelCopyOfTheBook.add(searchCopyOfTheBookButton);
        searchPanelCopyOfTheBook.add(searchCopyOfTheBook);
        searchPanelCopyOfTheBook.add(searchParameterCopyOfTheBook);
        searchCopyOfTheBookButton.addActionListener(actionListenerCopyOfTheBook);

        saveTab.add(saveLine, BorderLayout.NORTH);
        JTabbedPane jtp = new JTabbedPane();
        bookTab.add(scrollPaneB, BorderLayout.CENTER);
        copyTab.add(scrollPaneC, BorderLayout.CENTER);
        saveTab.add(scrollPaneS, BorderLayout.CENTER);

        jtp.addTab("Book", bookTab);
        jtp.addTab("CopyOfBook", copyTab);
        jtp.addTab("Save and Load", saveTab);

        b.addColumn("ID");
        b.addColumn("Name");
        b.addColumn("Authors");
        b.addColumn("Year");
        b.addColumn("Pages");

        c.addColumn("Inventory Number");
        c.addColumn("Book ID");
        c.addColumn("Issue");

        s.addColumn("File Name");
        s.addColumn("Date change");

        File folderLoad = new File("D:\\ncLab");
        File[] files = folderLoad.listFiles();
        for (File f:files) {
            if (f.getName().endsWith("ini")){
                Vector<String> newrow = new Vector<>();
                newrow.add(f.getName());
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                String reportDate = df.format(f.lastModified());
                newrow.add(reportDate);
                s.addRow(newrow);
            }
        }

        add(jtp);

        /*  заполним HashMap для проверки */
        Book book1 = new Book(1, "И. С. Тургенев", "Отцы и дети", 1971, 188);
        Book book2 = new Book(10, "Н. В. Гоголь", "Мертвые души", 1972, 416);
        CopyOfTheBook copyOfTheBook1 = new CopyOfTheBook(1, 1, true);
        CopyOfTheBook copyOfTheBook2 = new CopyOfTheBook(2, 1, false);
        CopyOfTheBook copyOfTheBook3 = new CopyOfTheBook(3, 1, false);
        CopyOfTheBook copyOfTheBook4 = new CopyOfTheBook(4, 2, true);
        CopyOfTheBook copyOfTheBook5 = new CopyOfTheBook(5, 2, false);
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

    /**
     * Процедура добавления в таблицу одной книги
     * @param book - книга
     */
    public void viewBook(Book book) {
        Vector<String> newRow = new Vector<String>();
        newRow.add(book.getName());
        newRow.add(book.getAuthors());
        newRow.add(Integer.toString(book.getYear()));
        newRow.add(Integer.toString(book.getPages()));
        b.addRow(newRow);
    }

    /**
     * Процедура добавления в таблицу одного экземпляра книги
     * @param book - экземпляры книги
     */
    public void viewCopyOfTheBook(CopyOfTheBook book) {
        Vector<String> newRow = new Vector<String>();
        newRow.clear();
        newRow.add(Long.toString(book.getInventoryNumber()));
        newRow.add(Long.toString(book.getIdBook()));
        newRow.add(Boolean.toString(book.getIssue()));
        c.addRow(newRow);
    }
    public void saveToFile() {

        if (getFileName() != "") {
            Serialization a = new Serialization();

            Storage tmp = new Storage();
            HashMap<Long, Book> tmpBook = new HashMap<>();

            for (int i = 0; i < b.getRowCount(); i++){
                Book tmpB = new Book();
                tmpB.setIdBook(Long.valueOf((String) (b.getValueAt(i, 0))));
                tmpB.setName((String) b.getValueAt(i, 1));
                tmpB.setAuthors((String) b.getValueAt(i, 2));
                tmpB.setYear(Integer.valueOf((String) (b.getValueAt(i, 3))));
                tmpB.setPages(Integer.valueOf((String) (b.getValueAt(i, 4))));
                tmpBook.put(tmpB.getIdBook(),tmpB );
            }

            tmp.setBookList(tmpBook);

            HashMap<Long, CopyOfTheBook> tmpCopy = new HashMap<>();
            for (int i = 0; i < c.getRowCount(); i++) {
                CopyOfTheBook tmpC = new CopyOfTheBook();
                tmpC.setIdBook(Long.valueOf((String) c.getValueAt(i, 0)));
                tmpC.setInventoryNumber(Long.valueOf((String) c.getValueAt(i, 1)));
                tmpC.setIssue(Boolean.valueOf((String) c.getValueAt(i, 2)));
                tmpCopy.put(tmpC.getIdBook(),tmpC);
            }

            tmp.setCopyOfTheBookList(tmpCopy);
            try {
                a.saveObjectStorage(tmp,(getFileName()+".ini"));
            }
            catch (IOException ex){}

            while(s.getRowCount()>0){
                s.removeRow(0);
            }
            File folderLoad = new File("D:\\ncLab");
            File[] files = folderLoad.listFiles();
            for (File f:files) {
                if (f.getName().endsWith(".ini")) {
                    Vector<String> newr = new Vector<String>();
                    newr.add(f.getName());
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                    String reportDate = df.format(f.lastModified());
                    newr.add(reportDate);
                    s.addRow(newr);
                }
            }
        }
    }

    public void loadFromFile() {
        Serialization a = new Serialization();

        while(b.getRowCount()>0){
            b.removeRow(0);
        }
        while(c.getRowCount()>0){
            c.removeRow(0);
        }
        HashMap<Long, Book> tmpBook = new HashMap<>();
        tmpBook.clear();
        Storage tmp = new Storage();
        try {
            tmp = a.loadObjectBook((String) s.getValueAt(saveAndLoad.getSelectedRow(), 0));
        }
        catch (IOException ex) {
        }
        catch (ClassNotFoundException ex){
        }

        tmp.hashCode();

        tmpBook = tmp.getBookList();

        for ( Book tmpB : tmpBook.values()) {
            Vector<String> newRow = new Vector<String>();
            newRow.add(Long.toString(tmpB.getIdBook()));
            newRow.add(tmpB.getName());
            newRow.add(tmpB.getAuthors());
            newRow.add(Integer.toString(tmpB.getYear()));
            newRow.add(Integer.toString(tmpB.getPages()));
            b.addRow(newRow);
        }


        HashMap<Long, CopyOfTheBook> tmpCopy = new HashMap<>();
        tmpCopy = tmp.getCopyOfTheBookList();

        for ( CopyOfTheBook tmpC : tmpCopy.values()) {
            Vector<String> newRow = new Vector<String>();
            newRow.add(Long.toString(tmpC.getIdBook()));
            newRow.add(Long.toString(tmpC.getInventoryNumber()));
            newRow.add(Boolean.toString(tmpC.getIssue()));
            c.addRow(newRow);
        }
    }


    public void clearTableBook(){ //очистка Book
        if (b.getRowCount() > 0) {
            for (int i = b.getRowCount() - 1; i > -1; i--) {
                b.removeRow(i);
            }
        }
    }

    public void clearTableCopyOfTheBook(){ //очистка CopyOfTheBook
        if (c.getRowCount() > 0) {
            for (int i = c.getRowCount() - 1; i > -1; i--) {
                c.removeRow(i);
            }
        }
    }

    public void fillTableBook(HashMap<Long, Book> result){ //вывод содержимого HashMap Book на экран
        clearTableBook();
        Set set = result.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Vector<String> newRow = new Vector<String>();
            Map.Entry me = (Map.Entry)iterator.next();
            newRow.add(me.getKey().toString());
            Book book = (Book) me.getValue();
            newRow.add(book.getName());
            newRow.add(book.getAuthors());
            newRow.add(Integer.toString(book.getYear()));
            newRow.add(Integer.toString(book.getPages()));
            b.addRow(newRow);
        }
    }

    public void fillTableCopyOfTheBook(HashMap<Long, CopyOfTheBook> result){ //вывод содержимого HashMap CopyOfTheBook на экран
        clearTableCopyOfTheBook();
        Set set = result.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Vector<String> newRow = new Vector<String>();
            Map.Entry me = (Map.Entry)iterator.next();
            newRow.add(me.getKey().toString());
            CopyOfTheBook copyOfTheBook = (CopyOfTheBook) me.getValue();
            newRow.add(Long.toString(copyOfTheBook.getIdBook()));
            newRow.add(Boolean.toString(copyOfTheBook.getIssue()));
            c.addRow(newRow);
        }
    }

    public class TestActionListenerBook implements ActionListener { //при нажатии на кнопку Search в Book
        public void actionPerformed(ActionEvent e) {
            String date = searchBook.getText();
            int act = 0;
            String action = searchParameterBook.getSelectedItem().toString();
            if (date.equals("")){
                act = 1;
            }
            else {
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

    public class TestActionListenerCopyOfTheBook implements ActionListener { //при нажатии на кнопку Search в CopyOfBook
        public void actionPerformed(ActionEvent e) {
            String date = searchCopyOfTheBook.getText();
            int act = 0;
            String action = searchParameterCopyOfTheBook.getSelectedItem().toString();
            if (date.equals("")){
                act = 2;
            }
            else {
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
                }
            }
            controller.operation(act, date, "", "", "", "");
        }
    }

    public static void main(String[] args) {
        WindowView w = new WindowView();
        w.setBounds(400, 250, 700, 300);
        w.setVisible(true);
    }
}



