/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.services.impl;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
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
import com.nhom12.repositories.ActivityRepository;
import com.nhom12.repositories.FacultyRepository;
import com.nhom12.repositories.StatRepository;
import com.nhom12.repositories.StudentClassRepository;
import com.nhom12.repositories.UserRepository;
import com.nhom12.services.StatService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private StatRepository statRepo;
    @Autowired
    private FacultyRepository facultyRepo;
    @Autowired
    private ActivityRepository activityRepo;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private StudentClassRepository classRepo;
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<Object[]> scoreRevenueByFaculty() {
        return this.statRepo.scoreRevenueByFaculty();
    }

    @Override
    public List<Object[]> scoreRevenueByClass() {
        return this.statRepo.scoreRevenueByClass();
    }

    @Override
    public void pdfOutput(HttpServletResponse response, int typeId) {
        try {
            List<Object[]> stats;
            if (typeId == 1) {
                stats = this.statRepo.scoreRevenueByFaculty();
            }
            if (typeId == 2) {
                stats = this.statRepo.scoreRevenueByClass();
            }

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

            //faculty
            Paragraph countFacultyTitle = new Paragraph();
            Text facultyTitle = new Text("")
                    .setFont(font)
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setBold();
            Text facultyTitle2 = new Text("")
                    .setFont(font)
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setBold();
            Text count = new Text("")
                    .setFont(font)
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.LEFT);
            Text count2 = new Text("")
                    .setFont(font)
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.LEFT);

            countFacultyTitle.addTabStops(new TabStop(450, TabAlignment.RIGHT));

            float[] columnWidths1 = {1, 1, 1, 4, 1};
            Table tableFaculty = new Table(UnitValue.createPercentArray(columnWidths1))
                    .setWidth(UnitValue.createPercentValue(100))
                    .setTextAlignment(TextAlignment.CENTER);

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
            if (typeId == 1) {
                title.add("BẢNG THỐNG KÊ BÁO CÁO THEO KHOA");
                count.setText(this.facultyRepo.countFaculty().toString());
                facultyTitle.setText("Số lượng khoa: ");
                countFacultyTitle.add(facultyTitle).add(count);
                countFacultyTitle.add(new Tab());
                count2.setText(this.activityRepo.countActivity().toString());
                facultyTitle2.setText("Số lượng hoạt động: ");
                countFacultyTitle.add(facultyTitle2).add(count2);

                List<Object[]> facultyStat = this.statRepo.scoreRevenueByFaculty();

                List<List<String>> dataFaculty = new ArrayList<>();
                dataFaculty.add(Arrays.asList("Khoa", "Tổng số hoạt động", "Số sinh viên tham gia", "Hoạt động tham gia nhiều nhất", "Số sinh viên tham gia"));

                for (Object[] obj : facultyStat) {
                    dataFaculty.add(Arrays.asList(obj[1].toString(), obj[2].toString(), obj[3].toString()));

                    Object[] obj4 = (Object[]) obj[4];
                    dataFaculty.add(Arrays.asList(obj4[1].toString(), obj4[2].toString()));

                }

                for (List<String> rowData : dataFaculty) {
                    for (String cellData : rowData) {
                        tableFaculty.addCell(new Cell().add(new Paragraph(cellData).setFont(font)));
                    }
                }

                document.add(title);
                document.add(countFacultyTitle);
                document.add(tableFaculty);

            }
            //bảng class
            if (typeId == 2) {
                title.add("BẢNG THỐNG KÊ BÁO CÁO THEO LỚP SINH VIÊN");
                countClass.setText(this.classRepo.countClass().toString());
                classTitle.setText("Số lượng lớp: ");
                countClassTitle.add(classTitle).add(countClass);
                countClassTitle.add(new Tab());
                countClass2.setText(this.userRepo.countStudent().toString());
                classTitle2.setText("Tổng số sinh viên: ");
                countClassTitle.add(classTitle2).add(countClass2);

                List<Object[]> classStat = this.statRepo.scoreRevenueByClass();

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
            }
            //--

            document.add(footerLeft);

            // Đóng document
            document.close();

            //footer
            //----------------------------
            // Thiết lập thông tin trả về
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=stat_report.pdf");
            response.setContentLength(baos.size());

            // Ghi dữ liệu PDF vào response
            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                response.getWriter().write("Có lỗi xảy ra: " + e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
