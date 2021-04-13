package com.demo.testGraphql.resolvers.book;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.demo.testGraphql.models.dtos.BookDto;
import com.demo.testGraphql.models.dtos.BookIn;
import com.demo.testGraphql.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMutationResolver implements GraphQLMutationResolver {
    @Autowired
    private BookService bookService;

    public BookDto createBook(BookIn in) {
        return bookService.createBook(in);
    }

    public BookDto updateBook(Long id, BookIn in) throws Exception {
        return bookService.updateBook(id, in);
    }
}
