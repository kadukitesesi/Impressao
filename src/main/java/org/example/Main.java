package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.text.PDFTextStripper;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

            String caminho = "C:/Users/User/Downloads/CARLOSEDURADOATUAL.pdf";
        try(PDDocument pdDocument = PDDocument.load(new File(caminho));) {
            if (!pdDocument.isEncrypted()) {
                PDFTextStripper pdfTextStripper = new PDFTextStripper();
                pdfTextStripper.setSortByPosition(true);
                String texto = pdfTextStripper.getText(pdDocument);
                System.out.printf("Texto do pdf %s", texto);

                PrinterJob impressora = PrinterJob.getPrinterJob();
                impressora.setPageable(new PDFPageable(pdDocument));

                if (impressora.printDialog()) {
                    impressora.print();
                    System.out.println("PDF enviado para a impressora.");
                } else {
                    System.out.println("Impressão cancelada.");
                }
            }
        } catch (IOException | PrinterException e) {
           e.printStackTrace();
        }
    }
}