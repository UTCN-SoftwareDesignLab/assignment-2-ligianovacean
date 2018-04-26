package bookStore.service;

import bookStore.service.report.CSVReport;
import bookStore.dto.BookDto;
import bookStore.entity.Author;
import bookStore.entity.Book;
import bookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private AuthorService authorService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, @Value("${bookService.maxBooks}") Integer maxBooks) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        System.out.println(maxBooks);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book create(BookDto bookDto) {
        Author author = authorService.findById(bookDto.authorId);
        Book b = new Book(bookDto.genre, bookDto.title, bookDto.price, bookDto.quantity, author);
        return bookRepository.save(b);
    }

    @Override
    public void delete(String title, Integer authorId) {
        List<Book> books = bookRepository.findByTitle(title);
        List<Book> toDeleteBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().getId() == authorId){
                toDeleteBooks.add(book);
            }
        }
        bookRepository.deleteInBatch(toDeleteBooks);
    }

    @Override
    public void update(BookDto book) {
        Book bookToUpdate = bookRepository.findByTitleAndAuthorId(book.title, book.authorId);
        bookToUpdate.setGenre(book.genre);
        bookToUpdate.setPrice(book.price);
        bookToUpdate.setQuantity(book.quantity);
        bookRepository.save(bookToUpdate);
    }

    @Override
    public Book get(String title, Integer authorId) {
        return bookRepository.findByTitleAndAuthorId(title, authorId);
    }

    @Override
    public List<Book> getOutOfStock() {
        return bookRepository.findByQuantity(0);
    }

}
