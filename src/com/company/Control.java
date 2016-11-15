package com.company;

import java.util.Map;

public class Control {

    private View view1;
    private Storage storage;
    private boolean quit = false;

    public Control(Storage storage){
        view1 = new View(this); //ссылка на текущий объект
        //storage = Storage.getInstance();
        this.storage = storage;
        view1.read();
    }

    public boolean getStatus(){
        return quit;
    }

    public void reviewBook(){ //просмотр книг
        view1.print("Книги:");
        for(Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            view1.print(entry.getValue().toString());
        }
    }

    public void reviewCopyOfTheBook(){ //просмотр экземпляров книг
        view1.print("Экземпляры книг:");
        for(Map.Entry<Long, CopyOfTheBook> entry : storage.getCopyOfTheBookList().entrySet()) {
            view1.print(entry.getValue().toString());
        }
    }

    public void searchByIdBook(){ //поиск книги по идентификатору
        view1.print("Поиск книги по идентификатору");
        view1.print("Введите идентификатор:");
        long idBook = Long.valueOf(view1.input());
        int searcher = 0;
        for(Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            if (entry.getValue().getIdBook() == idBook){
                view1.print(entry.getValue().toString());
                searcher++;
            }
            if (searcher == 0){
                view1.print("По Вашему запросу ничего не найдено!");
            }
            searcher = 0;
        }
    }

    public void searchByName(){ //поиск книг по названию
        view1.print("Поиск книги по названию");
        view1.print("Введите название:");
        String name = view1.input();
        int searcher = 0;
        for(Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            if (entry.getValue().getName().toString().equals(name)){
                view1.print(entry.getValue().toString());
                searcher++;
            }
            if (searcher == 0){
                view1.print("По Вашему запросу ничего не найдено!");
            }
            searcher = 0;
        }
    }

    public void searchByAuthors(){ //поиск книг по авторам
        view1.print("Поиск книги по авторам");
        view1.print("Введите авторов: (И. О. Фамилия1, И. О. Фамилия2 и т.д.)");
        String authors = view1.input();
        int searcher = 0;
        for(Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            if (entry.getValue().getAuthors().toString().equals(authors)){
                view1.print(entry.getValue().toString());
                searcher++;
            }
            if (searcher == 0){
                view1.print("По Вашему запросу ничего не найдено!");
            }
            searcher = 0;
        }
    }

    public void searchByYear(){ //поиск книг по году
        view1.print("Поиск книги по году");
        view1.print("Введите год:");
        int year = Integer.valueOf(view1.input()), searcher = 0;
        for(Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            if (entry.getValue().getYear() == year){
                view1.print(entry.getValue().toString());
                searcher++;
            }
            if (searcher == 0){
                view1.print("По Вашему запросу ничего не найдено!");
            }
            searcher = 0;
        }
    }

    public void searchByPages(){ //поиск книг по страницам
        view1.print("Поиск книги по страницам");
        view1.print("Введите количество страниц:");
        int pages = Integer.valueOf(view1.input()), searcher = 0;
        for(Map.Entry<Long, Book> entry : storage.getBookList().entrySet()) {
            if (entry.getValue().getPages() == pages){
                view1.print(entry.getValue().toString());
                searcher++;
            }
            if (searcher == 0){
                view1.print("По Вашему запросу ничего не найдено!");
            }
            searcher = 0;
        }
    }

    public void searchByInventoryNumber(){ //поиск экземпляра книги по инвентарному номеру
        view1.print("Поиск экземпляра книги по инвентарному номеру");
        view1.print("Введите инвентарный номер:");
        long inventoryNumber = Long.valueOf(view1.input());
        int searcher = 0;
        for(Map.Entry<Long, CopyOfTheBook> entry : storage.getCopyOfTheBookList().entrySet()) {
            if (entry.getValue().getInventoryNumber() == inventoryNumber){
                view1.print(entry.getValue().toString());
                searcher++;
            }
            if (searcher == 0){
                view1.print("По Вашему запросу ничего не найдено!");
            }
            searcher = 0;
        }
    }

    public void searchByBook(){ //поиск экземпляров книги по книге
        view1.print("Поиск экземпляров книг по книге");
        view1.print("Введите идентификатор книги:");
        long idBook = Long.valueOf(view1.input());
        int searcher = 0;
        for(Map.Entry<Long, CopyOfTheBook> entry : storage.getCopyOfTheBookList().entrySet()) {
            if (entry.getValue().getIdBook() == idBook){
                view1.print(entry.getValue().toString());
                searcher++;
            }
            if (searcher == 0){
                view1.print("По Вашему запросу ничего не найдено!");
            }
            searcher = 0;
        }
    }

    public void searchByIssue(){ //поиск экземпляров книг по информации о выдаче
        view1.print("Поиск экзмепляров книг по информации о выдаче");
        view1.print("Введите информацию о выдаче: (true - выдана, false - нет)");
        boolean issue = Boolean.valueOf(view1.input());
        int searcher = 0;
        for(Map.Entry<Long, CopyOfTheBook> entry : storage.getCopyOfTheBookList().entrySet()) {
            if (entry.getValue().getIssue() == issue){
                view1.print(entry.getValue().toString());
                searcher++;
            }
            if (searcher == 0){
                view1.print("По Вашему запросу ничего не найдено!");
            }
            searcher = 0;
        }
    }

    public boolean addBook() { //добавление в Book
        view1.print("Добавление книги");
        view1.print("Введите идентификатор книги: ");
        try{
            long idBook = Long.valueOf(view1.input());
            if (!storage.getBookList().containsKey(idBook)){
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
            }
            else {
                view1.print("Книга с таким идентификатором уже существует!");
                return false;
            }
        }
        catch(NumberFormatException E) {
            view1.print("Проверьте правильность введённых Вами данных");
            return false;
        }
    }

    public boolean addCopyOfTheBook() { //добавление в CopyOfTheBook
        view1.print("Добавление экземпляра книги");
        view1.print("Введите инвентарный номер книги: ");
        try{
            long inventoryNumber = Long.valueOf(view1.input());
            if(!storage.getCopyOfTheBookList().containsKey(inventoryNumber)){
                view1.print("Введите идентификатор книги: ");
                long idBook = Long.valueOf(view1.input());
                view1.print("Выдана книга или нет: (true - выдана, false - нет)");
                boolean issue = Boolean.valueOf(view1.input());
                CopyOfTheBook copyOfTheBook1 = new CopyOfTheBook(inventoryNumber, idBook, issue);
                storage.getCopyOfTheBookList().put(inventoryNumber, copyOfTheBook1);
                view1.print("Экземпляр книги успешно добавлен!");
                return true;
            }
            else{
                view1.print("Книга с таким инвентарным номером уже существует!");
                return false;
            }
        }
        catch(NumberFormatException E) {
            view1.print("Проверьте правильность введённых Вами данных");
            return false;
        }
    }

    public boolean changeBook(){ //изменение Book
        view1.print("Изменение книги");
        view1.print("Введите идентификатор книги: ");
        try{
            long idBook = Long.valueOf(view1.input());
            if(storage.getBookList().containsKey(idBook)){
                Book book0 = storage.getBookList().get(idBook);
                view1.print(book0.toString());
                view1.print("Введите авторов книги: ");
                String authors = view1.input();
                if(!authors.equals("")){
                    book0.setAuthors(authors);
                }
                view1.print("Введите название книги: ");
                String name = view1.input();
                if(!name.equals("")){
                    book0.setName(name);
                }
                view1.print("Введите год издания: ");
                String strYear = view1.input();
                if(!strYear.equals("")){
                    book0.setYear(Integer.valueOf(strYear));
                }
                view1.print("Введите количество страниц: ");
                String strPages = view1.input();
                if(!strPages.equals("")){
                    book0.setPages(Integer.valueOf(strPages));
                }
                view1.print("Книга успешно изменена!");
                return true;
            }
            else{
                view1.print("Книга с таким инвентарным номером не существует!");
                return false;
            }
        }
        catch(NumberFormatException E) {
            view1.print("Проверьте правильность введённых Вами данных");
            return false;
        }
    }

    public boolean changeCopyOfTheBook(){ //изменение CopyOfTheBook
        view1.print("Изменение экземпляра книги");
        view1.print("Введите инвентарный номер книги: ");
        try{
            long inventoryNumber = Long.valueOf(view1.input());
            if(storage.getCopyOfTheBookList().containsKey(inventoryNumber)){
                CopyOfTheBook copyOfTheBook0 = storage.getCopyOfTheBookList().get(inventoryNumber);
                view1.print(copyOfTheBook0.toString());
                view1.print("Введите идентификатор книги: ");
                String strIdBook = view1.input();
                if(!strIdBook.equals("")){
                    copyOfTheBook0.setIdBook(Integer.valueOf(strIdBook));
                }
                view1.print("Выдана книга или нет: (true - выдана, false - нет)");
                String strIssue = view1.input();
                if(!strIssue.equals("")){
                    copyOfTheBook0.setIssue(Boolean.valueOf(strIssue));
                }
                view1.print("Книга с №=" + inventoryNumber + " успешно изменена!");
                return true;
            }
            else{
                view1.print("Книга с таким инвентарным номером не существует!");
                return false;
            }
        }
        catch(NumberFormatException E) {
            view1.print("Проверьте правильность введённых Вами данных");
            return false;
        }
    }

    public boolean removeBook(){ //удаление из Book
        view1.print("Удаление книги");
        view1.print("Введите идентификатор книги: ");
        try{
            long index = Long.valueOf(view1.input());
            if(storage.getBookList().remove(index) == null){
                view1.print("Книга с таким идентификатором не существует!");
                return false;
            }
            view1.print("Книга с id=" + index + " успешно удалена!");
            return true;
        }
        catch(NumberFormatException E) {
            view1.print("Проверьте правильность введённых Вами данных");
            return false;
        }
    }

    public boolean removeCopyOfTheBook(){ //удаление из CopyOfTheBook
        view1.print("Удаление экземпляра книги");
        view1.print("Введите инвентарный номер книги: ");
        try{
            long index = Long.valueOf(view1.input());
            if(storage.getCopyOfTheBookList().remove(index) == null){
                view1.print("Книга с таким инвентарным номером не существует!");
                return false;
            }
            view1.print("Книга с №=" + index + " успешно удалена!");
            return true;
        }
        catch(NumberFormatException E) {
            view1.print("Проверьте правильность введённых Вами данных");
            return false;
        }
    }

    public void operation(String action){
        try{
            int act = Integer.valueOf(action);
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
                    searchByIdBook();
                    break;
                case 10: //поиск книг по названию
                    searchByName();
                    break;
                case 11: //поиск книг по авторам
                    searchByAuthors();
                    break;
                case 12: //поиск книг по году
                    searchByYear();
                    break;
                case 13: //поиск книг по количеству страниц
                    searchByPages();
                    break;
                case 14: //поиск экземпляров книг по инвентарному номеру
                    searchByInventoryNumber();
                    break;
                case 15: //поиск экземпляров книг по книге
                    searchByBook();
                    break;
                case 16: //поиск экземпляров книг по информации о выдаче
                    searchByIssue();
                case 0:
                    quit = true;
                    break;
                default:
                    view1.print("Проверьте правильность введённых Вами данных");
                    break;
            }
        }
        catch(NumberFormatException E) {
            view1.print("Проверьте правильность введённых Вами данных");
        }
    }
}
