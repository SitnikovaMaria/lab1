/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.awt.BorderLayout;
import java.awt.FlowLayout; 
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.JTextField;




 
public class WindowView extends JFrame implements View{
    private DefaultTableModel b = new DefaultTableModel();
    private DefaultTableModel c = new DefaultTableModel();
    private DefaultTableModel s = new DefaultTableModel();

    public void print(String s) {
        System.out.println(s);
    }
    public void read() {}
    public String input() {
        return "1";
    }
    private JTextField saveLine = new JTextField();

    /**
     * Конструктор - создание нового объекта
     */
    public WindowView() {          
          
          setTitle("Library");
          
          JPanel bookTab = new JPanel();
          bookTab.setLayout(new BorderLayout());
          JPanel copyTab = new JPanel();
          copyTab.setLayout(new BorderLayout());
          JPanel saveTab = new JPanel();
          saveTab.setLayout(new BorderLayout());
          JTable book = new JTable(b);
          JTable copyOfBook = new JTable(c);
          JTable saveAndLoad = new JTable(s);
          JScrollPane scrollPaneS = new JScrollPane(saveAndLoad);
          JScrollPane scrollPaneB = new JScrollPane(book);
          JScrollPane scrollPaneC = new JScrollPane(copyOfBook);
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
          saveLine = new JTextField();

          bookTab.add(bookFunctionButtons,BorderLayout.SOUTH);
          copyTab.add(copyFunctionButtons,BorderLayout.SOUTH);
          saveTab.add(saveAndLoadButtons,BorderLayout.SOUTH);
          saveTab.add(saveLine,BorderLayout.NORTH);
          JTabbedPane jtp = new JTabbedPane();
          bookTab.add(scrollPaneB,BorderLayout.CENTER);
          copyTab.add(scrollPaneC,BorderLayout.CENTER);
          saveTab.add(scrollPaneS,BorderLayout.CENTER);
          jtp.addTab("Book", bookTab);
          jtp.addTab("CopyOfBook", copyTab);
          jtp.addTab("Save and Load", saveTab);
          
            
          b.addColumn("Name");
          b.addColumn("Authors");
          b.addColumn("Year");
          b.addColumn("Pages");          
            
          c.addColumn("Inventory Number");
          c.addColumn("Book ID");
          c.addColumn("Issue");

          s.addColumn("File Name");

          add(jtp);

    }

    /**
     * Процедура добавления в таблицу одной книги
     * @param book - книга
     */
    public void viewBook(Book book){
        Vector<String> newRow = new Vector<String>();
        newRow.add(book.getName());
        newRow.add(book.getAuthors());
        newRow.add(Integer.toString(book.getYear()));
        newRow.add(Integer.toString(book.getPages()));
        b.addRow(newRow);
    }

    /**
     * Процедура добавления в таблицу одного экземпляра книги
     * @param book - экземпляр книги
     */
    public void viewCopyOfTheBook(CopyOfTheBook book){
        Vector<String> newRow = new Vector<String>();
        newRow.add(Long.toString(book.getInventoryNumber()));
        newRow.add(Long.toString(book.getIdBook()));
        newRow.add(Boolean.toString(book.getIssue()));
        b.addRow(newRow);
    }

    /**
     * Процедура добавления в таблицу нескольких книг
     * @param list - массив книг
     */
    public void viewList(ArrayList<Book> list){
        Vector<String> newRow = new Vector<String>();
        for (Book tmp:list){
            newRow.clear();
            newRow.add(tmp.getName());
            newRow.add(tmp.getAuthors());
            newRow.add(Integer.toString(tmp.getYear()));
            newRow.add(Integer.toString(tmp.getPages()));
            c.addRow(newRow);
        }
    }

    /**
     * Процедура добавления в таблицу нескольких экземпляров книги
     * @param book - экземпляры книги
     */
    public void viewCopyOfTheBook(ArrayList<CopyOfTheBook> book){
        Vector<String> newRow = new Vector<String>();
        for (CopyOfTheBook tmp:book){
            newRow.clear();
            newRow.add(Long.toString(tmp.getInventoryNumber()));
            newRow.add(Long.toString(tmp.getIdBook()));
            newRow.add(Boolean.toString(tmp.getIssue()));
            c.addRow(newRow);
        }
    }

    public void saveToFile() {
        if (saveLine.getText() != "") {
            Serialization a = new Serialization();
            try {
                Book tmp = new Book();
                for (int i = 0; i < b.getRowCount(); i++) {
                    tmp.setName((String) b.getValueAt(i, 1));
                    tmp.setAuthors((String) b.getValueAt(i, 2));
                    tmp.setYear((Integer) b.getValueAt(i, 3));
                    tmp.setPages((Integer) b.getValueAt(i, 4));
                    a.saveObjectBook(tmp,saveLine.getText()+"Book.txt");
                }


                CopyOfTheBook temp= new CopyOfTheBook();
                for (int i = 0; i < c.getRowCount(); i++) {
                    temp.setIdBook((Long) c.getValueAt(i, 1));
                    temp.setInventoryNumber((Long) c.getValueAt(i, 2));
                    temp.setIssue((Boolean) b.getValueAt(i, 3));
                    a.saveObjectCopy(temp,saveLine.getText()+"CopyOfTheBook.txt");
                }



            }
            catch (IOException ex) {
            }
        }
    }

    public static void main(String[] args) {
        WindowView w = new WindowView();
        w.setBounds(400, 400, 400, 200);
        w.setVisible(true);
    }
     
}



