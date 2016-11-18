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

    public void addBook(String date1, String date2, String date3, String date4, String date5) { //добавление в Book
        try {
            long idBook = Long.valueOf(date1);
            if (!storage.getBookList().containsKey(idBook)) {
                int year = Integer.valueOf(date4);
                int pages = Integer.valueOf(date5);
                Book book1 = new Book(idBook, date2, date3, year, pages);
                storage.getBookList().put(idBook, book1);
                /* книга успешно добавлена */
                //вызываем метод из вью для вывода новых значений
            } else {
                /* книга с таким идентификатором уже существует */
                //вызываем метод из вью для вывода старых значений
            }
        } catch (NumberFormatException E) {
            /* проверьте правильность введённых Вами данных */
            //вызываем метод из вью для вывода старых значений
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
                //вызываем метод из вью для вывода новых значений
            } else {
                /* книга с таким инвентарным номером уже существует */
                //вызываем метод из вью для вывода старых значений
            }
        } catch (NumberFormatException E) {
            /* проверьте правильность введённых Вами данных */
            //вызываем метод из вью для вывода старых значений
        }
    }

    public void changeBook(String date1, String date2, String date3, String date4, String date5) { //изменение Book
        try {
            long idBook = Long.valueOf(date1);
            if (storage.getBookList().containsKey(idBook)) {
                Book book0 = storage.getBookList().get(idBook);
                if (!date2.equals("")) {
                    book0.setAuthors(date1);
                }
                if (!date3.equals("")) {
                    book0.setName(date3);
                }
                if (!date4.equals("")) {
                    book0.setYear(Integer.valueOf(date4));
                }
                if (!date5.equals("")) {
                    book0.setPages(Integer.valueOf(date5));
                }
                /* книга успешно изменена */;
                //вызываем метод из вью для вывода новых значений
            } else {
                /* книга с таким идентификатором уже существует */
                //вызываем метод из вью для вывода старых значений
            }
        } catch (NumberFormatException E) {
            /* проверьте правильность введённых Вами данных */
            //вызываем метод из вью для вывода старых значений
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
                //вызываем метод из вью для вывода новых значений
            } else {
                /* книга с таким инвентарным номером уже существует */
                //вызываем метод из вью для вывода старых значений
            }
        } catch (NumberFormatException E) {
            /* проверьте правильность введённых Вами данных */
            //вызываем метод из вью для вывода старых значений
        }
    }

    public void removeBook(String date1) { //удаление из Book
        try {
            long index = Long.valueOf(date1);
            if (storage.getBookList().remove(index) == null) {
                /* книга с таким идентификатором не существует */
                //вызываем метод из вью для вывода старых значений
            }
            /* книга успешно удалена */
            //вызываем метод из вью для вывода новых значений
        } catch (NumberFormatException E) {
            /* проверьте правильность введённых Вами данных */
            //вызываем метод из вью для вывода старых значений
        }
    }

    public void removeCopyOfTheBook(String date1) { //удаление из CopyOfTheBook
        try {
            long index = Long.valueOf(date1);
            if (storage.getCopyOfTheBookList().remove(index) == null) {
                /* книга с таким инвентарным номером не существует */
                //вызываем метод из вью для вывода старых значений
            }
            /* книга успешно удалена */
            //вызываем метод из вью для вывода новых значений
        } catch (NumberFormatException E) {
            /* проверьте правильность введённых Вами данных */
            //вызываем метод из вью для вывода старых значений
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
