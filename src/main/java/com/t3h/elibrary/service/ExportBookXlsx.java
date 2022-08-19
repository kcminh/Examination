package com.t3h.elibrary.service;

import com.t3h.elibrary.entity.Book;
import com.t3h.elibrary.repository.BookRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ExportBookXlsx {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> listBook(){
        return bookRepository.findAll();
    }
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Book> Books;


    public ExportBookXlsx(List<Book> listBooks){
        this.Books = listBooks;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Books");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Book Id", style);
        createCell(row, 1, "Name", style);
        createCell(row, 2, "Author", style);
        createCell(row, 3, "Publisher", style);
        createCell(row, 4, "Category Id", style);
        createCell(row, 5, "Book Country", style);
        createCell(row, 6, "Facebook", style);
        createCell(row, 7, "Status", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Book book : Books) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, book.getBookId(), style);
            createCell(row, columnCount++, book.getName(), style);
            createCell(row, columnCount++, book.getAuthor(), style);
            createCell(row, columnCount++, book.getPublisher(), style);
            createCell(row, columnCount++, book.getCategoryId(), style);
            createCell(row, columnCount++, book.getBookCountry(), style);
            createCell(row, columnCount++, book.getFaceBook(), style);
            createCell(row, columnCount++, book.getStatus(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
