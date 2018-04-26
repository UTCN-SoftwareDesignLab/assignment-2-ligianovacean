package bookStore;

import bookStore.dto.AuthorDTO;
import bookStore.dto.BookDto;
import bookStore.dto.SaleDTO;
import bookStore.entity.Book;
import bookStore.service.BookService;
import bookStore.service.SaleService;
import bookStore.service.report.ReportFactory;
import bookStore.service.report.ReportService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookAPIController {
    @Autowired
    BookService bookService;
    @Autowired
    SaleService saleService;
    @Autowired
    ReportService reportService;
    private int saleTotal;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @RequestMapping(value = "/createBooks", method = RequestMethod.POST)
    public String create(@RequestBody @Valid BookDto book) {
        bookService.create(book);
        return "redirect:createBooks?success";
    }

    @RequestMapping(value = "/deleteBooks", method = RequestMethod.POST)
    public String delete(@RequestBody String deleteData) {
        String[] data = deleteData.split(",");
        String title = data[0].substring(10, data[0].length()-1);
        Integer authorId = Integer.parseInt(data[1].substring(14, data[1].length() - 2));
        bookService.delete(title, authorId);
        return "redirect:deleteBooks?success";
    }

    @RequestMapping(value = "/updateBooks", method = RequestMethod.POST)
    public String update(@RequestBody @Valid BookDto book) {
        bookService.update(book);
        return "redirect:updateBooks?success";
    }

    @RequestMapping(value = "/sellBook", method = RequestMethod.POST)
    public String sellBook(@RequestBody SaleDTO saleDTO) {
        Book book = bookService.get(saleDTO.title, saleDTO.authorId);
        saleDTO.initialQuantity = book.getQuantity();
        saleDTO.price = book.getPrice();
        saleTotal = saleService.sell(saleDTO);
        return "redirect:sellBook?success";
    }


    @ResponseBody
    @RequestMapping(value= "/getTotal", method = RequestMethod.GET)
    public int getTotal() {
        return saleTotal;
    }


    @RequestMapping(value = "/generateReport", method = RequestMethod.POST)
    public String  generateReport(@RequestBody String reportType) {
        reportService.generateReport(reportType);
        return "redirect:generateCsvReport?success";
    }


}
