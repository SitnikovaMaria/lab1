package com.company;

import java.util.Scanner;

public class View {
    private Control cont1;
    private Scanner in;

    public View(Control cont1) {
        this.cont1 = cont1;
        this.in = new Scanner(System.in);
    }

    public void read() {
        do{
            print("Выберите действие:");
            print("1. Просмотр книг");
            print("2. Просмотр экземпляров книг");
            print("3. Добавить книгу");
            print("4. Добавить экземпляр книги");
            print("5. Изменить книгу");
            print("6. Изменить экземпляр книги");
            print("7. Удалить книгу");
            print("8. Удалить экземпляр книги");
            cont1.operation(in.nextLine());
        }
        while (!cont1.getStatus());
    }

    public String input() {
        return in.nextLine();
    }

    public void print(String str) {
        System.out.println(str);
    }
}
