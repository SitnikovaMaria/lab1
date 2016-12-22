package com.view;

import com.model.Book;
import com.model.CopyOfTheBook;

import java.util.HashMap;

public interface View{
    public void fillTableBook(HashMap<Long, Book> result);
    public void fillTableCopyOfTheBook(HashMap<Long, CopyOfTheBook> result);
}
