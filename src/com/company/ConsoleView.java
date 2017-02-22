package com.company;

import java.util.ArrayList;

public class ConsoleView /*implements View*/ {

    public void viewBook(Book book){
        System.out.print(book.getName()/*.name*/);
        System.out.print(" ");
        System.out.print(book.getAuthors()/*authors*/);
        System.out.print(" ");
        System.out.print(book.getYear()/*year*/);
        System.out.print(" ");
        System.out.print(book.getPages()/*pages*/);
        System.out.println();
    }

    public void viewCopyOfTheBook(com.company.CopyOfTheBook book){
        System.out.print(book.getInventoryNumber()/*inventoryNumber*/);
        System.out.print(" ");
        System.out.print(book.getIdBook()/*idBook*/);
        System.out.print(" ");
        System.out.print(book.getIssue()/*issue*/);
        System.out.println();
    }

    public void viewList(ArrayList<Book> list){
        for (Book tmp:list){
            System.out.print(tmp.getName()/*.name*/);
            System.out.print(" ");
            System.out.print(tmp.getAuthors()/*authors*/);
            System.out.print(" ");
            System.out.print(tmp.getYear()/*year*/);
            System.out.print(" ");
            System.out.print(tmp.getPages()/*pages*/);
            System.out.println();
        }
    }

    public void viewCopyOfTheBook(ArrayList<CopyOfTheBook> book){
        for (CopyOfTheBook tmp:book){
            System.out.print(tmp.getInventoryNumber()/*inventoryNumber*/);
            System.out.print(" ");
            System.out.print(tmp.getIdBook()/*idBook*/);
            System.out.print(" ");
            System.out.print(tmp.getIssue()/*issue*/);
            System.out.println();
        }
    }
}
