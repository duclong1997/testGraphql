package com.demo.testGraphql.publisher;

import com.demo.testGraphql.models.dtos.BookDto;
import org.reactivestreams.Publisher;

public interface BookPublisher {

    public void publish(BookDto bookDto);

    public Publisher<BookDto> getBookPublisherForId(Long id);

    public Publisher<BookDto> getBookPublisher();

}
