package bookStore.service;

import bookStore.dto.BookDto;
import bookStore.dto.SaleDTO;
import bookStore.entity.Book;
import bookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl implements SaleService {
    BookRepository bookRepository;

    @Autowired
    public SaleServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public int sell(SaleDTO saleDTO) {
        if (saleDTO.quantity > saleDTO.initialQuantity) {
            System.out.println("Not enough books!");
            return 0;
        }
        Book bookToSell = bookRepository.findByTitleAndAuthorId(saleDTO.title, saleDTO.authorId);
        bookToSell.setQuantity(saleDTO.initialQuantity - saleDTO.quantity);
        bookRepository.save(bookToSell);
        return computeTotal(saleDTO);
    }

    private int computeTotal(SaleDTO saleDTO) {
        return saleDTO.price*saleDTO.quantity;
    }
}
