package com.company;

import java.util.HashMap;
import java.util.Map;

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
        long idBook = Long.valueOf(date);
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            if (entry.getKey() == idBook) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        view1.fillTableBook(result);
    }

    public void searchByName(String date) { //поиск книг по названию
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            if (entry.getValue().getName().equals(date)) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        view1.fillTableBook(result);
    }

    public void searchByAuthors(String date) { //поиск книг по авторам
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            if (entry.getValue().getAuthors().equals(date)) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        view1.fillTableBook(result);
    }

    public void searchByYear(String date) { //поиск книг по году
        int year = Integer.valueOf(date);
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            if (entry.getValue().getYear() == year) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        view1.fillTableBook(result);
    }

    public void searchByPages(String date) { //поиск книг по страницам
        int pages = Integer.valueOf(date);
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            if (entry.getValue().getPages() == pages) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        view1.fillTableBook(result);
    }

    public void searchByInventoryNumber(String date) { //поиск экземпляра книги по инвентарному номеру
        long inventoryNumber = Long.valueOf(date);
        HashMap<Long, CopyOfTheBook> result = new HashMap<Long, CopyOfTheBook>();
        for (Map.Entry<Long, CopyOfTheBook> entry : storage.getCopyOfTheBookList().entrySet()) {
            if (entry.getKey() == inventoryNumber) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        view1.fillTableCopyOfTheBook(result);
    }

    public void searchByBook(String date) { //поиск экземпляров книги по книге
        long idBook = Long.valueOf(date);
        HashMap<Long, CopyOfTheBook> result = new HashMap<Long, CopyOfTheBook>();
        for (Map.Entry<Long, CopyOfTheBook> entry : storage.getCopyOfTheBookList().entrySet()) {
            if (entry.getValue().getIdBook() == idBook) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        view1.fillTableCopyOfTheBook(result);
    }

    public void searchByIssue(String date) { //поиск экземпляров книг по информации о выдаче
        boolean issue = Boolean.valueOf(date);
        HashMap<Long, CopyOfTheBook> result = new HashMap<Long, CopyOfTheBook>();
        for (Map.Entry<Long, CopyOfTheBook> entry : storage.getCopyOfTheBookList().entrySet()) {
            if (entry.getValue().getIssue() == issue) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        view1.fillTableCopyOfTheBook(result);
    }

    public boolean addBook() { //добавление в Book
        try {
            long idBook = Long.valueOf(view1.input());
            if (!storage.getBookList().containsKey(idBook)) {
                view1.print("Введите авторов книги: ");
                String authors = view1.input();
                view1.print("Введите название книги: ");
                String name = view1.input();
                view1.print("Введите год издания: ");
                int year = Integer.valueOf(view1.input());
                view1.print("Введите количество страниц: ");
                int pages = Integer.valueOf(view1.input());
                Book book1 = new Book(idBook, authors, name, year, pages);
                storage.getBookList().put(idBook, book1);
                view1.print("Книга успешно добавлена!");
                return true;
            } else {
                view1.print("Книга с таким идентификатором уже существует!");
                return false;
            }
        } catch (NumberFormatException E) {
            view1.print("Проверьте правильность введённых Вами данных");
            return false;
        }
    }

    public boolean addCopyOfTheBook() { //добавление в CopyOfTheBook
        try {
            long inventoryNumber = Long.valueOf(view1.input());
            if (!storage.getCopyOfTheBookList().containsKey(inventoryNumber)) {
                view1.print("Введите идентификатор книги: ");
                long idBook = Long.valueOf(view1.input());
                view1.print("Выдана книга или нет: (true - выдана, false - нет)");
                boolean issue = Boolean.valueOf(view1.input());
                CopyOfTheBook copyOfTheBook1 = new CopyOfTheBook(inventoryNumber, idBook, issue);
                storage.getCopyOfTheBookList().put(inventoryNumber, copyOfTheBook1);
                view1.print("Экземпляр книги успешно добавлен!");
                return true;
            } else {
                view1.print("Книга с таким инвентарным номером уже существует!");
                return false;
            }
        } catch (NumberFormatException E) {
            view1.print("Проверьте правильность введённых Вами данных");
            return false;
        }
    }

    public boolean changeBook() { //изменение Book
        try {
            long idBook = Long.valueOf(view1.input());
            if (storage.getBookList().containsKey(idBook)) {
                Book book0 = storage.getBookList().get(idBook);
                view1.print(book0.toString());
                view1.print("Введите авторов книги: ");
                String authors = view1.input();
                if (!authors.equals("")) {
                    book0.setAuthors(authors);
                }
                view1.print("Введите название книги: ");
                String name = view1.input();
                if (!name.equals("")) {
                    book0.setName(name);
                }
                view1.print("Введите год издания: ");
                String strYear = view1.input();
                if (!strYear.equals("")) {
                    book0.setYear(Integer.valueOf(strYear));
                }
                view1.print("Введите количество страниц: ");
                String strPages = view1.input();
                if (!strPages.equals("")) {
                    book0.setPages(Integer.valueOf(strPages));
                }
                view1.print("Книга успешно изменена!");
                return true;
            } else {
                view1.print("Книга с таким инвентарным номером не существует!");
                return false;
            }
        } catch (NumberFormatException E) {
            view1.print("Проверьте правильность введённых Вами данных");
            return false;
        }
    }

    public boolean changeCopyOfTheBook() { //изменение CopyOfTheBook
        try {
            long inventoryNumber = Long.valueOf(view1.input());
            if (storage.getCopyOfTheBookList().containsKey(inventoryNumber)) {
                CopyOfTheBook copyOfTheBook0 = storage.getCopyOfTheBookList().get(inventoryNumber);
                view1.print(copyOfTheBook0.toString());
                view1.print("Введите идентификатор книги: ");
                String strIdBook = view1.input();
                if (!strIdBook.equals("")) {
                    copyOfTheBook0.setIdBook(Integer.valueOf(strIdBook));
                }
                view1.print("Выдана книга или нет: (true - выдана, false - нет)");
                String strIssue = view1.input();
                if (!strIssue.equals("")) {
                    copyOfTheBook0.setIssue(Boolean.valueOf(strIssue));
                }
                view1.print("Книга с №=" + inventoryNumber + " успешно изменена!");
                return true;
            } else {
                view1.print("Книга с таким инвентарным номером не существует!");
                return false;
            }
        } catch (NumberFormatException E) {
            view1.print("Проверьте правильность введённых Вами данных");
            return false;
        }
    }

    public boolean removeBook() { //удаление из Book
        try {
            long index = Long.valueOf(view1.input());
            if (storage.getBookList().remove(index) == null) {
                view1.print("Книга с таким идентификатором не существует!");
                return false;
            }
            view1.print("Книга с id=" + index + " успешно удалена!");
            return true;
        } catch (NumberFormatException E) {
            view1.print("Проверьте правильность введённых Вами данных");
            return false;
        }
    }

    public boolean removeCopyOfTheBook() { //удаление из CopyOfTheBook
        try {
            long index = Long.valueOf(view1.input());
            if (storage.getCopyOfTheBookList().remove(index) == null) {
                view1.print("Книга с таким инвентарным номером не существует!");
                return false;
            }
            view1.print("Книга с №=" + index + " успешно удалена!");
            return true;
        } catch (NumberFormatException E) {
            view1.print("Проверьте правильность введённых Вами данных");
            return false;
        }
    }

    public void operation(int act, String date) {
        switch (act) {
            case 1: //просмотр книг
                reviewBook();
                break;
            case 2: //просмотр экземпляров книг
                reviewCopyOfTheBook();
                break;
            case 3: //добавление книги
                addBook();
                break;
            case 4: //добавление экземпляра книги
                addCopyOfTheBook();
                break;
            case 5: //изменение книги
                changeBook();
                break;
            case 6: //изменение экземпляра книги
                changeCopyOfTheBook();
                break;
            case 7: //удаление книги
                removeBook();
                break;
            case 8: //удаление экземпляра книги
                removeCopyOfTheBook();
                break;
            case 9: //поиск книги по идентификатору
                searchByIdBook(date);
                break;
            case 10: //поиск книг по названию
                searchByName(date);
                break;
            case 11: //поиск книг по авторам
                searchByAuthors(date);
                break;
            case 12: //поиск книг по году
                searchByYear(date);
                break;
            case 13: //поиск книг по количеству страниц
                searchByPages(date);
                break;
            case 14: //поиск экземпляров книг по инвентарному номеру
                searchByInventoryNumber(date);
                break;
            case 15: //поиск экземпляров книг по книге
                searchByBook(date);
                break;
            case 16: //поиск экземпляров книг по информации о выдаче
                searchByIssue(date);
                break;
            case 0:
                quit = true;
                break;
            default:
                break;
        }
    }
}