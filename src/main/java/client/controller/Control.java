package client.controller;

import client.Storage;
import client.model.*;
import client.model.Publisher;
import client.view.WindowView;

import java.util.*;

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

    public void reviewBook() { //�������� ����
        HashMap<Long, Book> result = new HashMap<Long, Book>();
        for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        view1.fillTableBook(result);
    }

    public void reviewCopyOfTheBook() { //�������� ����������� ����
        HashMap<Long, CopyOfTheBook> result = new HashMap<Long, CopyOfTheBook>();
        for (Map.Entry<Long, CopyOfTheBook> entry : storage.getCopyOfTheBookList().entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        view1.fillTableCopyOfTheBook(result);
    }

    public void addBook(String dateIdBook, String authors, String name, String dateYear, String datePages, String catalog, String publisher) { //���������� � Book
        try {
            long idBook = Long.valueOf(dateIdBook);
            if (!storage.getBookList().containsKey(idBook)) {
                int year = Integer.valueOf(dateYear);
                int pages = Integer.valueOf(datePages);
                boolean bool = false;
                for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
                    if (entry.getValue().getAuthors().equals(authors) && entry.getValue().getName().equals(name) && (entry.getValue().getYear() == year) && (entry.getValue().getPages() == pages) && (entry.getValue().getCatalog() == catalog) && (entry.getValue().getPublisher() == publisher)) {
                        /* ����� ������� ������� ��� ���������� */
                        bool = true;
                    }
                }
                if (!bool) {
                    Book book1 = new Book(idBook, authors, name, year, pages, catalog, publisher);
                    storage.getBookList().put(idBook, book1);
                    /* ����� ������� ��������� */
                }
            }
        } catch (NumberFormatException E) {
            /* ��������� ������������ �������� ���� ������ */
        } finally {
            view1.fillTableBook(storage.getBookList());
        }
    }

    public void addCopyOfTheBook(String dateInventoryNumber, String dateIdBook, String dateIssue, String reader) { //���������� � CopyOfTheBook
        try {
            long inventoryNumber = Long.valueOf(dateInventoryNumber);
            if (!storage.getCopyOfTheBookList().containsKey(inventoryNumber)) {
                long idBook = Long.valueOf(dateIdBook);
                boolean issue = Boolean.valueOf(dateIssue);
                CopyOfTheBook copyOfTheBook1 = new CopyOfTheBook(inventoryNumber, idBook, issue, reader);
                storage.getCopyOfTheBookList().put(inventoryNumber, copyOfTheBook1);
                /* ����� ������� ��������� */
                view1.fillTableCopyOfTheBook(storage.getCopyOfTheBookList());
            } else {
                /* ����� � ����� ����������� ������� ��� ���������� */
                view1.fillTableCopyOfTheBook(storage.getCopyOfTheBookList());
            }
        } catch (NumberFormatException E) {
            /* ��������� ������������ �������� ���� ������ */
            view1.fillTableCopyOfTheBook(storage.getCopyOfTheBookList());
        }
    }

    public void changeBook(String dateIdBook, String authors, String name, String dateYear, String datePages, String catalog, String publisher) { //��������� Book
        try {
            long idBook = Long.valueOf(dateIdBook);
            if (storage.getBookList().containsKey(idBook)) {
                int year = Integer.valueOf(dateYear);
                int pages = Integer.valueOf(datePages);
                boolean bool = false;
                for (Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
                    if (entry.getValue().getAuthors().equals(authors) && entry.getValue().getName().equals(name) && (entry.getValue().getYear() == year) && (entry.getValue().getPages() == pages) && entry.getValue().getCatalog().equals(catalog) && entry.getValue().getPublisher().equals(publisher)) {
                        /* ����� ������� ������� ��� ���������� */
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
                    /* ����� ������� �������� */
                }
            }
        } catch (NumberFormatException E) {
            /* ��������� ������������ �������� ���� ������ */
        } finally {
            view1.fillTableBook(storage.getBookList());
        }
    }

    public void changeCopyOfTheBook(String dateInventoryNumber, String dateIdBook, String dateIssue, String reader) { //��������� CopyOfTheBook
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
                /* ����� ������� �������� */
                view1.fillTableCopyOfTheBook(storage.getCopyOfTheBookList());
            } else {
                /* ����� � ����� ����������� ������� ��� ���������� */
                view1.fillTableCopyOfTheBook(storage.getCopyOfTheBookList());
            }
        } catch (NumberFormatException E) {
            /* ��������� ������������ �������� ���� ������ */
            view1.fillTableCopyOfTheBook(storage.getCopyOfTheBookList());
        }
    }

    public void removeBook(String dateIdBook) { //�������� �� Book
        try {
            long idBook = Long.valueOf(dateIdBook);
            storage.getBookList().remove(idBook);
            /* ����� ������� ������� */
        } catch (NumberFormatException E) {
            /* ��������� ������������ �������� ���� ������ */
        } finally{
            view1.fillTableBook(storage.getBookList());
        }
    }

    public void removeCopyOfTheBook(String dateInventoryNumber) { //�������� �� CopyOfTheBook
        try {
            long inventoryNumber = Long.valueOf(dateInventoryNumber);
            storage.getCopyOfTheBookList().remove(inventoryNumber);
            /* ����� ������� ������� */
        } catch (NumberFormatException E) {
            /* ��������� ������������ �������� ���� ������ */
        } finally {
            view1.fillTableCopyOfTheBook(storage.getCopyOfTheBookList());
        }
    }

    public void addPublisher(String dateIdPublisher, String name, String registeredAddress, String businessAddress) { //���������� � Publisher
        try {
            long idPublisher = Long.valueOf(dateIdPublisher);
            if (!storage.getPublisherList().containsKey(idPublisher)) {
                boolean bool = false;
                for (Map.Entry<Long, Publisher> entry : storage.getPublisherList().entrySet()) {
                    if (entry.getValue().getName().equals(name) && entry.getValue().getRegisteredAddress().equals(registeredAddress) && entry.getValue().getBusinessAddress().equals(businessAddress)) {
                        /* ������������ � ������ ������� ��� ���������� */
                        bool = true;
                    }
                }
                if (!bool) {
                    Publisher publisher1 = new Publisher(idPublisher, name, registeredAddress, businessAddress);
                    storage.getPublisherList().put(idPublisher, publisher1);
                    /* ������������ ������� ��������� */
                }
            }
        } catch (NumberFormatException E) {
            /* ��������� ������������ �������� ���� ������ */
        } finally {
            view1.fillTablePublisher(storage.getPublisherList());
        }
    }

    public void changePublisher(String dateIdPublisher, String name, String registeredAddress, String businessAddress) { //��������� Publisher
        try {
            long idPublisher = Long.valueOf(dateIdPublisher);
            if (storage.getPublisherList().containsKey(idPublisher)) {
                boolean bool = false;
                for (Map.Entry<Long, Publisher> entry : storage.getPublisherList().entrySet()) {
                    if (entry.getValue().getName().equals(name) && entry.getValue().getRegisteredAddress().equals(registeredAddress) && entry.getValue().getBusinessAddress().equals(businessAddress)) {
                        /* ������������ � ������ ������� ��� ���������� */
                        bool = true;
                    }
                }
                if (!bool) {
                    Publisher publisher0 = storage.getPublisherList().get(idPublisher);
                    if (!name.equals("")) {
                        publisher0.setName(name);
                    }
                    if (!registeredAddress.equals("")) {
                        publisher0.setRegisteredAddress(registeredAddress);
                    }
                    if (!businessAddress.equals("")) {
                        publisher0.setBusinessAddress(businessAddress);
                    }
                    /* ������������ ������� �������� */
                }
            }
        } catch (NumberFormatException E) {
            /* ��������� ������������ �������� ���� ������ */
        } finally {
            view1.fillTablePublisher(storage.getPublisherList());
        }
    }

    public void removePublisher(String dateIdPublisher) { //�������� �� Publisher
        try {
            long idPublisher = Long.valueOf(dateIdPublisher);
            storage.getPublisherList().remove(idPublisher);
            /* ������������ ������� ������� */
        } catch (NumberFormatException E) {
            /* ��������� ������������ �������� ���� ������ */
        } finally{
            view1.fillTablePublisher(storage.getPublisherList());
        }
    }

    public void addCatalog(String dateIdCatalog, String name, String nameOfParent) { //���������� � Catalog
        try {
            long idCatalog = Long.valueOf(dateIdCatalog);
            if (!storage.getPublisherList().containsKey(idCatalog)) {
                boolean bool = false;
                for (Map.Entry<Long, Catalog> entry : storage.getCatalogList().entrySet()) {
                    if (entry.getValue().getName().equals(name) && entry.getValue().getNameOfParent().equals(nameOfParent)) {
                        /* ������� � ������ ������� ��� ���������� */
                        bool = true;
                    }
                }
                if (!bool) {
                    Catalog catalog1 = new Catalog(idCatalog, name, nameOfParent);
                    storage.getCatalogList().put(idCatalog, catalog1);
                    /* ������� ������� �������� */
                }
            }
        } catch (NumberFormatException E) {
            /* ��������� ������������ �������� ���� ������ */
        } finally {
            /* TODO ����� ������ ���� ����� ��� ������ ��������� */
        }
    }

    public void changeCatalog(String dateIdCatalog, String name, String nameOfParent) { //��������� Catalog
        try {
            long idCatalog = Long.valueOf(dateIdCatalog);
            if (storage.getCatalogList().containsKey(idCatalog)) {
                boolean bool = false;
                for (Map.Entry<Long, Catalog> entry : storage.getCatalogList().entrySet()) {
                    if (entry.getValue().getName().equals(name) && entry.getValue().getNameOfParent().equals(nameOfParent)) {
                        /* ������� � ������ ������� ��� ���������� */
                        bool = true;
                    }
                }
                if (!bool) {
                    Catalog catalog0 = storage.getCatalogList().get(idCatalog);
                    if (!name.equals("")) {
                        catalog0.setName(name);
                    }
                    if (!nameOfParent.equals("")) {
                        catalog0.setNameOfParent(nameOfParent);
                    }
                    /* ������� ������� ������ */
                }
            }
        } catch (NumberFormatException E) {
            /* ��������� ������������ �������� ���� ������ */
        } finally {
            /* TODO ����� ������ ���� ����� ��� ������ ��������� */
        }
    }

    public void removeCatalog(String dateIdCatalog) { //�������� �� Catalog
        try {
            long idCatalog = Long.valueOf(dateIdCatalog);
            storage.getCatalogList().remove(idCatalog);
            /* ������� ������� ����� */
        } catch (NumberFormatException E) {
            /* ��������� ������������ �������� ���� ������ */
        } finally{
            /* TODO ����� ������ ���� ����� ��� ������ ��������� */
        }
    }

    public void operation(int act, String date1, String date2, String date3, String date4, String date5, String date6, String date7) {
        switch (act) {
            case 1: //�������� ����
                reviewBook();
                break;
            case 2: //�������� ����������� ����
                reviewCopyOfTheBook();
                break;
            case 3: //���������� �����
                addBook(date1, date2, date3, date4, date5, date6, date7);
                break;
            case 4: //���������� ���������� �����
                addCopyOfTheBook(date1, date2, date3, date4);
                break;
            case 5: //��������� �����
                changeBook(date1, date2, date3, date4, date5, date6, date7);
                break;
            case 6: //��������� ���������� �����
                changeCopyOfTheBook(date1, date2, date3, date4);
                break;
            case 7: //�������� �����
                removeBook(date1);
                break;
            case 8: //�������� ���������� �����
                removeCopyOfTheBook(date1);
                break;
            case 9: //����� ����� �� ��������������
                search.searchByIdBook(date1);
                break;
            case 10: //����� ���� �� ��������
                search.searchByName(date1);
                break;
            case 11: //����� ���� �� �������
                search.searchByAuthors(date1);
                break;
            case 12: //����� ���� �� ����
                search.searchByYear(date1);
                break;
            case 13: //����� ���� �� ���������� �������
                search.searchByPages(date1);
                break;
            case 14: //����� ����������� ���� �� ������������ ������
                search.searchByInventoryNumber(date1);
                break;
            case 15: //����� ����������� ���� �� �����
                search.searchByBook(date1);
                break;
            case 16: //����� ����������� ���� �� ���������� � ������
                search.searchByIssue(date1);
                break;
            case 17: //����� ����������� ���� �� ��������
                search.searchByReader(date1);
                break;
            case 18: //����� ����������� ���� �� ��������
                search.searchByCatalog(date1);
                break;
            case 19: //����� ����������� ���� �� ������������
                search.searchByPublisher(date1);
                break;
            case 20: //���������� ������������
                addPublisher(date1, date2, date3, date4);
                break;
            case 21: //��������� ������������
                changePublisher(date1, date2, date3, date4);
                break;
            case 22: //�������� ������������
                removePublisher(date1);
                break;
            case 23: //���������� ��������
                addCatalog(date1, date2, date3);
                break;
            case 24: //��������� ��������
                changeCatalog(date1, date2, date3);
                break;
            case 25: //�������� ��������
                removeCatalog(date1);
                break;
            case 26: //����� ������������ �� ID
                search.searchPublisherById(date1);
                break;
            case 27: //����� ������������ �� Name
                search.searchPublisherByName(date1);
                break;
            case 28: //����� ������������ �� Registered address
                search.searchByRegisteredAddress(date1);
                break;
            case 29: //����� ������������ �� Business address
                search.searchByBusinessAddress(date1);
                break;
            case 0:
                quit = true;
                break;
            default:
                break;
        }
    }
}
