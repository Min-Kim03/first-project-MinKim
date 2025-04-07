package org.example.pdfparse

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class PDFTextExtractor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 파일 경로 입력 받기
        System.out.println("Enter your file path" + ": ");
        String file_Path = sc.nextLine();

        try(PDDocument document = PDDocument.load(new File(file_Path))){
            PDFTextStripper text_extract = new PDFTextStripper();
            String text = text_extract.getText(document);
            System.out.println("[PDF Content]: ");
            System.out.println(text);
        } catch(IOException e){
            System.out.println("Invalid PDF (unable to read)" + e.getMessage());
        }

    }
}
