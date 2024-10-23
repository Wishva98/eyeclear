package com.eyeclear;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                        float cylinder, Date examinationDate, String optometrist) {
        this.prescID = prescID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.axis = axis;
        this.cylinder = cylinder;
        this.examinationDate = examinationDate;
        this.optometrist = optometrist;
    }

    public String[] getRemarkTypes() {
        return remarkTypes;
    }

    public void setRemarkTypes(String[] remarkTypes) {
        this.remarkTypes = remarkTypes;
    }

    public ArrayList<String> getPostRemarks() {
        return postRemarks;
    }

    public void setPostRemarks(ArrayList<String> postRemarks) {
        this.postRemarks = postRemarks;
    }

    public boolean addPrescription() throws IOException {
        if (firstName.length() < 4 || firstName.length() > 15 || lastName.length() < 4 || lastName.length() > 15) return false;
        if (address.length() < 20) return false;
        if (sphere < -20.00 || sphere > 20.00) return false;
        if (optometrist.length() < 8 || optometrist.length() > 25) return false;
        if (-4.00 > cylinder || cylinder > 4.00) return false;
        if (0 > axis || axis > 180) return false;
        if (isValidDate(examinationDate.toString())) return false;
        writePrescriptionToFile();
        return true;
    }

    public boolean addRemarks(String remark, String type) throws IOException {
        // Condition 1: Remark word count (6 to 20 words)
        String[] words = remark.split(" ");
        if (words.length < 6 || words.length > 20) {
            return false;
        }

        // Condition 2: First letter of the remark should be uppercase
        if (!Character.isUpperCase(remark.charAt(0))) {
            return false;
        }

        // Condition 3: Type should be "Client" or "Optometrist"
        if (!type.equals(remarkTypes[0]) && !type.equals(remarkTypes[1])) {
            return false;
        }

        // Condition 4: No more than 2 remarks
        if (postRemarks.size() >= 2) {
            return false;
        }

        // Add the remark
        postRemarks.add(remark);

        // Append remark to the text file
        writeRemarkToFile(remark, type);

        return true;
    }

    //validate the date using
    private boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Method to write prescription data to a file
    private void writePrescriptionToFile() throws IOException {
        String fileName = "prescriptions.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

        writer.write("Prescription ID: " + prescID + "\n");
        writer.write("First Name: " + firstName + "\n");
        writer.write("Last Name: " + lastName + "\n");
        writer.write("Address: " + address + "\n");
        writer.write("Sphere: " + sphere + "\n");
        writer.write("Cylinder: " + cylinder + "\n");
        writer.write("Axis: " + axis + "\n");
        writer.write("Examination Date: " + sdf.format(examinationDate) + "\n");
        writer.write("Optometrist: " + optometrist + "\n");
        writer.write("-----------------------------\n");

        writer.close();
    }

    // Method to write remarks data to a file
    private void writeRemarkToFile(String remark, String category) throws IOException {
        String fileName = "remarks.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

        writer.write("Prescription ID: " + prescID + "\n");
        writer.write("Category: " + category + "\n");
        writer.write("Remark: " + remark + "\n");
        writer.write("-----------------------------\n");

        writer.close(); // Always close the writer
    }

}
