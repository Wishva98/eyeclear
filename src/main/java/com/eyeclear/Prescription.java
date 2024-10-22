package com.eyeclear;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Prescription {
    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private Date examinationDate;
    private String optometrist;
    private String[] remarkTypes = {"Client", "Optometrist"};
    private ArrayList<String> postRemarks = new ArrayList<>();

    public Prescription(int prescID, String firstName, String lastName, String address, float sphere, float axis,
                        float cylinder, Date examinationDate, String optometrist, String[] remarkTypes,
                        ArrayList<String> postRemarks) {
        this.prescID = prescID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.axis = axis;
        this.cylinder = cylinder;
        this.examinationDate = examinationDate;
        this.optometrist = optometrist;
        this.remarkTypes = remarkTypes;
        this.postRemarks = postRemarks;
    }

    public boolean addPrescription(){
        if (firstName.length() < 4 || firstName.length() > 15 || lastName.length() < 4 || lastName.length() > 15) return false;
        if (address.length() < 20) return false;
        if (sphere < -20.00 || sphere > 20.00 || cylinder < -4.00 || cylinder > 4.00 || axis < 0 || axis > 180) return false;
        if (-40.00 > cylinder || cylinder > 40.00) return false;
        if (0 <= axis && axis <= 180) return false;
        try (FileWriter writer = new FileWriter("presc.txt", true)) {
            writer.write("Prescription ID: " + prescID + "\n");
            writer.write("First Name: " + firstName + "\n");
            writer.write("Last Name: " + lastName + "\n");
            writer.write("Address: " + address + "\n");
            writer.write("Sphere: " + sphere + ", Cylinder: " + cylinder + ", Axis: " + axis + "\n");
            writer.write("Examination Date: " + examinationDate + "\n");
            writer.write("Optometrist: " + optometrist + "\n");
            writer.write("------------------------------\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean addRemarks(){
        return true;
    }

    private boolean validate_names(String name){
        return (4 <= name.length()) && (name.length() <= 15);
    }

    private boolean isDateValid(Date date){
        date.f
    }
}
