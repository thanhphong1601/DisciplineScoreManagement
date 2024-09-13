/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.controllers;

import com.cloudinary.http44.api.Response;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TabAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.nhom12.services.StatService;
import com.nhom12.services.StudentClassService;
import com.nhom12.services.UserService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiStatController {

    @Autowired
    private StatService statService;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private StudentClassService classService;
    @Autowired
    private UserService userService;

    @GetMapping(path = "/stats/class/")
    public ResponseEntity<List<Object[]>> statByClass() {
        List<Object[]> stat = this.statService.scoreRevenueByClass();
        return new ResponseEntity<>(stat, HttpStatus.OK);
    }

    @GetMapping(value = "/stats/class/pdf/", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> outpubPdf() throws IOException {
        try {
            List<Object[]> stats;
            stats = this.statService.scoreRevenueByClass();

            // Tạo ByteArrayOutputStream để lưu dữ liệu PDF
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // Khởi tạo PdfWriter và PdfDocument
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            //font
            String fontPath = servletContext.getRealPath("/resources/fonts/times.ttf");
            PdfFont font = PdfFontFactory.createFont(fontPath, PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);

            //title
            Paragraph title = new Paragraph();
            title.setFont(font).setBold().setFontSize(20).setTextAlignment(TextAlignment.CENTER);

            //footer
            Paragraph footer = new Paragraph();
            Paragraph footerLeft = new Paragraph();
            footerLeft.setFont(font).setItalic().setFontSize(10).setTextAlignment(TextAlignment.LEFT);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String currentDate = LocalDate.now().format(formatter);
            footerLeft.add(String.format("Ngày tạo: %s", currentDate));

            //class
            Paragraph countClassTitle = new Paragraph();
            Text classTitle = new Text("")
                    .setFont(font)
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setBold();
            Text classTitle2 = new Text("")
                    .setFont(font)
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setBold();
            Text countClass = new Text("")
                    .setFont(font)
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.LEFT);
            Text countClass2 = new Text("")
                    .setFont(font)
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.LEFT);

            countClassTitle.addTabStops(new TabStop(450, TabAlignment.RIGHT));

            float[] columnWidths2 = {2, 1, 1, 4, 1, 1, 4, 1};
            Table tableClass = new Table(UnitValue.createPercentArray(columnWidths2))
                    .setWidth(UnitValue.createPercentValue(100))
                    .setTextAlignment(TextAlignment.CENTER);

            //-----------------------------------------------------------
            //bảng class
            title.add("BẢNG THỐNG KÊ BÁO CÁO THEO LỚP SINH VIÊN");
            countClass.setText(this.classService.countClass().toString());
            classTitle.setText("Số lượng lớp: ");
            countClassTitle.add(classTitle).add(countClass);
            countClassTitle.add(new Tab());
            countClass2.setText(this.userService.countStudent().toString());
            classTitle2.setText("Tổng số sinh viên: ");
            countClassTitle.add(classTitle2).add(countClass2);

            List<Object[]> classStat = this.statService.scoreRevenueByClass();

            List<List<String>> dataClass = new ArrayList<>();
            dataClass.add(Arrays.asList("Lớp", "Sỉ số", "Số lượt tham gia", "Tích cực nhất", "Số hoạt động tham gia", "Tổng ĐRL", "Hoạt động tham gia nhiều nhất", "Số lượng không tham gia"));

            for (Object[] obj : classStat) {
                dataClass.add(Arrays.asList(obj[1].toString(), obj[2].toString(), obj[3].toString()));

                Object[] obj4 = (Object[]) obj[4];
                dataClass.add(Arrays.asList(obj4[1].toString(), obj4[2].toString()));

                Object[] obj8 = (Object[]) obj[8];
                dataClass.add(Arrays.asList(obj8[2].toString()));

                Object[] obj6 = (Object[]) obj[6];
                dataClass.add(Arrays.asList(obj6[1].toString()));
                dataClass.add(Arrays.asList(obj[7].toString()));

            }

            for (List<String> rowData : dataClass) {
                for (String cellData : rowData) {
                    tableClass.addCell(new Cell().add(new Paragraph(cellData).setFont(font)));
                }
            }

            document.add(title);
            document.add(countClassTitle);
            document.add(tableClass);

            //--
            document.add(footerLeft);

            //footer
            //----------------------------
            
            // Đóng document
            document.close();

            // Thiết lập thông tin trả về
            ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=class_report.pdf");
            headers.setContentType(MediaType.APPLICATION_PDF);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

}
