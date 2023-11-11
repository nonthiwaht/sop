package com.example.sop;

public interface BookRepository {
    BookEntity findById(String bookId);
}
