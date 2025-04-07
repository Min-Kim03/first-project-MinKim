package org.example.pdfparser;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class PDFTextExtractor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 파일 경로 입력 받기
        System.out.println("Enter your file path: ");
        String file_Path = sc.nextLine();

        try(PDDocument document = PDDocument.load(new File(file_Path))){
            System.out.println("Enter 0 to print full PDF or enter specific page number you want to print: ");
            String user_input = sc.nextLine();

            if(user_input.equals("0")){
                printAllPage(document);
            } else{
                try{
                    int page_Num = Integer.parseInt(user_input);
                    printSpecificPage(document, page_Num);
                }catch (NumberFormatException e){
                    System.out.println("Please enter valid page number");
                }
            }
        } catch(IOException e){
            System.out.println("Invalid PDF (unable to read)" + e.getMessage());
        }

    }

    public static void printAllPage(PDDocument document) throws IOException{ // 전체 텍스트 추출 메서드
        PDFTextStripper text_extract = new PDFTextStripper();
        String text = text_extract.getText(document);
        System.out.println("[PDF Content]: ");
        System.out.println(text);
    }

    public static void printSpecificPage(PDDocument document, int pageNumber) throws IOException {
        // 부분 출력 메서드
        PDFTextStripper partial_Text = new PDFTextStripper();
        partial_Text.setStartPage(pageNumber); // 특정 페이지 시작점과 마침점을 똑같이 세팅.
        partial_Text.setEndPage(pageNumber);

        String text = partial_Text.getText(document);
        System.out.println("[Text on page: " + pageNumber + " ]");
        System.out.println(text);
    }
}
