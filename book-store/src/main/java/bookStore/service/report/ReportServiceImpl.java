package bookStore.service.report;

import bookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReportServiceImpl implements ReportService{
    BookRepository bookRepository;

    @Autowired
    public ReportServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void generateReport(String reportType) {
        ReportFactory.getReportType(reportType).generateReport(bookRepository.findByQuantity(0));
    }
}
