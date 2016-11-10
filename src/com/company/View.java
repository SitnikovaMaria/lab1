package com.company;

import java.util.ArrayList;

public interface View{
    public void viewBook(Book book);
    public void viewCopyOfTheBook(CopyOfTheBook book);
    public void viewList(ArrayList<Book> list);
    public void viewCopyOfTheBook(ArrayList<CopyOfTheBook> book);
}

