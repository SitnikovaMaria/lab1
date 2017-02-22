package com.company;

import java.util.*;
import java.util.regex.*;

public class Control {

    private WindowView view1;
    private Storage storage;
    private boolean quit = false;
    private Search search;
    private Sort sort;

    public Control(Storage storage, WindowView view1) {
        this.view1 = view1;
        this.storage = storage;
        sort = new Sort();
        search = new Search(storage, view1, sort);
    }

    public boolean getStatus() {
        return quit;
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

    public void addBook(String dateIdBook, String authors, String name, String dateYear, String datePages, String catalog, String publisher) { //добавление в Book
        try {
            long idBook = Long.valueOf(dateIdBook);
            if (!storage.getBookList().containsKey(idBook)) {
                int year = Integer.valueOf(dateYear);
                int pages = Integer.valueOf(datePages);
                boolean bool = false;
                for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
                    if (entry.getValue().getAuthors().equals(name) && entry.getValue().getName().equals(authors) && (entry.getValue().getYear() == year) && (entry.getValue().getPages() == pages) && (entry.getValue().getCatalog() == catalog) && (entry.getValue().getPublisher() == publisher)) {
                        /* книга стакими данными уже существует */
                        bool = true;
                    }
                }
                if (!bool) {
                    Book book1 = new Book(idBook, authors, name, year, pages, catalog, publisher);
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

    public void addCopyOfTheBook(String dateInventoryNumber, String dateIdBook, String dateIssue, String reader) { //добавление в CopyOfTheBook
        try {
            long inventoryNumber = Long.valueOf(dateInventoryNumber);
            if (!storage.getCopyOfTheBookList().containsKey(inventoryNumber)) {
                long idBook = Long.valueOf(dateIdBook);
                boolean issue = Boolean.valueOf(dateIssue);
                CopyOfTheBook copyOfTheBook1 = new CopyOfTheBook(inventoryNumber, idBook, issue, reader);
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

    public void changeBook(String dateIdBook, String authors, String name, String dateYear, String datePages, String catalog, String publisher) { //изменение Book
        try {
            long idBook = Long.valueOf(dateIdBook);
            if (storage.getBookList().containsKey(idBook)) {
                int year = Integer.valueOf(dateYear);
                int pages = Integer.valueOf(datePages);
                boolean bool = false;
                for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
                    if (entry.getValue().getAuthors().equals(authors) && entry.getValue().getName().equals(name) && (entry.getValue().getYear() == year) && (entry.getValue().getPages() == pages) && (entry.getValue().getCatalog() == catalog) && (entry.getValue().getPublisher() == publisher)) {
                        /* книга стакими данными уже существует */
                        bool = true;
                    }
                }
                if (!bool) {
                    Book book0 = storage.getBookList().get(idBook);
                    if (!name.equals("")) {
                        book0.setAuthors(authors);
                    }
                    if (!authors.equals("")) {
                        book0.setName(name);
                    }
                    if (!dateYear.equals("")) {
                        book0.setYear(year);
                    }
                    if (!datePages.equals("")) {
                        book0.setPages(pages);
                    }
                    if (!catalog.equals("")) {
                        book0.setCatalog(catalog);
                    }
                    if (!publisher.equals("")) {
                        book0.setPublisher(publisher);
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

    public void changeCopyOfTheBook(String dateInventoryNumber, String dateIdBook, String dateIssue, String reader) { //изменение CopyOfTheBook
        try {
            long inventoryNumber = Long.valueOf(dateInventoryNumber);
            if (storage.getCopyOfTheBookList().containsKey(inventoryNumber)) {
                CopyOfTheBook copyOfTheBook0 = storage.getCopyOfTheBookList().get(inventoryNumber);
                if (!dateIdBook.equals("")) {
                    copyOfTheBook0.setIdBook(Integer.valueOf(dateIdBook));
                }
                if (!dateIssue.equals("")) {
                    copyOfTheBook0.setIssue(Boolean.valueOf(dateIssue));
                }
                if (!reader.equals("")) {
                    copyOfTheBook0.setReader(reader);
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

    public void removeBook(String dateIdBook) { //удаление из Book
        try {
            long idBook = Long.valueOf(dateIdBook);
            storage.getBookList().remove(idBook);
            /* книга успешно удалена */
        } catch (NumberFormatException E) {
            /* проверьте правильность введённых Вами данных */
        } finally{
            view1.fillTableBook(storage.getBookList());
        }
    }

    public void removeCopyOfTheBook(String dateInventoryNumber) { //удаление из CopyOfTheBook
        try {
            long inventoryNumber = Long.valueOf(dateInventoryNumber);
            storage.getCopyOfTheBookList().remove(inventoryNumber);
            /* книга успешно удалена */
        } catch (NumberFormatException E) {
            /* проверьте правильность введённых Вами данных */
        } finally {
            view1.fillTableCopyOfTheBook(storage.getCopyOfTheBookList());
        }
    }

    public void operation(int act, String date1, String date2, String date3, String date4, String date5, String date6, String date7) {
        switch (act) {
            case 1: //просмотр книг
                reviewBook();
                break;
            case 2: //просмотр экземпляров книг
                reviewCopyOfTheBook();
                break;
            case 3: //добавление книги
                addBook(date1, date2, date3, date4, date5, date6, date7);
                break;
            case 4: //добавление экземпляра книги
                addCopyOfTheBook(date1, date2, date3, date4);
                break;
            case 5: //изменение книги
                changeBook(date1, date2, date3, date4, date5, date6, date7);
                break;
            case 6: //изменение экземпляра книги
                changeCopyOfTheBook(date1, date2, date3, date4);
                break;
            case 7: //удаление книги
                removeBook(date1);
                break;
            case 8: //удаление экземпляра книги
                removeCopyOfTheBook(date1);
                break;
            case 9: //поиск книги по идентификатору
                search.searchByIdBook(date1);
                break;
            case 10: //поиск книг по названию
                search.searchByName(date1);
                break;
            case 11: //поиск книг по авторам
                search.searchByAuthors(date1);
                break;
            case 12: //поиск книг по году
                search.searchByYear(date1);
                break;
            case 13: //поиск книг по количеству страниц
                search.searchByPages(date1);
                break;
            case 14: //поиск экземпляров книг по инвентарному номеру
                search.searchByInventoryNumber(date1);
                break;
            case 15: //поиск экземпляров книг по книге
                search.searchByBook(date1);
                break;
            case 16: //поиск экземпляров книг по информации о выдаче
                search.searchByIssue(date1);
                break;
            case 17: //поиск экземпляров книг по читателю
                search.searchByReader(date1);
                break;
            case 18: //поиск экземпляров книг по каталогу
                search.searchByCatalog(date1);
                break;
            case 19: //поиск экземпляров книг по издательству
                search.searchByPublisher(date1);
                break;
            case 0:
                quit = true;
                break;
            default:
                break;
        }
    }
}
