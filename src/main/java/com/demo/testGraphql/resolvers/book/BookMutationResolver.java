package com.demo.testGraphql.resolvers.book;

import com.demo.testGraphql.models.dtos.BookDto;
import com.demo.testGraphql.models.dtos.BookIn;
import com.demo.testGraphql.publisher.BookPublisher;
import com.demo.testGraphql.services.BookService;
import graphql.execution.DataFetcherResult;
import graphql.kickstart.execution.error.GenericGraphQLError;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class BookMutationResolver implements GraphQLMutationResolver {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookPublisher bookPublisher;

    @PreAuthorize("isAuthenticated()")
    public DataFetcherResult<BookDto> createBook(BookIn in) {

        BookDto bookDto = bookService.createBook(in);
        // publish
        bookPublisher.publish(bookDto);

        return DataFetcherResult.<BookDto>newResult()
                // get data
                .data(bookDto)
                // get error
                .error(new GenericGraphQLError("error create fail"))
                .build();
    }

    @PreAuthorize("isAuthenticated()")
    public BookDto updateBook(Long id, BookIn in) throws Exception {
        return bookService.updateBook(id, in);
    }
}
