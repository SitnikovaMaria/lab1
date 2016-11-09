package com.company;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в библиотеку!");
        Book book1 = new Book(1, "И. С. Тургенев", "Отцы и дети", 1971, 188);
        Book book2 = new Book(2, "Н. В. Гоголь", "Мертвые души", 1972, 416);
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
        Control controller = new Control(storage);
    }
}
