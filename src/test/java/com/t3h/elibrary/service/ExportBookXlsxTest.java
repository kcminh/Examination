package com.t3h.elibrary.service;

import com.t3h.elibrary.entity.Book;
import com.t3h.elibrary.repository.BookRepository;
import lombok.Getter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;



@SpringBootTest
public class ExportBookXlsxTest {
    @Mock
    private BookRepository bookRepository;

    private ExportBookXlsx exportBookXlsxNeedTest;

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private Random random;


    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this.getClass());
        random = new Random();
    }

    @Test
    public void itShouldExportSuccess() throws IOException {
        Book book1 = new Book();
        book1.setBookId(random.nextInt());
        book1.setBookCountry(UUID.randomUUID().toString());
        book1.setName(UUID.randomUUID().toString());
        book1.setPublisher(UUID.randomUUID().toString());
        book1.setAuthor(UUID.randomUUID().toString());
        book1.setFaceBook(UUID.randomUUID().toString());
        book1.setCategoryId(random.nextInt());

        Book book2 = new Book();
        book2.setBookId(random.nextInt());
        book2.setBookCountry(UUID.randomUUID().toString());
        book2.setName(UUID.randomUUID().toString());
        book2.setPublisher(UUID.randomUUID().toString());
        book2.setAuthor(UUID.randomUUID().toString());
        book2.setFaceBook(UUID.randomUUID().toString());
        book2.setCategoryId(random.nextInt());

        List<Book> expect = List.of(book1, book2);

        given(bookRepository.findAll())
                .willReturn(expect);

        exportBookXlsxNeedTest = new ExportBookXlsx(expect);


        InMemoryServletOutputStream outputStream = new InMemoryServletOutputStream();
        HttpServletResponse httpServletResponse = new InMemoryHttpServletResponse(outputStream);

        exportBookXlsxNeedTest.export(httpServletResponse);

        assertTrue(!outputStream.getData().isEmpty());

    }
}
@Getter
class InMemoryServletOutputStream extends ServletOutputStream{

    private List<Object> data = new ArrayList<>();

    @Override
    public boolean isReady() {
        return data != null;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }

    @Override
    public void write(int b) throws IOException {
        data.add(b);
    }
}
class InMemoryHttpServletResponse implements HttpServletResponse{

    private ServletOutputStream servletOutputStream;
    public InMemoryHttpServletResponse(ServletOutputStream servletOutputStream){
        this.servletOutputStream = servletOutputStream;

    }
    @Override
    public void addCookie(Cookie cookie) {

    }

    @Override
    public boolean containsHeader(String s) {
        return false;
    }

    @Override
    public String encodeURL(String s) {
        return null;
    }

    @Override
    public String encodeRedirectURL(String s) {
        return null;
    }

    @Override
    public String encodeUrl(String s) {
        return null;
    }

    @Override
    public String encodeRedirectUrl(String s) {
        return null;
    }

    @Override
    public void sendError(int i, String s) throws IOException {

    }

    @Override
    public void sendError(int i) throws IOException {

    }

    @Override
    public void sendRedirect(String s) throws IOException {

    }

    @Override
    public void setDateHeader(String s, long l) {

    }

    @Override
    public void addDateHeader(String s, long l) {

    }

    @Override
    public void setHeader(String s, String s1) {

    }

    @Override
    public void addHeader(String s, String s1) {

    }

    @Override
    public void setIntHeader(String s, int i) {

    }

    @Override
    public void addIntHeader(String s, int i) {

    }

    @Override
    public void setStatus(int i) {

    }

    @Override
    public void setStatus(int i, String s) {

    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public String getHeader(String s) {
        return null;
    }

    @Override
    public Collection<String> getHeaders(String s) {
        return null;
    }

    @Override
    public Collection<String> getHeaderNames() {
        return null;
    }

    @Override
    public void setTrailerFields(Supplier<Map<String, String>> supplier) {
        HttpServletResponse.super.setTrailerFields(supplier);
    }

    @Override
    public Supplier<Map<String, String>> getTrailerFields() {
        return HttpServletResponse.super.getTrailerFields();
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return servletOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return null;
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentLengthLong(long l) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
