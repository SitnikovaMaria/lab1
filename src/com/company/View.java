package com.company;

import java.util.HashMap;

public interface View{
    public void viewBook(Book book);
    public void viewCopyOfTheBook(CopyOfTheBook book);
    public void fillTableBook(HashMap<Long, Book> result);
    public void fillTableCopyOfTheBook(HashMap<Long, CopyOfTheBook> result);
}