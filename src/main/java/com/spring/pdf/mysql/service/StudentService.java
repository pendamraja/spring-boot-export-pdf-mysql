package com.spring.pdf.mysql.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.spring.pdf.mysql.entity.Student;
import com.spring.pdf.mysql.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student insertData(Student student) {
        return studentRepository.save(student);
    }
    public List<Student> getData() {
        return studentRepository.findAll();
    }
    public Student updateData(Student student) {
        if (studentRepository.existsById(student.getStudent_id())) {
           return studentRepository.save(student);
        }
        return null;
    }
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public ByteArrayInputStream studentPDFReport(List<Student> studentList) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            // Add Content to PDF file ->
            Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD, 22);
            Paragraph para = new Paragraph("Student Information", fontHeader);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            // Add PDF Table Header ->
            Stream.of("Student ID", "Student Name", "Age", "Address", "Email" , "PhoneNo" , "College Name").forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD);
                header.setBackgroundColor(Color.CYAN);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(2);
                header.setPhrase(new Phrase(headerTitle, headFont));
                table.addCell(header);
            });

            for (Student student : studentList) {
                PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(student.getStudent_id())));
                idCell.setPaddingLeft(4);
                idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);

                PdfPCell studentNameCell = new PdfPCell(new Phrase(student.getStudentName()));
                studentNameCell.setPaddingLeft(4);
                studentNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                studentNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(studentNameCell);

                PdfPCell ageCell = new PdfPCell(new Phrase(String.valueOf(student.getAge())));
                ageCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                ageCell.setPaddingRight(4);
                table.addCell(ageCell);

                PdfPCell addressCell = new PdfPCell(new Phrase(String.valueOf(student.getAddress())));
                addressCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                addressCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                addressCell.setPaddingRight(4);
                table.addCell(addressCell);

                PdfPCell emailCell = new PdfPCell(new Phrase(String.valueOf(student.getEmail())));
                emailCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                emailCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                emailCell.setPaddingRight(4);
                table.addCell(emailCell);

                PdfPCell phoneNumCell = new PdfPCell(new Phrase(String.valueOf(student.getPhoneNo())));
                phoneNumCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                phoneNumCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                phoneNumCell.setPaddingRight(4);
                table.addCell(phoneNumCell);

                PdfPCell collegeCell = new PdfPCell(new Phrase(String.valueOf(student.getCollege())));
                collegeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                collegeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                collegeCell.setPaddingRight(4);
                table.addCell(collegeCell);
            }
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());

    }
}
