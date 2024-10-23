package com.eyeclear;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PrescriptionTest {

    @Test
    public void testAddPrescriptionValid() throws ParseException, IOException {
        Prescription p = new Prescription(120004,"John", "tilda", "182 Dallas dr, Dallas VIC Australia",
                -1.5f, 90, -3.0f, new SimpleDateFormat("dd/MM/YY").parse("12/10/24"), "OptometristX");

        assertTrue(p.addPrescription());
    }

    @Test
    public void testAddPrescriptionInvalidFirstName() throws ParseException, IOException {
        Prescription p = new Prescription(120005,"Sal", "corner", "182 Dallas dr, Dallas VIC Australia",
                -1.5f, 90, -3.0f, new SimpleDateFormat("dd/MM/YY").parse("12/10/24"), "OptometristX");

        assertFalse(p.addPrescription());
    }

    //Invalid address
    @Test
    public void testAddPrescriptionInvalidAddress() throws ParseException, IOException {
        Prescription p = new Prescription(120090,"Dany", "sphilburge", "dallas dr",
                -1.5f, 90, -3.0f, new SimpleDateFormat("dd/MM/YY").parse("12/10/24"), "OptometristX");

        assertFalse(p.addPrescription());
    }

    //Invalid sphere
    @Test
    public void testAddPrescriptionInvalidSphere() throws ParseException, IOException {
        Prescription p = new Prescription(120049,"Tom", "cruise", "182 Dallas dr, Dallas VIC Australia",
                -21.0f, 90, -3.0f, new SimpleDateFormat("dd/MM/YY").parse("12/10/24"), "OptometristX");

        assertFalse(p.addPrescription());
    }

    //invalid Optometrist
    @Test
    public void testAddPrescriptionInvalidOptometristName() throws ParseException, IOException {
        Prescription p = new Prescription(120259,"brad", "pitt", "182 Dallas dr, Dallas VIC Australia",
                -1.5f, 90, -3.0f, new SimpleDateFormat("dd/MM/YY").parse("12/10/24"), "OptX");

        assertFalse(p.addPrescription());
    }

    //Test invalid date format
    @Test
    void testAddPrescription_InvalidDateFormat() throws ParseException, IOException {
        Prescription presc = new Prescription(120259,"brad", "pitt", "182 Dallas dr, Dallas VIC Australia",
                -1.5f, 90, -3.0f, new SimpleDateFormat("YYYY/MM/dd").parse("2024/10/25"), "OptX");

        assertFalse(presc.addPrescription());
    }



}