package com.eyeclear;

import org.junit.jupiter.api.Test;

import javax.imageio.IIOException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RemarksTest {

    @Test
    void testAddRemark_Valid() throws ParseException, IOException {
        Prescription presc = new Prescription(120004,"John", "tilda", "182 Dallas dr, Dallas VIC Australia",
                -1.5f, 90, -3.0f, new SimpleDateFormat("dd/MM/YY").parse("12/10/24"), "OptometristX");

        assertTrue(presc.addRemarks("This is a valid remark with proper length.","Client"));
    }

    @Test
    void testAddRemark_InvalidRemarkTooShort() throws ParseException, IOException {
        Prescription presc = new Prescription(120004,"John", "tilda", "182 Dallas dr, Dallas VIC Australia",
                -1.5f, 90, -3.0f, new SimpleDateFormat("dd/MM/YY").parse("12/10/24"), "OptometristX");

        assertFalse(presc.addRemarks("Short words", "Optometrist"));
    }

    @Test
    void testAddRemark_ValidClientCategory() throws ParseException, IOException {
        Prescription presc = new Prescription(120004,"John", "tilda", "182 Dallas dr, Dallas VIC Australia",
                -1.5f, 90, -3.0f, new SimpleDateFormat("dd/MM/YY").parse("12/10/24"), "OptometristX");

        assertTrue(presc.addRemarks("This is a valid remark with proper length.","Client"));
    }

    @Test
    void testAddRemark_InvalidTooManyRemarks() throws ParseException, IOException {
        Prescription presc = new Prescription(120004,"John", "tilda", "182 Dallas dr, Dallas VIC Australia",
                -1.5f, 90, -3.0f, new SimpleDateFormat("dd/MM/YY").parse("12/10/24"), "OptometristX");

        // Valid first remarks
        assertTrue(presc.addRemarks("This is a valid first remark with proper length.","Client"));
        // Valid second remarks
        assertTrue(presc.addRemarks("This is a valid second remark with proper length.","Optometrist"));
        // Invalid: prescription cannot have more than 2 remarks
        assertFalse(presc.addRemarks("This is a valid second remark with proper length.","Optometrist"));
    }

    @Test
    void testAddRemark_InvalidRemarkNoCapitalLetter() throws ParseException, IOException {
        Prescription presc = new Prescription(120004,"John", "tilda", "182 Dallas dr, Dallas VIC Australia",
                -1.5f, 90, -3.0f, new SimpleDateFormat("dd/MM/YY").parse("12/10/24"), "OptometristX");

        // Invalid (doesn't start with uppercase)
        assertFalse(presc.addRemarks("this remark starts with a lowercase letter.","Client"));
    }
//
    @Test
    void testAddRemark_ValidOptometristCategory() throws ParseException, IOException {
        Prescription presc = new Prescription(120004,"John", "tilda", "182 Dallas dr, Dallas VIC Australia",
                -1.5f, 90, -3.0f, new SimpleDateFormat("dd/MM/YY").parse("12/10/24"), "OptometristX");
        presc.setRemarkTypes(new String[]{"Optometrist"}); // Valid

        assertTrue(presc.addRemarks("This is a valid first remark with proper length","Optometrist"));
    }
}
