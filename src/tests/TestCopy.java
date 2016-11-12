package tests;

import com.company.CopyOfTheBook;
import static org.junit.Assert.*;

public class TestCopy  extends TestCase {

    private CopyOfTheBook tmp= new CopyOfTheBook(1, 1, false);

    @Test
    public void testGetNumber() {
        System.out.println("set and get");
        tmp= new CopyOfTheBook(1, 1, false);
        long expresult=1;
        long result = getInventoryNumber();
        assertEquals (expresult, result);
        fail("Fail in getInventoryNumber.");
    }

    @Test
    public void testSetAndGetNumber() {
        System.out.println("set and get");
        long expresult=3;
        tmp.setInventoryNumber(3);
        long result = getInventoryNumber();
        assertEquals (expresult, result);
        fail("Fail in setInventoryNumber or getInventoryNumber");
    }

    @Test
    public void testGetId() {
        System.out.println("set and get");
        tmp= new CopyOfTheBook(1, 1, false);
        long expresult=1;
        long result = getIdBook();
        assertEquals (expresult, result);
        fail("Fail in getIdBook.");
    }

    @Test
    public void testSetAndGetId() {
        System.out.println("set and get");
        long expresult=3;
        tmp.setIdBook(3);
        long result = getIdBook();
        assertEquals (expresult, result);
        fail("Fail in setIdBook or getIdBook");
    }

    @Test
    public void testGetIssue() {
        System.out.println("set and get");
        tmp= new CopyOfTheBook(1, 1, false);
        boolean expresult=false;
        boolean result = getIssue();
        assertEquals (expresult, result);
        fail("Fail in getIssue.");
    }

    @Test
    public void testSetAndGetIssue() {
        System.out.println("set and get");
        boolean expresult=true;
        tmp.setIssue(true);
        boolean result = getIssue();
        assertEquals (expresult, result);
        fail("Fail in setIssue or getIssue");
    }

}

