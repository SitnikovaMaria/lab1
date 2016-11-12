/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windowview;

import java.awt.BorderLayout;
import java.awt.FlowLayout; 
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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




 
public class WindowView extends JFrame implements View{
    private DefaultTableModel b = new DefaultTableModel();
    private DefaultTableModel c = new DefaultTableModel();

    /**
     * Конструктор - создание нового объекта
     */
    public WindowView() {          
          
          setTitle("Library");

          
          JPanel panel = new JPanel();
          add(panel); 
          panel.setLayout(new BorderLayout());
          JTable book = new JTable(b);
          JTable copyOfBook = new JTable(c); 
          JScrollPane scrollPaneB = new JScrollPane(book);
          JScrollPane scrollPaneC = new JScrollPane(copyOfBook);
          JPanel panel2 = new JPanel();
          panel.add(panel2,BorderLayout.SOUTH);
          panel2.setLayout(new FlowLayout());
          JButton button1 = new JButton("Add");          
          JButton button2 = new JButton("Change");  
          panel2.add(button1);
          panel2.add(button2);
          JTabbedPane jtp = new JTabbedPane();
         
          
          jtp.addTab("Book",scrollPaneB );
          jtp.addTab("CopyOfBook", scrollPaneC);
          panel.add(jtp,BorderLayout.CENTER);       
          
            
          b.addColumn("Name");
          b.addColumn("Authors");
          b.addColumn("Year");
          b.addColumn("Pages");          
            
          c.addColumn("Inventory Number");
          c.addColumn("Book ID");
          c.addColumn("Issue");
            
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
            newRow.add(book.getName());
            newRow.add(book.getAuthors());
            newRow.add(Integer.toString(book.getYear()));
            newRow.add(Integer.toString(book.getPages()));
            b.addRow(newRow);
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
            newRow.add(Long.toString(book.getInventoryNumber()));
            newRow.add(Long.toString(book.getIdBook()));
            newRow.add(Boolean.toString(book.getIssue()));
            b.addRow(newRow);
        }
    }

    public static void main(String[] args) {
      WindowView w = new WindowView();
      w.setVisible(true);

    }
     
}



