package com.demo.testGraphql.resolvers.book;

import com.demo.testGraphql.models.dtos.BookDto;
import com.demo.testGraphql.services.BookService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookResolver implements GraphQLResolver<BookDto> {

    private BookService bookService;

    public String description(BookDto bookDto) {
        return bookDto.getDescription() + " resolver";
    }

}
