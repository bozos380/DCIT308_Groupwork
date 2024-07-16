package com.example.pharmacy;

import com.example.pharmacy.dao.DrugDAO;
import com.example.pharmacy.model.Drug;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ReportGenerator {

    public static void generateTextReport() {
        List<Drug> drugs = DrugDAO.getAllDrugs();

        StringBuilder report = new StringBuilder();
        report.append("Pharmacy Management System - Drug Report\n");
        report.append("=======================================\n");

        for (Drug drug : drugs) {
            report.append("Name: ").append(drug.getName()).append("\n");
            report.append("Manufacturer: ").append(drug.getManufacturer()).append("\n");
            report.append("Price: $").append(drug.getPrice()).append("\n");
            report.append("Quantity: ").append(drug.getQuantity()).append(" units\n");
            report.append("---------------------------------------\n");
        }

        try {
            FileOutputStream fos = new FileOutputStream("DrugReport.txt");
            fos.write(report.toString().getBytes());
            fos.close();
            System.out.println("Text report generated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generatePDFReport() {
        List<Drug> drugs = DrugDAO.getAllDrugs();

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("DrugReport.pdf"));
            document.open();

            document.add(new Paragraph("Pharmacy Management System - Drug Report"));
            document.add(new Paragraph("=======================================\n"));

            for (Drug drug : drugs) {
                document.add(new Paragraph("Name: " + drug.getName()));
                document.add(new Paragraph("Manufacturer: " + drug.getManufacturer()));
                document.add(new Paragraph("Price: $" + drug.getPrice()));
                document.add(new Paragraph("Quantity: " + drug.getQuantity() + " units"));
                document.add(new Paragraph("---------------------------------------\n"));
            }

            document.close();
            System.out.println("PDF report generated successfully!");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        generateTextReport();
        generatePDFReport();
    }
}
