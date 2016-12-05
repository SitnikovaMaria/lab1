package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {
    private Storage storage;
    private WindowView view;
    private Sort sort;

    public Search(Storage storage, WindowView view, Sort sort){
        this.storage = storage;
        this.view = view;
        this.sort = sort;
    }

    public void searchByIdBook(String date) { //поиск книги по идентификатору
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            date.substring(0);
            p = Pattern.compile(date);
        }
        else {
            p = Pattern.compile(date);
        }
        Matcher m;
        String text;
        for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            text = String.valueOf(entry.getValue().getIdBook());
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        view.fillTableBook(result);
    }

    public void searchByName(String date) { //поиск книг по названию
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            date.substring(0);
            p = Pattern.compile(date);
        }
        else {
            p = Pattern.compile(date);
        }
        Matcher m;
        String text;
        for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            text = entry.getValue().getName();
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Long, Book> sortedHashMap = sort.sortByName(result);
        view.fillTableBook(sortedHashMap);
    }

    public void searchByAuthors(String date) { //поиск книг по авторам
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            date.substring(0);
            p = Pattern.compile(date);
        }
        else {
            p = Pattern.compile(date);
        }
        Matcher m;
        String text;
        for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            text = entry.getValue().getAuthors();
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Long, Book> sortedHashMap = sort.sortByAuthors(result);
        view.fillTableBook(sortedHashMap);
    }

    public void searchByYear(String date) { //поиск книг по году
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            date.substring(0);
            p = Pattern.compile(date);
        }
        else {
            p = Pattern.compile(date);
        }
        Matcher m;
        String text;
        for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            text = String.valueOf(entry.getValue().getYear());
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Long, Book> sortedHashMap = sort.sortByYear(result);
        view.fillTableBook(sortedHashMap);
    }

    public void searchByPages(String date) { //поиск книг по страницам
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            date.substring(0);
            p = Pattern.compile(date);
        }
        else {
            p = Pattern.compile(date);
        }
        Matcher m;
        String text;
        for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            text = String.valueOf(entry.getValue().getPages());
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Long, Book> sortedHashMap = sort.sortByPages(result);
        view.fillTableBook(sortedHashMap);
    }

    public void searchByInventoryNumber(String date) { //поиск экземпляра книги по инвентарному номеру
        HashMap<Long, CopyOfTheBook> result = new HashMap<Long, CopyOfTheBook>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            date.substring(0);
            p = Pattern.compile(date);
        }
        else {
            p = Pattern.compile(date);
        }
        Matcher m;
        String text;
        for (Map.Entry<Long, CopyOfTheBook> entry : storage.getCopyOfTheBookList().entrySet()) {
            text = String.valueOf(entry.getValue().getInventoryNumber());
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        view.fillTableCopyOfTheBook(result);
    }

    public void searchByBook(String date) { //поиск экземпляров книги по книге
        HashMap<Long, CopyOfTheBook> result = new HashMap<Long, CopyOfTheBook>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            date.substring(0);
            p = Pattern.compile(date);
        }
        else {
            p = Pattern.compile(date);
        }
        Matcher m;
        String text;
        for (Map.Entry<Long, CopyOfTheBook> entry : storage.getCopyOfTheBookList().entrySet()) {
            text = String.valueOf(entry.getValue().getIdBook());
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Long, CopyOfTheBook> sortedHashMap = sort.sortByIdBook(result);
        view.fillTableCopyOfTheBook(sortedHashMap);
    }

    public void searchByIssue(String date) { //поиск экземпляров книг по информации о выдаче
        HashMap<Long, CopyOfTheBook> result = new HashMap<Long, CopyOfTheBook>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            date.substring(0);
            p = Pattern.compile(date);
        }
        else {
            p = Pattern.compile(date);
        }
        Matcher m;
        String text;
        for (Map.Entry<Long, CopyOfTheBook> entry : storage.getCopyOfTheBookList().entrySet()) {
            text = String.valueOf(entry.getValue().getIssue());
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Long, CopyOfTheBook> sortedHashMap = sort.sortByIssue(result);
        view.fillTableCopyOfTheBook(sortedHashMap);
    }

    public void searchByReader(String date) { //поиск экземпляров книг по читателю
        HashMap<Long, CopyOfTheBook> result = new HashMap<Long, CopyOfTheBook>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            date.substring(0);
            p = Pattern.compile(date);
        }
        else {
            p = Pattern.compile(date);
        }
        Matcher m;
        String text;
        for (Map.Entry<Long, CopyOfTheBook> entry : storage.getCopyOfTheBookList().entrySet()) {
            text = entry.getValue().getReader();
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Long, CopyOfTheBook> sortedHashMap = sort.sortByReader(result);
        view.fillTableCopyOfTheBook(sortedHashMap);
    }
}
