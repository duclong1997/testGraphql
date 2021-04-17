package com.demo.testGraphql.services;

import com.demo.testGraphql.models.dtos.BookDto;
import com.demo.testGraphql.models.dtos.BookIn;

import java.util.List;

public interface BookService {

    BookDto createBook(BookIn in);

    BookDto getDetail(Long id) throws Exception;

    List<BookDto> getBooks();

    BookDto updateBook(Long id, BookIn in) throws Exception;

    List<BookDto> getBookAfter(Long id);
}
