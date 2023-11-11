package com.example.sop;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "book")
@Data
public class BookEntity implements Serializable {
    @Serial
    //private  static  final long serialVersionID = ;

    @Id
    @Column(unique = true)
    private String bookId;

    private String userId;

}
