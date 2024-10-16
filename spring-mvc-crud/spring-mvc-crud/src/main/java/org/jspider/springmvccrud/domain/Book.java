package org.jspider.springmvccrud.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    private int bookId;
    private String bookName;
    private  double bookPrice;
    private String bookCategory;
}
