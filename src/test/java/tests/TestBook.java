package com.company;

import static org.junit.Assert.*;

public class TestBook extends TestCase {

    private Book testBook;

    @test
    public void testGetIdBook() {
        System.out.println("Test getIdBook");
        testBook = new Book(6, "Avtory", "Nazvanie", 1999, 13);
        long expected = 6;
        long actual = testBook.getIdBook();
        assertEquals(expected, actual);
        fail("Fail in getIdBook");
    }

    public void testSetAndGetIdBook() {
        System.out.println("Test setIdBook and getIdBook");
        testBook = new Book(6, "Avtory", "Nazvanie", 1999, 13);
        long expected = 9;
        testBook.setIdBook(9);
        long actual = testBook.getIdBook();
        assertEquals (expected, actual);
        fail("Fail in setIdBook or getIdBook");
    }

    public void testGetAuthors() {
        System.out.println("Test getAuthors");
        testBook = new Book(6, "Avtory", "Nazvanie", 1999, 13);
        String expected = "Avtory";
        String actual = testBook.getAuthors();
        assertEquals(expected, actual);
        fail("Fail in getAuthors");
    }

    public void testSetAndGetAuthors() {
        System.out.println("Test setAuthors and getAuthors");
        testBook = new Book(6, "Avtory", "Nazvanie", 1999, 13);
        String expected = "Avtory knigi";
        testBook.setAuthors("Avtory knigi");
        String actual = testBook.getAuthors();
        assertEquals(expected, actual);
        fail("Fail in setAuthors or getAuthors");
    }

    public void testGetName() {
        System.out.println("Test getName");
        testBook = new Book(6, "Avtory", "Nazvanie", 1999, 13);
        String expected = "Nazvanie";
        String actual = testBook.getName();
        assertEquals(expected, actual);
        fail("Fail in getName");
    }

    public void testSetAndGetName() {
        System.out.println("Test setName and getName");
        testBook = new Book(6, "Avtory", "Nazvanie", 1999, 13);
        String expected = "Nazvanie knigi";
        testBook.setName("Nazvanie knigi");
        String actual = testBook.getName();
        assertEquals(expected, actual);
        fail("Fail in setName or getName");
    }

    public void testGetYear() {
        System.out.println("Test getYear");
        testBook = new Book(6, "Avtory", "Nazvanie", 1999, 13);
        long expected = 1999;
        long actual = testBook.getYear();
        assertEquals(expected, actual);
        fail("Fail in getYear");
    }

    public void testSetAndGetYear() {
        System.out.println("Test setYear and getYear");
        testBook = new Book(6, "Avtory", "Nazvanie", 1999, 13);
        long expected = 2000;
        testBook.setYear(2000);
        long actual = testBook.getYear();
        assertEquals (expected, actual);
        fail("Fail in setYear or getYear");
    }

    public void testPages() {
        System.out.println("Test getPages");
        testBook = new Book(6, "Avtory", "Nazvanie", 1999, 13);
        long expected = 13;
        long actual = testBook.getPages();
        assertEquals(expected, actual);
        fail("Fail in getPages");
    }

    public void testSetAndGetPages() {
        System.out.println("Test setPages and getPages");
        testBook = new Book(6, "Avtory", "Nazvanie", 1999, 13);
        long expected = 15;
        testBook.setPages(15);
        long actual = testBook.getPages();
        assertEquals(expected, actual);
        fail("Fail in setPages or getPages");
    }

    public void testToString() {
        System.out.println("Test toString");
        testBook = new Book(6, "Avtory", "Nazvanie", 1999, 13);
        String expected = "id: 6;   авторы: Avtory;   название: Nazvanie;   год издания: 1999;   страницы: 13.";
        String actual = testBook.toString();
        assertEquals(expected, actual);
        fail("Fail in toString");
    }
}
