package com.company;

import java.util.ArrayList;

public class Control {

   // private static final int ;
    private Storage stor1 = Storage.getInstance(); //поменять название TODO
    private View view1;
    private Storage storage;

    public Control(Storage stor1, View view1){
        view1 = new View(this); //ссылка на текущий объект
        storage = Storage.getInstance();
    }

    public void reviewBook(){ //просмотр книг
        view1.print("Книги:");
        for(Book i: storage.getBookList()) {
            view1.print(i.toString());
        }
    }

    public void reviewCopyOfTheBook(){ //просмотр экземпляров книг
        view1.print("Экземпляры книг:");
        for(CopyOfTheBook i: storage.getCopyOfTheBookList()) {
            view1.print(i.toString());
        }
    }
    /*
    public void addBook(Book b) { //добавление в Book
        view1.print("Добавление книги");
        //считать с консоли index и obj
        bookList.add(index, obj);
        view1.print("Книга успешно добавлена!");
    }

    public void addCopyOfTheBook() { //добавление в CopyOfTheBook
        view1.print("Добавление экземпляра книги");
        //считать с консоли index и obj
        copyOfTheBookList.add(index, obj);
        view1.print("Экземпляр книги успешно добавлен!");
    }

    public void changeBook(){ //изменение Book
        view1.print("Изменение книги");
        //считать с консоли index и obj
        bookList.set(index, obj);
        view1.print("Книга с id=" + index + " успешно изменена!");
    }

    public void changeCopyOfTheBook(){ //изменение CopyOfTheBook
        view1.print("Изменение экземпляра книги");
        //считать с консоли index и obj
        copyOfTheBookList.set(index, obj);
        view1.print("Книга с №=" + index + " успешно изменена!");
    }*/

    public void removeBook(int index){ //удаление из Book
        view1.print("Удаление книги");
        view1.print("Введите идентификатор книги: ");

        try{
            storage.getBookList().remove(index);
        }
        catch (IndexOutOfBoundsException E){
            view1.print("Книги с таким идентификатором не существует!");
            removeBook();
        }
        view1.print("Книга с id=" + index + " успешно удалена!");
    }

    public void removeCopyOfTheBook(int index){ //удаление из CopyOfTheBook
        view1.print("Удаление экземпляра книги");
        view1.print("Введите инвентарный номер книги: ");
        try{
            storage.getCopyOfTheBookList().remove(index);
        }
        catch (IndexOutOfBoundsException E){
            view1.print("Экземпляра книги с таким инвентарным номером не существует!");
            removeCopyOfTheBook();
        }
        view1.print("Книга с №=" + index + " успешно удалена!");
    }

    public void operation(String action, int act){
        try{
            act = Integer.valueOf(action);
            switch (act) {
                case 1: //просмотр книг
                    reviewBook();
                    act = 0;
                    break;
                case 2: //просмотр экземпляров книг
                    reviewCopyOfTheBook();
                    act = 0;
                    break;
                /*case 3: //добавление книги
                    addBook();
                    act = 0;
                    break;
                case 4: //добавление экземпляра книги
                    addCopyOfTheBook();
                    act = 0;
                    break;
                case 5: //изменение книги
                    changeBook();
                    act = 0;
                    break;
                case 6: //изменение экземпляра книги
                    changeCopyOfTheBook();
                    act = 0;
                    break;*/
                case 7: //удаление книги
                    removeBook();
                    act = 0;
                    break;
                case 8: //удаление экземпляра книги
                    removeCopyOfTheBook();
                    act = 0;
                    break;
            }
            view1.print("Выберите действие:");
            view1.print("1. Просмотр книг");
            view1.print("2. Просмотр экземпляров книг");
            view1.print("3. Добавить книгу");
            view1.print("4. Добавить экземпляр книги");
            view1.print("5. Изменить книгу");
            view1.print("6. Изменить экземпляр книги");
            view1.print("7. Удалить книгу");
            view1.print("8. Удалить экземпляр книги");
        }
        catch(NumberFormatException E) {
            view1.print("Проверьте правильность введённых вами данных");
        }
    }
}
