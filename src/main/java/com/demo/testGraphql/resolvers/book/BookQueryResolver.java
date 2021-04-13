package com.demo.testGraphql.resolvers.book;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.demo.testGraphql.models.dtos.BookDto;
import com.demo.testGraphql.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class BookQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private BookService bookService;

    public BookDto bookDetail(Long id) throws Exception {
        return bookService.getDetail(id);
    }

    public List<BookDto> getBooks(Integer page, Integer size) {
        return bookService.getBooks();
    }
}
