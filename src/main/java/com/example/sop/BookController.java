package com.example.sop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/borrow/{bookId}/{userId}")
    public ResponseEntity<String> borrowBook(@PathVariable String bookId, @PathVariable String userId) {
        boolean isBorrowed = bookService.borrowBook(bookId, userId);

        if (isBorrowed) {
            return ResponseEntity.status(HttpStatus.OK).body("หนังสือเล่มนี้ถูกยืมไปแล้ว");
        } else {
            bookService.updateBookStatusAndUser(bookId, userId);
            return ResponseEntity.status(HttpStatus.OK).body("ยืมหนังสือเล่มนี้สำเร็จ");
        }
    }

    @GetMapping("/return/{bookId}/{userId}")
    public ResponseEntity<String> returnBook(@PathVariable String bookId, @PathVariable String userId) {
        boolean isReturned = bookService.returnBook(bookId, userId);

        if (isReturned) {
            return ResponseEntity.status(HttpStatus.OK).body("คืนหนังสือเรียบร้อย");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ผู้ยืมไม่ใช่คนเดียวกัน");
        }
    }
}
