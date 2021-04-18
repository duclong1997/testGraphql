package com.demo.testGraphql.resolvers.book;

import com.demo.testGraphql.connections.CursorConnection;
import com.demo.testGraphql.models.dtos.BookDto;
import com.demo.testGraphql.services.BookService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.*;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BookQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private BookService bookService;

    @Autowired
    private CursorConnection cursorConnection;

    @PreAuthorize("isAuthenticated()")
    public BookDto bookDetail(Long id) throws Exception {
        return bookService.getDetail(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ANONYMOUS')")
    public List<BookDto> getBooks(Integer page, Integer size) {
        return bookService.getBooks();
    }

    public Connection<BookDto> books(int first, @Nullable String cursor) {
        // list edges => {node, cursor}
        List<Edge<BookDto>> edges = getBooksWithCursor(cursor)
                .stream()
                .map(book -> new DefaultEdge<>(book, cursorConnection.createCursorWith(book.getId())))
                .limit(first)
                .collect(Collectors.toList());

        var firstCursor = cursorConnection.getFirstCursorFrom(edges);
        var lastCursor = cursorConnection.getLastCursorFrom(edges);

        var pageInfo = new DefaultPageInfo(firstCursor, lastCursor, cursor != null, edges.size() >= first);
        return new DefaultConnection<>(edges, pageInfo);
    }

    private List<BookDto> getBooksWithCursor(String cursor) {
        if (cursor == null) {
            return bookService.getBooks();
        }
        return bookService.getBookAfter(cursorConnection.decodeCursorWith(cursor));
    }
}
