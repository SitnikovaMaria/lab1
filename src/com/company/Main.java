package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("Добро пожаловатьв библиотеку!");
        System.out.println("Выберите действие:");
        System.out.println("1. Просмотр книг");
        System.out.println("2. Просмотр экземпляров книг");
        System.out.println("3. Добавить книгу");
        System.out.println("4. Добавить экземпляр книги");
        System.out.println("5. Изменить книгу");
        System.out.println("6. Изменить экземпляр книги");
        System.out.println("7. Удалить книгу");
        System.out.println("8. Удалить экземпляр книги");
        Book book1 = new Book(1, "И. С. Тургенев", "Отцы и дети", 1971, 188);
        Book book2 = new Book(2, "Н. В. Гоголь", "Мертвые души", 1972, 416);
        CopyOfTheBook copyOfTheBook1 = new CopyOfTheBook(1, 1, true);
        CopyOfTheBook copyOfTheBook2 = new CopyOfTheBook(2, 1, false);
        CopyOfTheBook copyOfTheBook3 = new CopyOfTheBook(3, 1, false);
        CopyOfTheBook copyOfTheBook4 = new CopyOfTheBook(4, 2, true);
        CopyOfTheBook copyOfTheBook5 = new CopyOfTheBook(5, 2, false);
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(book1);
        bookList.add(book2);
        ArrayList<CopyOfTheBook> copyOfTheBookList = new ArrayList<CopyOfTheBook>();
        copyOfTheBookList.add(copyOfTheBook1);
        copyOfTheBookList.add(copyOfTheBook2);
        copyOfTheBookList.add(copyOfTheBook3);
        copyOfTheBookList.add(copyOfTheBook4);
        copyOfTheBookList.add(copyOfTheBook5);
        Control cont0 = new Control(0,0, ..., bookList, copyOfTheBookList, view1, Scanner);
    }
}
