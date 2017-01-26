package client.controller;

import client.Storage;
import client.model.*;
import client.view.WindowView;

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

    public void searchByIdBook(String date) { //����� ����� �� ��������������
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            p = Pattern.compile(date.substring(1));
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

    public void searchByName(String date) { //����� ���� �� ��������
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            p = Pattern.compile(date.substring(1));
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

    public void searchByAuthors(String date) { //����� ���� �� �������
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            p = Pattern.compile(date.substring(1));
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

    public void searchByYear(String date) { //����� ���� �� ����
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            p = Pattern.compile(date.substring(1));
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

    public void searchByPages(String date) { //����� ���� �� ���������
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            p = Pattern.compile(date.substring(1));
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

    public void searchByInventoryNumber(String date) { //����� ���������� ����� �� ������������ ������
        HashMap<Long, CopyOfTheBook> result = new HashMap<Long, CopyOfTheBook>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            p = Pattern.compile(date.substring(1));
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

    public void searchByBook(String date) { //����� ����������� ����� �� �����
        HashMap<Long, CopyOfTheBook> result = new HashMap<Long, CopyOfTheBook>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            p = Pattern.compile(date.substring(1));
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

    public void searchByIssue(String date) { //����� ����������� ���� �� ���������� � ������
        HashMap<Long, CopyOfTheBook> result = new HashMap<Long, CopyOfTheBook>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            p = Pattern.compile(date.substring(1));
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

    public void searchByReader(String date) { //����� ����������� ���� �� ��������
        HashMap<Long, CopyOfTheBook> result = new HashMap<Long, CopyOfTheBook>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            p = Pattern.compile(date.substring(1));
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

    public void searchByCatalog(String date) { //����� ����������� ���� �� ��������
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            p = Pattern.compile(date.substring(1));
        }
        else {
            p = Pattern.compile(date);
        }
        Matcher m;
        String text;
        for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            text = entry.getValue().getCatalog();
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Long, Book> sortedHashMap = sort.sortByCatalog(result);
        view.fillTableBook(sortedHashMap);
    }

    public void searchByPublisher(String date) { //����� ����������� ���� �� ������������
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            p = Pattern.compile(date.substring(1));
        }
        else {
            p = Pattern.compile(date);
        }
        Matcher m;
        String text;
        for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            text = entry.getValue().getPublisher();
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Long, Book> sortedHashMap = sort.sortByPublisher(result);
        view.fillTableBook(sortedHashMap);
    }

    public void searchPublisherById(String date){ //����� ������������ �� ID
        HashMap<Long, Publisher> result = new HashMap<Long, Publisher>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            p = Pattern.compile(date.substring(1));
        }
        else {
            p = Pattern.compile(date);
        }
        Matcher m;
        String text;
        for (Map.Entry<Long, Publisher> entry : storage.getPublisherList().entrySet()) {
            text = String.valueOf(entry.getValue().getIdPublisher());
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        view.fillTablePublisher(result);
    }

    public void searchPublisherByName(String date){ //����� ������������ �� Name
        HashMap<Long, Publisher> result = new HashMap<Long, Publisher>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            p = Pattern.compile(date.substring(1));
        }
        else {
            p = Pattern.compile(date);
        }
        Matcher m;
        String text;
        for (Map.Entry<Long, Publisher> entry : storage.getPublisherList().entrySet()) {
            text = entry.getValue().getName();
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Long, Publisher> sortedHashMap = sort.sortPublisherByName(result);
        view.fillTablePublisher(sortedHashMap);
    }

    public void searchByRegisteredAddress(String date){ //����� ������������ �� Registered address
        HashMap<Long, Publisher> result = new HashMap<Long, Publisher>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            p = Pattern.compile(date.substring(1));
        }
        else {
            p = Pattern.compile(date);
        }
        Matcher m;
        String text;
        for (Map.Entry<Long, Publisher> entry : storage.getPublisherList().entrySet()) {
            text = entry.getValue().getRegisteredAddress();
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Long, Publisher> sortedHashMap = sort.sortByRegisteredAddress(result);
        view.fillTablePublisher(sortedHashMap);
    }

    public void searchByBusinessAddress(String date){ //����� ������������ �� Business address
        HashMap<Long, Publisher> result = new HashMap<Long, Publisher>();
        Pattern p;
        if ((date.charAt(0) == '*') || (date.charAt(0) == '?')){
            p = Pattern.compile(date.substring(1));
        }
        else {
            p = Pattern.compile(date);
        }
        Matcher m;
        String text;
        for (Map.Entry<Long, Publisher> entry : storage.getPublisherList().entrySet()) {
            text = entry.getValue().getBusinessAddress();
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Long, Publisher> sortedHashMap = sort.sortByBusinessAddress(result);
        view.fillTablePublisher(sortedHashMap);
    }
}
