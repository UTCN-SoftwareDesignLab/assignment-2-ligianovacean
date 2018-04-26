package bookStore.service;

import bookStore.dto.BookDto;
import bookStore.entity.Book;

import javax.validation.Valid;
import java.util.List;

public interface BookService {
    List<Book> getAll();
    Book create(BookDto book);
    void delete(String title, Integer authorId);
    void update(BookDto book);
    Book get(String title, Integer authorId);
    List<Book> getOutOfStock();
}
