package com.company;

import java.util.*;
import java.util.regex.*;

public class Control {

    private WindowView view1;
    private Storage storage;
    private boolean quit = false;

    public Control(Storage storage, WindowView view1) {
        this.view1 = view1;
        this.storage = storage;
    }

    public boolean getStatus() {
        return quit;
    }

    private HashMap<Long, Book> sortByName(HashMap<Long, Book> map){ //сортировка по имени
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

    private HashMap<Long, Book> sortByAuthors(HashMap<Long, Book> map) { //сортировка по авторам
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

    private HashMap<Long, Book> sortByYear(HashMap<Long, Book> map) { //сортировка по году
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

    private HashMap<Long, Book> sortByPages(HashMap<Long, Book> map) { //сортировка по страницам
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

    private HashMap<Long, CopyOfTheBook> sortByIdBook(HashMap<Long, CopyOfTheBook> map) { //сортировка экземпляров книг по книге
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

    private HashMap<Long, CopyOfTheBook> sortByIssue(HashMap<Long, CopyOfTheBook> map) { //сортировка по информации о выдаче
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

    public void reviewBook() { //просмотр книг
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        view1.fillTableBook(result);
    }

    public void reviewCopyOfTheBook() { //просмотр экземпляров книг
        HashMap<Long, CopyOfTheBook> result = new HashMap<Long, CopyOfTheBook>();
        for (Map.Entry<Long, CopyOfTheBook> entry : storage.getCopyOfTheBookList().entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        view1.fillTableCopyOfTheBook(result);
    }

    public void searchByIdBook(String date) { //поиск книги по идентификатору
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        Pattern p = Pattern.compile(date);
        Matcher m;
        String text;
        for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            text = String.valueOf(entry.getValue().getIdBook());
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        view1.fillTableBook(result);
    }

    public void searchByName(String date) { //поиск книг по названию
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        Pattern p = Pattern.compile(date);
        Matcher m;
        String text;
        for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            text = entry.getValue().getName();
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Long, Book> sortedHashMap = sortByName(result);
        view1.fillTableBook(sortedHashMap);
    }

    public void searchByAuthors(String date) { //поиск книг по авторам
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        Pattern p = Pattern.compile(date);
        Matcher m;
        String text;
        for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            text = entry.getValue().getAuthors();
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Long, Book> sortedHashMap = sortByAuthors(result);
        view1.fillTableBook(sortedHashMap);
    }

    public void searchByYear(String date) { //поиск книг по году
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        Pattern p = Pattern.compile(date);
        Matcher m;
        String text;
        for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            text = String.valueOf(entry.getValue().getYear());
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Long, Book> sortedHashMap = sortByYear(result);
        view1.fillTableBook(sortedHashMap);
    }

    public void searchByPages(String date) { //поиск книг по страницам
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        Pattern p = Pattern.compile(date);
        Matcher m;
        String text;
        for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            text = String.valueOf(entry.getValue().getPages());
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Long, Book> sortedHashMap = sortByPages(result);
        view1.fillTableBook(sortedHashMap);
    }

    public void searchByInventoryNumber(String date) { //поиск экземпляра книги по инвентарному номеру
        HashMap<Long, CopyOfTheBook> result = new HashMap<Long, CopyOfTheBook>();
        Pattern p = Pattern.compile(date);
        Matcher m;
        String text;
        for (Map.Entry<Long, CopyOfTheBook> entry : storage.getCopyOfTheBookList().entrySet()) {
            text = String.valueOf(entry.getValue().getInventoryNumber());
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        view1.fillTableCopyOfTheBook(result);
    }

    public void searchByBook(String date) { //поиск экземпляров книги по книге
        HashMap<Long, CopyOfTheBook> result = new HashMap<Long, CopyOfTheBook>();
        Pattern p = Pattern.compile(date);
        Matcher m;
        String text;
        for (Map.Entry<Long, CopyOfTheBook> entry : storage.getCopyOfTheBookList().entrySet()) {
            text = String.valueOf(entry.getValue().getIdBook());
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Long, CopyOfTheBook> sortedHashMap = sortByIdBook(result);
        view1.fillTableCopyOfTheBook(sortedHashMap);
    }

    public void searchByIssue(String date) { //поиск экземпляров книг по информации о выдаче
        HashMap<Long, CopyOfTheBook> result = new HashMap<Long, CopyOfTheBook>();
        Pattern p = Pattern.compile(date);
        Matcher m;
        String text;
        for (Map.Entry<Long, CopyOfTheBook> entry : storage.getCopyOfTheBookList().entrySet()) {
            text = String.valueOf(entry.getValue().getIssue());
            m = p.matcher(text);
            if (m.find()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Long, CopyOfTheBook> sortedHashMap = sortByIssue(result);
        view1.fillTableCopyOfTheBook(sortedHashMap);
    }

    public void addBook(String date1, String date2, String date3, String date4, String date5) { //добавление в Book
        try {
            long idBook = Long.valueOf(date1);
            if (!storage.getBookList().containsKey(idBook)) {
                int year = Integer.valueOf(date4);
                int pages = Integer.valueOf(date5);
                boolean bool = false;
                for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
                    if (entry.getValue().getAuthors().equals(date2) && entry.getValue().getName().equals(date3) && (entry.getValue().getYear() == year) && (entry.getValue().getPages() == pages)) {
                        /* книга стакими данными уже существует */
                        bool = true;
                    }
                }
                if (!bool) {
                    Book book1 = new Book(idBook, date2, date3, year, pages);
                    storage.getBookList().put(idBook, book1);
                    /* книга успешно добавлена */
                }
            }
        } catch (NumberFormatException E) {
            /* проверьте правильность введённых Вами данных */
        } finally {
            view1.fillTableBook(storage.getBookList());
        }
    }

    public void addCopyOfTheBook(String date1, String date2, String date3) { //добавление в CopyOfTheBook
        try {
            long inventoryNumber = Long.valueOf(date1);
            if (!storage.getCopyOfTheBookList().containsKey(inventoryNumber)) {
                long idBook = Long.valueOf(date2);
                boolean issue = Boolean.valueOf(date3);
                CopyOfTheBook copyOfTheBook1 = new CopyOfTheBook(inventoryNumber, idBook, issue);
                storage.getCopyOfTheBookList().put(inventoryNumber, copyOfTheBook1);
                /* книга успешно добавлена */
                view1.fillTableCopyOfTheBook(storage.getCopyOfTheBookList());
            } else {
                /* книга с таким инвентарным номером уже существует */
                view1.fillTableCopyOfTheBook(storage.getCopyOfTheBookList());
            }
        } catch (NumberFormatException E) {
            /* проверьте правильность введённых Вами данных */
            view1.fillTableCopyOfTheBook(storage.getCopyOfTheBookList());
        }
    }

    public void changeBook(String date1, String date2, String date3, String date4, String date5) { //изменение Book
        try {
            long idBook = Long.valueOf(date1);
            if (storage.getBookList().containsKey(idBook)) {
                int year = Integer.valueOf(date4);
                int pages = Integer.valueOf(date5);
                boolean bool = false;
                for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
                    if (entry.getValue().getAuthors().equals(date2) && entry.getValue().getName().equals(date3) && (entry.getValue().getYear() == year) && (entry.getValue().getPages() == pages)) {
                        /* книга стакими данными уже существует */
                        bool = true;
                    }
                }
                if (!bool) {
                    Book book0 = storage.getBookList().get(idBook);
                    if (!date2.equals("")) {
                        book0.setAuthors(date1);
                    }
                    if (!date3.equals("")) {
                        book0.setName(date3);
                    }
                    if (!date4.equals("")) {
                        book0.setYear(year);
                    }
                    if (!date5.equals("")) {
                        book0.setPages(pages);
                    }
                    /* книга успешно изменена */
                }
            }
        } catch (NumberFormatException E) {
            /* проверьте правильность введённых Вами данных */
        } finally {
            view1.fillTableBook(storage.getBookList());
        }
    }

    public void changeCopyOfTheBook(String date1, String date2, String date3) { //изменение CopyOfTheBook
        try {
            long inventoryNumber = Long.valueOf(date1);
            if (storage.getCopyOfTheBookList().containsKey(inventoryNumber)) {
                CopyOfTheBook copyOfTheBook0 = storage.getCopyOfTheBookList().get(inventoryNumber);
                if (!date2.equals("")) {
                    copyOfTheBook0.setIdBook(Integer.valueOf(date2));
                }
                if (!date3.equals("")) {
                    copyOfTheBook0.setIssue(Boolean.valueOf(date3));
                }
                /* книга успешно изменена */
                view1.fillTableCopyOfTheBook(storage.getCopyOfTheBookList());
            } else {
                /* книга с таким инвентарным номером уже существует */
                view1.fillTableCopyOfTheBook(storage.getCopyOfTheBookList());
            }
        } catch (NumberFormatException E) {
            /* проверьте правильность введённых Вами данных */
            view1.fillTableCopyOfTheBook(storage.getCopyOfTheBookList());
        }
    }

    public void removeBook(String date1) { //удаление из Book
        try {
            long index = Long.valueOf(date1);
            storage.getBookList().remove(index);
            /* книга успешно удалена */
        } catch (NumberFormatException E) {
            /* проверьте правильность введённых Вами данных */
        } finally{
            view1.fillTableBook(storage.getBookList());
        }
    }

    public void removeCopyOfTheBook(String date1) { //удаление из CopyOfTheBook
        try {
            long index = Long.valueOf(date1);
            storage.getCopyOfTheBookList().remove(index);
            /* книга успешно удалена */
        } catch (NumberFormatException E) {
            /* проверьте правильность введённых Вами данных */
        } finally {
            view1.fillTableCopyOfTheBook(storage.getCopyOfTheBookList());
        }
    }

    public void operation(int act, String date1, String date2, String date3, String date4, String date5) {
        switch (act) {
            case 1: //просмотр книг
                reviewBook();
                break;
            case 2: //просмотр экземпляров книг
                reviewCopyOfTheBook();
                break;
            case 3: //добавление книги
                addBook(date1, date2, date3, date4, date5);
                break;
            case 4: //добавление экземпляра книги
                addCopyOfTheBook(date1, date2, date3);
                break;
            case 5: //изменение книги
                changeBook(date1, date2, date3, date4, date5);
                break;
            case 6: //изменение экземпляра книги
                changeCopyOfTheBook(date1, date2, date3);
                break;
            case 7: //удаление книги
                removeBook(date1);
                break;
            case 8: //удаление экземпляра книги
                removeCopyOfTheBook(date1);
                break;
            case 9: //поиск книги по идентификатору
                searchByIdBook(date1);
                break;
            case 10: //поиск книг по названию
                searchByName(date1);
                break;
            case 11: //поиск книг по авторам
                searchByAuthors(date1);
                break;
            case 12: //поиск книг по году
                searchByYear(date1);
                break;
            case 13: //поиск книг по количеству страниц
                searchByPages(date1);
                break;
            case 14: //поиск экземпляров книг по инвентарному номеру
                searchByInventoryNumber(date1);
                break;
            case 15: //поиск экземпляров книг по книге
                searchByBook(date1);
                break;
            case 16: //поиск экземпляров книг по информации о выдаче
                searchByIssue(date1);
                break;
            case 0:
                quit = true;
                break;
            default:
                break;
        }
    }
}
