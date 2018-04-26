package bookStore;

import bookStore.dto.AuthorDTO;
import bookStore.dto.BookDto;
import bookStore.service.AuthorService;
import bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class BookStoreController {
    @Autowired
    AuthorService authorService;
    @Autowired
    BookService bookService;


    @RequestMapping("/admin")
    String admin(Model model) {
        return "admin";
    }

    @RequestMapping("/admin-book")
    String adminBookPage(Model model) {
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("books", bookService.getAll());
        return "book-form";
    }

    @RequestMapping("/admin-user")
    String adminUserPage(Model model) {
        model.addAttribute("authors", authorService.getAll());
        return "user-form";
    }

    @RequestMapping("user")
    String user(Model model){
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("books", bookService.getAll());
        return "user";
    }
}
