package com.example.sop;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/BoRe")
public class BorrowReturnController {
    @PostMapping
    public String createBook(){
        return "HTTP POST Handled";
    }
    @GetMapping
    public String getBook(){
        return "HTTP GET Handled";
    }
    @PutMapping
    public String updateBook(){
        return "HTTP PUT Handled";
    }
    @DeleteMapping
    public String deleteBook(){
        return "HTTP DELETE Handled";
    }
}
