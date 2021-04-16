package com.demo.testGraphql.services.impl;

import com.demo.testGraphql.mappers.BookMapper;
import com.demo.testGraphql.models.dtos.BookDto;
import com.demo.testGraphql.models.dtos.BookIn;
import com.demo.testGraphql.models.entities.Book;
import com.demo.testGraphql.repositories.BookRepository;
import com.demo.testGraphql.services.BookService;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private Clock clock;

    @Override
    @Transactional
    public BookDto createBook(BookIn in) {
        Book book = new Book();
        book.setName(in.getName());
        book.setDescription(in.getDescription());
        book.setPrice(in.getPrice());

        book = bookRepository.save(book);
        return bookMapper.entityToDto(book);
    }

    @Override
    @Transactional
    public BookDto getDetail(Long id) throws Exception {
        Optional<Book> bookOp = bookRepository.findById(id);
        if (bookOp.isPresent()) {
            Book book = bookOp.get();
            BookDto bookDto = bookMapper.entityToDto(book);
            bookDto.setCreateAt(ZonedDateTime.now(clock));
            bookDto.setCreateOn(LocalDate.now(clock));
            return bookMapper.entityToDto(book);
        }
        throw new GraphQLException("book not exist");
    }

    @Override
    @Transactional
    public List<BookDto> getBooks() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.entitiesToDtos(books);
    }

    @Override
    @Transactional
    public BookDto updateBook(Long id, BookIn in) throws Exception {
        Optional<Book> bookOp = bookRepository.findById(id);
        if (bookOp.isPresent()) {
            Book book = bookOp.get();
            book.setPrice(in.getPrice());
            book.setName(in.getName());
            book.setDescription(in.getDescription());

            book = bookRepository.save(book);
            return bookMapper.entityToDto(book);
        }
        throw new GraphQLException("Book not exist");
    }
}
