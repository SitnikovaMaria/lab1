package com.controller;

import com.model.Book;
import com.model.CopyOfTheBook;
import com.model.Publisher;

import java.util.*;

public class Sort {

    public Sort(){}

    public HashMap<Long, Book> sortByName(HashMap<Long, Book> map){ //сортировка по имени
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry<Long, Book>) (o1)).getValue().getName()).compareTo(((Map.Entry<Long, Book>) (o2)).getValue().getName());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public HashMap<Long, Book> sortByAuthors(HashMap<Long, Book> map) { //сортировка по авторам
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry<Long, Book>) (o1)).getValue().getAuthors()).compareTo(((Map.Entry<Long, Book>) (o2)).getValue().getAuthors());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public HashMap<Long, Book> sortByYear(HashMap<Long, Book> map) { //сортировка по году
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry<Long, Book>) (o1)).getValue().getYear()).compareTo(((Map.Entry<Long, Book>) (o2)).getValue().getYear());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public HashMap<Long, Book> sortByPages(HashMap<Long, Book> map) { //сортировка по страницам
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry<Long, Book>) (o1)).getValue().getPages()).compareTo(((Map.Entry<Long, Book>) (o2)).getValue().getPages());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public HashMap<Long, CopyOfTheBook> sortByIdBook(HashMap<Long, CopyOfTheBook> map) { //сортировка экземпляров книг по книге
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry<Long, CopyOfTheBook>) (o1)).getValue().getIdBook()).compareTo(((Map.Entry<Long, CopyOfTheBook>) (o2)).getValue().getIdBook());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public HashMap<Long, CopyOfTheBook> sortByIssue(HashMap<Long, CopyOfTheBook> map) { //сортировка по информации о выдаче
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry<Long, CopyOfTheBook>) (o1)).getValue().getIssue()).compareTo(((Map.Entry<Long, CopyOfTheBook>) (o2)).getValue().getIssue());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public HashMap<Long, CopyOfTheBook> sortByReader(HashMap<Long, CopyOfTheBook> map) { //сортировка по читателю
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry<Long, CopyOfTheBook>) (o1)).getValue().getReader()).compareTo(((Map.Entry<Long, CopyOfTheBook>) (o2)).getValue().getReader());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public HashMap<Long, Book> sortByCatalog(HashMap<Long, Book> map) { //сортировка по каталогу
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry<Long, Book>) (o1)).getValue().getCatalog()).compareTo(((Map.Entry<Long, Book>) (o2)).getValue().getCatalog());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public HashMap<Long, Book> sortByPublisher(HashMap<Long, Book> map) { //сортировка по издательству
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry<Long, Book>) (o1)).getValue().getPublisher()).compareTo(((Map.Entry<Long, Book>) (o2)).getValue().getPublisher());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public HashMap<Long, Publisher> sortPublisherByName(HashMap<Long, Publisher> map) { //сортировка издательств по Name
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry<Long, Publisher>) (o1)).getValue().getName()).compareTo(((Map.Entry<Long, Publisher>) (o2)).getValue().getName());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public HashMap<Long, Publisher> sortByRegisteredAddress(HashMap<Long, Publisher> map) { //сортировка издательств Registered address
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry<Long, Publisher>) (o1)).getValue().getRegisteredAddress()).compareTo(((Map.Entry<Long, Publisher>) (o2)).getValue().getRegisteredAddress());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public HashMap<Long, Publisher> sortByBusinessAddress(HashMap<Long, Publisher> map) { //сортировка издательств Business address
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry<Long, Publisher>) (o1)).getValue().getBusinessAddress()).compareTo(((Map.Entry<Long, Publisher>) (o2)).getValue().getBusinessAddress());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
}
