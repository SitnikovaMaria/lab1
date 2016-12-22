package com.view;

import com.company.Storage;
import com.controller.Control;
import com.model.*;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
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
    private DefaultTableModel catalogTableModel = new DefaultTableModel();
    private DefaultTableModel publisherTableModel = new DefaultTableModel();
    private JButton addBookButton;
    private JButton addCopyButton;
    private JButton changeBookButton;
    private JButton changeCopyButton;
    private JButton deleteBook;
    private JButton deleteCopy;
    private JButton addPublisherButton;
    private JButton changePublisherButton;
    private JButton deletePublisher;
    private JButton addCatalogButton;
    private JButton changeCatalogButton;
    private JButton deleteCatalog;
    private JTextField saveLine = new JTextField();
    private JTextField searchBook = new JTextField(40);
    private JTextField searchCopyOfTheBook = new JTextField(40);
    private JTextField searchPublisher = new JTextField(40);
    private JTable saveAndLoad;

    public String getFileName() {
        return (saveLine.getText());
    }

    private String[] itemsBook = {
            "ID",
            "Name",
            "Authors",
            "Year",
            "Pages",
            "Catalog",
            "Publisher"
    };

    private String[] itemsCopyOfTheBook = {
            "Inventory Number",
            "Book ID",
            "Issue",
            "Reader"
    };

    private String[] itemsPublisher = {
            "ID",
            "Name",
            "Registered address",
            "Business address"
    };

    private String[] itemsIssue = {
            "true",
            "false"
    };

    private JComboBox searchParameterBook = new JComboBox(itemsBook);
    private JComboBox searchParameterCopyOfTheBook = new JComboBox(itemsCopyOfTheBook);
    private JComboBox searchParameterPublisher = new JComboBox(itemsPublisher);
    private JComboBox forIssue = new JComboBox(itemsIssue);

    private ActionListener actionListenerBook = new TestActionListenerBook();
    private ActionListener actionListenerCopyOfTheBook = new TestActionListenerCopyOfTheBook();
    private ActionListener actionListenerPublisher = new TestActionListenerPublisher();

    private JTable bookTable;
    private JTable copyOfTheBookTable;
    private JTable catalogTable;
    private JTable publisherTable;

    public WindowView() {
        Catalog catalog1 = new Catalog(1, "Романы", "Общий");
        Catalog catalog2 = new Catalog(2, "Поэмы", "Общий");
        Catalog catalog3 = new Catalog(3, "Другое", "Общий");
        HashMap<Long, Catalog> catalogList = new HashMap<>();
        catalogList.put(catalog1.getIdCatalog(), catalog1);
        catalogList.put(catalog2.getIdCatalog(), catalog2);
        catalogList.put(catalog3.getIdCatalog(), catalog3);
        Publisher publisher1 = new Publisher(1, "Просвещение", "127521, Москва, 3-й проезд Марьиной рощи, 41", "prosv@prosv.ru");
        Publisher publisher2 = new Publisher(2, "Дрофа", "123308, г. Москва, ул. Зорге, д.1", "info@drofa-ventana.ru");
        Publisher publisher3 = new Publisher(3, "Другое", "Неизвестный адрес", "Неизвестный адрес");
        HashMap<Long, Publisher> publisherList = new HashMap<>();
        publisherList.put(publisher1.getIdPublisher(), publisher1);
        publisherList.put(publisher2.getIdPublisher(), publisher2);
        publisherList.put(publisher3.getIdPublisher(), publisher3);

        setTitle("Library");
        controller = new Control(storage, this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel bookTab = new JPanel();
        bookTab.setLayout(new BorderLayout());
        JPanel copyTab = new JPanel();
        copyTab.setLayout(new BorderLayout());
        JPanel saveTab = new JPanel();
        saveTab.setLayout(new BorderLayout());
        JPanel publisherTab = new JPanel();
        publisherTab.setLayout(new BorderLayout());

        bookTable = new JTable(bookTableModel);
        copyOfTheBookTable = new JTable(copyBookTableModel);
        saveAndLoad = new JTable(saveAndLoadTableModel);
        catalogTable = new JTable(catalogTableModel);
        publisherTable = new JTable(publisherTableModel);

        bookTable.getSelectionModel().addListSelectionListener(this);
        copyOfTheBookTable.getSelectionModel().addListSelectionListener(this);
        catalogTable.getSelectionModel().addListSelectionListener(this);
        publisherTable.getSelectionModel().addListSelectionListener(this);

        JScrollPane scrollPaneBook = new JScrollPane(bookTable);
        JScrollPane scrollPaneCopy = new JScrollPane(copyOfTheBookTable);
        JScrollPane scrollPaneSaveAndLoad = new JScrollPane(saveAndLoad);
        JScrollPane scrollPanePublisher = new JScrollPane(publisherTable);

        JPanel bookFunctionButtons = new JPanel();
        bookFunctionButtons.setLayout(new FlowLayout());

        deleteBook = new JButton("Delete");
        deleteBook.setEnabled(false);
        deleteBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.operation(7, (String) bookTableModel.getValueAt(bookTable.getSelectedRow(), 0), "", "", "", "", "", "");
            }
        });

        addBookButton = new JButton("Add");
        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JFrame addForm = new JFrame("ADD BOOK");
                addForm.setBounds(400, 300, 800, 150);
                JPanel addPanel = new JPanel();
                addPanel.setLayout(new BorderLayout());
                final JTextField bookIdLine = new JTextField(9);
                final JTextField nameLine = new JTextField(9);
                final JTextField authorLine = new JTextField(9);
                final JTextField yearLine = new JTextField(9);
                final JTextField pagesLine = new JTextField(9);
                final JTextField catalogLine = new JTextField(9);
                final JTextField publisherLine = new JTextField(9);
                JPanel labelPanel = new JPanel();
                labelPanel.setLayout(new FlowLayout());
                labelPanel.add(bookIdLine);
                labelPanel.add(nameLine);
                labelPanel.add(authorLine);
                labelPanel.add(yearLine);
                labelPanel.add(pagesLine);
                labelPanel.add(catalogLine);
                labelPanel.add(publisherLine);
                JPanel buttonPanel = new JPanel();
                JPanel textPanel = new JPanel(new FlowLayout());
                addPanel.add(labelPanel, BorderLayout.CENTER);
                addPanel.add(textPanel, BorderLayout.NORTH);
                JLabel idBookLabel = new JLabel("IdBook");
                idBookLabel.setPreferredSize(new Dimension(100, 20));
                idBookLabel.setHorizontalAlignment(JLabel.CENTER);
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
                JLabel catalogLabel = new JLabel("Catalog");
                catalogLabel.setPreferredSize(new Dimension(100, 20));
                catalogLabel.setHorizontalAlignment(JLabel.CENTER);
                JLabel publisherLabel = new JLabel("Publisher");
                publisherLabel.setPreferredSize(new Dimension(100, 20));
                publisherLabel.setHorizontalAlignment(JLabel.CENTER);
                textPanel.add(idBookLabel);
                textPanel.add(nameLabel);
                textPanel.add(authorLabel);
                textPanel.add(yearLabel);
                textPanel.add(pagesLabel);
                textPanel.add(catalogLabel);
                textPanel.add(publisherLabel);
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
                            controller.operation(3, bookIdLine.getText(), authorLine.getText(), nameLine.getText(), yearLine.getText(), pagesLine.getText(), catalogLine.getText(), publisherLine.getText());
                            addForm.setVisible(false);
                        }
                        else{
                            if (!year.matches()){
                                yearLine.setBackground(Color.red);
                            }
                            else{
                                yearLine.setBackground(Color.white);
                            }
                            if (!pages.matches()){
                                pagesLine.setBackground(Color.red);
                            }
                            else{
                                pagesLine.setBackground(Color.white);
                            }
                            if (!bookId.matches()){
                                bookIdLine.setBackground(Color.red);
                            }
                            else{
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
                addForm.setBounds(400, 300, 800, 150);
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
                final JTextField catalogLine = new JTextField(9);
                catalogLine.setText((String) bookTableModel.getValueAt(bookTable.getSelectedRow(), 5));
                final JTextField publisherLine = new JTextField(9);
                publisherLine.setText((String) bookTableModel.getValueAt(bookTable.getSelectedRow(), 6));
                JPanel labelPanel = new JPanel();
                labelPanel.setLayout(new FlowLayout());
                labelPanel.add(nameLine);
                labelPanel.add(authorLine);
                labelPanel.add(yearLine);
                labelPanel.add(pagesLine);
                labelPanel.add(catalogLine);
                labelPanel.add(publisherLine);
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
                JLabel catalogLabel = new JLabel("Catalog");
                catalogLabel.setPreferredSize(new Dimension(100, 20));
                catalogLabel.setHorizontalAlignment(JLabel.CENTER);
                JLabel publisherLabel = new JLabel("Publisher");
                publisherLabel.setPreferredSize(new Dimension(100, 20));
                publisherLabel.setHorizontalAlignment(JLabel.CENTER);
                textPanel.add(nameLabel);
                textPanel.add(authorLabel);
                textPanel.add(yearLabel);
                textPanel.add(pagesLabel);
                textPanel.add(catalogLabel);
                textPanel.add(publisherLabel);
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
                            controller.operation(5, (String) bookTableModel.getValueAt(bookTable.getSelectedRow(), 0), authorLine.getText(), nameLine.getText(), yearLine.getText(), pagesLine.getText(), catalogLine.getText(), publisherLine.getText());
                            addForm.setVisible(false);
                        }
                        else{
                            if (!year.matches()){
                                yearLine.setBackground(Color.red);
                            }
                            else{
                                yearLine.setBackground(Color.white);
                            }
                            if (!pages.matches()){
                                pagesLine.setBackground(Color.red);
                            }
                            else{
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
                controller.operation(8, (String) copyBookTableModel.getValueAt(copyOfTheBookTable.getSelectedRow(), 0), "", "", "", "", "", "");
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
                                controller.operation(4, inventoryNumberLine.getText(), bookIdLine.getText(), "false", "нет", "", "", "");
                            } else {
                                if (readerLine.getText().equals("нет")) {
                                    controller.operation(2, "", "", "", "", "", "", "");
                                } else {
                                    controller.operation(4, inventoryNumberLine.getText(), bookIdLine.getText(), "true", readerLine.getText(), "", "", "");
                                }
                            }
                            addFormCopy.setVisible(false);
                        }
                        else {
                            if (!bookId.matches()){
                                bookIdLine.setBackground(Color.red);
                            }
                            else{
                                bookIdLine.setBackground(Color.white);
                            }
                            if (!inventoryNumber.matches()){
                                inventoryNumberLine.setBackground(Color.red);
                            }
                            else{
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
                            controller.operation(6, (String) copyBookTableModel.getValueAt(copyOfTheBookTable.getSelectedRow(), 0), (String) copyBookTableModel.getValueAt(copyOfTheBookTable.getSelectedRow(), 1), "false", "нет", "", "", "");
                        } else {
                            if (readerLine.getText().equals("нет")) {
                                controller.operation(2, "", "", "", "", "", "", "");
                            } else {
                                controller.operation(6, (String) copyBookTableModel.getValueAt(copyOfTheBookTable.getSelectedRow(), 0), (String) copyBookTableModel.getValueAt(copyOfTheBookTable.getSelectedRow(), 1), "true", readerLine.getText(), "", "", "");
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

        JPanel publisherFunctionButtons = new JPanel();
        publisherFunctionButtons.setLayout(new FlowLayout());

        deletePublisher = new JButton("Delete");
        deletePublisher.setEnabled(false);
        deletePublisher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.operation(22, (String) publisherTableModel.getValueAt(publisherTable.getSelectedRow(), 0), "", "", "", "", "", "");
            }
        });

        addPublisherButton = new JButton("Add");
        addPublisherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JFrame addFormPublisher = new JFrame("ADD PUBLISHER");
                addFormPublisher.setBounds(400, 300, 400, 150);
                JPanel addPanel = new JPanel();
                addPanel.setLayout(new BorderLayout());
                final JTextField idPublisherLine = new JTextField(7);
                final JTextField nameLine = new JTextField(7);
                final JTextField registeredAddressLine = new JTextField(7);
                final JTextField businessAddressLine = new JTextField(7);
                JPanel labelPanel = new JPanel();
                JPanel textPanel = new JPanel(new FlowLayout());
                addPanel.add(labelPanel, BorderLayout.CENTER);
                addPanel.add(textPanel, BorderLayout.NORTH);
                labelPanel.setLayout(new FlowLayout());
                labelPanel.add(idPublisherLine);
                labelPanel.add(nameLine);
                labelPanel.add(registeredAddressLine);
                labelPanel.add(businessAddressLine);
                JPanel buttonPanel = new JPanel();
                JLabel idPublisherLabel = new JLabel("Publisher ID");
                idPublisherLabel.setPreferredSize(new Dimension(60,10));
                idPublisherLabel.setHorizontalAlignment(JLabel.CENTER);
                JLabel nameLabel = new JLabel("Name");
                nameLabel.setPreferredSize(new Dimension(80, 10));
                nameLabel.setHorizontalAlignment(JLabel.CENTER);
                JLabel registeredAddressLabel = new JLabel("Registered address");
                registeredAddressLabel.setPreferredSize(new Dimension(70, 10));
                registeredAddressLabel.setHorizontalAlignment(JLabel.CENTER);
                JLabel businessAddressLabel = new JLabel("Business address");
                businessAddressLabel.setPreferredSize(new Dimension(70, 10));
                businessAddressLabel.setHorizontalAlignment(JLabel.CENTER);
                textPanel.add(idPublisherLabel);
                textPanel.add(nameLabel);
                textPanel.add(registeredAddressLabel);
                textPanel.add(businessAddressLabel);
                JButton ok = new JButton();
                ok.setText("OK");
                ok.setSize(10, 5);
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Pattern p = Pattern.compile("^[0-9]{1,15}$");
                        Matcher idPublisher = p.matcher(idPublisherLine.getText());
                        if (idPublisher.matches()) {
                            controller.operation(20, idPublisherLine.getText(), nameLine.getText(), registeredAddressLine.getText(), businessAddressLine.getText(), "", "", "");
                            addFormPublisher.setVisible(false);
                        }
                        else {
                            if (!idPublisher.matches()){
                                idPublisherLine.setBackground(Color.red);
                            }
                            else{
                                idPublisherLine.setBackground(Color.white);
                            }
                        }
                    }
                });
                buttonPanel.add(ok);
                addPanel.add(buttonPanel, BorderLayout.SOUTH);
                addFormPublisher.add(addPanel);
                addFormPublisher.setVisible(true);
            }
        });

        changePublisherButton = new JButton("Change");
        changePublisherButton.setEnabled(false);
        changePublisherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JFrame addFormPublisher = new JFrame("CHANGE PUBLISHER");
                addFormPublisher.setBounds(400, 300, 400, 150);
                JPanel addPanel = new JPanel();
                addPanel.setLayout(new BorderLayout());
                final JTextField nameLine = new JTextField(9);
                final JTextField registeredAddressLine = new JTextField(9);
                final JTextField businessAddressLine = new JTextField(9);
                JPanel labelPanel = new JPanel();
                JPanel textPanel = new JPanel(new FlowLayout());
                addPanel.add(labelPanel, BorderLayout.CENTER);
                addPanel.add(textPanel, BorderLayout.NORTH);
                labelPanel.setLayout(new FlowLayout());
                labelPanel.add(nameLine);
                labelPanel.add(registeredAddressLine);
                labelPanel.add(businessAddressLine);
                JPanel buttonPanel = new JPanel();
                JLabel nameLabel = new JLabel("Name");
                nameLabel.setPreferredSize(new Dimension(105, 20));
                nameLabel.setHorizontalAlignment(JLabel.CENTER);
                JLabel registeredAddressLabel = new JLabel("Registered address");
                registeredAddressLabel.setPreferredSize(new Dimension(105, 20));
                registeredAddressLabel.setHorizontalAlignment(JLabel.CENTER);
                JLabel businessAddressLabel = new JLabel("Business address");
                businessAddressLabel.setPreferredSize(new Dimension(105, 20));
                businessAddressLabel.setHorizontalAlignment(JLabel.CENTER);
                textPanel.add(nameLabel);
                textPanel.add(registeredAddressLabel);
                textPanel.add(businessAddressLabel);
                JButton ok = new JButton();
                ok.setText("OK");
                ok.setSize(10, 5);
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.operation(21, (String) publisherTableModel.getValueAt(publisherTable.getSelectedRow(), 0), nameLine.getText(), registeredAddressLine.getText(), businessAddressLine.getText(), "", "", "");
                        addFormPublisher.setVisible(false);
                    }
                });
                buttonPanel.add(ok);
                addPanel.add(buttonPanel, BorderLayout.SOUTH);
                addFormPublisher.add(addPanel);
                addFormPublisher.setVisible(true);
            }
        });

        publisherFunctionButtons.add(addPublisherButton);
        publisherFunctionButtons.add(changePublisherButton);
        publisherFunctionButtons.add(deletePublisher);

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
        publisherTab.add(publisherFunctionButtons, BorderLayout.SOUTH);

        addSearchPanelBook(bookTab);
        addSearchPanelCopyOfBook(copyTab);
        addSearchPanelPublisher(publisherTab);

        saveTab.add(saveLine, BorderLayout.NORTH);
        JTabbedPane jtp = new JTabbedPane();
        bookTab.add(scrollPaneBook, BorderLayout.CENTER);
        copyTab.add(scrollPaneCopy, BorderLayout.CENTER);
        saveTab.add(scrollPaneSaveAndLoad, BorderLayout.CENTER);
        publisherTab.add(scrollPanePublisher, BorderLayout.CENTER);

        jtp.addTab("Book", bookTab);
        jtp.addTab("CopyOfBook", copyTab);
        jtp.addTab("Save and Load", saveTab);
        jtp.addTab("Publisher", publisherTab);

        bookTableModel.addColumn("ID");
        bookTableModel.addColumn("Name");
        bookTableModel.addColumn("Authors");
        bookTableModel.addColumn("Year");
        bookTableModel.addColumn("Pages");
        bookTableModel.addColumn("Catalog");
        bookTableModel.addColumn("Publisher");

        copyBookTableModel.addColumn("Inventory Number");
        copyBookTableModel.addColumn("Book ID");
        copyBookTableModel.addColumn("Issue");
        copyBookTableModel.addColumn("Reader");

        saveAndLoadTableModel.addColumn("File Name");
        saveAndLoadTableModel.addColumn("Date change");

        publisherTableModel.addColumn("ID");
        publisherTableModel.addColumn("Name");
        publisherTableModel.addColumn("Registered Address");
        publisherTableModel.addColumn("Business Address");

        File folderLoad = new File("D:\\Other\\lab3");
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

        /*JPanel catalogFunctionButtons = new JPanel();
        catalogFunctionButtons.setLayout(new FlowLayout());

        deleteCatalog = new JButton("Delete");
        deleteCatalog.setEnabled(false);
        deleteCatalog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.operation(25, "", "", "", "", "", "", ""); //TODO второй параметр - id удаляемого каталога
            }
        });

        catalogFunctionButtons.add(deleteCatalog);*/

        Book book1 = new Book(1, "И. С. Тургенев", "Отцы и дети", 1971, 188, catalog1.getName(), publisher1.getName());
        Book book2 = new Book(2, "Н. В. Гоголь", "Мертвые души", 1972, 416, catalog2.getName(), publisher2.getName());
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

        String ROOT = "Общий";
        String[] nodes = new String[]{catalog1.getName(), catalog2.getName()};
        final   String[][] leafs = new String[][]{{book1.getAuthors() + " '" + book1.getName() + "'"},
                {book2.getAuthors()+" '"+book2.getName()+"'"}};
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(ROOT);
        DefaultMutableTreeNode list1 = new DefaultMutableTreeNode(nodes[0]);
        DefaultMutableTreeNode list2 = new DefaultMutableTreeNode(nodes[1]);
        root.add(list1);
        root.add(list2);
        for ( int i = 0; i < leafs[0].length; i++)
            list1.add(new DefaultMutableTreeNode(leafs[0][i], false));
        for ( int i = 0; i < leafs[1].length; i++)
            list2.add(new DefaultMutableTreeNode(leafs[1][i], false));
        DefaultTreeModel treeModel1 = new DefaultTreeModel(root, true);
        JTree tree = new JTree(treeModel1);
        JPanel catalogTab = new JPanel(new GridLayout(1, 2));
        catalogTab.add(new JScrollPane(tree), BorderLayout.CENTER);

        //catalogTab.add(catalogFunctionButtons, BorderLayout.SOUTH);

        jtp.addTab("Catalog", catalogTab);
        add(jtp);

        Storage storage = Storage.getInstance();
        storage.setBookList(bookList);
        storage.setCopyOfTheBookList(copyOfTheBookList);
        storage.setPublisherList(publisherList);
        storage.setCatalogList(catalogList);
        fillTableBook(bookList);
        fillTableCopyOfTheBook(copyOfTheBookList);
        fillTablePublisher(publisherList);
        /* TODO здесь должен быть метод для вывода каталогов */
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

    private void addSearchPanelPublisher(JPanel publisherTab){
        JPanel searchPanelPublisher = new JPanel();
        publisherTab.add(searchPanelPublisher, BorderLayout.NORTH);
        searchPanelPublisher.setLayout(new FlowLayout());
        JButton searchPublisherButton = new JButton("Search");
        searchPanelPublisher.add(searchPublisherButton);
        searchPanelPublisher.add(searchPublisher);
        searchPanelPublisher.add(searchParameterPublisher);
        searchPublisherButton.addActionListener(actionListenerPublisher);
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
                tmpB.setCatalog((String) (bookTableModel.getValueAt(i, 5)));
                tmpB.setPublisher((String) (bookTableModel.getValueAt(i, 6)));
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
                    if (tmpB.getYear() == tmpNewB.getYear()) {
                        if (tmpB.getPages() == tmpNewB.getPages()) {
                            if (tmpB.getAuthors().equals(tmpNewB.getAuthors())) {
                                if (tmpB.getCatalog().equals(tmpNewB.getCatalog())) {
                                    if (tmpB.getPublisher().equals(tmpNewB.getPublisher())) {
                                        if (tmpB.getName().equals(tmpNewB.getName())) {
                                            check = false;
                                        }
                                    }
                                }
                            }
                        }
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
                    if (tmpC.getReader().equals(tmpNewC.getReader())) {
                        if (tmpC.getInventoryNumber() == tmpNewC.getInventoryNumber() && tmpC.getIssue() == tmpNewC.getIssue()) {
                            check = false;
                        }
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
                tmpB.setCatalog((String) bookTableModel.getValueAt(i, 5));
                tmpB.setPublisher((String) bookTableModel.getValueAt(i, 6));
                tmpBook.put(tmpB.getIdBook(), tmpB);
            }
            tmp.setBookList(tmpBook);

            HashMap<Long, CopyOfTheBook> tmpCopy = new HashMap<>();
            for (int i = 0; i < copyBookTableModel.getRowCount(); i++) {
                CopyOfTheBook tmpC = new CopyOfTheBook();
                tmpC.setIdBook(Long.valueOf((String) copyBookTableModel.getValueAt(i, 0)));
                tmpC.setInventoryNumber(Long.valueOf((String) copyBookTableModel.getValueAt(i, 1)));
                tmpC.setIssue(Boolean.valueOf((String) copyBookTableModel.getValueAt(i, 2)));
                tmpC.setReader((String) copyBookTableModel.getValueAt(i, 3));
                tmpCopy.put(tmpC.getIdBook(), tmpC);
            }
            tmp.setCopyOfTheBookList(tmpCopy);

            HashMap<Long, Publisher> tmpPublisher = new HashMap<>();
            for (int i = 0; i < publisherTableModel.getRowCount(); i++) {
                Publisher tmpP = new Publisher();
                tmpP.setIdPublisher(Long.valueOf((String) publisherTableModel.getValueAt(i, 0)));
                tmpP.setName((String) publisherTableModel.getValueAt(i, 1));
                tmpP.setRegisteredAddress((String) publisherTableModel.getValueAt(i, 2));
                tmpP.setBusinessAddress((String) publisherTableModel.getValueAt(i, 3));
                tmpPublisher.put(tmpP.getIdPublisher(), tmpP);
            }
            tmp.setPublisherList(tmpPublisher);

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
        File folderLoad = new File("D:\\Other\\lab3");
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
            while (publisherTableModel.getRowCount() > 0) {
                publisherTableModel.removeRow(0);
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
                    newRow.add(tmpB.getCatalog());
                    newRow.add(tmpB.getPublisher());
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

                HashMap<Long, Publisher> tmpPublisher = new HashMap<>();
                tmpPublisher = tmp.getPublisherList();
                for (Publisher tmpP : tmpPublisher.values()) {
                    Vector<String> newRow = new Vector<String>();
                    newRow.add(Long.toString(tmpP.getIdPublisher()));
                    newRow.add(tmpP.getName());
                    newRow.add(tmpP.getRegisteredAddress());
                    newRow.add(tmpP.getBusinessAddress());
                    publisherTableModel.addRow(newRow);
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

    public void clearTablePublisher() {
        if (publisherTableModel.getRowCount() > 0) {
            for (int i = publisherTableModel.getRowCount() - 1; i > -1; i--) {
                publisherTableModel.removeRow(i);
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
            newRow.add(entry.getValue().getCatalog());
            newRow.add(entry.getValue().getPublisher());
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

    public void fillTablePublisher(HashMap<Long, Publisher> result) {
        clearTablePublisher();
        for (Map.Entry<Long, Publisher> entry : result.entrySet()) {
            Vector<String> newRow = new Vector<String>();
            newRow.add(Long.toString(entry.getKey()));
            newRow.add(entry.getValue().getName());
            newRow.add(entry.getValue().getRegisteredAddress());
            newRow.add(entry.getValue().getBusinessAddress());
            publisherTableModel.addRow(newRow);
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
        if (e.getSource() == publisherTable.getSelectionModel() && e.getFirstIndex() >= 0) {
            if (publisherTable.getSelectedRowCount() != 0) {
                changePublisherButton.setEnabled(true);
                deletePublisher.setEnabled(true);
            } else {
                changePublisherButton.setEnabled(false);
                deletePublisher.setEnabled(false);
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
                    case "Catalog":
                        act = 18;
                        break;
                    case "Publisher":
                        act = 19;
                        break;
                }
            }
            controller.operation(act, date, "", "", "", "", "", "");
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
            controller.operation(act, date, "", "", "", "", "", "");
        }
    }

    public class TestActionListenerPublisher implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String date = searchPublisher.getText();
            int act = 0;
            String action = searchParameterPublisher.getSelectedItem().toString();
            if (date.equals("")) {
                act = 2;
            } else {
                switch (action) {
                    case "ID":
                        act = 26;
                        break;
                    case "Name":
                        act = 27;
                        break;
                    case "Registered address":
                        act = 28;
                        break;
                    case "Business address":
                        act = 29;
                        break;
                }
            }
            controller.operation(act, date, "", "", "", "", "", "");
        }
    }

    public static void main(String[] ar) {
        WindowView w = new WindowView();
        w.setBounds(400, 250, 700, 300);
        w.setVisible(true);
    }
}



