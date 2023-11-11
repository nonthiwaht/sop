package com.example.sop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public boolean borrowBook(String bookId, String userId) {
        BookEntity book = bookRepository.findById(bookId);

        //ต้องการจะเช็คว่าถ้าหนังสือในไอดีนี้ยังไม่ถูกยืม คือในหัวคิดไว้ว่า status=true คือถูกยืมอยุ่ส่วน status=false คือยังว่าง แต่ไม่รู้จะเขียนโลจิกยังไงดี
        if (book != null && !book.isBorrowed()) {
            book.setBorrowed(true);
            book.setUserId(userId);
            bookRepository.save(book);
            return true;
        }

        return false;
    }

    @Transactional
    public void updateBookStatusAndUser(String bookId, String userId) {
        BookEntity book = bookRepository.findById(bookId);

        if (book != null) {
            book.setBorrowed(true);
            book.setUserId(userId);
            bookRepository.save(book);
        }
    }

    @Transactional
    public boolean returnBook(String bookId, String userId) {
        BookEntity book = bookRepository.findById(bookId);

        //ต้องการเช็คว่าหนังสือถูกยืมจริงไหมและถ้าถูกยืมไอดีผู้ใช้ที่เก็บไว้ในดาต้าเบสและไอดีที่เพิ่งแสกนเข้ามาต้องตรงกันด้วย แต่ไม่รู้จะเขียนโลจิกยังไงดี
        if (book != null && book.isBorrowed() && book.getUserId().equals(userId)) {
            //set false เพื่อให้ status=false หมายถึงหนังสือนี้ถูกคืนแล้วและเคลียไอดีผู้ใช้ให้ว่างด้วย
            book.setBorrowed(false);
            book.setUserId(null);
            bookRepository.save(book);
            return true;
        }

        return false;
    }
}
