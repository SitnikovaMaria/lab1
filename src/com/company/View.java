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
        while (true) {
            cont1.operation(in.nextLine());
        }
    }

    public void print(String str) {
        System.out.println(str);
    }
}
