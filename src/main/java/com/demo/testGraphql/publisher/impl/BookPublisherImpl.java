package com.demo.testGraphql.publisher.impl;

import com.demo.testGraphql.models.dtos.BookDto;
import com.demo.testGraphql.publisher.BookPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookPublisherImpl extends EntityPublisherImpl<BookDto> implements BookPublisher {

    @Override
    public void publish(BookDto bookDto) {
        this.sink.next(bookDto);
    }

    @Override
    public Publisher<BookDto> getBookPublisherForId(Long id) {
        return processor.filter(book -> id.equals(book.getId())).map(book -> {
            log.info("publishing book: {}", book);
            return book;
        });
    }

    @Override
    public Publisher<BookDto> getBookPublisher() {
        return processor.map(book -> {
            log.info("publishing book : {}", book);
            return book;
        });
    }

}
