package com.demo.testGraphql.resolvers.book;

import com.demo.testGraphql.models.dtos.BookDto;
import com.demo.testGraphql.publisher.BookPublisher;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSubscriptionResolver implements GraphQLSubscriptionResolver {

    @Autowired
    private BookPublisher bookPublisher;

    @PreAuthorize("isAuthenticated()")
    public Publisher<BookDto> books() {
        return bookPublisher.getBookPublisher();
    }

    @PreAuthorize("isAuthenticated()")
    public Publisher<BookDto> bookDetail(Long id) {
        return bookPublisher.getBookPublisherForId(id);
    }

}
