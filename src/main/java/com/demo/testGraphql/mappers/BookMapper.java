package com.demo.testGraphql.mappers;

import com.demo.testGraphql.models.dtos.BookDto;
import com.demo.testGraphql.models.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BookMapper extends EntityMapper<BookDto, Book> {
}
