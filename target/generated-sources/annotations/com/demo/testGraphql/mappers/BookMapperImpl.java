package com.demo.testGraphql.mappers;

import com.demo.testGraphql.models.dtos.BookDto;
import com.demo.testGraphql.models.entities.Book;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-14T13:47:54+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.7 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book dtoToEntity(BookDto dto) {
        if ( dto == null ) {
            return null;
        }

        Book book = new Book();

        if ( dto.getId() != null ) {
            book.setId( dto.getId() );
        }
        if ( dto.getName() != null ) {
            book.setName( dto.getName() );
        }
        if ( dto.getDescription() != null ) {
            book.setDescription( dto.getDescription() );
        }
        if ( dto.getPrice() != null ) {
            book.setPrice( dto.getPrice() );
        }

        return book;
    }

    @Override
    public BookDto entityToDto(Book entity) {
        if ( entity == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        if ( entity.getId() != null ) {
            bookDto.setId( entity.getId() );
        }
        if ( entity.getName() != null ) {
            bookDto.setName( entity.getName() );
        }
        if ( entity.getDescription() != null ) {
            bookDto.setDescription( entity.getDescription() );
        }
        if ( entity.getPrice() != null ) {
            bookDto.setPrice( entity.getPrice() );
        }

        return bookDto;
    }

    @Override
    public List<Book> dtosToEntities(List<BookDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Book> list = new ArrayList<Book>( dtos.size() );
        for ( BookDto bookDto : dtos ) {
            list.add( dtoToEntity( bookDto ) );
        }

        return list;
    }

    @Override
    public List<BookDto> entitiesToDtos(List<Book> entities) {
        if ( entities == null ) {
            return null;
        }

        List<BookDto> list = new ArrayList<BookDto>( entities.size() );
        for ( Book book : entities ) {
            list.add( entityToDto( book ) );
        }

        return list;
    }
}
