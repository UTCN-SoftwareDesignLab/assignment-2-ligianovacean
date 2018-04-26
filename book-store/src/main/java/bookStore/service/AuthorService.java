package bookStore.service;

import bookStore.dto.AuthorDTO;
import bookStore.entity.Author;

import java.util.List;


public interface AuthorService {
    List<Author> getAll();
    Author findById(int authorId);
    Author create(AuthorDTO authorDTO);
}
